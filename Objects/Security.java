package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;
import Types.StockOrderType;
import Util.DataManager;
import java.time.LocalDateTime;
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

    /**
     * buy or sell one specific stocks
     *
     * @return true, if bill succeed.
     */
    public String updatePosition(Stock targetStock, boolean isBuyBill, int requestQuantity) {
        StockOrderType billType;
        String targetStockName = targetStock.getName();

        if (isBuyBill && targetStock.getPrice() * requestQuantity > this.getBalance()) {
            return "Insufficient funds.";
        }

        if (!stockName2position.containsKey(targetStockName)) {
            stockName2position.put(targetStockName, new Position(targetStock, 0));
        }

        Position targetPosition = stockName2position.get(targetStockName);

        if (isBuyBill) {
            billType = StockOrderType.BUY;
            if (targetPosition.addStock(targetStock, requestQuantity)) {
                withdraw(targetStock.getPrice() * requestQuantity,
                        "Buy " + requestQuantity + " of " + targetStockName, true);
            } else {
                return "Could not add stock to position.";
            }
        } else {
            billType = StockOrderType.SELL;

            if (targetPosition.deductStock(targetStock, requestQuantity)) {
                deposit(targetStock.getPrice() * requestQuantity,
                        "Sell " + requestQuantity + " of " + targetStockName,
                        getCurrencyType(), true);
            } else {
                return "You do not own enough of this stock to sell the selected quantity.";
            }
        }

        String accountID = this.getAccountID();
        StockOrder stockOrder = new StockOrder(LocalDateTime.now(), targetStock,
                requestQuantity, targetStock.getPrice(), billType, accountID);
        DataManager.writeStockOrder(stockOrder);

        return null;
    }
}