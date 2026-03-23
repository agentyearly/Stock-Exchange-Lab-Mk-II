// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class StockExchange {
   private Map<String, Stock> listedStocks = new HashMap();

   public StockExchange() {
   }

   public String getQuote(String var1) {
      if (this.listedStocks.containsKey(var1)) {
         Stock var2 = (Stock)this.listedStocks.get(var1);
         return var2.getQuote();
      } else {
         return var1 + " not found";
      }
   }

   public void listStock(String var1, String var2, double var3) {
      Stock var5 = new Stock(var1, var2, var3);
      this.listedStocks.put(var1, var5);
   }

   public void placeOrder(TradeOrder var1) {
      if (this.listedStocks.containsKey(var1.getSymbol())) {
         Stock var2 = (Stock)this.listedStocks.get(var1.getSymbol());
         var2.placeOrder(var1);
      } else {
         var1.getTrader().receiveMessage(var1.getSymbol() + " not found");
      }

   }

   protected Map<String, Stock> getListedStocks() {
      return this.listedStocks;
   }

   public String toString() {
      String var1 = this.getClass().getName() + "[";
      String var2 = "";
      Field[] var3 = this.getClass().getDeclaredFields();

      for(Field var7 : var3) {
         try {
            var1 = var1 + var2 + var7.getType().getName() + " " + var7.getName() + ":" + String.valueOf(var7.get(this));
         } catch (IllegalAccessException var9) {
            System.out.println(var9);
         }

         var2 = ", ";
      }

      return var1 + "]";
   }
}
