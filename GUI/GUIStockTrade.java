package GUI;

import Objects.*;
import Util.ComboBoxGenerator;

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
    JLabel errorLabel, confirmationLabel;
    JComboBox<String> stockCB, aCB;
    HashMap<String, Stock> stockMap;
    HashMap<String, Account> accMap;

    GUIInvestmentHome home;
    Person person;

    public GUIStockTrade(Person person, GUIInvestmentHome home) {
        this.home = home;
        this.person = person;

        panel = new JPanel();
        panel.setLayout(null);

        errorLabel = new JLabel();
        errorLabel.setBounds(100, 400, 500, 25);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        confirmationLabel = new JLabel();
        confirmationLabel.setBounds(100, 400, 500, 25);
        confirmationLabel.setForeground(Color.GREEN);
        confirmationLabel.setVisible(false);
        panel.add(confirmationLabel);

        JLabel accountLabel = new JLabel("Please pick an account to trade with: ");
        accountLabel.setBounds(30, 150, 250, 25);
        panel.add(accountLabel);

        accMap = ComboBoxGenerator.getAccMap(person, true);
        aCB = new JComboBox<>(ComboBoxGenerator.getAccKeys(accMap));
        aCB.setBounds(280, 150, 200, 25);
        panel.add(aCB);

        JLabel stockLabel = new JLabel("Please pick a stock: ");
        stockLabel.setBounds(50, 200, 150, 25);
        panel.add(stockLabel);

        stockMap = ComboBoxGenerator.getStockMap();
        stockCB = new JComboBox<>(ComboBoxGenerator.getStockKeys(stockMap));
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
        sellBtn.setBounds(270, 310, 80, 40);

        buyBtn.addActionListener(e -> {
            try {
                Security securityAccount = (Security) accMap.get(aCB.getSelectedItem());
                int requestAmount = Integer.parseInt(requestAmountText.getText());
                Stock targetStock = stockMap.get(stockCB.getSelectedItem());

                String updatePositionError = securityAccount.updatePosition(targetStock, true, requestAmount);
                if (updatePositionError == null) {
                    confirmationLabel.setText("Purchase complete.");
                    confirmationLabel.setVisible(true);
                    errorLabel.setVisible(false);

                    updateAccounts();
                } else {
                    errorLabel.setText(updatePositionError);
                    errorLabel.setVisible(true);
                    confirmationLabel.setVisible(false);
                }
            } catch (Exception exception) {
                errorLabel.setText("Please enter a valid stock quantity.");
                errorLabel.setVisible(true);
                confirmationLabel.setVisible(false);
            }
        });

        sellBtn.addActionListener(e -> {
            try {
                Security securityAccount = (Security) accMap.get(aCB.getSelectedItem());
                int requestAmount = Integer.parseInt(requestAmountText.getText());
                Stock targetStock = stockMap.get(stockCB.getSelectedItem());

                String updatePositionError = securityAccount.updatePosition(targetStock, false, requestAmount);
                if (updatePositionError == null) {
                    confirmationLabel.setText("Sale complete.");
                    confirmationLabel.setVisible(true);
                    errorLabel.setVisible(false);

                    updateAccounts();
                } else {
                    errorLabel.setText(updatePositionError);
                    errorLabel.setVisible(true);
                    confirmationLabel.setVisible(false);
                }
            } catch (Exception exception) {
                errorLabel.setText("Please enter a valid stock quantity.");
                errorLabel.setVisible(true);
                confirmationLabel.setVisible(false);
            }
        });

        panel.add(buyBtn);
        panel.add(sellBtn);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateAccounts() {
        aCB.removeAllItems();
        accMap = ComboBoxGenerator.getAccMap(person, true);
        for (String g : ComboBoxGenerator.getAccKeys(accMap)) {
            aCB.addItem(g);
        }

        home.updatePositions();
        home.updateHistory();
    }
}