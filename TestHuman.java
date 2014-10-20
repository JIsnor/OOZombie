import static org.junit.Assert.*;
import junit.textui.TestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestHuman {
	
	Human h;
	Fruit f;
	Zombie z;

	@Before
	public void setUp() throws Exception {
		
		h = new Human(0, 0, "testH");
		z = new Zombie(1,1,"testZ");
		f = new Fruit(2,3);
	}

	@After
	public void tearDown() throws Exception {
		
		h = null;
		f = null;
	}

	@Test
	public void testHuman() {
		
		assertNotEquals("Name is non-null: ", h.name, null);
	}

	@Test
	public void testEatFruit() {
		
		int oldHealth = h.getHealth();
		h.eatFruit(f);
		assertEquals("eatFruit works: ", h.getHealth(), oldHealth + f.getNutrition());
	}

	@Test
	public void testGetHealth() {
		assertTrue(h.getHealth() > 0);
	}

	@Test
	public void testSetHealth() {
		int newHealth = 120;
		h.setHealth(newHealth);
		assertTrue(h.getHealth() == newHealth);

		newHealth = -120;
		h.setHealth(newHealth);
		assertTrue(h.getHealth() == newHealth);
	}

	@Test
	public void testTakeDamage(){
		int oldHealth = h.getHealth();
		h.getBitten(z);
		assertTrue(h.health + z.damageInflicted == oldHealth);
	}
	
	@Test
	public void testEntity() {
		
		assertTrue(h.coords != null);
		assertTrue(h.representation != null);
		assertTrue(h.name != null);
	}

	@Test
	public void testCoords(){
		assertEquals(h.coords.length, h.getCoords().length);
		assertEquals(h.coords[0], h.getCoords()[0]);
		assertEquals(h.coords[1], h.getCoords()[1]);
	}
	
	@Test
	public void testChangeCoordsBy() {
		
		final int X_CHANGE = 5;
		final int Y_CHANGE = 2;
		int oldX = h.coords[0];
		int oldY = h.coords[1];
	
		
		h.changeCoordsBy(X_CHANGE, Y_CHANGE);
		assertEquals("Change coords works: ", h.coords[0], oldX + X_CHANGE);
		assertEquals("Change coords works: ", h.coords[1], oldY + Y_CHANGE);
	}

	@Test
	public void testSetCoords() {
		
		final int SET_X, SET_Y;
		SET_X = 5;
		SET_Y = 6;
		
		h.setCoords(SET_X, SET_Y);
		assertTrue(h.getCoords()[0] == SET_X);
		assertTrue(h.getCoords()[1] == SET_Y);
	}

	@Test
	public void testGetRepresentation() {
		assertEquals("getRepresentation works", Representation.HUMAN.getCharRep(), h.representation.getCharRep());
	}
}
