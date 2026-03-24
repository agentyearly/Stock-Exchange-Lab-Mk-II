public class MyTests_StockExchange {
     // Shared test data 
    private static String symbol = "GGGL";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static int numShares = 123;
    private static int numToSubtract = 24;
    private static double price = 123.45;

    public static void test() {
        System.out.println("\n===== StockExchange Tests =====");
        testListStock();
        testGetQuote();
        testPlaceOrder();
    }
    public static void testListStock(){
        System.out.println("\nRunning testListStock...");
        StockExchange se = new StockExchange();
        int size = se.getListedStocks().size(); 
        se.listStock(symbol, "Giggle", price);
        int newSize = se.getListedStocks().size(); 
        if (newSize  == size + 1){
            System.out.println("A stock was added to Listed Stocks. Success!");
        }
        else{
            System.out.println("No stock was added. Fail!");
        }
    }
    public static void testPlaceOrder(){
        System.out.println("\nRunning testPlaceOrder...");
        StockExchange se = new StockExchange();
        Trader sally = new Trader( new Brokerage (se), "Sally", "scoobydoo");
        TradeOrder order = new TradeOrder(sally, symbol, buyOrder, marketOrder, numShares, price);
        se.listStock(symbol, "Giggle", price);
        se.placeOrder(order);
        if (sally.hasMessages()){
            System.out.println("Success!");
        }
        TradeOrder order2 = new TradeOrder(sally, "LTT", buyOrder, marketOrder, numShares, price);
        se.placeOrder(order2);
        System.out.println("Executed without crashing. Success!");
    }
    public static void testGetQuote(){
        System.out.println("\nRunning testGetQuote...");
        StockExchange se = new StockExchange();
        se.listStock(symbol, "Giggle", price);
        System.out.println("This should print a quote: " + se.getQuote(symbol));
        System.out.println("This should print 'not found': " + se.getQuote("LLT"));
    }
}