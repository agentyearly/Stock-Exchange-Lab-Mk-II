import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a brokerage.
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;
    private Set<Trader> loggedTraders;
    private StockExchange exchange;

    // Constructor
    public Brokerage( StockExchange exchange )
    {
        this.exchange = exchange;
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
    }
    /**
     * Tries to register a new trader with a given screen name and password. 
     * If successful, creates a Trader object for this trader and adds this trader to the map of all traders (using the screen name as the key).
     * @param name - the screen name of the trader.
     * @param password - the password for the trader.
     * @return 0 if successful, or an error code (a negative integer) if failed:
                -1 -- invalid screen name (must be 4-10 chars)
                -2 -- invalid password (must be 2-10 chars)
                -3 -- the screen name is already taken.
     */
    public int addUser(String name, String password){
        if ( name == null || name.length() < 4 || name.length() > 10 )
            return -1;

        if ( password == null || password.length() < 2 || password.length() > 10 )
            return -2;

        if ( traders.containsKey( name ) )
            return -3;

        Trader trader = new Trader( this, name, password );
        traders.put( name, trader );
        return 0;
    }

    /**
     * Requests a quote for a given stock from the stock exachange 
     * and passes it along to the trader by calling trader's receiveMessage method.
     * @param symbol - the stock symbol for which a quote is requested.
     * @param trader - the trader requesting the quote.
     * @return void
     */
    public void getQuote(String symbol, Trader trader){
        String quote = exchange.getQuote(symbol);
        trader.receiveMessage(quote);
    }
    
    /**
     * Tries to login a trader with a given screen name and password. 
     * If no messages are waiting for the trader, sends a "Welcome to SafeTrade!" message to the trader. 
     * Adds the trader to the set of all logged-in traders.
     * @param name - the screen name of the trader.
     * @param password - the password for the trader.
     * @return 0 if successful, or an error code (a negative integer) if failed:
                -1 -- screen name not found.
                -2 -- invalid passphrase.
                -3 -- user already logged in.
     */
    public int login(String name, String password){
        if ( !traders.containsKey( name ) )
            return -1;                               // no name~

        Trader trader = traders.get( name );

        if ( !trader.getPassword().equals( password ) )
            return -2;                             // wrong password~

        if ( loggedTraders.contains( trader ) )
            return -3;                           // already logged in/recorded in loggedTraders
        if ( !trader.hasMessages() )
            trader.receiveMessage( "Welcome to SafeTrade!" );
        
        trader.setView( new TraderWindow( trader ) );
        loggedTraders.add( trader );
        return 0;
    }

    /**
     * Removes a specified trader from the set of logged-in traders. The trader may be assumed to logged in already.
     * @param trader - the trader to be logged out.
     * @return void
     */
    public void logout(Trader trader){
        loggedTraders.remove(trader);
    }

    /**
     * Places an order at the stock exchange.
     * @param order - the order to be placed at the stock exchangge.
     * @return void
     */
    public void placeOrder(TradeOrder order){
        exchange.placeOrder( order );
    }

    
    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }

    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }

    protected StockExchange getExchange()
    {
        return exchange;
    }

    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
