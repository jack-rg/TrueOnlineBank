package Objects;


import Types.AccountType;
import Util.DataManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Key elements for a broker account are:
 *      1. ticker
 *      2. Num Of Stocks
 *      3. average Price
 *      4. current Price
 *      6. Unrealized P&L
 *      7. total Market Value
 */

public class SecurityAccount extends Account {

    protected static String INADEQUATE_BALANCE_WARN = "Sorry, you dont have enough balance ";
    protected static String CURRENT_BALANCE_INFO = "The current balance is: ";
    protected static String REQUEST_FINISH_INFO  = "Requested finished, position updated";
    protected static String EMPTY_POSITION_INFO = "You dont have andy position ";
    protected static String FAILED_REQUEST_INFO = "Request failed";

    private Map<String, StockPosition> stockName2position;

    public SecurityAccount(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
        this.stockName2position = new HashMap<>();
    }

    public SecurityAccount(AccountType accountType, String accountID, String userID, double value) {
        super(accountType, accountID, userID, value);
        this.stockName2position = new HashMap<>();
    }


    public void accountPositionsDisplay(){
        if(stockName2position == null || stockName2position.size() == 0){
            System.out.println(EMPTY_POSITION_INFO);
            return;
        }
        for(String stockName : stockName2position.keySet()){
            StockPosition position = stockName2position.get(stockName);
            position.positionInfoDisplay();
        }
        System.out.println("Current Balance: " + this.getBalance());
    }

    public Map<String, StockPosition> getStockName2position(){
        return this.stockName2position;
    }


    /**
     * buy or sell one specific stocks
     * @return true, if bill succeed.
     */
    public boolean updatePosition(Stock targetStock, boolean isBuyBill, int requestQuantity){
        String billType;
        boolean requestSucceed = false;
        String targetStockName = targetStock.getName();
        // check balance
        if(targetStock.getLastPrice() * requestQuantity > this.getBalance()){
            System.out.println(INADEQUATE_BALANCE_WARN + CURRENT_BALANCE_INFO + this.getBalance());
            return false;
        }
        // Not contains position already, create one
        if(!stockName2position.containsKey(targetStockName)){
            stockName2position.put(targetStockName, new StockPosition(targetStock, 0));
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");

        StockPosition targetPosition = stockName2position.get(targetStockName);

        if(isBuyBill){
            billType = "BUY";
            requestSucceed = targetPosition.addStock(targetStock, requestQuantity);
            // update balance
            this.setBalance(this.getBalance() - targetStock.getLastPrice() * requestQuantity);

        }
        else{
            billType = "SELL";
            requestSucceed = targetPosition.deductStock(targetStock, requestQuantity);
            // update balance
            this.setBalance(this.getBalance() + targetStock.getLastPrice() * requestQuantity);
        }
        if(!requestSucceed){
            System.out.println(FAILED_REQUEST_INFO);
            return false;
        }
        this.accountPositionsDisplay();

        StockOrder stockOrder = new StockOrder(targetStock,requestQuantity,billType,dtf.format(LocalDateTime.now()));
        DataManager.writeUserStockOrder(stockOrder, this.getUserID());

        return true;
    }


//    public static void main(String[] args){
//        SecurityAccount securityAccount = new SecurityAccount(AccountType.SECURITY, "SecurityAccount0", "Yuan", 20000);
//        Stock targetStock0 = new Stock("TWTR", "Twitter", 20);
//       Stock targetStock1 = new Stock("AAPL", "Apple", 131);
//
//       securityAccount.updatePosition(targetStock0, true, 100);
//       securityAccount.updatePosition(targetStock1, true, 10);
//       securityAccount.updatePosition(targetStock0, false, 10);
//
//        // check position is manually requested by manager or user.
//        DataManager.writeUserPositions(securityAccount, "Yuan");
//    }
}