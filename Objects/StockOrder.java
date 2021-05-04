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
    Stock stock;
    int quantity;
    StockOrderType side;
    LocalDateTime orderDate;
    double pricePerStock;
    // in our project, we take the Order was filled Instantly for a simple case;

    public StockOrder(LocalDateTime orderDate, Stock stock, int quantity, double pricePerStock, StockOrderType side) {
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerStock = pricePerStock;
        this.side = side;
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

    public StockOrderType getOrderType() { return side; }
}
