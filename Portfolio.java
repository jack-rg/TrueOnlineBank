

import java.util.*;

public class Portfolio{
 private ArrayList<Stock> stocks; 
  
 public Portfolio(){
   stocks = new ArrayList<Stock>();
 }
  
 
 public ArrayList<Stock> getRawPortfolio(){
  return stocks; 
 }
 
 public Stock getStock(String ticker){
   for(Stock s: stocks){
     if(s.getTicker().equals(ticker)){
      return s; 
     }
   }
   return null;
 }
 
 public double getTotalValue(){
   double sum = 0;  
   for(Stock s: stocks){
    sum += s.getValue();
   }
   return sum;
 }
 
 
}