package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Objects.Account;
import Objects.User;
import Util.DataManager;

/**
 * GUIUser creates the GUI that allows managers to see a single user's information.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIUser {
    JPanel panel;
    JButton goBackButton;
    CardLayout cards;

    GUIHome home;

    public GUIUser(User user, GUIHome home) {
        this.home = home;

        panel = new JPanel();
        cards = new CardLayout();
        panel.setLayout(new BorderLayout());

        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.2);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        topPanel.add(goBackButton);

        JLabel userNameLabel = new JLabel("Username: " + user.getUserName() +
                " - UserID: " + user.getUserID());
        userNameLabel.setBounds(125, 25, 400, 25);
        topPanel.add(userNameLabel);

        sp.add(topPanel);

        JPanel bottomPanel = new JPanel();

        ArrayList<Account> accounts = user.getRawAccounts();

        if (accounts.size() > 0) {
            JPanel accountsPanel = new JPanel();

            GUIAccountManager accManage = new GUIAccountManager(user, home);
            accountsPanel.add(accManage.getPanel());

            bottomPanel.add(new JScrollPane(accountsPanel));

            sp.add(bottomPanel);
        }

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }
}