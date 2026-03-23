public class MyTests {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        RUNNING ALL TESTS        ");
        System.out.println("=================================");

        MyTests_TradeOrder.test();
        MyTests_Stock.test();
        MyTests_PriceComparator.test();
        MyTests_Brokerage.test();
        MyTests_StockExchange.test();

        System.out.println("\n=================================");
        System.out.println("          TESTS COMPLETE         ");
        System.out.println("=================================");
    }
}