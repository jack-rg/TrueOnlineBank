package Objects;

/**
 * Stock position represent the details of a stock that the buyer have
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Position {
    public static int MAX_POSITION_LIMIT = 100;

    private Stock stock;
    private int quantity;
    private double mktValue; // currentPrice * quantity
    private double totalCost;
    private double avgPrice; // totalCost / quantity;

    private double unrealizedPL;
    private double unrealizedPLRate;


    // create position
    public Position(Stock stock, int quantity){
        this.quantity = quantity;
        this.stock = stock;
        this.mktValue = stock.getPrice() * quantity;
        this.totalCost = 0;
        this.totalCost += stock.getPrice() * quantity;
        this.avgPrice = totalCost / (double) quantity;
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

    public boolean addStock(Stock addStock, int addQuantity){
        double currPrice = addStock.getPrice();
        this.totalCost += currPrice * addQuantity;
        this.quantity += addQuantity;
        this.mktValue = quantity * currPrice;
        this.avgPrice = totalCost / quantity;
        this.unrealizedPL = mktValue - totalCost;
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;

        return true;
    }

    public boolean deductStock(Stock addStock, int deductQuantity) {
        if (this.quantity < deductQuantity) {
            return false;
        }

        double currPrice = addStock.getPrice();
        this.totalCost -= currPrice * deductQuantity;
        this.quantity -= deductQuantity;
        this.mktValue = quantity * currPrice;
        this.avgPrice = totalCost / quantity;
        this.unrealizedPL = mktValue - totalCost;
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;

        return true;
    }
}
