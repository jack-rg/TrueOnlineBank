package GUI;

import Objects.Transaction;
import Types.TransactionType;

import javax.swing.*;
import java.awt.*;

public class GUITransaction {
    JPanel panel;

    public GUITransaction(Transaction transaction) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        JLabel dateLabel = new JLabel(transaction.getDate());
        panel.add(dateLabel);

        JLabel nameLabel = new JLabel(transaction.getName());
        panel.add(nameLabel);

        float amount = transaction.getAmount();
        JLabel amountLabel;
        if (transaction.getType().equals(TransactionType.WITHDRAWAL)) {
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
