package discount;

import java.util.List;

import main.ReceiptItem;
import main.ScanItem;

public interface Discountable {
	/**
	 * Calculates from the scanned items the items for the receipt.
	 * @param scannedItems the items the POS scanned
	 * @return items for the receipt
	 */
	List<ReceiptItem> calculate(final List<ScanItem> scannedItems);
}
