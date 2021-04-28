package Objects;

import java.util.ArrayList;

import Interfaces.TransactionInterface;
import Types.AccountState;
import Types.TransactionType;
import Util.DataManager;

public abstract class Account implements TransactionInterface {
    private String accountID;
    private String userID;
    private double value;
    private String name;
    private AccountState status;

    private ArrayList<Transaction> transactions;

    public Account(String userName, String accountID, String userID) {
        this.name = userName;
        this.accountID = accountID;
        this.userID = userID;
        value = 0;
        status = AccountState.ACTIVE;
    }

    public Account(String userName, String accountID, String userID, double value) {
        this(userName, accountID, userID);
        this.value = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(int newValue) {
        value = newValue;
    }

    public ArrayList<Transaction> getTransactions() { return transactions; }

    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }

    public void deposit(float funds, String name) {
        Transaction transaction = new Transaction(name, funds, TransactionType.DEPOSIT);
        DataManager.writeTransaction(transaction, userID, accountID);

        value += funds;
    }

    public boolean withdrawal(float funds, String name) {
        if (value - funds >= 0) {
            Transaction transaction = new Transaction(name, funds, TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(transaction, userID, accountID);

            value -= funds;
            return true;
        } else {
            return false;
        }
    }
}
