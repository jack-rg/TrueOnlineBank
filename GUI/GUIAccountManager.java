package GUI;

import Objects.Account;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIAccountManager {
    JPanel panel;
    JButton goBackButton;

    public GUIAccountManager(Account account) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        topPanel.add(goBackButton);

        JLabel accountNameLabel = new JLabel(account.getAccountType() + ": $" + account.getBalance());
        accountNameLabel.setBounds(30, 80, 150, 25);
        topPanel.add(accountNameLabel);

        panel.add(topPanel);

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }
}