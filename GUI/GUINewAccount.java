package GUI;

import Objects.*;
import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUINewAccount extends JPanel {
    JPanel panel;
    ButtonGroup accountTypeBG, depositBG;
    JLabel errorL, depositL, accountChoiceL, depositAmountL;
    JRadioButton yesRB, noRB;
    JComboBox<String> accountCB;
    JTextField depositTF;

    GUIAccountsHome home;
    Person person;

    HashMap<String, Account> accMap;

    public GUINewAccount(Person person, GUIAccountsHome home) {
        this.home = home;
        this.person = person;

        String userID = person.getUserID();
        String accountID = "A" + userID.substring(1) + (person.getRawAccounts().size() + 1);

        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountTypeL = new JLabel("Please choose a type of account:");
        accountTypeL.setBounds(30, 50, 250, 25);
        panel.add(accountTypeL);

        JRadioButton checkingRB = new JRadioButton("Checking");
        checkingRB.setBounds(30, 90, 120, 25);
        panel.add(checkingRB);

        JRadioButton savingRB = new JRadioButton("Savings");
        savingRB.setBounds(160, 90, 120, 25);
        panel.add(savingRB);

        accountTypeBG = new ButtonGroup();
        accountTypeBG.add(checkingRB);
        accountTypeBG.add(savingRB);

        JLabel currencyTypeL = new JLabel("Please choose a type of currency:");
        currencyTypeL.setBounds(30, 140, 250, 25);
        panel.add(currencyTypeL);

        CurrencyType[] currencyTypes = CurrencyType.values();
        JComboBox<CurrencyType> currencyTypeCB = new JComboBox<>(currencyTypes);
        currencyTypeCB.setBounds(250, 140, 90, 25);
        panel.add(currencyTypeCB);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);
        panel.add(submitBtn);

        errorL = new JLabel();
        errorL.setBounds(30, 410, 280, 40);
        errorL.setForeground(Color.RED);
        errorL.setVisible(false);
        panel.add(errorL);

        depositL = new JLabel("Would you like to deposit money into this account?");
        depositL.setBounds(30, 190, 450, 25);
        depositL.setVisible(false);
        panel.add(depositL);

        yesRB = new JRadioButton("Yes");
        yesRB.setBounds(30, 220, 100, 25);
        yesRB.setVisible(false);
        panel.add(yesRB);

        noRB = new JRadioButton("No");
        noRB.setBounds(120, 220, 100, 25);
        noRB.setVisible(false);
        panel.add(noRB);

        depositBG = new ButtonGroup();
        depositBG.add(yesRB);
        depositBG.add(noRB);

        accountChoiceL = new JLabel("Please choose an account to deposit from:");
        accountChoiceL.setBounds(30, 250, 400, 25);
        accountChoiceL.setVisible(false);
        panel.add(accountChoiceL);

        accMap = AccountManager.getAccMap(person);

        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(30, 280, 300, 25);
        accountCB.setVisible(false);
        panel.add(accountCB);

        depositAmountL = new JLabel("Please enter the deposit amount:");
        depositAmountL.setBounds(30, 310, 400, 25);
        depositAmountL.setVisible(false);
        panel.add(depositAmountL);

        // TODO: validation on input
        depositTF = new JTextField(20);
        depositTF.setBounds(250, 310, 165, 25);
        depositTF.setVisible(false);
        panel.add(depositTF);

        yesRB.addChangeListener(e -> {
            accountChoiceL.setVisible(true);
            accountCB.setVisible(true);

            depositAmountL.setVisible(true);
            depositTF.setVisible(true);
        });

        noRB.addChangeListener(e -> {
            accountChoiceL.setVisible(false);
            accountCB.setVisible(false);

            depositAmountL.setVisible(false);
            depositTF.setVisible(false);
        });

        if (person.getActiveAccounts().size() > 0) {
            depositL.setVisible(true);
            yesRB.setVisible(true);
            noRB.setVisible(true);

            submitBtn.addActionListener(e -> {
                if (accountTypeBG.getSelection() == null) {
                    errorL.setText("Please select a type of account.");
                    errorL.setVisible(true);
                    return;
                }

                if (depositBG.getSelection() == null) {
                    errorL.setText("Please select whether you'd like to deposit.");
                    errorL.setVisible(true);
                    return;
                }

                if (yesRB.isSelected()) {
                    try {
                        double amount = Double.parseDouble(depositTF.getText());
                        Account account = accMap.get(accountCB.getSelectedItem());

                        if (amount <= account.getBalance()) {
                            account.withdraw(amount, "Transfer to Account " + accountID);

                            if (savingRB.isSelected()) {
                                createNewUser(person, new Saving(AccountType.SAVING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem(), AccountState.ACTIVE, amount));
                            } else {
                                createNewUser(person, new Checking(AccountType.CHECKING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem(), AccountState.ACTIVE, amount));
                            }
                        } else {
                            errorL.setText("Insufficient funds");
                            errorL.setVisible(true);
                        }
                    } catch (Exception exception) {
                        errorL.setText("Please enter a valid deposit amount.");
                        errorL.setVisible(true);
                    }
                } else {
                    if (savingRB.isSelected()) {
                        createNewUser(person, new Saving(AccountType.SAVING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                    } else {
                        createNewUser(person, new Checking(AccountType.CHECKING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                    }
                }
            });
        } else {
            submitBtn.addActionListener(e -> {
                if (accountTypeBG.getSelection() == null) {
                    errorL.setText("Please select a type of account.");
                    errorL.setVisible(true);
                    return;
                }

                if (savingRB.isSelected()) {
                    createNewUser(person, new Saving(AccountType.SAVING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                } else {
                    createNewUser(person, new Checking(AccountType.CHECKING, accountID, userID, (CurrencyType) currencyTypeCB.getSelectedItem()));
                }
            });
        }
    }

    public JPanel getPanel() { return panel; }

    public void update() {
        accountTypeBG.clearSelection();
        errorL.setVisible(false);

        depositBG.clearSelection();
        depositTF.setText("");

        accountCB.removeAllItems();
        accMap = AccountManager.getAccMap(person);
        for (String a : AccountManager.getAccKeys(accMap)) {
            accountCB.addItem(a);
        }

        if (person.getActiveAccounts().size() > 0) {
            depositL.setVisible(true);
            yesRB.setVisible(true);
            noRB.setVisible(true);
        } else {
            depositL.setVisible(false);
            yesRB.setVisible(false);
            noRB.setVisible(false);
        }

        accountChoiceL.setVisible(false);
        depositAmountL.setVisible(false);
        depositTF.setVisible(false);
        accountCB.setVisible(false);
    }

    private void createNewUser(Person person, Account account) {
        person.addNewAccount(account);
        home.updateAll();
    }
}
