package GUI;

import Objects.Account;
import Objects.Person;
import Objects.Saving;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIAccountManager {
    JPanel panel, accountsPanel;
    CardLayout cards;
    Person user;

    GUIHome home;

    public GUIAccountManager(Person person, GUIHome home) {
        this.home = home;

        panel = new JPanel();
        cards = new CardLayout();
        panel.setLayout(cards);

        this.user = person;

        update();
    }

    public void update() {
        panel.removeAll();

        ArrayList<Account> accounts = user.getRawAccounts();

        accountsPanel = new JPanel();
        accountsPanel.setLayout(new GridLayout(accounts.size(), 1));
        for (Account a : accounts) {
            JButton aBtn = new JButton(a.toString());
            accountsPanel.add(aBtn);

            GUIAccount acc = new GUIAccount(a, home);
            acc.hideButton();
            if ((a instanceof Saving) && a.getBalance() >= 10000) {
                acc.showInterest();
            }
            panel.add(acc.getPanel(), a.getAccountID());

            acc.getGoBackButton().addActionListener(e -> cards.show(panel, "AccountPanel"));

            aBtn.addActionListener(e -> cards.show(panel, a.getAccountID()));
        }

        panel.add(accountsPanel, "AccountPanel");
        cards.show(panel, "AccountPanel");
    }

    public JPanel getPanel() {
        return panel;
    }
}