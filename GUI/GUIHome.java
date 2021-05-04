package GUI;

import Objects.Manager;
import Objects.Person;

import javax.swing.*;
import java.awt.*;

public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;
    JButton logOutBtn;

    GUIAccountsHome accountsHome;
    GUILoan loans;
    GUIUsersOverview usersOverview;
    GUIDailyTransactions dailyTransactions;

    public GUIHome(Person person) {
        tabbedPane = new JTabbedPane();

        accountsHome = new GUIAccountsHome(person, this);
        tabbedPane.addTab("Accounts", accountsHome.getTabbedPane());

        loans = new GUILoan(person, this);
        tabbedPane.addTab("Loans", loans.getPanel());

        if (person.isRich()) {
            GUIInvestmentHome investmentHome = new GUIInvestmentHome(person);
            tabbedPane.addTab("Invest", investmentHome.getTabbedPane());
        }

        GUISettings settings = new GUISettings(person);
        tabbedPane.addTab("Settings", settings.getPanel());

        logOutBtn = settings.getLogOutBtn();

        if (person instanceof Manager) {
            usersOverview = new GUIUsersOverview(this);
            tabbedPane.addTab("View Users", usersOverview.getPanel());

            dailyTransactions = new GUIDailyTransactions(this);
            tabbedPane.addTab("Daily Transactions", dailyTransactions.getPanel());

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

    public void updateAccountsHome() {
        accountsHome.update();
    }

    public void updateLoans() {
        loans.update();
    }

    public void updateDailyTransactions() {
        dailyTransactions.update();
    }
}
