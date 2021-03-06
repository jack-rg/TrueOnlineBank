package Objects;

import Util.DataManager;

/**
 * Stock position represent the details of a stock that the buyer have
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Position {
    private final Stock stock;
    private final String accountID;

    private int quantity;
    private double mktValue; // currentPrice * quantity
    private double totalCost;
    private double unrealizedPL;
    private double unrealizedPLRate;

    public Position(Stock stock, int quantity, double totalCost, String accountID) {
        this.quantity = quantity;
        this.stock = stock;
        this.totalCost = totalCost;
        this.accountID = accountID;

        this.mktValue = stock.getPrice() * quantity;
        this.unrealizedPL = (mktValue - totalCost);
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getUnrealizedPL() {
        return unrealizedPL;
    }

    public String getAccountID() {
        return accountID;
    }

    public double getUnrealizedPLRate() {
        return unrealizedPLRate;
    }

    /**
     * @param stock       the stock to add
     * @param addQuantity the amount of the stock to add
     * @return true, if add complete
     */
    public boolean addStock(Stock stock, int addQuantity) {
        double currPrice = stock.getPrice();
        this.totalCost += currPrice * addQuantity;
        this.quantity += addQuantity;
        this.mktValue = quantity * currPrice;
        this.unrealizedPL = mktValue - totalCost;
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;

        DataManager.writePosition(this);

        return true;
    }

    /**
     * @param stock          the stock to deduct
     * @param deductQuantity the amount of stock to deduct
     * @return true, if deduct has complete
     */
    public boolean deductStock(Stock stock, int deductQuantity) {
        if (quantity < deductQuantity) {
            return false;
        }

        double currPrice = stock.getPrice();
        this.totalCost -= currPrice * deductQuantity;
        this.quantity -= deductQuantity;
        this.mktValue = quantity * currPrice;
        this.unrealizedPL = mktValue - totalCost;
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;

        DataManager.writePosition(this);

        return true;
    }
}
