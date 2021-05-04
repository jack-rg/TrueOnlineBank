package Objects;

/**
 * stock:
 * 1. symbol
 * 2. name
 * 3. lastPrice
 */

public class Stock {
    private String symbol; // abbreviate stock code
    private String name;    // name
    private double lastPrice; // the most recent price of stock

    public Stock(String symbol, String name, double lastPrice) {
        this.symbol = symbol;
        this.name = name;
        this.lastPrice = lastPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * refresh the lastPrice
     *
     * @param lastPrice
     */
    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public void stockInfoDisplay() {
        System.out.println("Stock Symbol " + this.symbol + "Name: " + this.name + "LastPrice: " + this.lastPrice);
    }
}