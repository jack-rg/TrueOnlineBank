package Objects;


import Types.StockOrderType;

import java.time.LocalDateTime;

/**
 * Stock position represent the details of a stock that the buyer have
 * 1. stock
 * 2. side : buy or sell
 * 3. FilledOrders / total ration
 * 4. Limit Price
 * 5. filled Quantity
 * 7. OrderPlacedTime
 * in our project, we take the Order was filled Instantly for a simple case:
 * 8. OrderFiiledTime ==  OrderPlacedTime
 *
 */
public class StockOrder {
    String accountId;
    Stock stock;
    int quantity;
    StockOrderType side;
    LocalDateTime orderDate;
    double pricePerStock;
    // in our project, we take the Order was filled Instantly for a simple case;

    public StockOrder(LocalDateTime orderDate, Stock stock, int quantity, double pricePerStock, StockOrderType side, String accountId) {
        this.accountId = accountId;
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerStock = pricePerStock;
        this.side = side;
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

    public StockOrderType getOrderType() { return side; }

    public String getAccountId() {
        return this.accountId;
    }
}
