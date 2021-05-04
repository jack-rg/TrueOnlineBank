package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;

/**
 * Saving extends Account to represent a Savings account
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Saving extends Account {
    private static final double INTEREST_RATE = 0.03;

    public Saving(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        super(accountType, accountID, userID, currencyType);
    }

    public Saving(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status, double value) {
        super(accountType, accountID, userID, currencyType, status, value);
    }

    /**
     * Overrides the inherited method to only allow withdrawals that don't
     * take the savings account balance under 2500.
     *
     * @param funds the amount to withdraw
     * @param name the name of the withdrawal
     * @param chargeFee whether or not we should charge a fee
     * @return true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(double funds, String name, boolean chargeFee) {
        if (balance - funds >= 2500) {
            return super.withdraw(funds, name, chargeFee);
        } else {
            return false;
        }
    }

    /**
     * Allows the manager to charge interest on the account.
     */
    public void chargeInterest() {
        transferToBank(balance * INTEREST_RATE, "INTEREST");
    }
}