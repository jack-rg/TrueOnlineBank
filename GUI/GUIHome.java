package GUI;

import Objects.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;

    ArrayList<Account> accounts;

    public GUIHome(ArrayList<Account> accounts) {
        super(new GridLayout(1, 1));

        this.accounts = accounts;

        tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");

        GUIAccountsHome accountsHome = new GUIAccountsHome(accounts);
        JComponent panel1 = accountsHome.getPanel();
        tabbedPane.addTab("Accounts", icon, panel1, null);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Withdraw and Transfer", icon, panel2, null);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Invest", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = makeTextPanel("Panel #4");
        tabbedPane.addTab("Settings", icon, panel4,
                "blah blah");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
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

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        // java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        // if (imgURL != null) {
        //     return new ImageIcon(imgURL);
        // } else {
        //     System.err.println("Couldn't find file: " + path);
        //     return null;
        // }
        return null; // set to null for know, could change to show icons if we wanted
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
