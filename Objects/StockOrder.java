package Objects;

import Types.StockOrderType;

import java.time.LocalDateTime;

/**
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */

/**
 * StockOrder represents a stock order.
 *     accountId: the accountID from the user who set the order
 *     stock: the stock bought / selled
 *     quantity: stock nums
 *     price: current stock price
 *     orderDate: the date of the order be generated
 */

public class StockOrder {
    String accountId;
    Stock stock;
    int quantity;
    StockOrderType stockOrderType;
    LocalDateTime orderDate;
    double pricePerStock;

    public StockOrder(LocalDateTime orderDate, Stock stock, int quantity, double pricePerStock, StockOrderType side, String accountId) {
        this.accountId = accountId;
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerStock = pricePerStock;
        this.stockOrderType = side;
        this.orderDate = orderDate;
    }

    public Stock getStock() {
        return stock;
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

    public String getAccountId() {
        return this.accountId;
    }
}
