import GUI.GUIBank;
import Objects.*;

public class BankATM{
  
  public BankATM(){
    //Run user interface
    GUIBank.run();
//	  User jack = new User("Jack", "pass", 001);
//	  User amanda = new User("Amanda", "pass2", 002);
//	  jack.addNewAccount(new Saving("Jack Saving", 01, 121));
//	  jack.addNewAccount(new Checking("Jack Checking", 02, 2132));
//	  amanda.addNewAccount(new Saving("Amanda Saving", 03, 2122));
//	 amanda.addNewAccount(new Checking("Amanda Checking", 04, 0));
//	  amanda.getAccountByID(03).deactivateAccount();
  }

  public static void main(String args[]){
    new BankATM();
  }
}