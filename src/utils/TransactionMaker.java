package utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import stockExchange.StockTransaction;
import stockExchange.TickerTape;
import stocks.Stock;
import stocks.StockList;

public class TransactionMaker {
	private List<Stock> stockList = StockList.getInstance().getStockIndex(); 
	private boolean debug = true;
	public TransactionMaker() {
	}// end constructor
	
	public StockTransaction generateTransaction(){
		Random stk = new Random();
		Random tkrpr = new Random();
		Random by = new Random();
		Random qty = new Random();
		Random slp = new Random();

		Stock s = (Stock) stockList.get(stk.nextInt(5));
		int t = tkrpr.nextInt(100)+100;
		int b = by.nextInt(2);
		boolean isBuy =( b ==0)? false:true;
		int stockQty = qty.nextInt(100)*10;
		long l = System.currentTimeMillis();
		StockTransaction toBeReturned = new StockTransaction(s, t, l , isBuy, stockQty	);
		
		if(debug)
		{
			String buySell = (isBuy) ? "BUY" : "SELL";
			Timestamp tsmp = new Timestamp (l);
			System.out.println("StockTransaction: [ "
					+ " Stock ["+ s +"]"
					+ " Ticker ["+ t +"]"
					+ " timestamp ["+ tsmp.toLocalDateTime() +"]"
					+ " buy / sell: ["+ buySell +"]"
					+ " stock qty: ["+ stockQty +"]"
					+ "]");
		}
		try {
			Thread.sleep(slp.nextInt(10)*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return toBeReturned;
	}// end generateTransaction
	
	public void simulateManyTransactions(){
		TickerTape tt = TickerTape.getInstance();
		List<StockTransaction> transactionList = tt.getTape();
		StockTransaction st = null;
		//int numberOfTransactions = 2500;
		int numberOfTransactions = 100;
		
		for (int i =0 ; i < numberOfTransactions; i++){
			 st = generateTransaction();
			transactionList
			.add(st);
			if(debug)
			{
				System.out.println("added transaction number: "
						+ " ["+ i +"]"
						+ " of ["+ numberOfTransactions +"]\n");
			}
		} // end for
	} // end simulateManyTransactions
} // end class
