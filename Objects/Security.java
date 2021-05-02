package Objects;

import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;

public class Security extends Account {
    public Security(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        super(accountType, accountID, userID, currencyType);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState accountState) {
        super(accountType, accountID, userID, currencyType, accountState);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status, float value) {
        super(accountType, accountID, userID, currencyType, status, value);
    }
}