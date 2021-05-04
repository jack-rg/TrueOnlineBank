package Objects;

public class User extends Person {

    public User(String userName, String password, String userID) {
        super(userName, password, userID);
    }

    public User(String userName, String password, String userID, Loan loan) {
        super(userName, password, userID, loan);
    }
}