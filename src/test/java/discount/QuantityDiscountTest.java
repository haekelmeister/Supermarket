package discount;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.ReceiptItem;
import main.ScanItem;

public class QuantityDiscountTest {

	@Test
	public void testInsufficentItems() {
		List<ScanItem> scannedItems = new ArrayList<ScanItem>();
		
		Discountable discount = new QuantityDiscount("A", 3, 120);
		
		List<ReceiptItem> intermediateReceipt = discount.calculate(scannedItems);
		assertEquals(0, intermediateReceipt.size());
		
		scannedItems.add(new ScanItem("A"));
		intermediateReceipt = discount.calculate(scannedItems);
		assertEquals(1, scannedItems.size());
		assertEquals(0, intermediateReceipt.size());
		
		scannedItems.add(new ScanItem("A"));
		intermediateReceipt = discount.calculate(scannedItems);
		assertEquals(2, scannedItems.size());
		assertEquals(0, intermediateReceipt.size());
		
		scannedItems.add(new ScanItem("A"));
		intermediateReceipt = discount.calculate(scannedItems);
		assertEquals(3, scannedItems.size());
		assertEquals(1, intermediateReceipt.size());
		assertEquals(120, intermediateReceipt.get(0).getPrice());
	}
	
	@Test
	public void testQuantityZero() {
		List<ScanItem> scannedItems = new ArrayList<ScanItem>();
		
		Discountable discount = new QuantityDiscount("A", 0, 120);
		
		List<ReceiptItem> intermediateReceipt = discount.calculate(scannedItems);
		assertEquals(0, intermediateReceipt.size());
		
		for(int i = 0; i < 10; i++) {
			scannedItems.add(new ScanItem("A"));
		}
		
		intermediateReceipt = discount.calculate(scannedItems);
		assertEquals(0, intermediateReceipt.size());
		assertEquals(10, scannedItems.size());
	}
	
	@Test(expected = AssertionError.class)
	public void testSKUNull() {
		@SuppressWarnings("unused")
		Discountable discount = new QuantityDiscount(null, 0, 120);	
	}
	
	@Test(expected = AssertionError.class)
	public void testNegativeQuantity() {
		@SuppressWarnings("unused")
		Discountable discount = new QuantityDiscount("", -1, 120);	
	}
}
