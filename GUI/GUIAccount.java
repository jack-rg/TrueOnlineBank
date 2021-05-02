package GUI;

import Objects.Account;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIAccount {
    JPanel panel;
    JButton goBackButton;
    JButton deleteAccountButton;

    public GUIAccount(Account account) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        topPanel.add(goBackButton);

        deleteAccountButton = new JButton("Delete this account");
        deleteAccountButton.setBounds(120, 25, 150, 40);
        topPanel.add(deleteAccountButton);

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account.deactivate();
            }
        });

        JLabel accountNameLabel = new JLabel(account.getAccountType() + ": $" + account.getBalance());
        accountNameLabel.setBounds(30, 80, 150, 25);
        topPanel.add(accountNameLabel);

        panel.add(topPanel);

        DataManager.loadTransactions(account);
        ArrayList<Transaction> transactions = account.getTransactions();

        if (transactions.size() > 0) {
            JPanel transactionsPanel = new JPanel();
            transactionsPanel.setLayout(new GridLayout(transactions.size(), 1));

            for (int i = 0; i < transactions.size(); i++) {
                Transaction t = transactions.get(i);
                transactionsPanel.add((new GUITransaction(t)).getPanel());
            }

            panel.add(new JScrollPane(transactionsPanel));
        }

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getDeleteAccountButton() { return deleteAccountButton; }
}