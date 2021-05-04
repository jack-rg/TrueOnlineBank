package GUI;

import Objects.Account;
import Objects.Loan;
import Objects.Person;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUILoan extends JPanel {
    JPanel panel;
    JTextField loanAmountTF, paymentAmountTF;
    JLabel loanAmountLabel, errorLabel, loanPaidLabel, accountChoiceLabel, paymentAmountLabel;
    JButton submitBtn;
    JCheckBox termsCB;
    JComboBox<String> accountCB;

    Person person;
    GUIHome home;

    HashMap<String, Account> accMap;

    public GUILoan(Person person, GUIHome home) {
        this.person = person;
        this.home = home;

        panel = new JPanel();
        panel.setLayout(null);

        loanAmountLabel = new JLabel();
        loanAmountLabel.setBounds(30, 50, 200, 25);
        loanAmountLabel.setVisible(false);
        panel.add(loanAmountLabel);

        loanAmountTF = new JTextField(20);
        loanAmountTF.setBounds(150, 50, 165, 25);
        loanAmountTF.setVisible(false);
        panel.add(loanAmountTF);

        loanPaidLabel = new JLabel();
        loanPaidLabel.setBounds(30, 90, 400, 25);
        loanPaidLabel.setVisible(false);
        panel.add(loanPaidLabel);

        termsCB = new JCheckBox("<html>I understand that I must pay back this loan within"
                + "<br>30 days or the bank will confiscate 50% of my assets.");
        termsCB.setBounds(30, 90, 400, 100);
        termsCB.setVisible(false);
        panel.add(termsCB);

        accountChoiceLabel = new JLabel("Please choose an account to pay from:");
        accountChoiceLabel.setBounds(30, 130, 400, 25);
        accountChoiceLabel.setVisible(false);
        panel.add(accountChoiceLabel);

        accMap = AccountManager.getAccMap(person);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(30, 160, 300, 25);
        accountCB.setVisible(false);
        panel.add(accountCB);

        paymentAmountLabel = new JLabel("Please enter the payment amount:");
        paymentAmountLabel.setBounds(30, 190, 400, 25);
        paymentAmountLabel.setVisible(false);
        panel.add(paymentAmountLabel);

        paymentAmountTF = new JTextField(20);
        paymentAmountTF.setBounds(270, 190, 165, 25);
        paymentAmountTF.setVisible(false);
        panel.add(paymentAmountTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 240, 280, 40);
        submitBtn.setVisible(false);
        panel.add(submitBtn);

        errorLabel = new JLabel();
        errorLabel.setBounds(30, 500, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        update();

        if (person.getLoan() != null) {
            submitBtn.addActionListener(e -> {
                try {
                    double paymentAmount = Double.parseDouble(paymentAmountTF.getText());
                    Account account = accMap.get(accountCB.getSelectedItem());

                    if (account.withdraw(paymentAmount, "Loan Payment")) {
                        person.getLoan().makePayment(paymentAmount);
                        update();
                        updateOthers();
                    } else {
                        errorLabel.setText("Insufficient funds");
                        errorLabel.setVisible(true);
                    }
                } catch (Exception exception) {
                    errorLabel.setText("Please enter a valid payment amount.");
                    errorLabel.setVisible(true);
                    return;
                }
            });
        } else {
            submitBtn.addActionListener(e -> {
                if (person.getTotalAssets() <= 0) {
                    errorLabel.setText("You do not have enough collateral to take out a loan.");
                    errorLabel.setVisible(true);
                    return;
                }

                if (!termsCB.isSelected()) {
                    errorLabel.setText("Please agree to the terms.");
                    errorLabel.setVisible(true);
                    return;
                }

                try {
                    double loanAmount = Double.parseDouble(loanAmountTF.getText());
                    person.takeOutLoan(loanAmount);

                    update();
                } catch (Exception exception) {
                    errorLabel.setText("Please enter a valid loan amount.");
                    errorLabel.setVisible(true);
                    return;
                }
            });
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public void update() {
        if (person.getLoan() != null) {
            Loan loan = person.getLoan();

            loanAmountLabel.setText("Loan Amount: $" + loan.getLoanAmount());
            loanAmountLabel.setVisible(true);
            loanPaidLabel.setText("Amount paid: $" + loan.getPaidAmount());
            loanPaidLabel.setVisible(true);
            accountChoiceLabel.setVisible(true);
            paymentAmountTF.setText("");
            paymentAmountTF.setVisible(true);
            paymentAmountLabel.setVisible(true);
            accountCB.removeAllItems();
            accMap = AccountManager.getAccMap(person);
            for (String a : AccountManager.getAccKeys(accMap)) {
                accountCB.addItem(a);
            }
            accountCB.setVisible(true);

            loanAmountTF.setVisible(false);
            termsCB.setVisible(false);

            submitBtn.setVisible(true);
            errorLabel.setVisible(false);
        } else {
            loanAmountLabel.setText("Enter loan amount:");
            loanAmountLabel.setVisible(true);
            loanAmountTF.setText("");
            loanAmountTF.setVisible(true);
            termsCB.setVisible(true);

            loanPaidLabel.setVisible(false);
            paymentAmountTF.setVisible(false);
            paymentAmountLabel.setVisible(false);
            accountChoiceLabel.setVisible(false);
            accountCB.setVisible(false);

            submitBtn.setVisible(true);
            errorLabel.setVisible(false);
        }
    }

    private void updateOthers() {
        home.updateAccountsHome();
    }
}
