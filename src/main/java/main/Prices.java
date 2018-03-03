package main;
import java.util.HashMap;

/**
 * This class knows the current price for each SKU (some sort of inner database).
 * The aim is for each SKU/item to ask for the price.
 */
public class Prices {
	private final HashMap<String, Integer> prices = new HashMap<String, Integer>();
	
	public void addPrice(final String sku, final Integer price) {
		prices.put(sku, price);
	}
	
	public int getPrice(final String sku) {
		return prices.containsKey(sku) ? prices.get(sku) : 0;
	}
	
}
