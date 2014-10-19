import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestDirection {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRandomDirection() {
		
		Direction d = Direction.randomDirection();
		assertTrue(!d.equals(null));
	}

	@Test
	public void testIntToDirection() {
		
		assertEquals("37 = LEFT: ", Direction.intToDirection(37), Direction.LEFT);
		assertEquals("38 = DOWN: ", Direction.intToDirection(38), Direction.DOWN);
		assertEquals("39 = RIGHT: ", Direction.intToDirection(39), Direction.RIGHT);
		assertEquals("40 = UP: ", Direction.intToDirection(40), Direction.UP);
		assertEquals("36 = null: ", Direction.intToDirection(36), null);
		assertEquals("41 = null: ", Direction.intToDirection(41), null);
		
	}

}
