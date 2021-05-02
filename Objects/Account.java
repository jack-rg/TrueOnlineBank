package Objects;

import java.util.ArrayList;

import Interfaces.TransactionInterface;
import Types.AccountState;
import Types.TransactionType;
import Util.DataManager;

public abstract class Account implements TransactionInterface {
    private String accountID;
    private String userID;
    private double balance;
    private String name;
    private AccountState status;

    private ArrayList<Transaction> transactions;

    public Account(String userName, String accountID, String userID) {
        this.name = userName;
        this.accountID = accountID;
        this.userID = userID;
        balance = 0;
        status = AccountState.ACTIVE;
    }

    public Account(String userName, String accountID, String userID, double value) {
        this(userName, accountID, userID);
        this.balance = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String newID) {
        accountID = newID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String newID) {
        userID = newID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newValue) {
        balance = newValue;
    }

    public ArrayList<Transaction> getTransactions() { return transactions; }

    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }

    public void deposit(float funds, String name) {
        Transaction transaction = new Transaction(name, funds, TransactionType.DEPOSIT);
        DataManager.writeTransaction(transaction, userID, accountID);

        balance += funds;
    }

    public boolean withdrawal(float funds, String name) {
        if (balance - funds >= 0) {
            Transaction transaction = new Transaction(name, funds, TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(transaction, userID, accountID);

            balance -= funds;
            return true;
        } else {
            return false;
        }
    }
}
