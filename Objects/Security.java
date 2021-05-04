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
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status accountStatus) {
        super(accountType, accountID, userID, currencyType, accountStatus);
    }

    public Security(AccountType accountType, String accountID, String userID, CurrencyType currencyType, Status status, double value) {
        super(accountType, accountID, userID, currencyType, status, value);
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
        // Not contains position already, create one
        if (!stockName2position.containsKey(targetStockName)) {
            stockName2position.put(targetStockName, new Position(targetStock, 0, 100));
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");

        Position targetPosition = stockName2position.get(targetStockName);

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

        StockOrder stockOrder = new StockOrder(LocalDateTime.now(), targetStock, requestQuantity, targetStock.getPrice(), billType);
        DataManager.writeStockOrder(stockOrder, this.getUserID());

        return true;
    }
}