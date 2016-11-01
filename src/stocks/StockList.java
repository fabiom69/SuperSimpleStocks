package stocks;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StockList {
/**
 * 
 * A single instance containing all the stocks,common and preferred, to be used in all the code.
 */
    private static StockList stockList;
    private boolean debug = true;
    static{
        try{
        	stockList = new StockList();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating the stock list instance");
        }
    }
	private List<Stock> stockIndex = new ArrayList<>();//
	
	private StockList()
	{
		// in a real application, the data will be read from outside...
		stockIndex.add(new Stock("TEA", 0, 0, 100));
		stockIndex.add(new Stock("POP", 8, 0, 100));
		stockIndex.add(new Stock("ALE", 23, 0, 60));
		stockIndex.add(new PreferredStock("GIN", 8, 2, 100));
		stockIndex.add(new Stock("JOE", 13, 0, 250));
	}
	
	public static StockList getInstance(){
	        return stockList;
	    }
	
	public List<Stock> getStockIndex() {
		return stockIndex;
	}
	
	public void printAllElements() {
		if(debug){
			List<Stock> printList = getStockIndex();
			
			ListIterator<Stock> listIterator = printList.listIterator();
			int counter =0;
			while(listIterator.hasNext())
			{
				Stock element = (Stock) listIterator.next();
						 System.out.println("STOCK element number: ["+ counter +"] contains:["+ element +"]" );
						 counter++;
			}// end while
		}
	}
	
}  // end class
