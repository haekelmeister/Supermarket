package discount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.ReceiptItem;
import main.ScanItem;

/**
 * This class implements the quantity discount type (like  "three for a dollar" or "buy two, get one free").
 * Other discount types could be: TradeDiscounts, PromotionalDiscounts, SeasonalDiscounts, CashDiscounts, GeographicalDiscounts.
 */
public class QuantityDiscount implements Discountable {
	private final String SKU;
	private int quantity;
	private int newPrice;
	
	public QuantityDiscount(final String SKU, int quantity, int newPrice) {
		assert SKU != null;
		assert quantity >= 0;
		
		this.SKU = SKU;
		this.quantity = quantity;
		this.newPrice = newPrice;
	}

	public List<ReceiptItem> calculate(final List<ScanItem> scannedItems) {
		
		if(quantity == 0) {
			return new ArrayList<ReceiptItem>();
		}
		
		List<ScanItem> partOfScannedItems = scannedItems.stream()
				.filter( receiptItem -> receiptItem.getSKU().equals(SKU) )
				.collect(Collectors.toList());
		
		int itemsNumber = partOfScannedItems.size();
		if(itemsNumber == 0) {
			return new ArrayList<ReceiptItem>();
		}
		
		// do not mark all items as processed (discounted) just the first "offersNumber"-once
		int offersNumber = itemsNumber / quantity;
		for(int index = 0; index < offersNumber * quantity; index++) {
			partOfScannedItems.get(index).setProcessed(true);
		}
		
		List<ReceiptItem> offers = new ArrayList<ReceiptItem>();
		for(int index = 0; index < offersNumber; index++) {
			final ReceiptItem receiptItem = new ReceiptItem(SKU, newPrice);
			offers.add(receiptItem);
		}
		
		return offers;
	}
}
