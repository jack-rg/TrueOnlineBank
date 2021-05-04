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
    private double price; // the most recent price of stock

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
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
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}