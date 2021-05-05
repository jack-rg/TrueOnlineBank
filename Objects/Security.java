package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;
import Types.StockOrderType;
import Util.DataManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Security extends Account to represent a Security Account.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Security extends Account {
    protected static String INADEQUATE_BALANCE_WARN = "Sorry, you dont have enough balance ";
    protected static String CURRENT_BALANCE_INFO = "The current balance is: ";
    protected static String REQUEST_FINISH_INFO = "Requested finished, position updated";
    protected static String EMPTY_POSITION_INFO = "You dont have andy position ";
    protected static String FAILED_REQUEST_INFO = "Request failed";

    ArrayList<Position> positions;
    ArrayList<StockOrder> orders;
    private Map<String, Position> stockName2position;

    public Security(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
        this.stockName2position = new HashMap<>();
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType) {
        super(accountType, accountID, userID, currencyType);
        this.stockName2position = new HashMap<>();
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status accountStatus) {
        super(accountType, accountID, userID, currencyType, accountStatus);
        this.stockName2position = new HashMap<>();
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status, double value) {
        super(accountType, accountID, userID, currencyType, status, value);
        this.stockName2position = new HashMap<>();
    }

    public ArrayList<Position> getPositions() {
        positions = DataManager.loadPositions(accountID);
        return positions;
    }

    public ArrayList<StockOrder> getOrders() {
        orders = DataManager.loadStockOrders(accountID);
        return orders;
    }

    public Map<String, Position> getStockName2position() {
        return this.stockName2position;
    }

    /**
     * buy or sell one specific stocks
     *
     * @return true, if bill succeed.
     */
    public boolean updatePosition(Stock targetStock, boolean isBuyBill, int requestQuantity) {

        StockOrderType billType;
        boolean requestSucceed = false;
        String targetStockName = targetStock.getName();
        // check balance
        if (targetStock.getPrice() * requestQuantity > this.getBalance()) {
            System.out.println(INADEQUATE_BALANCE_WARN + CURRENT_BALANCE_INFO + this.getBalance());
            return false;
        }
        System.out.println("1");
        // Not contains position already, create one

        System.out.println(stockName2position == null);
        if (!stockName2position.containsKey(targetStockName)) {
            //System.out.println(targetStockName);
            stockName2position.put(targetStockName, new Position(targetStock, 0));
        }

        System.out.println("1 end");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");

        Position targetPosition = stockName2position.get(targetStockName);

        System.out.println("2");
        if (isBuyBill) {
            billType = StockOrderType.BUY;
            requestSucceed = targetPosition.addStock(targetStock, requestQuantity);
            // update balance
            this.setBalance(this.getBalance() - targetStock.getPrice() * requestQuantity);

        } else {
            billType = StockOrderType.SELL;
            requestSucceed = targetPosition.deductStock(targetStock, requestQuantity);
            // update balance
            this.setBalance(this.getBalance() + targetStock.getPrice() * requestQuantity);
        }

        if (!requestSucceed) {
            System.out.println(FAILED_REQUEST_INFO);
            return false;
        }

//        this.accountPositionsDisplay();

        System.out.println("3");
        String accountID = this.getAccountID();
        System.out.println(accountID);
        StockOrder stockOrder = new StockOrder(LocalDateTime.now(), targetStock, requestQuantity, targetStock.getPrice(), billType, accountID);
        System.out.println("OrderBill created");
        // TODO: 2021/5/4 debug
        System.out.println("UserID: " + this.getUserID());

//        System.out.println(stockOrder.getOrderDate() + " "+
//                stockOrder.getOrderStockSymbol() + " " +
//                stockOrder.getQuantity()+ " " +
//                stockOrder.getPricePerStock() + " " +
//                stockOrder.getAccountId() + " "
//        );

        DataManager.writeStockOrder(stockOrder);

        System.out.println("end");

        return true;
    }
}