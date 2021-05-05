package Objects;

import java.util.*;

public class StockList {
    public static String[] DEFAULT_STOCKS_NAME_LIST = {"Twiter","Apple","paypal", "Berkshire Hathaway Inc"};
    public static String[] DEFAULT_STOCKS_SYMBOL_LIST = {"TWTR", "APPL", "PYPL","BRK"};
    public static double[] DEFAULT_PURCHASE_PRICE = { 54.13, 127.71, 100, 250.03 };
    private List<Stock> stocksList = new ArrayList<>();
    private Map<String,Stock> stockNameMap2Stock = new HashMap<String, Stock>();
    private Map<String,Stock> stockSymbolMap2Stock = new HashMap<String, Stock>();

    public StockList(){
        for(int i = 0; i < DEFAULT_STOCKS_NAME_LIST.length; i++){
            Stock stock  = new Stock( DEFAULT_STOCKS_SYMBOL_LIST[i], DEFAULT_STOCKS_NAME_LIST[i], DEFAULT_PURCHASE_PRICE[i]);
            stocksList.add(stock);
            stockNameMap2Stock.put(DEFAULT_STOCKS_NAME_LIST[i], stock);
            stockSymbolMap2Stock.put(DEFAULT_STOCKS_SYMBOL_LIST[i], stock);
        }
    }


    public boolean checkHasStock(String stockName, String stockSymbol){
        return stockNameMap2Stock.containsKey(stockName) && stockSymbolMap2Stock.containsKey(stockSymbol);
    }

    public Stock getStockByName(String name){
        return stockNameMap2Stock.get(name);
    }


//    public static void main(String[] args){
//        System.out.println("Whatever");
//    }
}

