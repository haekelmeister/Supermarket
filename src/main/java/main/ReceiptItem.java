package main;

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
