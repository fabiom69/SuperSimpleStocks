package launcher;

import java.util.List;
import java.util.ListIterator;

import stockExchange.TickerTape;
import stocks.Stock;
import utils.TransactionMaker;
import utils.TransactionSearcher;

/**
 Project overview:
 
 the launcher pkg contains only the launcher class;
 stocks contain the two Stock classes;
 stockExchange contains the classes for the generation of stock trades
 utils contains the transaction utilities: maker and searcher, and the main calculations that are not present in the Stock and PreferredStock classes.
 
 the number of transaction can be edited changing the variable in utils.TransactionMaker.simulateManyTransactions();
 the time interval to be considered for the stock price can be changed modifying the variable in  utils.TransactionSearcher.calculateStockPrice(String)
 both were left there for efficiency; the time interval was reduced for quick testing purposes.
 
 the classes have a debug boolean variable; setting it to false eliminates all the printout pseudo logging messages.
 
 @author Fabio Mentasti
 
 
 PLEASE NOTE: in the assigned task, the formula contains an inverted question mark.
 
 looking for info on the web, I understood that the formula is, simply, 
 
  dividend * par value 
  --------------------
     ticker price.
 
 I'm using this.
 
  
 I'm not considering, in this code, any multi-threaded and syncronization issues: this influences some of my choices:
 the ArrayList collection type, for example.
 
 I'm not considering any persistence and serialization issues, so I'm not using any persistence ID, the classes do not implement Serializable, and so on.
 
 I'm not using loggers and Junit configuration, to keep the structure the simplest possible. Will be used in a working environment.
 
 
 */


public class Launcher {
	private static boolean debug = true;

	public static void main(String[] args) {
		TickerTape t = TickerTape.getInstance();
		// creation of base stock instances; this will be done in an external file or the data will be read from an outside source.
		// done in the StockList singleton, that contains a collection of Stocks
		
		// simulation of insertion of elements in the list, with a delay to use different timestamps.
		TransactionMaker tm = new TransactionMaker();
		tm.simulateManyTransactions();  // this answers to the "record a trade" question
		if(debug){
			System.out.println("tape size is: [ "+ t.getTape().size() +"]");
			// t.printAllElements();
		}
		TransactionSearcher ts = new TransactionSearcher();  // loads the class that handles the singletons of ticker tape and stock list 
		
		ts.calculateDividendYeld("TEA"); // this answers to the "calculate dividend yeld" question
		ts.calculateDividendYeld("POP");
		ts.calculateDividendYeld("ALE");
		ts.calculateDividendYeld("GIN");
		ts.calculateDividendYeld("JOE");
		
		List<Stock> searchList = ts.getS().getStockIndex();
		
		ListIterator<Stock> listIterator = searchList.listIterator(searchList.size());

		while(listIterator.hasPrevious())
		{
			Stock element = (Stock) listIterator.previous();
			float val = element.calculatePERatio(); // this answers to the "calculate P/E ratio" question
			if (debug){
				 System.out.println("["+ element +"] peRatio is: ["+ val +"]" );
			}
			
		}// end while
		
		ts.calculateStockPrice("TEA"); // this answers to the "calculate stock price" question
		ts.calculateStockPrice("POP");
		ts.calculateStockPrice("ALE");
		ts.calculateStockPrice("GIN");
		ts.calculateStockPrice("JOE");
		
		ts.calculateGBCEAllShareIndex(); // this answers to the "GBCE All Share Index" question
		
		
	}  // end main

}
