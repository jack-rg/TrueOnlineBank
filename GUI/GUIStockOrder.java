package GUI;

import Objects.StockOrder;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * GUIStockOrder creates the GUI that allows users to view a single stock order.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIStockOrder extends JPanel {
    JPanel panel;

    public GUIStockOrder(StockOrder order) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 5));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        JLabel date = new JLabel(order.getOrderDate().format(formatter));
        panel.add(date);

        JLabel stock = new JLabel(order.getOrderStockSymbol());
        panel.add(stock);

        JLabel quantity = new JLabel(order.getQuantity() + "");
        panel.add(quantity);

        JLabel pricePerStock = new JLabel(order.getPricePerStock() + "");
        panel.add(pricePerStock);

        JLabel orderType = new JLabel(order.getOrderType() + "");
        panel.add(orderType);
    }

    public JPanel getPanel() {
        return panel;
    }
}
