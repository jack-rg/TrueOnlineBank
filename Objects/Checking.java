package Objects;

public class Checking extends Account {

	public Checking(String userName, String accountID, String userID) {
		super(userName, accountID, userID);
	}

	public Checking(String userName, String accountID, String userID, double value) {
		super(userName, accountID, userID, value);
	}
}