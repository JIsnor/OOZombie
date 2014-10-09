import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ZombieTest {
	
	Zombie z;

	@Before
	public void setUp() throws Exception {
		
		z = new Zombie(0, 0, "test");
	}

	@After
	public void tearDown() throws Exception {
		
		z = null;
	}

	@Test
	public void testZombie() {
		
		assertFalse("Zombie name is null: ", z.name.equals(null));
	}

	@Test
	public void testSaunter() {
		
		int oldX = z.getCoords()[0];
		int oldY = z.getCoords()[1];
		z.saunter();
		assertTrue("Zombie saunter moved ZombieX: ", oldX != z.getCoords()[0]);
		assertTrue("Zombie saunter moved ZombieY: ", oldY != z.getCoords()[1]);
	}

	@Test
	public void testGetDamageInflicted() {
		
		assertEquals("Zombie damage inflicted method works: ", z.damageInflicted, z.getDamageInflicted());
	}

}
