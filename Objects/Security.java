package Objects;

/**
 * Several Security rules are:
 * 1. Only Manager has the authority to look throught the User Database / Logs
 * 2. Manager can only see a encrypted password
 *
 */
public class Security extends Account {


    public Security(String userName, String accountID, String userID) {
        super(userName, accountID, userID);
    }

    public Security(String userName, String accountID, String userID, double value) {
        super(userName, accountID, userID, value);
    }
}