package GUI;

import Objects.*;
import Types.CurrencyType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    JSplitPane sp;

    JButton buyBtn, sellBtn;
    JTextField stockNameText;
    JTextField stockSymbolText;
    JTextField requestAmountText;
    JLabel errorLabel;

    public GUIStockTrade(Person person) {



        panel = new JPanel();

        errorLabel = new JLabel();
        errorLabel.setBounds(250, 400, 150, 25);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);


        JLabel stockNameLabel = new JLabel("Enter The stock name: ");
        stockNameLabel.setBounds(100, 200, 200, 25);
        panel.add(stockNameLabel);

        stockNameText = new JTextField(20);
        stockNameText.setBounds(300, 200, 165, 25);
        panel.add(stockNameText);

        JLabel symbolLabel = new JLabel("Enter The stock symbol: ");
        symbolLabel.setBounds(100, 230, 200, 25);
        panel.add(symbolLabel);

        JLabel amountLabel = new JLabel("Enter the amount: ");
        amountLabel.setBounds(100, 260, 200, 25);
        panel.add(amountLabel);

        stockSymbolText = new JTextField(20);
        stockSymbolText.setBounds(300, 230, 165, 25);
        panel.add(stockSymbolText);

        requestAmountText = new JTextField(20);
        requestAmountText.setBounds(300, 260, 165, 25);
        panel.add(requestAmountText);

        panel.setLayout(null);
        buyBtn = new JButton("BUY");
        buyBtn.setBounds(180, 310, 80, 40);

        sellBtn = new JButton("SELL");
        sellBtn.setBounds(270,310,80, 40);

        buyBtn.addActionListener(e -> {
            try {
                Security securityAccount = person.getSecurityAccount();
                StockList stockList = new StockList();
                String stockName = stockNameText.getText();
                String stockSymbol = stockSymbolText.getText();
                int requestAmount = Integer.parseInt(requestAmountText.getText());

                //System.out.println(stockName);
                Stock targetStock = stockList.getStockByName(stockName);
                securityAccount.updatePosition(targetStock, true, requestAmount);
                errorLabel.setText("Purchase Complete");
                errorLabel.setVisible(true);
            } catch (Exception exception) {

            }
        });

        sellBtn.addActionListener(e -> {
            try {
                Security securityAccount = person.getSecurityAccount();
                StockList stockList = new StockList();
                String stockName = stockNameText.getText();
                String stockSymbol = stockSymbolText.getText();
                int requestAmount = Integer.parseInt(requestAmountText.getText());

//                if(!stockList.checkHasStock(stockName, stockSymbol)){
//                    errorLabel.setText("Cannot find corresponding stock");
//                    errorLabel.setVisible(true);
//                }

                Stock targetStock = stockList.getStockByName(stockName);
//                System.out.println(targetStock.getName());
                securityAccount.updatePosition(targetStock, false, requestAmount);
                errorLabel.setText("Sell Complete");
                errorLabel.setVisible(true);
            } catch (Exception exception) {

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


}
