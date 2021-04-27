package GUI;

import Objects.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIAccount {
    JPanel panel;
    JButton goBackButton;

    public GUIAccount(Account account) {
        panel = new JPanel();
        panel.setLayout(null);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        panel.add(goBackButton);

        JButton deleteAccountButton = new JButton("Delete this account");
        deleteAccountButton.setBounds(120, 25, 150, 40);
        panel.add(deleteAccountButton);

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: delete account lol
            }
        });

        JLabel accountNameLabel = new JLabel(account.getName() + ": $" + account.getValue());
        accountNameLabel.setBounds(30, 100, 150, 25);
        panel.add(accountNameLabel);

        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new GridLayout(4, 1)); // TODO: rows should be however many transactions there are attached to the account
        for (int i = 0; i < 4; i++) {
            // TODO: new GUITransaction()
        }
        panel.add(transactionsPanel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGoBackButton() { return goBackButton; }
}