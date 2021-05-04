package Objects;

import Types.StockOrderType;

import java.time.LocalDateTime;

/**
 * StockOrder represents a stock order.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class StockOrder {
    Stock stock;
    int quantity;
    StockOrderType stockOrderType;
    LocalDateTime orderDate;
    double pricePerStock;

    public StockOrder(LocalDateTime orderDate, Stock stock, int quantity, double pricePerStock, StockOrderType side) {
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerStock = pricePerStock;
        this.stockOrderType = side;
        this.orderDate = orderDate;
    }

    public String getOrderStockSymbol(){
        return stock.getSymbol();
    }

    public double getPricePerStock(){
        return pricePerStock;
    }

    public LocalDateTime getOrderDate(){
        return this.orderDate;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public StockOrderType getOrderType() { return stockOrderType; }
}
