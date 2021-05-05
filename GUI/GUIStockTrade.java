package GUI;

import Objects.*;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * GUIStockTrade creates the GUI that allows users to trade stocks.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIStockTrade extends JPanel {
    JPanel panel;

    JButton buyBtn, sellBtn;
    JTextField requestAmountText;
    JLabel errorLabel;
    JComboBox<String> stockCB, accountCB;

    HashMap<String, Stock> stockMap;
    HashMap<String, Account> accMap;

    Person person;

    public GUIStockTrade(Person person) {
        this.person = person;

        panel = new JPanel();
        panel.setLayout(null);

        errorLabel = new JLabel();
        errorLabel.setBounds(250, 400, 150, 25);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        JLabel accountLabel = new JLabel("Please pick an account to trade with: ");
        accountLabel.setBounds(30, 150, 250, 25);
        panel.add(accountLabel);

        accMap = AccountManager.getAccMap(person, true);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(280, 150, 200, 25);
        panel.add(accountCB);

        JLabel stockLabel = new JLabel("Please pick a stock: ");
        stockLabel.setBounds(50, 200, 150, 25);
        panel.add(stockLabel);

        stockMap = AccountManager.getStockMap();
        stockCB = new JComboBox<>(AccountManager.getStockKeys(stockMap));
        stockCB.setBounds(180, 200, 300, 25);
        panel.add(stockCB);

        JLabel amountLabel = new JLabel("Enter the quantity: ");
        amountLabel.setBounds(100, 240, 200, 25);
        panel.add(amountLabel);

        requestAmountText = new JTextField(20);
        requestAmountText.setBounds(300, 240, 165, 25);
        panel.add(requestAmountText);

        buyBtn = new JButton("BUY");
        buyBtn.setBounds(180, 310, 80, 40);

        sellBtn = new JButton("SELL");
        sellBtn.setBounds(270,310,80, 40);

        buyBtn.addActionListener(e -> {
            try {
                Security securityAccount = (Security) accMap.get(accountCB.getSelectedItem());
                int requestAmount = Integer.parseInt(requestAmountText.getText());

                Stock targetStock = stockMap.get(stockCB.getSelectedItem());
                securityAccount.updatePosition(targetStock, true, requestAmount);

                errorLabel.setText("Purchase complete.");
                errorLabel.setVisible(true);

                update();
            } catch (Exception exception) {
                errorLabel.setText("Unable to complete purchase.");
                errorLabel.setVisible(true);
            }
        });

        sellBtn.addActionListener(e -> {
            try {
                Security securityAccount = (Security) accMap.get(accountCB.getSelectedItem());
                int requestAmount = Integer.parseInt(requestAmountText.getText());

                Stock targetStock = stockMap.get(stockCB.getSelectedItem());
                securityAccount.updatePosition(targetStock, false, requestAmount);

                errorLabel.setText("Sell complete");
                errorLabel.setVisible(true);

                update();
            } catch (Exception exception) {
                errorLabel.setText("Unable to complete sale.");
                errorLabel.setVisible(true);
            }
        });

        sellBtn.setVisible(true);
        buyBtn.setVisible(true);

        panel.add(buyBtn);
        panel.add(sellBtn);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void update() {
        accountCB.removeAllItems();
        accMap = AccountManager.getAccMap(person, false);
        for (String a : AccountManager.getAccKeys(accMap)) {
            accountCB.addItem(a);
        }
    }
}
