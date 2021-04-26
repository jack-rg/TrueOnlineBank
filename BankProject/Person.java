import java.util.*;
public abstract class Person{
  private String userName;
  private String password;
  private int userID;
  private ArrayList<Account> accounts;
  private Portfolio portfolio;
  
  public Person(String userName, String password, int userID){
    this.userName = userName;
    this.password = password;
    this.userID = userID;
    accounts = new ArrayList<Account>();
    portfolio = new Portfolio();
  }
  
  public String getUserName(){
    return userName; 
  }
  
  public void setUserName(String newName){
    userName = newName;
  }
  
  public String getPassword(){
    return password;
  }
  
  public void setPassword(String newPass){
    password = newPass; 
  }
  
  public boolean authenPassword(String checkPass){
    return password.equals(checkPass); 
  }
  
  public int getUserID(){
    return userID;
  }
  
  public void setUserID(int newID){
    userID = newID; 
  }
  
  public ArrayList<Account> getRawAccounts(){
    return accounts; 
  }
  
  public void addNewAccount(Account newAccount){
    accounts.add(newAccount); 
  }
  
  public Account getAccountByID(int getID){
    for(Account a: accounts){
      if(a.getAccountID() == getID){
        return a; 
      }
    }
    return null;
  } 
  
  public double getTotalAssets(){
    double sum = 0;
    for(Account a: accounts){
      sum += a.getValue();
    }
    return sum;
  }
  
  public Portfolio getPortfolio(){
    return portfolio;
  }
    
  
}