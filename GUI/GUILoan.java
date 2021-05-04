package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;

public class GUILoan extends JPanel {
    JPanel panel;
    JTextField loanAmountTF;
    JLabel loanAmountLabel, errorLabel;
    JButton submitBtn;
    JCheckBox termsCB;

    public GUILoan(Person person) {
        panel = new JPanel();
        panel.setLayout(null);

        loanAmountLabel = new JLabel("Enter loan amount:");
        loanAmountLabel.setBounds(30, 50, 200, 25);
        loanAmountLabel.setVisible(false);
        panel.add(loanAmountLabel);

        loanAmountTF = new JTextField(20);
        loanAmountTF.setBounds(150, 50, 165, 25);
        loanAmountTF.setVisible(false);
        panel.add(loanAmountTF);

        termsCB = new JCheckBox("<html>I understand that I must pay back this loan within"
                + "<br>30 days or the bank will confiscate 50% of my assets.");
        termsCB.setBounds(30, 90, 400, 100);
        termsCB.setVisible(false);
        panel.add(termsCB);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 200, 280, 40);
        submitBtn.setVisible(false);
        panel.add(submitBtn);

        errorLabel = new JLabel();
        errorLabel.setBounds(30, 300, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        if (person.getLoan() != null) {

        } else {
            loanAmountLabel.setVisible(true);
            loanAmountTF.setVisible(true);
            termsCB.setVisible(true);
            submitBtn.setVisible(true);

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
                    System.out.println(loanAmount);
                    person.takeOutLoan(loanAmount);
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
}
