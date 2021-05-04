package GUI;

import Objects.Person;
import Objects.StockOrder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUIStockOrderHistory creates the GUI that allows users to see their entire
 * stock order history.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIStockOrderHistory extends JPanel {
    JPanel panel;
    JSplitPane sp;

    public GUIStockOrderHistory(Person person) {
        ArrayList<StockOrder> orders = person.getSecurityAccount().getOrders();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.1);

        JPanel topPanel = new JPanel();
        topPanel.setBounds(25, 25, 400, 500);

        JPanel titlePanel = new JPanel(new GridLayout(1, 5));
        titlePanel.add(new JLabel("Date"));
        titlePanel.add(new JLabel("Stock"));
        titlePanel.add(new JLabel("Quantity"));
        titlePanel.add(new JLabel("Price per Stock"));
        titlePanel.add(new JLabel("Order Type"));

        sp.add(titlePanel);

        JPanel ordersPanel = new JPanel(new GridLayout(orders.size(), 1));
        for (StockOrder o : orders) {
            ordersPanel.add((new GUIStockOrder(o)).getPanel());
        }
        sp.add(ordersPanel);

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() { return panel; }
}
