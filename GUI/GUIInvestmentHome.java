package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;

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

    public GUIInvestmentHome(Person person) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        GUIStockPositions stockPositions = new GUIStockPositions(person);
        tabbedPane.addTab("Positions", stockPositions.getPanel());

        GUIStockTrade stockTrade = new GUIStockTrade(person);
        tabbedPane.addTab("Trade", stockTrade.getPanel());

        GUIStockOrderHistory orderHistory = new GUIStockOrderHistory(person);
        tabbedPane.addTab("Order History", orderHistory.getPanel());
        add(tabbedPane);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
