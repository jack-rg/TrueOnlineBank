package GUI;

import Objects.Account;
import Objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;

    ArrayList<Account> accounts;

    public GUIHome(User user) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();

        GUIAccountsHome accountsHome = new GUIAccountsHome(user);
        tabbedPane.addTab("Accounts", accountsHome.getTabbedPane());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Invest", panel3);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_3);

        GUISettings settings = new GUISettings(user);
        tabbedPane.addTab("Settings", settings.getPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_4);

        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
