package Objects;

import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;

public class Checking extends Account {
	public Checking(AccountType accountType, String accountID, String userID) {
		super(accountType, accountID, userID);
	}

	public Checking(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
		super(accountType, accountID, userID, currencyType);
	}

	public Checking(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState accountState) {
		super(accountType, accountID, userID, currencyType, accountState);
	}

	public Checking(AccountType accountType, String accountID, String userID, CurrencyType currencyType, AccountState status, double value) {
		super(accountType, accountID, userID, currencyType, status, value);
	}
}