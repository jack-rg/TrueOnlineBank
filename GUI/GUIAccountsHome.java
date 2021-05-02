package GUI;

import Objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUIAccountsHome extends JPanel {
    JTabbedPane tabbedPane;

    public GUIAccountsHome(User user) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        GUIAccountsOverview accountsOverview = new GUIAccountsOverview(user);
        tabbedPane.addTab("Overview", accountsOverview.getPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        GUINewAccount newAccount = new GUINewAccount(user, accountsOverview, tabbedPane);
        tabbedPane.addTab("Create New Account", newAccount.getPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        GUIDeposit deposit = new GUIDeposit(user, accountsOverview);
        deposit.getSubmitBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
            }
        });
        tabbedPane.addTab("Deposit", deposit.getPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        GUIWithdraw withdraw = new GUIWithdraw(user, accountsOverview, tabbedPane);
        tabbedPane.addTab("Withdraw", withdraw.getPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        add(tabbedPane);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
