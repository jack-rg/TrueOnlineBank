package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;

public class GUIInvestmentHome extends JPanel {
    JTabbedPane tabbedPane;

    public GUIInvestmentHome(Person person) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        GUIStockPositions stockPositions = new GUIStockPositions(person);
        tabbedPane.addTab("Positions", stockPositions.getPanel());

        GUIStockPositions accountsOverview = new GUIStockPositions(person);
        tabbedPane.addTab("Trade", accountsOverview.getPanel());

        GUIStockOrderHistory orderHistory = new GUIStockOrderHistory(person);
        tabbedPane.addTab("Order History", orderHistory.getPanel());
        add(tabbedPane);
    }

    public JTabbedPane getTabbedPane(){
        return tabbedPane;
    }
}
