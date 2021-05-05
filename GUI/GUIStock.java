package GUI;

import java.awt.*;
import javax.swing.*;

import Objects.Stock;

/**
 * GUIStockManager creates the GUI that allows managers to see a list of all stocks and their information.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIStock {
    JPanel panel;

    public GUIStock(Stock stock, JLabel errorLabel) {
        panel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

        JLabel stockNameLabel = new JLabel(stock.getName());
        stockNameLabel.setBounds(100, 20, 80, 25);
        panel.add(stockNameLabel);

        JLabel stockTickerLabel = new JLabel(stock.getSymbol());
        stockTickerLabel.setBounds(180, 20, 80, 25);
        panel.add(stockTickerLabel);

        JLabel stockPriceLabel = new JLabel(String.valueOf(stock.getPrice()));
        stockPriceLabel.setBounds(260, 20, 80, 25);
        panel.add(stockPriceLabel);

        JButton changeStockPriceBtn = new JButton("Change");
        changeStockPriceBtn.setBounds(350, 20, 80, 25);

        JTextField stockPriceTxt = new JTextField();
        stockPriceTxt.setText(String.valueOf(stock.getPrice()));
        stockPriceTxt.setBounds(260, 20, 80, 25);
        stockPriceTxt.setVisible(false);
        panel.add(stockPriceTxt);

        JButton stockPriceSubmitBtn = new JButton("Submit");
        stockPriceSubmitBtn.setBounds(400, 20, 80, 25);
        stockPriceSubmitBtn.setVisible(false);

        changeStockPriceBtn.addActionListener(e -> {
            stockPriceLabel.setVisible(false);
            stockPriceTxt.setVisible(true);

            changeStockPriceBtn.setVisible(false);
            stockPriceSubmitBtn.setVisible(true);
            errorLabel.setVisible(false);
        });

        stockPriceSubmitBtn.addActionListener(e -> {
            String userInput = stockPriceTxt.getText();
            if (!userInput.equals("")) {
                try {
                    double newStockPrice = Double.parseDouble(stockPriceTxt.getText());
                    if (newStockPrice > 0) {
                        stock.setPrice(newStockPrice);
                        stockPriceLabel.setText(stockPriceTxt.getText());
                        errorLabel.setVisible(false);
                    } else {
                        errorLabel.setText("Please input a new stock price greater than 0");
                        errorLabel.setVisible(true);
                    }
                } catch (Exception except) {
                    errorLabel.setText("Please input a valid price");
                    errorLabel.setVisible(true);
                }
            }

            stockPriceLabel.setVisible(true);
            stockPriceTxt.setVisible(false);
            stockPriceTxt.setText(stockPriceTxt.getText());
            changeStockPriceBtn.setVisible(true);
            stockPriceSubmitBtn.setVisible(false);
        });

        panel.add(changeStockPriceBtn);
        panel.add(stockPriceSubmitBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}