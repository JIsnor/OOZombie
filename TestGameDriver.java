import static org.junit.Assert.*;
import junit.textui.TestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestGameDriver {

	GameDriver testDriver;
	
	@Before
	public void setUp() throws Exception {
		testDriver = new GameDriver();
	}

	@After
	public void tearDown() throws Exception {
		testDriver = null;
	}
	
	//------------------------
	// MODEL TESTS
	//------------------------	
	@Test
	public void testBoardDimensions() throws Exception{
		testDriver.initializeBoard();
		assertEquals(testDriver.model.grid.length, testDriver.model.DIMENSION_X);
		assertEquals(testDriver.model.grid[0].length, testDriver.model.DIMENSION_Y);	
	}
	
	@Test
	public void testBoardBoolean() throws Exception{
		boolean[][] grid = testDriver.model.grid;
		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y < grid.length; y++){
				assertEquals(grid[x][y], true);
			}
		}
	}
	
	
	//------------------------
	// CONTROLLER TESTS
	//------------------------	
	@Test
	public void testNoEnd(){
		testDriver.model.entities.add(new Human(0, 0, "Benji"));
		testDriver.model.entities.add(new Fruit(1, 1));
		assertFalse(testDriver.gameOver());
	}
	
	@Test
	public void testFruitlessEnd(){
		testDriver.model.entities.add(new Human(0, 0, "Benji"));
		assertTrue(testDriver.gameOver());
	}
	
	@Test
	public void testHumanlessEnd(){
		testDriver.model.entities.add(new Fruit(1, 1));
		assertTrue(testDriver.gameOver());
	}
	
	@Test
	public void testFruitlessHumanlessEnd(){
		assertTrue(testDriver.gameOver());
	}
	
	@Test
	public void humanEatsFruit(){
		Human benji = new Human(0, 0, "Benji");
		Fruit fruit = new Fruit(1, 1);
		testDriver.model.entities.add(benji);
		testDriver.model.entities.add(fruit);

		assertNotEquals(testDriver.model.entities.indexOf(fruit), -1);		
		testDriver.humanEatsFruit(benji, fruit);
		assertEquals(testDriver.model.entities.indexOf(fruit), -1);
	}
	
	@Test
	public void zombieBitesHuman(){
		Human benji = new Human(0, 0, "Benji");
		Zombie james = new Zombie(testDriver.model.DIMENSION_X - 1, testDriver.model.DIMENSION_Y - 1, "James");
		testDriver.model.entities.add(benji);
		testDriver.model.entities.add(james);
		
		int bitesRequired = (int) Math.ceil(benji.health / james.damageInflicted);
		
		assertTrue(bitesRequired >= 1);
		assertNotEquals(testDriver.model.entities.indexOf(benji), -1);
		for(int i = 0; i < bitesRequired; i++){
			assertNotEquals(testDriver.model.entities.indexOf(benji), -1);		
			testDriver.zombieBitesHuman(james, benji);
		}
		assertEquals(testDriver.model.entities.indexOf(benji), -1);
	}
}
