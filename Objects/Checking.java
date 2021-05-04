package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;

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

    public boolean transferTo(String accountID, double amount) {
        withdraw(TRANSACTION_FEE, "TRANSACTION FEE", false);

        return super.transferTo(accountID, amount);
    }
}