package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;

public class GUILoan extends JPanel {
    JPanel panel;
    JTextField loanAmountTF;
    JLabel loanAmountLabel, errorLabel;
    JButton submitBtn;

    public GUILoan(Person person) {
        panel = new JPanel();
        panel.setLayout(null);

        loanAmountLabel = new JLabel("Enter loan amount:");
        loanAmountLabel.setBounds(30, 50, 400, 25);
        loanAmountLabel.setVisible(false);
        panel.add(loanAmountLabel);

        loanAmountTF = new JTextField(20);
        loanAmountTF.setBounds(250, 170, 165, 25);
        loanAmountTF.setVisible(false);
        panel.add(loanAmountTF);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(30, 370, 280, 40);

        errorLabel = new JLabel();
        errorLabel.setBounds(30, 410, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);

        if (person.getLoan() != null) {

        } else {
            loanAmountLabel.setVisible(true);
            loanAmountTF.setVisible(true);
            submitBtn.setVisible(true);

            submitBtn.addActionListener(e -> {
                if (person.getTotalAssets() <= 0) {
                    errorLabel.setText("You do not have enough collateral to take out a loan.");
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
