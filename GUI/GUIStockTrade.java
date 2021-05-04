package GUI;

import Objects.Person;
import Objects.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIStockTrade extends JPanel {
    JPanel panel;
    JSplitPane sp;

    JTextField loanAmountTF, paymentAmountTF;
    JLabel originalLoanAmountLabel, loanBalanceLabel, errorLabel, loanPaidLabel, accountChoiceLabel, paymentAmountLabel;
    JButton submitBtn;
    JCheckBox termsCB, interestTermsCB;
    JComboBox<String> accountCB;

    public GUIStockTrade(Person person) {
        ArrayList<Position> positions = person.getSecurityAccount().getPositions();

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4));
        submitBtn = new JButton();
        submitBtn.setText("??????");
        submitBtn.setVisible(true);
        panel.add(submitBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}
