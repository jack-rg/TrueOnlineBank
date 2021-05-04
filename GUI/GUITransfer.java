package GUI;

import Objects.Account;
import Objects.Person;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * GUITransfer creates the GUI that allows users to transfer money across accounts or to different users.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUITransfer extends JPanel {
    JPanel panel;
    JComboBox<String> toAccountCB, fromAccountCB;
    ButtonGroup transferTypeBG;
    JLabel toAccountChoiceLabel, fromAccountChoiceLabel, depositAmountLabel, errorLabel;
    JTextField accountIDTF, transferTF;
    JButton submitBtn;

    HashMap<String, Account> accMap;

    Person person;

    public GUITransfer(Person person, GUIAccountsHome home) {
        this.person = person;

        panel = new JPanel();
        panel.setLayout(null);

        accMap = AccountManager.getAccMap(person);

        JLabel transferLabel = new JLabel("What type of transfer would you like to do?");
        transferLabel.setBounds(30, 50, 450, 25);
        panel.add(transferLabel);

        JRadioButton accountTransferRB = new JRadioButton("Account Transfer");
        accountTransferRB.setBounds(30, 80, 200, 25);
        panel.add(accountTransferRB);

        JRadioButton userTransferRB = new JRadioButton("User Transfer");
        userTransferRB.setBounds(230, 80, 200, 25);
        panel.add(userTransferRB);

        transferTypeBG = new ButtonGroup();
        transferTypeBG.add(accountTransferRB);
        transferTypeBG.add(userTransferRB);

        fromAccountChoiceLabel = new JLabel("Please choose an account to transfer from:");
        fromAccountChoiceLabel.setBounds(30, 120, 400, 25);
        fromAccountChoiceLabel.setVisible(false);
        panel.add(fromAccountChoiceLabel);

        fromAccountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        fromAccountCB.setBounds(30, 150, 300, 25);
        fromAccountCB.setVisible(false);
        panel.add(fromAccountCB);

        toAccountChoiceLabel = new JLabel();
        toAccountChoiceLabel.setBounds(30, 190, 400, 25);
        toAccountChoiceLabel.setVisible(false);
        panel.add(toAccountChoiceLabel);

        accountIDTF = new JTextField(20);
        accountIDTF.setBounds(300, 190, 165, 25);
        accountIDTF.setVisible(false);
        panel.add(accountIDTF);

        toAccountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        toAccountCB.setBounds(30, 210, 300, 25);
        toAccountCB.setVisible(false);
        panel.add(toAccountCB);

        depositAmountLabel = new JLabel("Please enter the transfer amount:");
        depositAmountLabel.setBounds(30, 250, 400, 25);
        depositAmountLabel.setVisible(false);
        panel.add(depositAmountLabel);

        transferTF = new JTextField(20);
        transferTF.setBounds(250, 250, 165, 25);
        transferTF.setVisible(false);
        panel.add(transferTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);
        submitBtn.setVisible(false);
        panel.add(submitBtn);

        errorLabel = new JLabel();
        errorLabel.setBounds(30, 410, 400, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        accountTransferRB.addChangeListener(e -> {
            fromAccountChoiceLabel.setVisible(true);
            fromAccountCB.setVisible(true);

            toAccountChoiceLabel.setText("Please choose an account to transfer to:");
            toAccountChoiceLabel.setVisible(true);
            toAccountCB.setVisible(true);

            accountIDTF.setVisible(false);

            depositAmountLabel.setVisible(true);
            transferTF.setVisible(true);

            submitBtn.setVisible(true);
        });

        userTransferRB.addChangeListener(e -> {
            fromAccountChoiceLabel.setVisible(true);
            fromAccountCB.setVisible(true);

            toAccountChoiceLabel.setText("Please enter the accountID to transfer to:");
            toAccountChoiceLabel.setVisible(true);
            toAccountCB.setVisible(false);

            accountIDTF.setVisible(true);

            depositAmountLabel.setVisible(true);
            transferTF.setVisible(true);

            submitBtn.setVisible(true);
        });

        submitBtn.addActionListener(e -> {
            Account fromAccount = accMap.get(fromAccountCB.getSelectedItem());
            Account toAccount = accMap.get(toAccountCB.getSelectedItem());

            String toAccountID;
            if (accountTransferRB.isSelected()) {
                toAccountID = toAccount.getAccountID();
            } else {
                toAccountID = accountIDTF.getText();
            }

            if (fromAccount.getAccountID().equals(toAccountID)) {
                errorLabel.setText("Cannot transfer funds across the same account.");
                errorLabel.setVisible(true);
            } else if (transferTF.getText().equals("")) {
                errorLabel.setText("Please enter a transfer amount.");
                errorLabel.setVisible(true);
            } else {
                double amount = Double.parseDouble(transferTF.getText());

                if (amount > fromAccount.getBalance()) {
                    errorLabel.setText("Insufficient funds.");
                    errorLabel.setVisible(true);
                } else {
                    if (accountTransferRB.isSelected()) {
                        fromAccount.transferTo(toAccount, amount);
                    } else {
                        if (toAccountID.equals("")) {
                            errorLabel.setText("Please enter an account ID.");
                            errorLabel.setVisible(true);
                            return;
                        }

                        if (fromAccount.transferTo(toAccountID, amount)) {
                            errorLabel.setText("Account not found.");
                            errorLabel.setVisible(true);
                            return;
                        }
                    }

                    home.update();
                }
            }
        });
    }

    public void update() {
        transferTypeBG.clearSelection();

        fromAccountChoiceLabel.setVisible(false);
        fromAccountCB.setVisible(false);

        toAccountChoiceLabel.setVisible(false);
        toAccountCB.setVisible(false);

        accountIDTF.setText("");
        accountIDTF.setVisible(false);

        depositAmountLabel.setVisible(false);

        transferTF.setText("");
        transferTF.setVisible(false);

        submitBtn.setVisible(false);

        accMap = AccountManager.getAccMap(person);

        toAccountCB.removeAllItems();
        fromAccountCB.removeAllItems();

        for (String a : AccountManager.getAccKeys(accMap)) {
            toAccountCB.addItem(a);
            fromAccountCB.addItem(a);
        }

        errorLabel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }
}
