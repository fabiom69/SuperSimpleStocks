package stockExchange;

import java.sql.Timestamp;
import stocks.PreferredStock;
import stocks.Stock;

/**
 * 
 * this class contain a single element of the ticker tape, stating the action involved, the price and the price timestamp
 * 
 * @author Fabio
 *
 */
public class StockTransaction {
	// the instance, once created, is immutable: it is recorded and not changeable. So, all variables are final.
	private final Stock stockSymbol;
	private final int tickerPrice;
	private final Timestamp priceTimestamp;
	private final boolean isBuy;
	private final int quantityOfShares;
	
	public StockTransaction(Stock stockSymbol, int tickerPrice, long priceTimestamp, boolean isBuy,
			int quantityOfShares) {
		super();
		this.stockSymbol = stockSymbol;
		this.tickerPrice = tickerPrice;
		this.priceTimestamp = new Timestamp(priceTimestamp);
		this.isBuy = isBuy;
		this.quantityOfShares = quantityOfShares;
	}

	public Stock getStockSymbol() {
		return stockSymbol;
	}

	public int getTickerPrice() {
		return tickerPrice;
	}

	public Timestamp getPriceTimestamp() {
		return priceTimestamp;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public int getQuantityOfShares() {
		return quantityOfShares;
	}

	@Override
	public String toString() {
		String buySell = (isBuy) ? "BUY" : "SELL";
		String commonPreferred = (this.stockSymbol.getClass().equals(PreferredStock.class)) ? "PREFERRED" : "COMMON";
	
		return "Transaction [stockSymbol=[" + stockSymbol + "], time =["+ priceTimestamp.toLocalDateTime()
				+ "], BUY / SELL=[" + buySell + "], quantityOfShares=[" + quantityOfShares 
				+ "] Type=[" + commonPreferred  
				+ "] tickerPrice=[" + tickerPrice + "]]";
	}

}
