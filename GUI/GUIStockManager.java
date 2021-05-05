package GUI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import Objects.Account;
import Objects.Saving;
import Objects.Stock;
import Objects.User;
import Util.DataManager;

/**
 * GUIStock creates the GUI that allows managers to see a single stock's information.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIStockManager {
    JPanel panel, topPanel;
    JButton addNewStockBtn, submitNewStockBtn;
    JScrollPane bottomPanel = new JScrollPane();
    JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    GUIHome home;

public GUIStockManager(GUIHome home){
    this.home = home;

    panel = new JPanel();
    panel.setLayout(new BorderLayout());

    sp.setResizeWeight(0.3);

    bottomPanel = new JScrollPane();
    topPanel = new JPanel();
    topPanel.setLayout(null);

    addNewStockBtn = new JButton("Add New");
    addNewStockBtn.setBounds(5, 10, 95, 40);
    topPanel.add(addNewStockBtn);

    submitNewStockBtn = new JButton("Submit");
    submitNewStockBtn.setBounds(5, 10, 95, 40);
    topPanel.add(submitNewStockBtn);

    JLabel stockNameLabel = new JLabel("Name: ");
    stockNameLabel.setBounds(120, 25, 50, 25);
    stockNameLabel.setVisible(false);
    topPanel.add(stockNameLabel);

    JTextField stockNameTxt = new JTextField(20);
    stockNameTxt.setBounds(175, 25, 80, 25);
    stockNameTxt.setVisible(false);
    topPanel.add(stockNameTxt);

    JLabel stockTickerLabel = new JLabel("Ticker: ");
    stockTickerLabel.setBounds(260, 25, 50, 25);
    stockTickerLabel.setVisible(false);
    topPanel.add(stockTickerLabel);

    JTextField stockTickerTxt = new JTextField(20);
    stockTickerTxt.setBounds(315, 25, 50, 25);
    stockTickerTxt.setVisible(false);
    topPanel.add(stockTickerTxt);

    JLabel stockPriceLabel = new JLabel("Price: ");
    stockPriceLabel.setBounds(370, 25, 50, 25);
    stockPriceLabel.setVisible(false);
    topPanel.add(stockPriceLabel);

    JTextField stockPriceTxt = new JTextField(20);
    stockPriceTxt.setBounds(425, 25, 50, 25);
    stockPriceTxt.setVisible(false);
    topPanel.add(stockPriceTxt);

    JLabel errorLabel = new JLabel();
    errorLabel.setBounds(30, 50, 400, 40);
    errorLabel.setForeground(Color.RED);
    errorLabel.setVisible(false);
    topPanel.add(errorLabel);

    addNewStockBtn.addActionListener(e -> {
        addNewStockBtn.setVisible(false);
        submitNewStockBtn.setVisible(true);
        stockNameLabel.setVisible(true);
        stockNameTxt.setVisible(true);
        stockTickerLabel.setVisible(true);
        stockTickerTxt.setVisible(true);
        stockPriceLabel.setVisible(true);
        stockPriceTxt.setVisible(true);
        errorLabel.setVisible(false);
    });

    submitNewStockBtn.addActionListener(e -> {
        String name = stockNameTxt.getText();
        String symbol = stockTickerTxt.getText();
        String price = stockPriceTxt.getText();

        if(!name.equals("") && !symbol.equals("") && !price.equals("")){
            try{
                Stock newStock = new Stock(name, symbol, Double.parseDouble(price));
                if(DataManager.existStock(newStock)){
                    errorLabel.setText("Stock already exists!");
                    errorLabel.setVisible(true);
                }
                else{
                    DataManager.writeStock(newStock);
                }
            }
            catch(Exception except){
                System.out.println(except);
                errorLabel.setText("Please input valid Stock information");
                errorLabel.setVisible(true);
            }
        }

        addNewStockBtn.setVisible(true);
        submitNewStockBtn.setVisible(false);
        stockNameLabel.setVisible(false);
        stockNameTxt.setVisible(false);
        stockNameTxt.setText("");
        stockTickerLabel.setVisible(false);
        stockTickerTxt.setVisible(false);
        stockTickerTxt.setText("");
        stockPriceLabel.setVisible(false);
        stockPriceTxt.setVisible(false);
        stockPriceTxt.setText("");

     updateBottomPanel(errorLabel);
    });

    sp.add(topPanel);
    updateBottomPanel(errorLabel);


    panel.add(sp, BorderLayout.CENTER);
}

public void updateBottomPanel(JLabel errorLabel){
    sp.remove(bottomPanel);
    ArrayList<Stock> allStocks = DataManager.loadStocks();
    JPanel stockList = new JPanel();

    stockList.setLayout(new GridLayout(allStocks.size(), 1));
    for (Stock s : allStocks) {
        GUIStock stock = new GUIStock(s, errorLabel);
        System.out.println(s.toString());

        stockList.add(stock.getPanel());
    }
    bottomPanel = new JScrollPane(stockList);
sp.add(bottomPanel);
}

    public JPanel getPanel() {
        return panel;
    }


}