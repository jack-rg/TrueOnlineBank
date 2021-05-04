package GUI;

import Objects.Account;
import Objects.Loan;
import Objects.Person;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * GUILoan creates the GUI that allows users to manage their loans.
 * Our implementation only allows users to take out one loan at a time.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUILoan extends JPanel {
    JPanel panel;
    JTextField loanAmountTF, paymentAmountTF;
    JLabel originalLoanAmountLabel, loanBalanceLabel, errorLabel, loanPaidLabel,
            accountChoiceLabel, paymentAmountLabel;
    JButton submitBtn;
    JCheckBox termsCB, interestTermsCB;
    JComboBox<String> accountCB;

    Person person;
    GUIHome home;

    HashMap<String, Account> accMap;

    public GUILoan(Person person, GUIHome home) {
        this.person = person;
        this.home = home;

        panel = new JPanel();
        panel.setLayout(null);

        originalLoanAmountLabel = new JLabel();
        originalLoanAmountLabel.setBounds(30, 50, 400, 25);
        originalLoanAmountLabel.setVisible(false);
        panel.add(originalLoanAmountLabel);

        loanBalanceLabel = new JLabel();
        loanBalanceLabel.setBounds(30, 90, 400, 25);
        loanBalanceLabel.setVisible(false);
        panel.add(loanBalanceLabel);

        loanAmountTF = new JTextField(20);
        loanAmountTF.setBounds(150, 50, 165, 25);
        loanAmountTF.setVisible(false);
        panel.add(loanAmountTF);

        loanPaidLabel = new JLabel();
        loanPaidLabel.setBounds(30, 130, 400, 25);
        loanPaidLabel.setVisible(false);
        panel.add(loanPaidLabel);

        termsCB = new JCheckBox("<html>I understand that I must pay back this loan within"
                + "<br>30 days or the bank will confiscate 50% of my assets.");
        termsCB.setBounds(30, 90, 400, 70);
        termsCB.setVisible(false);
        panel.add(termsCB);

        interestTermsCB = new JCheckBox("<html>I understand that this loan is being taken"
                + "<br>out with an interest rate of 1% per day.");
        interestTermsCB.setBounds(30, 150, 400, 70);
        interestTermsCB.setVisible(false);
        panel.add(interestTermsCB);

        accountChoiceLabel = new JLabel("Please choose an account to pay from:");
        accountChoiceLabel.setBounds(30, 170, 400, 25);
        accountChoiceLabel.setVisible(false);
        panel.add(accountChoiceLabel);

        accMap = AccountManager.getAccMap(person);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(30, 200, 300, 25);
        accountCB.setVisible(false);
        panel.add(accountCB);

        paymentAmountLabel = new JLabel("Please enter the payment amount:");
        paymentAmountLabel.setBounds(30, 240, 400, 25);
        paymentAmountLabel.setVisible(false);
        panel.add(paymentAmountLabel);

        paymentAmountTF = new JTextField(20);
        paymentAmountTF.setBounds(270, 240, 165, 25);
        paymentAmountTF.setVisible(false);
        panel.add(paymentAmountTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 300, 280, 40);
        submitBtn.setVisible(false);
        panel.add(submitBtn);

        errorLabel = new JLabel();
        errorLabel.setBounds(30, 400, 500, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        update();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void update() {
        if (person.getLoan() != null) {
            Loan loan = person.getLoan();

            originalLoanAmountLabel.setText("Original Loan Amount: $" + loan.getLoanAmount());
            originalLoanAmountLabel.setVisible(true);
            loanBalanceLabel.setText("Loan Balance (including interest): $" + loan.getLoanBalance());
            loanBalanceLabel.setVisible(true);
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
            interestTermsCB.setVisible(false);

            removeSubmitBtnListeners();
            submitBtn.addActionListener(e -> {
                try {
                    double paymentAmount = Double.parseDouble(paymentAmountTF.getText());
                    if (paymentAmount > person.getLoan().getLoanBalance() || paymentAmount < 1) {
                        errorLabel.setText("Please enter a payment amount between $1 and $" +
                                person.getLoan().getLoanBalance());
                        errorLabel.setVisible(true);
                        return;
                    }

                    Account account = accMap.get(accountCB.getSelectedItem());

                    if (account.transferToBank(paymentAmount, "Loan Payment")) {
                        person.getLoan().makePayment(paymentAmount);

                        update();
                        updateOthers();
                    } else {
                        errorLabel.setText("Insufficient funds.");
                        errorLabel.setVisible(true);
                    }
                } catch (Exception exception) {
                    errorLabel.setText("Please enter a valid payment amount.");
                    errorLabel.setVisible(true);
                }
            });
        } else {
            originalLoanAmountLabel.setText("Enter loan amount:");
            originalLoanAmountLabel.setVisible(true);
            loanBalanceLabel.setVisible(false);
            loanAmountTF.setText("");
            loanAmountTF.setVisible(true);
            termsCB.setSelected(false);
            termsCB.setVisible(true);
            interestTermsCB.setSelected(false);
            interestTermsCB.setVisible(true);

            loanPaidLabel.setVisible(false);
            paymentAmountTF.setVisible(false);
            paymentAmountLabel.setVisible(false);
            accountChoiceLabel.setVisible(false);
            accountCB.setVisible(false);

            removeSubmitBtnListeners();
            submitBtn.addActionListener(e -> {
                if (person.getTotalAssets() <= 0) {
                    errorLabel.setText("You do not have enough collateral to take out a loan.");
                    errorLabel.setVisible(true);
                    return;
                }

                if (!termsCB.isSelected() || !interestTermsCB.isSelected()) {
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
                }
            });
        }

        submitBtn.setVisible(true);
        errorLabel.setVisible(false);
    }

    private void updateOthers() {
        home.updateAccountsHome();
    }

    private void removeSubmitBtnListeners() {
        ActionListener[] listeners = submitBtn.getActionListeners();
        for (ActionListener listener : listeners) {
            submitBtn.removeActionListener(listener);
        }
    }
}