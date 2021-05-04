package Objects;

public class Manager extends Person {

    public Manager(String userName, String password, String userID) {
        super(userName, password, userID);
    }

    public Manager(String userName, String password, String userID, Loan loan) {
        super(userName, password, userID, loan);
    }
}