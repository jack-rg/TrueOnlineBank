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

	public Checking(AccountType accountType, String accountID, String userID, double value, CurrencyType currencyType, AccountState status) {
		super(accountType, accountID, userID, currencyType, value, status);
	}
}