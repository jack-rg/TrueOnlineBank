package GUI;

import Objects.Manager;
import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;
    JButton logOutBtn;

    GUIAccountsHome accountsHome;
    GUILoan loans;

    public GUIHome(Person person) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();

        accountsHome = new GUIAccountsHome(person);
        tabbedPane.addTab("Accounts", accountsHome.getTabbedPane());

        loans = new GUILoan(person);
        tabbedPane.addTab("Loans", loans.getPanel());

        if (person.isRich()) {
            GUIInvestmentHome investmentHome = new GUIInvestmentHome(person);
            tabbedPane.addTab("Invest", investmentHome.getPanel());
        }

        GUISettings settings = new GUISettings(person);
        tabbedPane.addTab("Settings", settings.getPanel());

        logOutBtn = settings.getLogOutBtn();

        if (person instanceof Manager) {
            JComponent panel4 = makeTextPanel("Panel #5");
            tabbedPane.addTab("Manage", panel4);

            JComponent panel5 = makeTextPanel("Panel #6");
            tabbedPane.addTab("Update Stocks", panel5);
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
