package stocks;

import utils.TransactionSearcher;

public class PreferredStock extends Stock {
	private boolean debug = true;
	public PreferredStock(String symbol, int lastDividend, int fixedDividendPerc, int parValue) {
		super(symbol, lastDividend, fixedDividendPerc, parValue);
	}
	
	@Override
	public float calculateDividendYeld() {
		long actualTime = System.currentTimeMillis();
		float tickerPrice = TransactionSearcher.getTickerPrice(this.getSymbol(), actualTime);
		float toBeReturned =0;
		float dividend = this.getFixedDividendPerc() /100;
		int parValue = this.getParValue();
		toBeReturned = (dividend * parValue) / tickerPrice;
		if(debug)
		{
			System.out.println("*********************************");
			 System.out.println("calculate Dividend Yeld for PREFERRED Stock "
					+ "Symbol: ["+ getSymbol() +"] "
			 		+ "dividend: ["+ dividend +"] "
			 		+ "par Value: ["+ parValue  +"] "
			 		+ "ticker Price: ["+ tickerPrice +"] "
			 		+ "RESULT: ["+ toBeReturned +"]" );
			 System.out.println("*********************************\n");
		}
		return toBeReturned;  
	}

	@Override
	public String toString() {
		return "PreferredStock [symbol=" + getSymbol() + ", Type=Preferred" +", Last Dividend=" + getLastDividend() + ", Fixed Dividend=" + getFixedDividendPerc()
				+"%"	+ ", Par Value=" + getParValue() + "]";
		}	
}
