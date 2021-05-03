package GUI;

import Objects.Account;
import Objects.Person;
import Objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUIWithdraw extends JPanel {
    JPanel panel;
    JButton submitBtn;

    public GUIWithdraw(Person person, GUIAccountsOverview accountsOverview, JTabbedPane tabbedPane) {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountChoiceLabel = new JLabel("Please choose an account to withdraw from:");
        accountChoiceLabel.setBounds(30, 50, 400, 25);
        panel.add(accountChoiceLabel);

        ArrayList<Account> accounts = person.getActiveAccounts();
        HashMap<String, Account> accMap = new HashMap<String, Account>();

        for (Account a : accounts) {
            accMap.put(a.toString(), a);
        }

        String[] keyArray = accMap.keySet().toArray(new String[0]);

        JComboBox<String> accountCB = new JComboBox<String>(keyArray);
        accountCB.setBounds(30, 80, 300, 25);
        panel.add(accountCB);

        JLabel depositAmountLabel = new JLabel("Please enter the withdraw amount:");
        depositAmountLabel.setBounds(30, 140, 400, 25);
        panel.add(depositAmountLabel);

        // TODO: validation on input
        JTextField depositTF = new JTextField(20);
        depositTF.setBounds(270, 140, 165, 25);
        panel.add(depositTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);

        JLabel errorLabel = new JLabel("Insufficient funds");
        errorLabel.setBounds(30, 410, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = accMap.get(accountCB.getSelectedItem());

                if (account.withdraw(Float.parseFloat(depositTF.getText()), "ATM Withdrawal")) {
                    accountsOverview.update();
                    tabbedPane.setSelectedIndex(0);
                } else {
                    errorLabel.setVisible(true);
                }
            }
        });

        panel.add(submitBtn);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getSubmitBtn() {
        return submitBtn;
    }
}
