public class MyTests_Brokerage {
    // Shared test data 
    private static String symbol = "GGGL";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static int numShares = 123;
    private static int numToSubtract = 24;
    private static double price = 123.45;

    public static void test() {

        System.out.println("\n===== Brokerage Tests =====");
        // TODO - fix the login and logout
        testBrokerageAddUser();
        //testBrokerageLogin();
        //testBrokerageLogout();
        testBrokerageGetQuote();
        testBrokeragePlaceOrder();


    }
    // ----------------------------
    // Brokerage Tests
    // ----------------------------
    public static void testBrokerageAddUser(){
        System.out.println("\nRunning testBrokerageAddUser...");
        Brokerage br = new Brokerage(new StockExchange());
        int result = br.addUser("Sally", "password1");      // if this works then so does the constructor

        System.out.println("Return value check:");
        System.out.println("Expected: 0");
        System.out.println("Actual: " + result);

        System.out.println("User added check:");
        System.out.println("Expected: true");
        System.out.println("Actual: " + br.getTraders().containsKey("Sally"));

        System.out.println("Invalid name check:");
        result = br.addUser("", "password1");
        System.out.println("Expected: -1");
        System.out.println("Actual: " + result);

        System.out.println("Invalid password check:");
        result = br.addUser("John", "b");
        System.out.println("Expected: -2");
        System.out.println("Actual: " + result);

        System.out.println("Duplicate user check:");
        result = br.addUser("Sally", "password1");
        System.out.println("Expected: -3");
        System.out.println("Actual: " + result);
    }

    public static void testBrokerageLogin(){
        System.out.println("\nRunning testBrokerageLogin...");
        Brokerage br = new Brokerage(new StockExchange());
        br.addUser("Sally", "password1");

        System.out.println("Invalid name check:");
        int result = br.login("", "password1");
        System.out.println("Expected: -1");
        System.out.println("Actual: " + result);

        System.out.println("Wrong password check:");
        result = br.login("Sally", "wrong");
        System.out.println("Expected: -2");
        System.out.println("Actual: " + result);

        System.out.println("Successful login check:");
        result = br.login("Sally", "password1");
        System.out.println("Expected: 0");
        System.out.println("Actual: " + result);

        System.out.println("Logged in check:");
        System.out.println("Expected: true");
        System.out.println("Actual: " + br.getLoggedTraders().contains(br.getTraders().get("Sally")));

        System.out.println("Duplicate login check:");
        result = br.login("Sally", "password1");
        System.out.println("Expected: -3");
        System.out.println("Actual: " + result);
    }

    public static void testBrokerageLogout(){
        System.out.println("\nRunning testBrokerageLogout...");
        Brokerage br = new Brokerage(new StockExchange());
        br.addUser("Sally", "password1");
        br.login("Sally", "password1");
        Trader sally = br.getTraders().get("Sally");
        br.logout(sally);
        System.out.println("Logged out check:");
        System.out.println("Expected: false");
        System.out.println("Actual: " + br.getLoggedTraders().contains(sally));
    }

    public static void testBrokerageGetQuote(){
        System.out.println("\nRunning testBrokerageGetQuote...");
        Brokerage br = new Brokerage(new StockExchange());
        br.addUser("Sally", "password1");
        Trader sally = br.getTraders().get("Sally");

        int before = sally.mailbox().size();
        br.getQuote(symbol, sally);
        int after = sally.mailbox().size();

        System.out.println("Mailbox size increased check:");
        System.out.println("Expected: " + (before + 1));
        System.out.println("Actual: " + after);
    }
    public static void testBrokeragePlaceOrder(){
        System.out.println("\nRunning testBrokeragePlaceOrder...");
        Brokerage br = new Brokerage(new StockExchange());
        br.addUser("Sally", "password1");
        Trader sally = br.getTraders().get("Sally");

        TradeOrder order = new TradeOrder(sally, symbol, buyOrder, marketOrder, numShares, price);
        br.placeOrder(order);

        System.out.println("Order placement completed without crashing.");
    }
}