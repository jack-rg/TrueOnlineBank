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
    private String accountID;
    private String userID;
    private float value;
    private AccountType accountType;
    private AccountState status;
    private CurrencyType currencyType;

    private ArrayList<Transaction> transactions;

    public Account(AccountType accountType, String accountID, String userID) {
        this.accountType = accountType;
        this.accountID = accountID;
        this.userID = userID;
        value = 0;
        status = AccountState.ACTIVE;
        currencyType = CurrencyType.USD;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        this(accountType, accountID, userID);
        this.currencyType = currencyType;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status) {
        this(accountType, accountID, userID, currencyType);
        this.status = status;
    }

    public Account(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status, float value) {
        this(accountType, accountID, userID, currencyType, status);
        this.value = value;
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

    public float getValue() {
        return value;
    }

    public void setValue(float newValue) {
        value = newValue;
    }

    public CurrencyType getCurrencyType() { return currencyType; }

    public void setCurrencyType(CurrencyType currencyType) { this.currencyType = currencyType; }

    public ArrayList<Transaction> getTransactions() { return transactions; }

    public void setTransactions(ArrayList<Transaction> transactions) { this.transactions = transactions; }

    public AccountState getStatus() { return status; }

    public void deposit(float funds, String name, CurrencyType cType) {
        float convertedFunds = CurrencyConverter.execute(cType, currencyType, funds);

        Transaction transaction = new Transaction(name, convertedFunds, TransactionType.DEPOSIT);
        DataManager.writeTransaction(transaction, userID, accountID);

        value += convertedFunds;
        DataManager.updateAccount(this);
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

        return "(" + accountID + ") " + accountType + ": " + moneySign + value;
    }
}
