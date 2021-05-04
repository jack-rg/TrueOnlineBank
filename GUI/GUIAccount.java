package GUI;

import Objects.Account;
import Objects.Saving;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUIAccount creates the GUI that shows account details.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIAccount {
    JPanel panel;
    JButton goBackButton, deleteAccountButton, payInterestBtn;
    JSplitPane sp;
    JScrollPane transactionsSP;
    JLabel accountNameLabel;

    Account account;

    GUIHome home;

    public GUIAccount(Account account) {
        this.account = account;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.1);

        JPanel topPanel = new JPanel();
        topPanel.setBounds(25, 25, 400, 500);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        topPanel.add(goBackButton);

        deleteAccountButton = new JButton("Delete this account");
        deleteAccountButton.setBounds(120, 25, 150, 40);
        topPanel.add(deleteAccountButton);

        payInterestBtn = new JButton("Charge Interest");
        payInterestBtn.setBounds(120, 25, 150, 40);
        topPanel.add(payInterestBtn);

        payInterestBtn.setVisible(false);

        accountNameLabel = new JLabel();
        accountNameLabel.setBounds(125, 25, 400, 25);
        topPanel.add(accountNameLabel);

        sp.add(topPanel);

        updateTransactions();

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public GUIAccount(Account account, GUIHome home) {
        this(account);

        this.home = home;
        payInterestBtn.addActionListener(e -> {
            if (account instanceof Saving) {
                ((Saving) account).chargeInterest();
                updateTransactions();
                home.updateDailyTransactions();
            }
        });
    }

    public void updateTransactions() {
        accountNameLabel.setText(account.getAccountType() + ": $" + account.getBalance());

        if (transactionsSP != null) {
            sp.remove(transactionsSP);
        }

        DataManager.loadTransactions(account);
        ArrayList<Transaction> transactions = account.getTransactions();

        if (transactions.size() > 0) {
            JPanel transactionsPanel = new JPanel();
            transactionsPanel.setLayout(new GridLayout(transactions.size(), 1));

            for (Transaction t : transactions) {
                transactionsPanel.add((new GUITransaction(t)).getPanel());
            }

            transactionsSP = new JScrollPane(transactionsPanel);
            sp.add(transactionsSP);
        }
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

    public void showInterest() {
        payInterestBtn.setVisible(true);
    }
}