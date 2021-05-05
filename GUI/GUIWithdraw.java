package GUI;

import Objects.Account;
import Objects.Person;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * GUIWithdraw creates the GUI that allows users to withdraw money.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIWithdraw extends JPanel {
    JPanel panel;
    JComboBox<String> accountCB;
    JTextField withdrawTF;
    JLabel errorLabel;

    HashMap<String, Account> accMap;

    Person person;

    public GUIWithdraw(Person person, GUIAccountsHome home) {
        this.person = person;

        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountChoiceLabel = new JLabel("Please choose an account to withdraw from:");
        accountChoiceLabel.setBounds(30, 50, 400, 25);
        panel.add(accountChoiceLabel);

        accMap = AccountManager.getAccMap(person, false);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(30, 80, 300, 25);
        panel.add(accountCB);

        JLabel depositAmountLabel = new JLabel("Please enter the withdraw amount:");
        depositAmountLabel.setBounds(30, 140, 400, 25);
        panel.add(depositAmountLabel);

        withdrawTF = new JTextField(20);
        withdrawTF.setBounds(270, 140, 165, 25);
        panel.add(withdrawTF);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);

        errorLabel = new JLabel();
        errorLabel.setBounds(30, 410, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        submitBtn.addActionListener(e -> {
            try {
                Account account = accMap.get(accountCB.getSelectedItem());

                if (account.withdraw(Double.parseDouble(withdrawTF.getText()),
                        "ATM Withdrawal", true)) {
                    home.update();
                } else {
                    errorLabel.setText("Insufficient funds");
                    errorLabel.setVisible(true);
                }
            } catch (Exception exception) {
                errorLabel.setText("Please enter a valid withdrawal amount.");
                errorLabel.setVisible(true);
            }
        });

        panel.add(submitBtn);
    }

    public void update() {
        withdrawTF.setText("");

        accountCB.removeAllItems();
        accMap = AccountManager.getAccMap(person, false);
        for (String a : AccountManager.getAccKeys(accMap)) {
            accountCB.addItem(a);
        }

        errorLabel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }
}
