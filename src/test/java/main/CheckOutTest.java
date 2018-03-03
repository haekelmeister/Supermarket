package main;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import discount.Discountable;
import discount.QuantityDiscount;
import main.CheckOut;
import main.Prices;

public class CheckOutTest {

	Prices prices = new Prices();
	List<Discountable> discountRules = new ArrayList<Discountable>();
	
	@Before
	public void setUp() throws Exception {
		prices.addPrice("A", 50);
		prices.addPrice("B", 30);
		prices.addPrice("C", 20);
		prices.addPrice("D", 15);
		
		discountRules.add(new QuantityDiscount("A", 3, 130));
		discountRules.add(new QuantityDiscount("B", 2, 45));
	}
	
	private int price(String commodities) {
		CheckOut co = new CheckOut(prices, discountRules);
		
		for(int index = 0; index < commodities.length(); index++) {
			co.scan(commodities.substring(index, index+1));
		}
		
        return co.total();
    }
	
	@Test
    public void testTotals() throws Exception {
        assertEquals(0, price(""));
        assertEquals(50, price("A"));
        assertEquals(80, price("AB"));
        assertEquals(115, price("CDBA"));

        assertEquals(100, price("AA"));
        assertEquals(130, price("AAA"));
        assertEquals(180, price("AAAA"));
        assertEquals(230, price("AAAAA"));
        assertEquals(260, price("AAAAAA"));

        assertEquals(160, price("AAAB"));
        assertEquals(175, price("AAABB"));
        assertEquals(190, price("AAABBD"));
        assertEquals(190, price("DABABA"));
    }

	@Test
    public void testIncremental() {
		CheckOut co = new CheckOut(prices, discountRules);
        assertEquals(0, co.total());
        
        co.scan("A");
        assertEquals(50, co.total());
        
        co.scan("B");
        assertEquals(80, co.total());
        
        co.scan("A");
        assertEquals(130, co.total());
        
        co.scan("A");
        assertEquals(160, co.total());
        
        co.scan("B");
        assertEquals(175, co.total());
	}

}
