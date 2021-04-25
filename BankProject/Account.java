public abstract class Account{
  private int accountID;
  private double value;
  
  
  public Account(int accountID){
   this.accountID = accountID;
   value = 0;
  }

  public Account(int accountID, double value){
   this.accountID = accountID;
   this.value = value;
  }
  
  public int getAccountID(){
   return accountID; 
  }
  
  public void setAccountID(int newID){
   accountID = newID; 
  }
  
  public double getValue(){
   return value; 
  }
  
  public void setValue(int newValue){
   value = newValue; 
  }
  
  public void deposit(int funds){
    value += funds;
  }
  
  public int withdrawl(int funds){
    try{
    value -= funds;
    return funds;
    }
    catch(Exception e){
     return -1; 
    }
  }
  
  
  
}