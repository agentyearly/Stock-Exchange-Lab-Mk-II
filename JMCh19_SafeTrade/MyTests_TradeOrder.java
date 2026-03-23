public class MyTests_TradeOrder {

    // Shared test data 
    private static String symbol = "GGGL";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static int numShares = 123;
    private static int numToSubtract = 24;
    private static double price = 123.45;

    public static void test() {

        // TradeOrder Tests Examples
        testTradeOrderConstructor();
        testTradeOrderToString();
        testTradeOrderGetTrader();
        testTradeOrderGetSymbol();
        testTradeOrderIsBuy();
        testTradeOrderIsSell();
        testTradeOrderIsMarket();
        testTradeOrderIsLimit();
        testTradeOrderGetShares();
        testTradeOrderGetPrice();
        testTradeOrderSubtractShares();
    }

    // ----------------------------
    // TradeOrder Tests
    // ----------------------------

    private static void testTradeOrderConstructor() {
        System.out.println("\nRunning testTradeOrderConstructor...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println(to.toString());
    }

    private static void testTradeOrderToString() {
        System.out.println("\nRunning testTradeOrderToString...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("toString() returned: " + to.toString());
    }

    private static void testTradeOrderGetTrader() {
        System.out.println("\nRunning testTradeOrderGetTrader...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: null");
        System.out.println("Actual: " + to.getTrader());
    }

    private static void testTradeOrderGetSymbol() {
        System.out.println("\nRunning testTradeOrderGetSymbol...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + symbol);
        System.out.println("Actual: " + to.getSymbol());
    }

    private static void testTradeOrderIsBuy() {
        System.out.println("\nRunning testTradeOrderIsBuy...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + buyOrder);
        System.out.println("Actual: " + to.isBuy());
    }

    private static void testTradeOrderIsSell() {
        System.out.println("\nRunning testTradeOrderIsSell...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + (!buyOrder));
        System.out.println("Actual: " + to.isSell());
    }

    private static void testTradeOrderIsMarket() {
        System.out.println("\nRunning testTradeOrderIsMarket...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + marketOrder);
        System.out.println("Actual: " + to.isMarket());
    }

    private static void testTradeOrderIsLimit() {
        System.out.println("\nRunning testTradeOrderIsLimit...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + (!marketOrder));
        System.out.println("Actual: " + to.isLimit());
    }

    private static void testTradeOrderGetShares() {
        System.out.println("\nRunning testTradeOrderGetShares...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + numShares);
        System.out.println("Actual: " + to.getShares());
    }

    private static void testTradeOrderGetPrice() {
        System.out.println("\nRunning testTradeOrderGetPrice...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        System.out.println("Expected: " + price);
        System.out.println("Actual: " + to.getPrice());
    }

    private static void testTradeOrderSubtractShares() {
        System.out.println("\nRunning testTradeOrderSubtractShares...");
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder,
                numShares, price);
        to.subtractShares(numToSubtract);
        System.out.println("After subtractShares(" + numToSubtract + ")");
        System.out.println("Expected: " + (numShares - numToSubtract));
        System.out.println("Actual: " + to.getShares());
    }

}