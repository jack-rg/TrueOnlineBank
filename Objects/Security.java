package Objects;

import Types.Status;
import Types.AccountType;
import Types.CurrencyType;
import Types.StockOrderType;
import Util.DataManager;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public Security(AccountType accountType, String accountID, String userID) {
        super(accountType, accountID, userID);
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

        Position targetPosition = new Position(targetStock, 0, 0, accountID);
        for (Position p : getPositions()) {
            if (p.getStock().getName().equals(targetStockName)) {
                targetPosition = p;
            }
        }

        if (isBuyBill) {
            billType = StockOrderType.BUY;
            if (targetPosition.addStock(targetStock, requestQuantity)) {
                positions.remove(targetPosition);
                withdraw(targetStock.getPrice() * requestQuantity,
                        "Buy " + requestQuantity + " of " + targetStockName, true);
            } else {
                return "Could not add stock to position.";
            }
        } else {
            billType = StockOrderType.SELL;

            if (targetPosition.deductStock(targetStock, requestQuantity)) {
                positions.add(targetPosition);
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