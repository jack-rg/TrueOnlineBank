package GUI;

import Objects.Account;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIAccount {
    JPanel panel;
    JButton goBackButton, deleteAccountButton, payInterestBtn;

    public GUIAccount(Account account) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.1);


        JPanel topPanel = new JPanel();
        topPanel.setBounds(25, 25, 400, 500);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        topPanel.add(goBackButton);

        deleteAccountButton = new JButton("Delete this account");
        deleteAccountButton.setBounds(120, 25, 150, 40);
        topPanel.add(deleteAccountButton);

        deleteAccountButton.addActionListener(e -> account.deactivate());

        payInterestBtn = new JButton("Pay Interest");
        payInterestBtn.setBounds(120, 25, 150, 40);
        topPanel.add(payInterestBtn);

        payInterestBtn.addActionListener(e -> account.payInterest()); //oooooop
        payInterestBtn.setVisible(false);


        JLabel accountNameLabel = new JLabel(account.getAccountType() + ": $" + account.getBalance());
        accountNameLabel.setBounds(125, 25, 400, 25);
        topPanel.add(accountNameLabel);

        sp.add(topPanel);

        DataManager.loadTransactions(account);
        ArrayList<Transaction> transactions = account.getTransactions();

        if (transactions.size() > 0) {
            JPanel transactionsPanel = new JPanel();
            transactionsPanel.setLayout(new GridLayout(transactions.size(), 1));

            for (Transaction t : transactions) {
                transactionsPanel.add((new GUITransaction(t)).getPanel());
            }

            sp.add(new JScrollPane(transactionsPanel));
        }
        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public void hideButton() {
        deleteAccountButton.setVisible(false);
    }
    public void showInterest() {payInterestBtn.setVisible(true);
    }

}