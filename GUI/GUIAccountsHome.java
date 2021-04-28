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
        CardLayout cards = new CardLayout();
        panel.setLayout(cards);

        JPanel accountsPanel = new JPanel();
        accountsPanel.setLayout(new GridLayout(accounts.size(), 1));
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            JButton aBtn = new JButton(a.getName() + ": $" + a.getValue());
            accountsPanel.add(aBtn);

            GUIAccount acc = new GUIAccount(a);
            panel.add(acc.getPanel(), a.getAccountID());

            acc.getGoBackButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cards.show(panel, "AccountPanel");
                }
            });

            aBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cards.show(panel, a.getAccountID());
                }
            });
        }

        panel.add(accountsPanel, "AccountPanel");
        cards.show(panel, "AccountPanel");
    }

    public JPanel getPanel() {
        return panel;
    }
}
