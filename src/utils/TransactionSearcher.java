package utils;

import java.util.List;
import java.util.ListIterator;
import stockExchange.StockTransaction;
import stockExchange.TickerTape;
import stocks.PreferredStock;
import stocks.Stock;
import stocks.StockList;

public class TransactionSearcher {
	
/**
 * this class contains some methods that apply the required formulas.
 * 
 */
	
	private static boolean debug = true;
	
	private static TickerTape t = TickerTape.getInstance();	
	private StockList s = StockList.getInstance();	

	public static int getTickerPrice(String stockSymbol , long actualTime){
		int toBeReturned =0;
		
		// read the collection in reverse order; the first matching element found is the most recent one.
		List<StockTransaction> searchList = t.getTape();
		ListIterator<StockTransaction> listIterator = searchList.listIterator(searchList.size());
		boolean notFound = true;
		
		while((listIterator.hasPrevious())&& (notFound))
		{
			StockTransaction element = listIterator.previous();
			if (element.getStockSymbol().getSymbol() == stockSymbol)
			{
				notFound = false;
				toBeReturned = element.getTickerPrice();
				if (debug){
					 System.out.println("get Ticker Price element found! ["+ element +"]" + "ticker price: ["+ toBeReturned +"]" );
				}
			}
		}// end while
		if (notFound)
		{
				if (debug){
					 System.out.println("get Ticker Price element *** NOT *** found! ["+ stockSymbol +"]" );
				}
		}// end if not found
		return toBeReturned;
	}


	public float calculateDividendYeld(String stockSymbol) {
		float toBeReturned =0;
		// read the collection in reverse order; the first matching element found is the most recent one.
		List<Stock> searchList = s.getStockIndex();
		ListIterator<Stock> listIterator = searchList.listIterator(searchList.size());
		boolean notFound = true;
		while((listIterator.hasPrevious())&& (notFound))
		{
			Stock element = (Stock) listIterator.previous();
			if (element.getSymbol() == stockSymbol)
			{
				notFound = false;

				if(element instanceof PreferredStock)
				{
					PreferredStock ele = (PreferredStock) element;
					toBeReturned = ele.calculateDividendYeld();
				}
				else{
					toBeReturned = element.calculateDividendYeld();
				}
				if (debug){
					 System.out.println("calculate Dividend Yeld element found! ["+ element +"] "+ "ticker price: ["+ toBeReturned +"]" );
				}
			}
		}// end while
		if (notFound)
		{
				if (debug){
					 System.out.println("calculate Dividend Yeld element *** NOT *** found! ["+ stockSymbol +"]" );
				}
		}// end if not found
		return toBeReturned;
	}
	
	public float calculateStockPrice(String stockSymbol){
		//long intervalForCalculation = 1*60*1000;
		long intervalForCalculation = 15*60*1000;
	
		float toBeReturned =0;
		float tradePriceQty=0;
		float qty=0;
		// read the collection in reverse order; the first matching element found is the most recent one.
		List<StockTransaction> searchList = t.getTape();
		ListIterator<StockTransaction> listIterator = searchList.listIterator(searchList.size());
		boolean transactionNotTooOld = true;
		while((listIterator.hasPrevious())&& (transactionNotTooOld))
		{
			StockTransaction element = listIterator.previous();
			long elapsedTime = System.currentTimeMillis() - element.getPriceTimestamp().getTime();
			if(elapsedTime > intervalForCalculation)
			{
				// we can stop; the previous transactions are all older than this one...
				transactionNotTooOld =false;
			}
			else{
				if (element.getStockSymbol().getSymbol() == stockSymbol)
				{
					 tradePriceQty+=(element.getTickerPrice()*element.getQuantityOfShares());
					 qty+=element.getQuantityOfShares();
					if (debug){
						 System.out.println("calculate Stock Price element found! ["+ element +"]" );
					}
				}
			}// end else
		}// end while
		
		if(qty ==0)
		{
			toBeReturned =0;
		}
		else
		{
			toBeReturned = tradePriceQty/qty;
		}
		if (debug){
			 System.out.println("************************************************" );
			 System.out.println("Stock price for ["+ stockSymbol +"]: ["+ toBeReturned +"]" );
			 System.out.println("************************************************" );
		}
		return toBeReturned;
	}
	
	public double calculateGBCEAllShareIndex(){
		double toBeReturned = 1;  // we multiply here, so we do not initialize to zero.
		int n = s.getStockIndex().size();
		List<Stock> searchList = s.getStockIndex();
		ListIterator<Stock> listIterator = searchList.listIterator(searchList.size());

		while(listIterator.hasPrevious())
		{
			Stock element = (Stock) listIterator.previous();
			double stockPrice = calculateStockPrice(element.getSymbol());
			toBeReturned *= nthroot(n, stockPrice);	
			if (debug){
				 System.out.println("************************************************" );
				 System.out.println("calculate GBCE All Share Index element found! ["+ element +"] size of n is: ["+ n +"] stock price is: ["+ stockPrice +"]"+" nth root OF THIS ELEMENT : ["+ nthroot(n, stockPrice) +"]"  );
				 System.out.println("************************************************" );
			}
		}// end while
		if (debug){
			         System.out.println("**********************************" );
					 System.out.println("GBCE All Share Index:   ["+ toBeReturned +"]" ); 
					 System.out.println("**********************************" );
		}
		return toBeReturned;
	}
// utility methods for the nth root; the roots are then multiplied to obtain the needed result.	
	public static double nthroot(int n, double A) {
		return nthroot(n, A, .001);
	}
	public static double nthroot(int n, double A, double p) {
		if(A < 0) {
			System.err.println("A < 0");
			return -1;
		} else if(A == 0) {
			return 0;
		}
		double x_prev = A;
		double x = A / n;  
		while(Math.abs(x - x_prev) > p) {
			x_prev = x;
			x = ((n - 1.0) * x + A / Math.pow(x, n - 1.0)) / n;
		}
		return x;
	}

	// returns the transaction searcher instance
	public StockList getS() {
		return s;
	}

} // end class
