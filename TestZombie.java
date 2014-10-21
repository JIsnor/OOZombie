import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestZombie {

	Zombie z;

	@Before
	public void setUp() throws Exception {

		z = new Zombie(new int[]{0,0});
	}

	@After
	public void tearDown() throws Exception {

		z = null;
	}


	@Test
	public void testCoords(){
		assertEquals(z.coords.length, z.getCoords().length);
		assertEquals(z.coords[0], z.getCoords()[0]);
		assertEquals(z.coords[1], z.getCoords()[1]);
	}
	
	
//	@Test
//	public void testZombie() {
//		assertFalse("Zombie name is null: ", z.name.equals(null));
//	}

	@Test
	public void testSaunter() {

		int oldX = z.coords[0];
		int oldY = z.coords[1];
		z.getMoveInDirection(Direction.UP);
		assertTrue("Zombie saunter moved ZombieX: ", oldX != z.coords[0]);
		assertTrue("Zombie saunter moved ZombieY: ", oldY != z.coords[1]);
	}

	@Test
	public void testGetDamageInflicted() {
		assertEquals("Zombie damage inflicted method works: ", z.damageInflicted, z.getDamageInflicted());
		assertTrue(z.getDamageInflicted() > 0);
	}
}
