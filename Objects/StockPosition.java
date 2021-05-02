package Objects;

import Util.DataManager;

/**
 * Stock position represent the details of a stock that the buyer have
 * 1. stock
 * 2. Quantity
 * 3. MktValue
 * 4. AvgPrice
 * 5. TotalCost
 * 6. UnrealizedP&L
 * 7. UnrealizedP&L rate
 * 8. position ratio
 * 9. Type: stock ? option ? or... (in this project we only consider limit stocks )
 */

public class StockPosition {
    private Stock stock;
    private int quantity;
    private double mktValue; // currentPrice * quantity
    private double totalCost;
    private double avgPrice; // totalCost / quantity;

    private double unrealizedPL;
    private double unrealizedPLRate;


    // create position
    public StockPosition(Stock stock, int quantity){
        this.quantity = quantity;
        this.stock = stock;
        this.mktValue = stock.getLastPrice() * quantity;
        this.totalCost = stock.getLastPrice() * quantity;
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

    public double getMktValue() {
        return mktValue;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public double getUnrealizedPL() {
        return unrealizedPL;
    }

    public double getUnrealizedPLRate() {
        return unrealizedPLRate;
    }

    // Add stocks to position
    public boolean addStock(Stock addStock, int addQuantity){
        double currPrice = addStock.getLastPrice();
        this.totalCost += currPrice * addQuantity;
        this.quantity += addQuantity;
        this.mktValue = quantity * currPrice;
        this.avgPrice = totalCost / quantity;
        this.unrealizedPL = mktValue - totalCost;
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;
        return true;
    }

    public boolean deductStock(Stock addStock, int deductQuantity){
        if(this.quantity < deductQuantity){
            // cannot proceed quantity exceeding current quantity
            return false;
        }
        double currPrice = addStock.getLastPrice();
        this.totalCost -= currPrice * deductQuantity;
        this.quantity -= deductQuantity;
        this.mktValue = quantity * currPrice;
        this.avgPrice = totalCost / quantity;
        this.unrealizedPL = mktValue - totalCost;
        this.unrealizedPLRate = (mktValue - totalCost) / totalCost;
        return true;
    }

    /**
     *
     * @return
     */
    public String getPositionStockName(){
        return this.stock.getName();
    }

    public String getPositionStockSymbol(){
        return this.stock.getSymbol();
    }

    public void positionInfoDisplay(){
        System.out.println( "Symbol: " + stock.getSymbol() +
                            " Name: " + stock.getName() +
                            " Market Value: " + getMktValue() +
                            " Total Cost: " + totalCost +
                            " Unrealized PL: " + unrealizedPL +
                            " Unrealized PL % " + unrealizedPLRate
        );
    }


    public static void main(String[] args){
        StockPosition stockPosition = new StockPosition(new Stock("TWTR", "Twitter", 55.28), 100);
        stockPosition.positionInfoDisplay();
        stockPosition.addStock(new Stock("TWTR", "Twitter", 20), 100);
        stockPosition.deductStock(new Stock("TWTR", "Twitter", 20), 100);
        stockPosition.positionInfoDisplay();

    }
}