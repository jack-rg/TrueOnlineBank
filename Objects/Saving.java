package Objects;

import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;

public class Saving extends Account {
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
}