import GUI.GUIBank;
import Objects.*;

public class BankATM {

    public BankATM() {
        //Run user interface
        GUIBank.run();

//        User jack = new User("Jack", "pass", "U1");
//        User amanda = new User("Amanda", "pass2", "U2");
//        jack.addNewAccount(new Saving("Saving", "A1", "U1", 121.0));
//        jack.addNewAccount(new Checking("Checking", "A2", "U1", 2132.0));
//        amanda.addNewAccount(new Saving("Saving", "A1", "U2", 2122.0));
//        amanda.addNewAccount(new Checking("Checking", "A2", "U2", 0.0));
//        amanda.getAccountByID("A2").deactivateAccount();
    }

    public static void main(String args[]) {
        new BankATM();
    }
}