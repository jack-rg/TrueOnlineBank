package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GUIAccountsHome extends JPanel {
    JTabbedPane tabbedPane;

    GUIAccountsOverview accountsOverview;
    GUINewAccount newAccount;
    GUIDeposit deposit;
    GUIWithdraw withdraw;
    GUITransfer transfer;

    public GUIAccountsHome(Person person) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        accountsOverview = new GUIAccountsOverview(person, this);
        tabbedPane.addTab("Overview", accountsOverview.getPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        newAccount = new GUINewAccount(person, this);
        tabbedPane.addTab("Create New Account", newAccount.getPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        deposit = new GUIDeposit(person, this);
        tabbedPane.addTab("Deposit", deposit.getPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        withdraw = new GUIWithdraw(person, this);
        tabbedPane.addTab("Withdraw", withdraw.getPanel());
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_3);

        transfer = new GUITransfer(person, this);
        tabbedPane.addTab("Transfer", transfer.getPanel());
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_3);

        add(tabbedPane);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void update() {
        accountsOverview.update();
        newAccount.update();
        deposit.update();
        withdraw.update();
        transfer.update();
        tabbedPane.setSelectedIndex(0);
    }
}
