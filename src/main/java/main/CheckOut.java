package main;
import java.util.ArrayList;
import java.util.List;

import discount.Discountable;

/**
 * The main logic of the demo project.
 * The class is able to scan items and to calculate the final price while applying discounts.
 */
public class CheckOut {
	
	private final Prices prices;
	private final List<Discountable> discountRules; 
	private final List<ScanItem> scannedItems = new ArrayList<ScanItem>();
	
	public CheckOut(Prices prices, List<Discountable> discountRules) {
		this.prices = prices;
		this.discountRules = discountRules;
	}
	
	public void scan(final String commodityName) {
		ScanItem receiptItem = new ScanItem(commodityName);
		scannedItems.add(receiptItem);
	}

	public int total() {
		final List<ReceiptItem> receipt = new ArrayList<ReceiptItem>();
		
		scannedItems.forEach( scanItem -> scanItem.setProcessed(false) );
		
		for(Discountable discount : discountRules) {
			receipt.addAll(discount.calculate(scannedItems));
		}
		
		for(ScanItem scanItem : scannedItems){
			if( ! scanItem.wasProcessed()) {
				final String sku = scanItem.getSKU();
				ReceiptItem receiptItem = new ReceiptItem(sku, prices.getPrice(sku));
				receipt.add(receiptItem);
			}
		}
		
		int total = 0;
		for(ReceiptItem receiptItem : receipt) {
			total += receiptItem.getPrice();
		}
		
		return  total;
	}
}
