package main;

/**
 * This class represents an item on the final customer receipt.
 * Its just a simple container class which will be used to sum up the final price.
 */
public class ReceiptItem {
	private final String commodityName;
	private int price;
	
	public ReceiptItem(String commodityName, int price) {
		this.commodityName = commodityName;
		this.price = price;
	}
	
	public String getCommodityName() {
		return commodityName;
	}
	
	public int getPrice() {
		return price;
	}
}
