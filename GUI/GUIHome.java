package GUI;

import Objects.Manager;
import Objects.Person;

import javax.swing.*;
import java.awt.*;

/**
 * GUIHome creates the GUI that allows users to view accounts,
 * take out loans, invest, and manage accounts (if user is a manager).
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;
    JButton logOutBtn;

    GUIAccountsHome accountsHome;
    GUILoan loans;
    GUIInvestmentHome investmentHome;
    GUIUsersOverview usersOverview;
    GUIDailyTransactions dailyTransactions;
    GUIStockManager updateStocks;

    Person person;

    public GUIHome(Person person) {
        this.person = person;

        tabbedPane = new JTabbedPane();

        accountsHome = new GUIAccountsHome(person, this);
        tabbedPane.addTab("Accounts", accountsHome.getTabbedPane());

        loans = new GUILoan(person, this);
        tabbedPane.addTab("Loans", loans.getPanel());

        if (person.isRich()) {
            investmentHome = new GUIInvestmentHome(person);
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

            updateStocks = new GUIStockManager(this);
            tabbedPane.addTab("Update Stocks", updateStocks.getPanel());
        }

        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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

    public void updateInvestments() {
        if (person.isRich()) {
//            investmentHome.update();
        }
    }

    public void updateDailyTransactions() {
        dailyTransactions.update();
    }
}
