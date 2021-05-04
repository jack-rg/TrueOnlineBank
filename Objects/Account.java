package Objects;

import java.util.ArrayList;

import Interfaces.TransactionInterface;
import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;
import Types.TransactionType;
import Util.CurrencyConverter;
import Util.DataManager;

public abstract class Account implements TransactionInterface {
    protected static final double TRANSACTION_FEE = 5.00;

    private String accountID;
    private String userID;
    protected double balance;
    private AccountType accountType;
    private AccountState status;
    private CurrencyType currencyType;

    private ArrayList<Transaction> transactions;

    public Account(AccountType accountType, String accountID, String userID) {
        this.accountType = accountType;
        this.accountID = accountID;
        this.userID = userID;
        balance = 0;
        status = AccountState.ACTIVE;
        currencyType = CurrencyType.USD;
    }

    public Account(AccountType userName, String accountID, String userID, double balance) {
        this(userName, accountID, userID);
        this.balance = balance;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        this(accountType, accountID, userID);
        this.currencyType = currencyType;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status) {
        this(accountType, accountID, userID, currencyType);
        this.status = status;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status, double balance) {
        this(accountType, accountID, userID, currencyType, status);
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public AccountState getStatus() {
        return status;
    }

    public void deposit(double funds, String name, CurrencyType cType) {
        double convertedFunds = CurrencyConverter.execute(cType, currencyType, funds);

        Transaction transaction = new Transaction(name, convertedFunds, TransactionType.DEPOSIT);
        DataManager.writeTransaction(transaction, userID, accountID);

        balance += funds;
        DataManager.updateAccount(this);
    }

    public boolean withdraw(double funds, String name) {
        if (balance - funds >= 0) {
            Transaction transaction = new Transaction(name, funds, TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(transaction, userID, accountID);

            Transaction withdrawalFee = new Transaction("WITHDRAWAL FEE", TRANSACTION_FEE, TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(withdrawalFee, userID, accountID);

            balance -= funds - TRANSACTION_FEE;
            DataManager.updateAccount(this);
            return true;
        } else {
            return false;
        }
    }

    public boolean transferTo(Account account, double amount) {
        withdraw(amount, "Transfer to " + account.getAccountID());
        account.deposit(amount, "Transfer from " + accountID, currencyType);
        return true;
    }

    public boolean transferTo(String accountID, double amount) {
        Account account = DataManager.loadAccount(accountID);
        if (account != null) {
            return transferTo(account, amount);
        }

        return false;
    }

    public void deactivate() {
        status = AccountState.INACTIVE;
        DataManager.deactivateAccount(this);
    }

    public String toString() {
        String moneySign;
        switch (currencyType) {
            case EUR:
                moneySign = "€";
                break;
            case INR:
                moneySign = "₹";
                break;
            case GBP:
                moneySign = "£";
                break;
            default:
                moneySign = "$";
                break;
        }

        return "(" + accountID + ") " + accountType + ": " + moneySign + balance;
    }
}
