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
    public Saving(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status) {
        super(accountType, accountID, userID, currencyType, status);
    }

    public Saving(AccountType accountType, String accountID, String userID, double d, CurrencyType currencyType, AccountState status) {
        super(accountType, accountID, userID, currencyType, d, status);
    }
}