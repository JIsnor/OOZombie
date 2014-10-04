import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class Tester {
	
	Entity h = new Human(0, 0, null);
	Entity z = new Zombie(0, 0, null);
	Entity f = new Fruit(0, 0);
	
	@Before
	public void initializeGameAndBoard() {
		
		//do some stuff pertaining to the initialization of
		//the game (i.e. initializing characters) and the
		//board (the tiles and whatever)
	}
	
	@Test
	public void testHumanMovement() {
		
		//Move a human, check to see if it worked?
		assertEquals("Zombie moved properly:",z.getCoords(),0/*some new shit*/);
	}
	
	@Test
	public void testZombieSaunter() {
		
		//Test saunter?
	}
	
	/*
	 * Test other various things.
	 */

}
