public class MyTests_PriceComparator {
    // Shared test data 
    private static String symbol = "GGGL";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static int numShares = 123;
    private static int numToSubtract = 24;
    private static double price = 123.45;


    public static void test() {

        System.out.println("\n===== PriceComparator Tests =====");
        testPriceComparatorConstructor();
        testPriceCompare(); 
    }
    public static void testPriceComparatorConstructor(){
        System.out.println("\nRunning testPriceComparatorConstructor...");
        PriceComparator pc = new PriceComparator();
        PriceComparator pc2 = new PriceComparator(false);
        System.out.println("Success!");
    }
    public static void testPriceCompare(){
        System.out.println("\nRunning testPriceCompare...");
        PriceComparator pc = new PriceComparator();
        PriceComparator pc2 = new PriceComparator(false);
        //both market
        TradeOrder order1 = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        TradeOrder order2 = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        System.out.println("Expected: 0");
        System.out.println("Actual: " + pc.compare(order1, order2)); 
        //order1 market, order2 limit
        TradeOrder order3 = new TradeOrder(null, symbol, buyOrder, true, numShares, price);
        TradeOrder order4 = new TradeOrder(null, symbol, buyOrder, false, numShares, price);
        System.out.println("Expected: -1");
        System.out.println("Actual: " + pc.compare(order3, order4)); 
        //order1 limit, order2 market
        TradeOrder order5 = new TradeOrder(null, symbol, buyOrder, false, numShares, price);
        TradeOrder order6 = new TradeOrder(null, symbol, buyOrder, true, numShares, price);
        System.out.println("Expected: 1");
        System.out.println("Actual: " + pc.compare(order5, order6)); 
        //both limit, asc true
        TradeOrder order7 = new TradeOrder(null, symbol, buyOrder, false, numShares, price);
        TradeOrder order8 = new TradeOrder(null, symbol, buyOrder, false, numShares, 125.87);
        System.out.println("Expected: -242");
        System.out.println("Actual: " + pc.compare(order7, order8)); 
        //both limit, asc false
        System.out.println("Expected: 242");
        System.out.println("Actual: " + pc2.compare(order7, order8)); 
    }
}