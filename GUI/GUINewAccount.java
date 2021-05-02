package GUI;

import Objects.*;
import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUINewAccount extends JPanel {
    JPanel panel;

    public GUINewAccount(User user, GUIAccountsOverview accountsOverview, JTabbedPane tabbedPane) {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountTypeLabel = new JLabel("Please choose a type of account:");
        accountTypeLabel.setBounds(30, 50, 250, 25);
        panel.add(accountTypeLabel);

        JRadioButton checkingRB = new JRadioButton("Checking");
        checkingRB.setBounds(30, 90, 120, 25);
        panel.add(checkingRB);

        JRadioButton savingRB = new JRadioButton("Savings");
        savingRB.setBounds(160, 90, 120, 25);
        panel.add(savingRB);

        ButtonGroup G1 = new ButtonGroup();
        G1.add(checkingRB);
        G1.add(savingRB);

        JLabel currencyTypeLabel = new JLabel("Please choose a type of currency:");
        currencyTypeLabel.setBounds(30, 140, 250, 25);
        panel.add(currencyTypeLabel);

        CurrencyType[] currencyTypes = CurrencyType.values();

        JComboBox<CurrencyType> currencyTypeCB = new JComboBox<CurrencyType>(currencyTypes);
        currencyTypeCB.setBounds(250, 140, 90, 25);
        panel.add(currencyTypeCB);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);
        panel.add(submitBtn);

        JLabel errorLabel = new JLabel("Insufficient funds");
        errorLabel.setBounds(30, 410, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        if (user.getActiveAccounts().size() > 0) {
            JLabel depositLabel = new JLabel("Would you like to deposit money into this account?");
            depositLabel.setBounds(30, 190, 450, 25);
            panel.add(depositLabel);

            JRadioButton yesRB = new JRadioButton("Yes");
            yesRB.setBounds(30, 220, 100, 25);
            panel.add(yesRB);

            JRadioButton noRB = new JRadioButton("No");
            noRB.setBounds(120, 220, 100, 25);
            panel.add(noRB);

            ButtonGroup G2 = new ButtonGroup();
            G2.add(yesRB);
            G2.add(noRB);

            JLabel accountChoiceLabel = new JLabel("Please choose an account to deposit from:");
            accountChoiceLabel.setBounds(30, 250, 400, 25);
            accountChoiceLabel.setVisible(false);
            panel.add(accountChoiceLabel);

            ArrayList<Account> accounts = user.getActiveAccounts();
            HashMap<String, Account> accMap = new HashMap<String, Account>();

            for (Account a : accounts) {
                accMap.put(a.toString(), a);
            }

            String[] keyArray = accMap.keySet().toArray(new String[0]);

            JComboBox<String> accountCB = new JComboBox<String>(keyArray);
            accountCB.setBounds(30, 280, 300, 25);
            accountCB.setVisible(false);
            panel.add(accountCB);

            JLabel depositAmountLabel = new JLabel("Please enter the deposit amount:");
            depositAmountLabel.setBounds(30, 310, 400, 25);
            depositAmountLabel.setVisible(false);
            panel.add(depositAmountLabel);

            // TODO: validation on input
            JTextField depositTF = new JTextField(20);
            depositTF.setBounds(250, 310, 165, 25);
            depositTF.setVisible(false);
            panel.add(depositTF);

            yesRB.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    accountChoiceLabel.setVisible(true);
                    accountCB.setVisible(true);

                    depositAmountLabel.setVisible(true);
                    depositTF.setVisible(true);
                }
            });

            noRB.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    accountChoiceLabel.setVisible(false);
                    accountCB.setVisible(false);

                    depositAmountLabel.setVisible(false);
                    depositTF.setVisible(false);
                }
            });

            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (yesRB.isSelected()) {
                        Float amount = Float.parseFloat(depositTF.getText());
                        Account account = accMap.get(accountCB.getSelectedItem());

                        if (amount <= account.getValue()) {
                            String userID = user.getUserID();
                            String accountID = "A" + userID.substring(1) + (user.getRawAccounts().size() + 1);

                            account.withdraw(amount, "Transfer to Account " + accountID);

                            if (savingRB.isSelected()) {
                                user.addNewAccount(new Saving(AccountType.SAVING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem(), AccountState.ACTIVE, amount));
                            } else {
                                user.addNewAccount(new Checking(AccountType.CHECKING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem(), AccountState.ACTIVE, amount));
                            }

                            accountsOverview.update();
                            tabbedPane.setSelectedIndex(0);
                        } else {
                            errorLabel.setVisible(true);
                        }
                    } else {
                        String userID = user.getUserID();
                        String accountID = "A" + userID.substring(1) + (user.getRawAccounts().size() + 1);

                        if (savingRB.isSelected()) {
                            user.addNewAccount(new Saving(AccountType.SAVING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                        } else {
                            user.addNewAccount(new Checking(AccountType.CHECKING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                        }

                        accountsOverview.update();
                        tabbedPane.setSelectedIndex(0);
                    }
                }
            });
        } else {
            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userID = user.getUserID();
                    String accountID = "A" + userID.substring(1) + (user.getRawAccounts().size() + 1);

                    if (savingRB.isSelected()) {
                        user.addNewAccount(new Saving(AccountType.SAVING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                    } else {
                        user.addNewAccount(new Checking(AccountType.CHECKING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                    }

                    accountsOverview.update();
                    tabbedPane.setSelectedIndex(0);
                }
            });
        }
    }

    public JPanel getPanel() { return panel; }
}
