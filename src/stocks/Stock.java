package stocks;

import utils.TransactionSearcher;

public class Stock {
	private boolean debug = true;
	private final String symbol;
	private int lastDividend;
	private int fixedDividendPerc;
	private int parValue;
	
	public Stock(String symbol, int lastDividend, int fixedDividendPerc, int parValue) {
		super();
		this.symbol = symbol;
		this.lastDividend = lastDividend;
		this.fixedDividendPerc = fixedDividendPerc;
		this.parValue = parValue;
	}
	
	public float calculateDividendYeld(){
		//
		long actualTime = System.currentTimeMillis();
		float tickerPrice = TransactionSearcher.getTickerPrice(this.getSymbol(), actualTime);
		float toBeReturned =0;
		// PLEASE NOTE: Below, I'm not using the multi-catch just for clarity's sake.
		try{
			toBeReturned = getLastDividend()/tickerPrice;
		}
		catch (ArithmeticException ae){
			ae.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		if(debug)
		{
			 System.out.println("*********************************");
			 System.out.println("calculate Dividend Yeld for COMMON Stock "
					+ "Symbol: ["+ getSymbol() +"] "
					+ "Last Dividend: ["+ getLastDividend() +"] "
			 		+ "ticker Price: ["+ tickerPrice +"] "
			 		+ "RESULT: ["+ toBeReturned +"]" );
			 System.out.println("*********************************\n");
		}
		return toBeReturned;
	} // end calculateDividendYeld

	public float calculatePERatio(){
		
		// I'm assuming that the Dividend is the last dividend indicate in the table, for all Stocks, including preferred ones.
		// of course, in a working situation, I will reach my boss to check this and use the right formula.
		// I consider only positive dividends; zero gives infinity, and negative dividends are not acceptable.
		// so the zero in the given table prints the error message; if a different outcome is needed, just state it.
		long actualTime = System.currentTimeMillis();
		float tickerPrice = TransactionSearcher.getTickerPrice(this.getSymbol(), actualTime);
		float toBeReturned =0;
		if (this.getLastDividend() <=0)
		{
			if(debug)
			{
				System.out.println("*********************************");
				 System.out.println("ERROR! CANNOT calculate P//E Ratio "
						 + "because Last Dividend is zero or less: ["+ getLastDividend() +"] "
						 +" returning zero."
						+ "Symbol: ["+ getSymbol() +"] "
				 		+ "Last Dividend: ["+ getLastDividend() +"] "
				 		+ "ticker Price: ["+ tickerPrice +"] "
				 		//+ "RESULT: ["+ toBeReturned +"]" 
				 		);
				 System.out.println("*********************************\n");
			}
			return toBeReturned;
		}
		// PLEASE NOTE: Below, I'm not using the multi-catch just for clarity's sake.
		try{
			toBeReturned = tickerPrice/this.getLastDividend();
		}
		catch (ArithmeticException ae){
			ae.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		if(debug)
		{
			System.out.println("*********************************");
			 System.out.println("calculate P//E Ratio "
					+ "Symbol: ["+ getSymbol() +"] "
			 		+ "Last Dividend: ["+ getLastDividend() +"] "
			 		+ "ticker Price: ["+ tickerPrice +"] "
			 		+ "RESULT: ["+ toBeReturned +"]" );
			 System.out.println("*********************************\n");
		}
		return toBeReturned;
	} // end calculatePERatio

	public int getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}
	public int getFixedDividendPerc() {
		return fixedDividendPerc;
	}
	public void setFixedDividendPerc(int fixedDividendPerc) {
		this.fixedDividendPerc = fixedDividendPerc;
	}
	public int getParValue() {
		return parValue;
	}
	public void setParValue(int parValue) {
		this.parValue = parValue;
	}
	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", Type=Common" +", Last Dividend=" + lastDividend + ", Fixed Dividend=" + fixedDividendPerc
			+"%"	+ ", Par Value=" + parValue + "]";
	}
	
}
