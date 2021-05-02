package GUI;

import Objects.Account;
import Objects.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUITransfer extends JPanel {
    JPanel panel;

    public GUITransfer(User user, GUIAccountsOverview accountsOverview, JTabbedPane tabbedPane) {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel transferLabel = new JLabel("What type of transfer would you like to do?");
        transferLabel.setBounds(30, 50, 450, 25);
        panel.add(transferLabel);

        JRadioButton accountTransferRB = new JRadioButton("Account Transfer");
        accountTransferRB.setBounds(30, 80, 200, 25);
        panel.add(accountTransferRB);

        JRadioButton userTransferRB = new JRadioButton("User Transfer");
        userTransferRB.setBounds(230, 80, 200, 25);
        panel.add(userTransferRB);

        ButtonGroup G2 = new ButtonGroup();
        G2.add(accountTransferRB);
        G2.add(userTransferRB);

        JLabel fromAccountChoiceLabel = new JLabel("Please choose an account to transfer from:");
        fromAccountChoiceLabel.setBounds(30, 120, 400, 25);
        fromAccountChoiceLabel.setVisible(false);
        panel.add(fromAccountChoiceLabel);

        ArrayList<Account> accounts = user.getActiveAccounts();
        HashMap<String, Account> accMap = new HashMap<String, Account>();

        for (Account a : accounts) {
            accMap.put(a.toString(), a);
        }

        String[] keyArray = accMap.keySet().toArray(new String[0]);

        JComboBox<String> fromAccountCB = new JComboBox<String>(keyArray);
        fromAccountCB.setBounds(30, 150, 300, 25);
        fromAccountCB.setVisible(false);
        panel.add(fromAccountCB);

        JLabel toAccountChoiceLabel = new JLabel("Please choose an account to transfer to:");
        toAccountChoiceLabel.setBounds(30, 180, 400, 25);
        toAccountChoiceLabel.setVisible(false);
        panel.add(toAccountChoiceLabel);

        JComboBox<String> toAccountCB = new JComboBox<>(keyArray);
        toAccountCB.setBounds(30, 210, 300, 25);
        toAccountCB.setVisible(false);
        panel.add(toAccountCB);

        JLabel depositAmountLabel = new JLabel("Please enter the transfer amount:");
        depositAmountLabel.setBounds(30, 250, 400, 25);
        depositAmountLabel.setVisible(false);
        panel.add(depositAmountLabel);

        // TODO: validation on input
        JTextField transferTF = new JTextField(20);
        transferTF.setBounds(250, 250, 165, 25);
        transferTF.setVisible(false);
        panel.add(transferTF);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);
        panel.add(submitBtn);

        JLabel errorLabel = new JLabel();
        errorLabel.setBounds(30, 410, 400, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        accountTransferRB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fromAccountChoiceLabel.setVisible(true);
                fromAccountCB.setVisible(true);

                toAccountChoiceLabel.setVisible(true);
                toAccountCB.setVisible(true);

                depositAmountLabel.setVisible(true);
                transferTF.setVisible(true);
            }
        });

        userTransferRB.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fromAccountChoiceLabel.setVisible(true);
                fromAccountCB.setVisible(true);

                toAccountChoiceLabel.setVisible(false);
                toAccountCB.setVisible(false);

                depositAmountLabel.setVisible(true);
                transferTF.setVisible(true);
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account fromAccount = accMap.get(fromAccountCB.getSelectedItem());
                Account toAccount = accMap.get(toAccountCB.getSelectedItem());

                if (fromAccount.getAccountID().equals(toAccount.getAccountID())) {
                    errorLabel.setText("Cannot transfer funds across the same account.");
                    errorLabel.setVisible(true);
                } else if (transferTF.getText().equals("")) {
                    errorLabel.setText("Please enter a transfer amount.");
                    errorLabel.setVisible(true);
                } else {
                    Float amount = Float.parseFloat(transferTF.getText());

                    if (amount > fromAccount.getBalance()) {
                        errorLabel.setText("Insufficient funds.");
                        errorLabel.setVisible(true);
                    } else {
                        fromAccount.withdraw(amount, "Transfer to " + toAccount.getAccountID());
                        toAccount.deposit(amount, "Transfer from " + fromAccount.getAccountID(), fromAccount.getCurrencyType());

                        accountsOverview.update();
                        tabbedPane.setSelectedIndex(0);
                    }
                }
            }
        });
    }

    public JPanel getPanel() { return panel; }
}
