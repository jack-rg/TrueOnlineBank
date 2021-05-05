package Objects;

import Util.DataManager;

/**
 * Stock represents a stock
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Stock {
    private final String name;
    private final String symbol;
    private double price;

    public Stock(String name, String symbol, double price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        DataManager.updateStock(this);
    }





}