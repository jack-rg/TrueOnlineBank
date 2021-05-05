package GUI;

import Objects.Person;

import javax.swing.*;

/**
 * GUIInvestmentHome creates the GUI that allows users to play in the stock market.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIInvestmentHome extends JPanel {
    JTabbedPane tabbedPane;

    GUIStockPositions stockPositions;
    GUIStockTrade stockTrade;
    GUIStockOrderHistory orderHistory;

    public GUIInvestmentHome(Person person) {
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        stockPositions = new GUIStockPositions(person, this);
        tabbedPane.addTab("Positions", stockPositions.getPanel());

        stockTrade = new GUIStockTrade(person, this);
        tabbedPane.addTab("Trade", stockTrade.getPanel());

        orderHistory = new GUIStockOrderHistory(person, this);
        tabbedPane.addTab("Order History", orderHistory.getPanel());
        add(tabbedPane);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /*
    the following *should* allow for updating the investment tabs but only updates parts of them
        public void update() {
            updatePositions();
            updateHistory();
            updateTrade();
        }

        public void updatePositions() {
            stockPositions.updateAccounts();
        }

        public void updateHistory() {
            orderHistory.updateAccounts();
        }

        public void updateTrade() {
            stockTrade.updateAccounts();
        }
    */
}
