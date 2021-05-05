package Objects;

/**
 * Stock represents a stock
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Stock {
    private final String symbol;
    private final String name;
    private double price;

    public Stock(String name, String symbol, double price) {
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

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "(" + symbol + ") " + name + ": $" + price;
    }
}