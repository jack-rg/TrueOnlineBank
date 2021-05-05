package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;

/**
 * Checking extends Account to represent a Checking account.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Checking extends Account {
    public Checking(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
    }

    public Checking(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        super(accountType, accountID, userID, currencyType);
    }

    public Checking(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status accountStatus) {
        super(accountType, accountID, userID, currencyType, accountStatus);
    }

    public Checking(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status, double value) {
        super(accountType, accountID, userID, currencyType, status, value);
    }

    /**
     * Overrides the inherited transferTo method to ensure that each transfer charges a transaction fee.
     *
     * @param accountID the accountID to transfer to
     * @param amount    the amount to transfer
     * @return true if the transfer was successful, false otherwise
     */
    public boolean transferTo(String accountID, double amount) {
        withdraw(TRANSACTION_FEE, "TRANSACTION FEE", false);

        return super.transferTo(accountID, amount);
    }
}