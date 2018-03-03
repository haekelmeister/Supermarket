package main;

public class ScanItem {
	private final String SKU;
	private boolean processed;
	
	public ScanItem(final String SKU) {
		this.SKU = SKU;
	}
	
	public String getSKU() {
		return SKU;
	}
	
	public boolean wasProcessed() {
		return processed;
	}
	
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
