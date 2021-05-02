package GUI;

import Objects.Account;
import Objects.User;
import Types.CurrencyType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUIDeposit extends JPanel {
    JPanel panel;
    JButton submitBtn;

    public GUIDeposit(User user, GUIAccountsOverview accountsOverview) {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountChoiceLabel = new JLabel("Please choose an account to deposit into:");
        accountChoiceLabel.setBounds(30, 50, 400, 25);
        panel.add(accountChoiceLabel);

        ArrayList<Account> accounts = user.getActiveAccounts();
        HashMap<String, Account> accMap = new HashMap<String, Account>();

        for (Account a : accounts) {
            accMap.put(a.toString(), a);
        }

        String[] keyArray = accMap.keySet().toArray(new String[0]);

        JComboBox<String> accountCB = new JComboBox<String>(keyArray);
        accountCB.setBounds(30, 80, 300, 25);
        panel.add(accountCB);

        JLabel currencyChoiceLabel = new JLabel("Please choose the currency of the deposit:");
        currencyChoiceLabel.setBounds(30, 120, 400, 25);
        panel.add(currencyChoiceLabel);

        CurrencyType[] currencyTypes = CurrencyType.values();

        JComboBox<CurrencyType> currencyTypeCB = new JComboBox<CurrencyType>(currencyTypes);
        currencyTypeCB.setBounds(350, 120, 90, 25);
        panel.add(currencyTypeCB);

        JLabel depositAmountLabel = new JLabel("Please enter the deposit amount:");
        depositAmountLabel.setBounds(30, 170, 400, 25);
        panel.add(depositAmountLabel);

        // TODO: validation on input
        JTextField depositTF = new JTextField(20);
        depositTF.setBounds(250, 170, 165, 25);
        panel.add(depositTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = accMap.get(accountCB.getSelectedItem());
                CurrencyType cType = (CurrencyType) currencyTypeCB.getSelectedItem();

                account.deposit(Float.parseFloat(depositTF.getText()), "ATM Deposit", cType);

                accountsOverview.update();
            }
        });

        panel.add(submitBtn);
    }

    public JPanel getPanel() { return panel; }

    public JButton getSubmitBtn() { return submitBtn; }
}
