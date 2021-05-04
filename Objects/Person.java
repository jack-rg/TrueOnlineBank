package Objects;

import Types.Status;
import Util.DataManager;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Person represents a person that can interact at the Bank.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public abstract class Person {
    private final String userID;
    private String userName;
    private String password;
    private ArrayList<Account> accounts;
    private Loan loan;

    public Person(String userName, String password, String userID) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        accounts = new ArrayList<>();
        loan = null;
    }

    public String getUserName() {
        return userName;
    }

    public boolean setUserName(String newName) {
        if (DataManager.updatePerson(this, newName, null)) {
            userName = newName;
            return true;
        }

        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPass) {
        password = newPass;
        DataManager.updatePerson(this, null, newPass);
    }

    public String getUserID() {
        return userID;
    }

    public Loan getLoan() {
        if (loan == null || loan.getStatus() == Status.INACTIVE) {
            loan = DataManager.loadLoan(userID);
        }
        return loan;
    }

    public ArrayList<Account> getRawAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Gets all active accounts.
     *
     * @return active accounts associated with the Person
     */
    public ArrayList<Account> getActiveAccounts() {
        ArrayList<Account> active = new ArrayList<>();

        for (Account a : accounts) {
            if (a.getStatus() == Status.ACTIVE && !(a instanceof Security)) {
                active.add(a);
            }
        }

        return active;
    }

    /**
     * Allows people to make new accounts.
     *
     * @param newAccount the person's new account
     */
    public void addNewAccount(Account newAccount) {
        DataManager.writeAccount(newAccount);
        accounts.add(newAccount);
    }

    /**
     * Gets the total balance across all the person's accounts.
     *
     * @return the total balance across all the person's accounts.
     */
    public double getTotalAssets() {
        double sum = 0;
        for (Account a : accounts) {
            sum += a.getBalance();
        }
        return sum;
    }

    /**
     * Determines whether the person is rich.
     *
     * @return true if at least one account has a balance of over 5000, false otherwise
     */
    public boolean isRich() {
        for (Account a : accounts) {
            if ((a instanceof Saving) && (a.getBalance() >= 5000)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Allows people to take out loans
     *
     * @param loanAmount the amount of the loan.
     */
    public void takeOutLoan(double loanAmount) {
        LocalDateTime date = LocalDateTime.now();
        Loan loan = new Loan(loanAmount, 0.00, date, userID, Status.ACTIVE);
        DataManager.writeLoan(loan, this);

        this.loan = loan;
    }

    /**
     * Gets the person's security account.
     *
     * @return the security account if the user has one, null otherwise
     */
    public Security getSecurityAccount() {
        for (Account a : accounts) {
            if (a instanceof Security) {
                return (Security) a;
            }
        }
        return null;
    }

    public String toString() {
    	return userName + " - " + userID;
    }
}