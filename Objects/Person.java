package Objects;

import Types.AccountState;
import Util.DataManager;

import java.util.*;

public abstract class Person {
    private String userName;
    private String password;
    private String userID;
    private ArrayList<Account> accounts;
    private Portfolio portfolio;

    public Person(String userName, String password, String userID) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        accounts = new ArrayList<Account>();
        portfolio = new Portfolio();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String newName) {
        userName = newName;
        DataManager.updatePerson(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPass) {
        password = newPass;
        DataManager.updatePerson(this);
    }

    public boolean authenPassword(String checkPass) {
        return password.equals(checkPass);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String newID) { userID = newID; }

    public ArrayList<Account> getRawAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) { this.accounts = accounts; }

    public ArrayList<Account> getActiveAccounts() {
        ArrayList<Account> active = new ArrayList<Account>();

        for (Account a : accounts) {
            if (a.getStatus() == AccountState.ACTIVE) {
                active.add(a);
            }
        }

        return active;
    }

    public void addNewAccount(Account newAccount) {
        DataManager.writeAccount(newAccount);
        accounts.add(newAccount);
    }

    public Account getAccountByID(String getID) {
        for (Account a : accounts) {
            if (a.getAccountID().equals(getID)) {
                return a;
            }
        }
        return null;
    }

    public double getTotalAssets() {
        double sum = 0;
        for (Account a : accounts) {
            sum += a.getValue();
        }
        return sum;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}