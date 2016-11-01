package stockExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * this class contains a collection of all transactions
 * 
 * @author Fabio
 *
 */
	public class TickerTape {
	    private static TickerTape tickerTape;
	    private boolean debug = true;
	    static{
	        try{
	        	tickerTape = new TickerTape();
	        }catch(Exception e){
	            throw new RuntimeException("Exception occured in creating ticker tape instance");
	        }
	    }
	
	private List<StockTransaction> tape= new ArrayList<>();//
 	
	private TickerTape()
    {}
   
	public static TickerTape getInstance(){
	        return tickerTape;
	    }

	public List<StockTransaction> getTape() {
		return 
				tape;
	}

	public void printAllElements() {
		if(debug){
			
			List<StockTransaction> printList = getTape();
			 System.out.println("-------------------- PRINT ALL ELEMENTS ------------------------------------" );
			ListIterator<StockTransaction> listIterator = printList.listIterator();
			int counter =0;
			while(listIterator.hasNext())
			{
				StockTransaction element = listIterator.next();
						 System.out.println("element number: ["+ counter +"] contains:["+ element +"]" );
						 counter++;
			}// end while
			System.out.println("-------------------- END PRINT ALL ELEMENTS --------------------------------" );
		}
	}

}
