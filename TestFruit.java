import static org.junit.Assert.*;
import junit.textui.TestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFruit{
	Fruit f;

	@Before
	public void setUp() throws Exception {
		f = new Fruit(new int[]{0,0});
	}

	@After
	public void tearDown() throws Exception {

		f = null;
	}
	
	@Test
	public void testNutrition(){
		assertEquals(f.getNutrition(), f.nutrition);
		assertTrue(f.nutrition > 0);
	}	
}