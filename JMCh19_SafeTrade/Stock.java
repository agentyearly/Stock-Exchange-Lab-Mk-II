import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;
/**
 * Represents a stock in the SafeTrade project
 */
public class Stock {
    public static DecimalFormat money = new DecimalFormat("0.00");

    private String stockSymbol;
    private String companyName;
    private double loPrice, hiPrice, lastPrice;
    private int volume;
    private PriorityQueue<TradeOrder> buyOrders, sellOrders;


    public Stock(java.lang.String symbol, java.lang.String name, double price) {
        stockSymbol = symbol;
        companyName = name;
        loPrice = price;
        hiPrice = price;
        lastPrice = price;
        volume = 0;
        sellOrders = new PriorityQueue<TradeOrder>(10, new PriceComparator(true));
        buyOrders = new PriorityQueue<TradeOrder>(10, new PriceComparator(false));
    }

    //
    // The following are for test purposes only
    //

    protected String getStockSymbol() {
        return stockSymbol;
    }

    protected String getCompanyName() {
        return companyName;
    }

    protected double getLoPrice() {
        return loPrice;
    }

    protected double getHiPrice() {
        return hiPrice;
    }

    protected double getLastPrice() {
        return lastPrice;
    }

    protected int getVolume() {
        return volume;
    }

    protected PriorityQueue<TradeOrder> getBuyOrders() {
        return buyOrders;
    }

    protected PriorityQueue<TradeOrder> getSellOrders() {
        return sellOrders;
    }

    protected void executeOrders() {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            TradeOrder buy = buyOrders.peek();
            TradeOrder sell = sellOrders.peek();

            double price;

            if (buy.isLimit() && sell.isLimit()) {
                if (buy.getPrice() < sell.getPrice()) {
                    return;
                }
                price = sell.getPrice();
            } else if (buy.isLimit() ^ sell.isLimit()) {
                price = buy.isLimit() ? buy.getPrice() : sell.getPrice();
            } else { 
                price = lastPrice;
            }

            int shares = Math.min(buy.getShares(), sell.getShares());
            buy.subtractShares(shares);
            sell.subtractShares(shares);

            if (buy.getShares() == 0)  {
                buyOrders.remove();
            }
            if (sell.getShares() == 0) {
                sellOrders.remove();
            }
            if (price < loPrice)  {
                loPrice = price;
            }
            if (price > hiPrice) {
                hiPrice = price;
            }
            volume += shares;

            Trader bt = buy.getTrader();
            Trader st = sell.getTrader();

            bt.receiveMessage("You bought: " + shares + " " + buy.getSymbol() + " at " + money.format(price) + " amt " + money.format(shares * price));
            st.receiveMessage("You sold: " + shares + " " + sell.getSymbol() + " at " + money.format(price) + " amt " + money.format(shares * price));
        }
    }

    public String getQuote() {
        String first = companyName + " (" + stockSymbol + ")" + "\n" + "Price: " + lastPrice + " hi: " + hiPrice + " lo: " + money.format(loPrice) + " vol: " + volume + "\n";
        String ask = "";
        if (!sellOrders.isEmpty()) {
            TradeOrder sellOrder = sellOrders.peek();
            ask = " Ask: " + sellOrder.getPrice() + " size: " + sellOrder.getShares();
        } else {
            ask = " Ask: none";
        }
            String bid = "";
           if (!buyOrders.isEmpty()) {
                TradeOrder buyOrder = buyOrders.peek();
                bid = " Bid: " + buyOrder.getPrice() + " size: " + buyOrder.getPrice();
            } else {
                bid = " Bid: none";
            }
        return first + ask + bid;
    }

    public void placeOrder(TradeOrder order) {
        String msg = "New Order: ";
        if (order.isBuy()) {
            buyOrders.add(order);
            msg = msg.concat("buy ");
        } else {
            sellOrders.add(order);
            msg = msg.concat("Sell ");
        }
        msg = msg.concat(order.getSymbol() + " (" + order.getTrader().getName() + ")" + "\n" + order.getShares()+ " shares at $" + order);
        order.getTrader().receiveMessage(msg); 
        executeOrders(); 
    }

    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
     */
    public String toString() {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                str += separator + field.getType().getName() + " "
                        + field.getName() + ":" + field.get(this);
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }

            separator = ", ";
        }

        return str + "]";
    }
   }