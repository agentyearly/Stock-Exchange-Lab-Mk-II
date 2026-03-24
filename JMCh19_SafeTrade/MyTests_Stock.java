public class MyTests_Stock {

    private static String symbol = "GGGL";
    private static String name = "Giggle.com";
    private static double startPrice = 10.00;

    private static int buyShares = 50;
    private static int sellShares = 20;
    private static double buyPrice = 10.00;
    private static double sellPrice = 9.50;

    public static void test() {
    
        System.out.println("\n===== Stock Tests =====");
        // TODO - add tests for Stock class
        StockConstructor();
        StockGetQuoteEmptyBooks();
        StockPlaceOrderAddsToBook();
        StockExecuteOrdersLimitLimit();
    }
    public static void StockConstructor() {
        Stock stock = new Stock(symbol, name, startPrice);
        //symbol
        System.out.println("Expected: " + symbol);
        System.out.println("Actual: " + stock.getStockSymbol());
        //name
        System.out.println("Expected: " + name);
        System.out.println("Actual: " + stock.getCompanyName());
        //price
        System.out.println("Expected: " + startPrice);
        System.out.println("Actual: " + stock.getLoPrice() + " | " + stock.getHiPrice() + " | " + stock.getLastPrice());
        //amt
        System.out.println("Expected: 0");
        System.out.println("Actual: " + stock.getVolume());
        //order books empty check
        System.out.println("Expected: 0 | 0");
        System.out.println("Actual: " + stock.getBuyOrders().size() + " | " + stock.getSellOrders().size());
    }

    public static void StockGetQuoteEmptyBooks() {
        Stock stock = new Stock(symbol, name, startPrice);
        String quote = stock.getQuote();
        //quote contains symbol
        System.out.println("Expected: true");
        System.out.println("Actual: " + quote.contains(symbol));
        //quote shows empty
        System.out.println("Expected: true");
        System.out.println("Actual: " + (quote.contains("Ask: none") && quote.contains("Bid: none")));
    }

    public static void StockPlaceOrderAddsToBook() {
        Brokerage br = new Brokerage(new StockExchange());
        br.addUser("Raymond", "password");
        Trader sally = br.getTraders().get("Raymond");

        Stock stock = new Stock(symbol, name, startPrice);

        int beforeMailbox = sally.mailbox().size();
        TradeOrder buy = new TradeOrder(sally, symbol, true, false, buyShares, buyPrice);
        stock.placeOrder(buy);
        int afterMailbox = sally.mailbox().size();
        //buy order added
        System.out.println("Expected: 1 / 0");
        System.out.println("Actual: " + stock.getBuyOrders().size() + " / " + stock.getSellOrders().size());
        //mailbox sized increased
        System.out.println("Expected: " + (beforeMailbox + 1));
        System.out.println("Actual: " + afterMailbox);
        //message starts with new order
        String lastMsg = sally.mailbox().peek();
        System.out.println("Expected: true");
        System.out.println("Actual: " + lastMsg.startsWith("New Order: "));
    }

    public static void StockExecuteOrdersLimitLimit() {
        Brokerage br = new Brokerage(new StockExchange());
        br.addUser("Raymond", "password");
        br.addUser("Jake", "password");
        Trader sally = br.getTraders().get("Raymond");
        Trader bob = br.getTraders().get("Jake");

        Stock stock = new Stock(symbol, name, startPrice);

        TradeOrder buy = new TradeOrder(sally, symbol, true, false, buyShares, buyPrice);
        TradeOrder sell = new TradeOrder(bob, symbol, false, false, sellShares, sellPrice);

        int volBefore = stock.getVolume();
        int sallyBefore = sally.mailbox().size();

        stock.placeOrder(buy);
        stock.placeOrder(sell);

        int volAfter = stock.getVolume();
        int sallyAfter = sally.mailbox().size();
        //trade excuted volume
        System.out.println("Expected: " + (volBefore + sellShares));
        System.out.println("Actual: " + volAfter);
        //remaining orders
        System.out.println("Expected: 1 / 0");
        System.out.println("Actual: " + stock.getBuyOrders().size() + " / " + stock.getSellOrders().size());
        //buyer got a message
        System.out.println("Expected: true");
        System.out.println("Actual: " + (sallyAfter > sallyBefore));
    }
}
