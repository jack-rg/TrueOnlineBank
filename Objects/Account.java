package Objects;

import java.util.ArrayList;

import Interfaces.TransactionInterface;
import Types.Status;
import Types.AccountType;
import Types.CurrencyType;
import Types.TransactionType;
import Util.CurrencyConverter;
import Util.DataManager;

/**
 * Account implements the TransactionInterface and provides a base for all types of accounts.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public abstract class Account implements TransactionInterface {
    protected static final double OPENING_FEE = 25.00;
    protected static final double CLOSING_FEE = 15.00;
    protected static final double TRANSACTION_FEE = 5.00;

    private final String userID;
    private final AccountType accountType;
    protected String accountID;
    protected double balance;
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

    public Account(AccountType accountType, String accountID, String userID,
                   CurrencyType currencyType, Status status) {
        this(accountType, accountID, userID, currencyType);
        this.status = status;
    }

    public Account(AccountType accountType, String accountID, String userID,
                   CurrencyType currencyType, Status status, double balance) {
        this(accountType, accountID, userID, currencyType, status);
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getUserID() {
        return userID;
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

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Converts the funds from the given currency type to the account's currency type,
     * and then deposits those funds into the account.
     *
     * @param funds the amount to deposit
     * @param name the name of the deposit
     * @param cType the currency type of the deposit
     * @param chargeFee whether or not we should charge a fee
     */
    public void deposit(double funds, String name, CurrencyType cType, boolean chargeFee) {
        double convertedFunds = CurrencyConverter.execute(cType, currencyType, funds);

        if (chargeFee) {
            Transaction withdrawalFee = new Transaction("DEPOSIT FEE", TRANSACTION_FEE,
                    TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(withdrawalFee, userID, accountID);
            balance -= TRANSACTION_FEE;
        }

        Transaction transaction = new Transaction(name, convertedFunds, TransactionType.DEPOSIT);
        DataManager.writeTransaction(transaction, userID, accountID);
        balance += funds;

        DataManager.updateAccount(this);
    }

    /**
     * Withdraws the funds from this account.
     *
     * @param funds the amount to withdraw
     * @param name the name of the withdrawal
     * @param chargeFee whether or not we should charge a fee
     * @return true if able to withdraw, false otherwise
     */
    public boolean withdraw(double funds, String name, boolean chargeFee) {
        if (chargeFee) {
            Transaction withdrawalFee = new Transaction("WITHDRAWAL FEE", TRANSACTION_FEE,
                    TransactionType.WITHDRAWAL);
            DataManager.writeTransaction(withdrawalFee, userID, accountID);
            balance -= TRANSACTION_FEE;
        }

        Transaction transaction = new Transaction(name, funds, TransactionType.WITHDRAWAL);
        DataManager.writeTransaction(transaction, userID, accountID);
        balance -= funds;

        DataManager.updateAccount(this);
        return true;
    }

    /**
     * Transfers the amount to the given account
     *
     * @param account the account to transfer to
     * @param amount the amount to transfer to
     * @return true if the transfer was successful, false otherwise
     */
    public boolean transferTo(Account account, double amount) {
        withdraw(amount, "Transfer to " + account.getAccountID(), true);
        account.deposit(amount, "Transfer from " + accountID, currencyType, true);
        return true;
    }

    /**
     * Retrieves the account from the given accountID,
     * then transfers the amount to the retrieved account.
     *
     * @param accountID the accountID to transfer to
     * @param amount the amount to transfer
     * @return true if the transfer was successful, false otherwise
     */
    public boolean transferTo(String accountID, double amount) {
        Account account = DataManager.loadAccount(accountID);
        if (account != null) {
            return !transferTo(account, amount);
        }

        return true;
    }

    /**
     * Loads the manager's account, transfers the amount to the manager.
     *
     * @param amount the amount to transfer
     * @param name the name of the transfer
     * @return true if the transfer was successful, false otherwise
     */
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

    /**
     * Charges the opening fee
     */
    public void chargeOpeningFee() {
        transferToBank(OPENING_FEE, "OPENING FEE");
    }

    /**
     * Deactivates the account upon user's request.
     */
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
