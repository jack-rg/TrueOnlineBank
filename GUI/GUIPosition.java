package GUI;

import Objects.Position;

import javax.swing.*;
import java.awt.*;

/**
 * GUIPosition creates the GUI that allows users to see their investment positions.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIPosition extends JPanel {
    JPanel panel;

    public GUIPosition(Position position) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));

        JLabel stock = new JLabel(position.getStock().getSymbol());
        panel.add(stock);

        JLabel quantity = new JLabel(position.getQuantity() + "");
        panel.add(quantity);

        JLabel totalCost = new JLabel(position.getTotalCost() + "");
        panel.add(totalCost);

        double unrealizedPL = position.getUnrealizedPL();
        JLabel unrealizedPLLabel;
        if (unrealizedPL < 0) {
            unrealizedPLLabel = new JLabel("-$" + Math.abs(unrealizedPL));
            unrealizedPLLabel.setForeground(Color.RED);
        } else {
            unrealizedPLLabel = new JLabel("$" + unrealizedPL);
            unrealizedPLLabel.setForeground(Color.GREEN);
        }
        panel.add(unrealizedPLLabel);
    }

    public JPanel getPanel() {
        return panel;
    }
}
