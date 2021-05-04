package Objects;

import Types.Status;
import Types.AccountType;
import Util.DataManager;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Person {
    private String userName;
    private String password;
    private String userID;
    private ArrayList<Account> accounts;
    private SecurityAccount securityAccount;
    private Loan loan;

    public Person(String userName, String password, String userID) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        accounts = new ArrayList<>();
        String accountID = "A" + userID.substring(1) + (this.accounts.size() + 1);
        securityAccount = new SecurityAccount(AccountType.SECURITY, accountID, userID);
        loan = null;
    }

    public Person(String userName, String password, String userID, Loan loan) {
        this(userName, password, userID);
        this.loan = loan;
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

    public boolean authenPassword(String checkPass) {
        return password.equals(checkPass);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String newID) {
        userID = newID;
    }

    public Loan getLoan() {
        if (loan == null || loan.getStatus() == Status.INACTIVE) {
            loan = DataManager.loadLoan(userID);
        }
        return loan;
    }

    public void setLoan(Loan loan) { this.loan = loan; }

    public ArrayList<Account> getRawAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Account> getActiveAccounts() {
        ArrayList<Account> active = new ArrayList<>();

        for (Account a : accounts) {
            if (a.getStatus() == Status.ACTIVE) {
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
            sum += a.getBalance();
        }
        return sum;
    }

    public boolean isRich() {
        for (Account a : accounts) {
            if ((a instanceof Saving) && (a.getBalance() >= 5000)) {
                return true;
            }
        }

        return false;
    }

    public void takeOutLoan(double loanAmount) {
        LocalDateTime date = LocalDateTime.now();
        Loan loan = new Loan(loanAmount, 0.00, date, userID, Status.ACTIVE);
        DataManager.writeLoan(loan, this);

        this.loan = loan;
    }

    public SecurityAccount getSecurityAccount() {
        return securityAccount;
    }


    public String toString() {
    	return userName + " - "+userID;
    	
    }

    /**
     * User pressed register button, create a Secrurity account
     * @return true. if create success
     */
    public boolean createSecurityAccountHandler() {
        String accountID = "A" + userID.substring(1) + (this.accounts.size() + 1);
        this.securityAccount = new SecurityAccount(AccountType.SECURITY, accountID, this.getUserID());
        return true;
    }

}