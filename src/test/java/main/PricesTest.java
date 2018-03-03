package main;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Prices;

public class PricesTest {
	Prices prices = new Prices();
	
	@Before
	public void setUp() {
		prices.addPrice("A", 50);
		prices.addPrice("B", 30);
		prices.addPrice("C", 20);
		prices.addPrice("D", 15);
	}

	@Test
	public void test() {
		assertEquals(50, prices.getPrice("A"));
		assertEquals(30, prices.getPrice("B"));
		assertEquals(20, prices.getPrice("C"));
		assertEquals(15, prices.getPrice("D"));
		
		// for unknown SKUs we return a "free of charge" price in this demo
		assertEquals(0, prices.getPrice("E"));
	}

}
