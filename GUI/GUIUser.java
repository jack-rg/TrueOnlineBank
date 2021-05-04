package GUI;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Objects.Account;
import Objects.User;
import Util.DataManager;

public class GUIUser {
    JPanel panel;
    JButton goBackButton;
    CardLayout cards;

    public GUIUser(User user) {
        panel = new JPanel();
        cards = new CardLayout();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);

        goBackButton = new JButton("Go back");
        goBackButton.setBounds(25, 25, 80, 40);
        topPanel.add(goBackButton);

        JLabel userNameLabel = new JLabel("Username: " + user.getUserName() + " - UserID: "+user.getUserID());
        userNameLabel.setBounds(30, 80, 400, 25);
        topPanel.add(userNameLabel);

        panel.add(topPanel);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(cards);

        
        DataManager.loadAccounts(user);
        ArrayList<Account> accounts = user.getRawAccounts();

        if (accounts.size() > 0) {
            JPanel accountsPanel = new JPanel();
            accountsPanel.setLayout(new GridLayout(accounts.size(), 1));

            	GUIAccountManager accManage = new GUIAccountManager(user);
                accountsPanel.add(accManage.getPanel());
            
            
            
            bottomPanel.add(new JScrollPane(accountsPanel));
            
            panel.add(bottomPanel);
        }

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

}