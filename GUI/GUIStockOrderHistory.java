package GUI;

import Objects.Account;
import Objects.Person;
import Objects.Security;
import Objects.StockOrder;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

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
    JPanel panel, topPanel, ordersPanel;
    JSplitPane sp;

    JComboBox<String> accountCB;

    HashMap<String, Account> accMap;
    ArrayList<StockOrder> orders;

    public GUIStockOrderHistory(Person person) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel aPanel = new JPanel();
        aPanel.setLayout(null);

        JLabel accountLabel = new JLabel("Please pick an account: ");
        accountLabel.setBounds(50, 160, 150, 25);
        aPanel.add(accountLabel);

        accMap = AccountManager.getAccMap(person, true);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(180, 160, 300, 25);
        aPanel.add(accountCB);

        panel.add(aPanel, BorderLayout.NORTH);

        accountCB.addActionListener(e -> {
            orders = ((Security) accMap.get(accountCB.getSelectedItem())).getOrders();
            update();
        });

        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.1);

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() { return panel; }

    public void update() {
        if (topPanel != null) {
            sp.remove(topPanel);
        }

        if (ordersPanel != null) {
            sp.remove(ordersPanel);
        }

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
    }
}
