package GUI;

import Objects.Checking;
import Objects.Saving;
import Objects.User;
import Types.AccountType;
import Types.CurrencyType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUINewAccount extends JPanel {
    JPanel panel;
    JButton submitBtn;

    public GUINewAccount(User user, GUIAccountsOverview accountsOverview) {
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

        ButtonGroup G = new ButtonGroup();
        G.add(checkingRB);
        G.add(savingRB);

        JLabel currencyTypeLabel = new JLabel("Please choose a type of currency:");
        currencyTypeLabel.setBounds(30, 140, 250, 25);
        panel.add(currencyTypeLabel);

        CurrencyType[] currencyTypes = CurrencyType.values();

        JComboBox<CurrencyType> currencyTypeCB = new JComboBox<CurrencyType>(currencyTypes);
        currencyTypeCB.setBounds(250, 140, 90, 25);
        panel.add(currencyTypeCB);

        if (user.getActiveAccounts().size() > 0) {
            JLabel depositLabel = new JLabel("Would you like to deposit money into this account \nfrom another account?");
            depositLabel.setBounds(30, 190, 450, 50);
            panel.add(depositLabel);
        }

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);

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
            }
        });

        panel.add(submitBtn);
    }

    public JPanel getPanel() { return panel; }

    public JButton getSubmitBtn() { return submitBtn; }
}
