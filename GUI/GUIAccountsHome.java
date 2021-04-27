package GUI;

import Objects.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIAccountsHome {
    JPanel panel;

    public GUIAccountsHome(ArrayList<Account> accounts) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(accounts.size(), 1));
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            panel.add(new JButton(a.getName() + ": $" + a.getValue()));
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
