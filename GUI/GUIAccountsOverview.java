package GUI;

import Objects.Account;
import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIAccountsOverview {
    JPanel panel, accountsPanel;
    CardLayout cards;
    Person user;

    GUIAccountsHome home;

    public GUIAccountsOverview(Person person, GUIAccountsHome home) {
        this.home = home;

        panel = new JPanel();
        cards = new CardLayout();
        panel.setLayout(cards);

        this.user = person;

        update();
    }

    public void update() {
        panel.removeAll();

        ArrayList<Account> accounts = user.getActiveAccounts();

        accountsPanel = new JPanel();
        accountsPanel.setLayout(new GridLayout(accounts.size(), 1));
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            JButton aBtn = new JButton(a.toString());
            accountsPanel.add(aBtn);

            GUIAccount acc = new GUIAccount(a);
            panel.add(acc.getPanel(), a.getAccountID());

            acc.getGoBackButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cards.show(panel, "AccountPanel");
                }
            });

            acc.getDeleteAccountButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    accountsPanel.remove(aBtn);
                    cards.show(panel, "AccountPanel");

                    home.updateAll();
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
