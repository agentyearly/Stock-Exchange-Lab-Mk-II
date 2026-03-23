/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{

    private boolean ascending_flag; 
    public PriceComparator(){
        ascending_flag = true; 
    }
    public PriceComparator(boolean asc){
        ascending_flag = asc; 
    }
    public int compare(TradeOrder order1, TradeOrder order2){
        if (order1.isMarket() && order2.isMarket()){
            return 0; 
        }
        if (order1.isMarket() && order2.isLimit()){
            return -1; 
        }
        if (order1.isLimit() && order2.isMarket()){
            return 1; 
        }
        else{
            if (ascending_flag){
                return (int)Math.round((order1.getPrice()-order2.getPrice()) * 100); 
            }
            else{
                return (int)Math.round((order2.getPrice()-order1.getPrice()) * 100); 
                }
        }
    }

}

