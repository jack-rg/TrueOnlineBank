package GUI;

import Objects.Account;
import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUIAccountsOverview creates the GUI that shows all the user's accounts.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
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
        for (Account a : accounts) {
            JButton aBtn = new JButton(a.toString());
            accountsPanel.add(aBtn);

            GUIAccount acc = new GUIAccount(a);
            panel.add(acc.getPanel(), a.getAccountID());

            acc.getGoBackButton().addActionListener(e -> cards.show(panel, "AccountPanel"));

            acc.getDeleteAccountButton().addActionListener(e -> {
                a.deactivate();
                accountsPanel.remove(aBtn);
                cards.show(panel, "AccountPanel");

                home.update();
            });

            aBtn.addActionListener(e -> cards.show(panel, a.getAccountID()));
        }

        panel.add(accountsPanel, "AccountPanel");
        cards.show(panel, "AccountPanel");
    }

    public JPanel getPanel() {
        return panel;
    }
}
