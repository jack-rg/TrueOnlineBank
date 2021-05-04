package GUI;

import Objects.Account;
import Objects.Person;
import Types.CurrencyType;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIDeposit extends JPanel {
    JPanel panel;
    JComboBox<String> accountCB;
    JTextField depositTF;
    JLabel errorLabel;

    HashMap<String, Account> accMap;

    Person person;

    public GUIDeposit(Person person, GUIAccountsHome home) {
        this.person = person;

        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountChoiceLabel = new JLabel("Please choose an account to deposit into:");
        accountChoiceLabel.setBounds(30, 50, 400, 25);
        panel.add(accountChoiceLabel);

        accMap = AccountManager.getAccMap(person);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(30, 80, 300, 25);
        panel.add(accountCB);

        JLabel currencyChoiceLabel = new JLabel("Please choose the currency of the deposit:");
        currencyChoiceLabel.setBounds(30, 120, 400, 25);
        panel.add(currencyChoiceLabel);

        CurrencyType[] currencyTypes = CurrencyType.values();

        JComboBox<CurrencyType> currencyTypeCB = new JComboBox<>(currencyTypes);
        currencyTypeCB.setBounds(350, 120, 90, 25);
        panel.add(currencyTypeCB);

        JLabel depositAmountLabel = new JLabel("Please enter the deposit amount:");
        depositAmountLabel.setBounds(30, 170, 400, 25);
        panel.add(depositAmountLabel);

        depositTF = new JTextField(20);
        depositTF.setBounds(250, 170, 165, 25);
        panel.add(depositTF);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);

        errorLabel = new JLabel("Insufficient funds");
        errorLabel.setBounds(30, 410, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        submitBtn.addActionListener(e -> {
            try {
                Account account = accMap.get(accountCB.getSelectedItem());
                CurrencyType cType = (CurrencyType) currencyTypeCB.getSelectedItem();

                account.deposit(Float.parseFloat(depositTF.getText()), "ATM Deposit", cType);

                home.update();
            } catch (Exception exception) {
                errorLabel.setText("Please enter a valid deposit amount.");
                errorLabel.setVisible(true);
            }
        });

        panel.add(submitBtn);
    }

    public void update() {
        depositTF.setText("");

        accountCB.removeAllItems();
        accMap = AccountManager.getAccMap(person);
        for (String a : AccountManager.getAccKeys(accMap)) {
            accountCB.addItem(a);
        }

        errorLabel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }
}
