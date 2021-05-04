package Objects;

import java.util.ArrayList;

import Interfaces.TransactionInterface;
import Types.Status;
import Types.AccountType;
import Types.CurrencyType;
import Types.TransactionType;
import Util.CurrencyConverter;
import Util.DataManager;

public abstract class Account implements TransactionInterface {
    protected static final double OPENING_FEE = 25.00;
    protected static final double CLOSING_FEE = 15.00;
    protected static final double TRANSACTION_FEE = 5.00;

    protected String accountID;
    private String userID;
    protected double balance;
    private AccountType accountType;
    private Status status;
    private CurrencyType currencyType;

    private ArrayList<Transaction> transactions;

    public Account(AccountType accountType, String accountID, String userID) {
        this.accountType = accountType;
        this.accountID = accountID;
        this.userID = userID;
        balance = 0;
        status = Status.ACTIVE;
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

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status) {
        this(accountType, accountID, userID, currencyType);
        this.status = status;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status, double balance) {
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

    public Status getStatus() {
        return status;
    }

    public void deposit(double funds, String name, CurrencyType cType, boolean chargeFee) {
        double convertedFunds = CurrencyConverter.execute(cType, currencyType, funds);

        if (chargeFee) {
            Transaction withdrawalFee = new Transaction("DEPOSIT FEE", TRANSACTION_FEE, TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(withdrawalFee, userID, accountID);
            balance -= TRANSACTION_FEE;
        }

        Transaction transaction = new Transaction(name, convertedFunds, TransactionType.DEPOSIT);
        DataManager.writeTransaction(transaction, userID, accountID);
        balance += funds;

        DataManager.updateAccount(this);
    }

    public boolean withdraw(double funds, String name, boolean chargeFee) {
        if (chargeFee) {
            Transaction withdrawalFee = new Transaction("WITHDRAWAL FEE", TRANSACTION_FEE, TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(withdrawalFee, userID, accountID);
            balance -= TRANSACTION_FEE;
        }

        Transaction transaction = new Transaction(name, funds, TransactionType.WITHDRAWAL);
        DataManager.writeTransaction(transaction, userID, accountID);
        balance -= funds;

        DataManager.updateAccount(this);
        return true;
    }

    public boolean transferTo(Account account, double amount) {
        withdraw(amount, "Transfer to " + account.getAccountID(), true);
        account.deposit(amount, "Transfer from " + accountID, currencyType, true);
        return true;
    }

    public boolean transferTo(String accountID, double amount) {
        Account account = DataManager.loadAccount(accountID);
        if (account != null) {
            return !transferTo(account, amount);
        }

        return true;
    }

    public boolean transferToBank(double amount, String name) {
        Account account = DataManager.loadAccount("A01");

        if (account != null) {
            if (withdraw(amount, name, false)) {
                account.deposit(amount, name + " from " + accountID, currencyType, false);
                return true;
            }
        }

        return false;
    }

    public void chargeOpeningFee() {
        transferToBank(OPENING_FEE, "OPENING FEE");
    }

    public void deactivate() {
        status = Status.INACTIVE;
        DataManager.deactivateAccount(this);

        transferToBank(CLOSING_FEE, "CLOSING FEE");
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
