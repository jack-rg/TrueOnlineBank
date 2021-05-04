package Objects;

import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;
import Types.TransactionType;
import Util.DataManager;

public class Saving extends Account {
    private static final double INTEREST_RATE = 0.03;

    public Saving(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
    }

    public Saving(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        super(accountType, accountID, userID, currencyType);
    }

    public Saving(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState accountState) {
        super(accountType, accountID, userID, currencyType, accountState);
    }

    public Saving(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status, double value) {
        super(accountType, accountID, userID, currencyType, status, value);
    }

    public boolean withdraw(double funds, String name, boolean chargeFee) {
        if (balance - funds >= 2500) {
            return super.withdraw(funds, name, chargeFee);
        } else {
            return false;
        }
    }

    public void chargeInterest() {
        transferToBank(balance * INTEREST_RATE, "INTEREST");
    }
}