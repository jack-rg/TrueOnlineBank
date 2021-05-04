package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;

public class Security extends Account {
    public Security(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        super(accountType, accountID, userID, currencyType);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status accountStatus) {
        super(accountType, accountID, userID, currencyType, accountStatus);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status, double value) {
        super(accountType, accountID, userID, currencyType, status, value);
    }
}