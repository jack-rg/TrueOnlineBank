package Objects;


import Util.DataManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    // TODO: 2021/5/1 Order logic, create when a request has been filled
    Stock stock;
    int quantity;
    String side;
    String orderPlacedTime;
    // in our project, we take the Order was filled Instantly for a simple case;
    //String OrderFilledTime;

    public StockOrder(Stock stock, int quantity, String side, String orderPlacedTime){
        this.stock = stock;
        this.quantity = quantity;
        this.side = side;
        this.orderPlacedTime = orderPlacedTime;
    }

    public void displayOrder(){
        System.out.println( "Symbol: " + stock.getSymbol() +
                " Name: " + stock.getName() +
                " Filled quantity: " + quantity +
                " Last Price " + stock.getLastPrice() +
                " orderPlacedtime: " + this.orderPlacedTime
        );
    }

    public String getOrderStockName(){
        return this.stock.getName();
    }

    public String getOrderStockSymbol(){
        return this.stock.getSymbol();
    }

    public double getOrderLastPrice(){
        return this.stock.getLastPrice();
    }

    public String getOrderPlacedTime(){
        return this.orderPlacedTime;
    }

    public int getQuantity(){
        return this.quantity;
    }
    // Order Test
    public static void main(String[] args){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
        StockOrder stockOrder = new StockOrder(new Stock("TWTR", "Twitter", 20), 100, "BUY", dtf.format(LocalDateTime.now()));
        stockOrder.displayOrder();
        String UserName = "yuan wei";
        DataManager.writeUserStockOrder(stockOrder, UserName);
    }
}
