package Objects;

public class Security extends Account {

    public Security(String userName, String accountID, String userID) {
        super(userName, accountID, userID);
    }

    public Security(String userName, String accountID, String userID, double value) {
        super(userName, accountID, userID, value);
    }
}