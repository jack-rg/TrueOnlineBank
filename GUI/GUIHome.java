package GUI;

import Objects.Manager;
import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;
    JButton logOutBtn;

    public GUIHome(Person person) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();

        GUIAccountsHome accountsHome = new GUIAccountsHome(person);
        tabbedPane.addTab("Accounts", accountsHome.getTabbedPane());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        GUILoan loans = new GUILoan(person);
        tabbedPane.addTab("Loans", loans.getPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        GUIInvestmentHome investmentHome = new GUIInvestmentHome(person);
        tabbedPane.addTab("Invest", investmentHome.getPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        GUISettings settings = new GUISettings(person);
        tabbedPane.addTab("Settings", settings.getPanel());
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        logOutBtn = settings.getLogOutBtn();

        if (person instanceof Manager) {
            JComponent panel4 = makeTextPanel("Panel #5");
            tabbedPane.addTab("Manage", panel4);
            tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

            JComponent panel5 = makeTextPanel("Panel #6");
            tabbedPane.addTab("Update Stocks", panel5);
            tabbedPane.setMnemonicAt(5, KeyEvent.VK_6);
        }

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

    public JButton getLogOutBtn() {
        return logOutBtn;
    }
}
