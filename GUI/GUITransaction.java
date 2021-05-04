package GUI;

import Objects.Transaction;
import Types.TransactionType;

import javax.swing.*;
import java.awt.*;

/**
 * GUITransaction creates the GUI that allows users to see a single transaction.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUITransaction {
    JPanel panel;

    public GUITransaction(Transaction transaction) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        JLabel dateLabel = new JLabel(transaction.getDate());
        panel.add(dateLabel);

        JLabel nameLabel = new JLabel(transaction.getName());
        panel.add(nameLabel);

        double amount = transaction.getAmount();
        JLabel amountLabel;
        if (transaction.getType() == (TransactionType.WITHDRAWAL)) {
            amountLabel = new JLabel("-$" + amount);
            amountLabel.setForeground(Color.RED);
        } else {
            amountLabel = new JLabel("$" + amount);
            amountLabel.setForeground(Color.GREEN);
        }

        panel.add(amountLabel);
    }

    public JPanel getPanel() {
        return panel;
    }
}
