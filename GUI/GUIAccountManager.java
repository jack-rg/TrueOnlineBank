package GUI;

import Objects.Account;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIAccountManager {
    JPanel panel;

    public GUIAccountManager(Account account) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        JButton accountNameBtn = new JButton(account.toString());
        panel.add(accountNameBtn);

        accountNameBtn.addActionListener(e -> {
            GUIAccount a = new GUIAccount(account);
            a.hideButtons();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}