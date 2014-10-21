import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Benji
 *
 */
public class Model {

	// size of our game
	final int DIMENSION_X = 20;
	final int DIMENSION_Y = 20;
	
	//grid of traversable space
	private boolean[][] grid = new boolean[DIMENSION_X][DIMENSION_Y];

	//stores all fruits, zombies, and human
	ArrayList<Entity> entities = new ArrayList<Entity>();

	//extra reference to the human to save us the trouble of searching
	//the entity list whenever they are needed
	Human player;

	//helpful game state information to be shown in the view
	private int totalFruits = 0;
	private int fruitsCollected = 0;

	//creates the initial state of the model based on a text file
	public Model(File f) {		
		getModelFromFile(f);
	}
	
	/**
	 * Edits the "grid" and "entities" field of this Model by
	 * reading the required information from a file
	 * @param inFile the file to be read
	 */
	public void getModelFromFile(File inFile) {
		
		int numEntities;
	
		//TODO: actually make the scanner read the boolean grid
		
		for(boolean[] row : grid){
			Arrays.fill(row, true);
		}
		
		try {
			Scanner sc = new Scanner(inFile);
			
			numEntities = sc.nextInt();
			
			//create entities, place them in the array list
			for(int i = 0; i < numEntities; i++) {
				Entity e = null;
				Representation temp = Representation.stringToRep(sc.next());
				int[] initCoords = {sc.nextInt(), sc.nextInt()};

				switch(temp) {
								
				case HUMAN: 
					e = new Human(initCoords);
					entities.add(e);
					player = (Human) e;
					break;
				case ZOMBIE:
					e = new Zombie(initCoords);
					entities.add(e);
					break;
				case FRUIT:
					e = new Fruit(initCoords);
					entities.add(e);
					totalFruits++;
					break;
				default:
					System.err.println("bad token in game file - Use H, Z, or F");
					System.exit(-1);
					break;
				}
			}
			
			sc.close();
		} catch (FileNotFoundException | NoSuchElementException e) {
			System.out.println("error in Model.getModelFromFile():\n  either bad GameData filename or incorrect file syntax");
			System.exit(-1);
		}
	}
	
	//determine whether a coordinate pair describes a legal point on our grid
	public boolean getTileTraversable(int[] coords){
		if(coords == null || coords.length != 2){
			Tools.printStackTrace();
			System.exit(-1);
		}
		
		if(coords[0] > grid.length - 1 || coords[1] > grid[0].length - 1 || coords[0] < 0 || coords[1] < 0){
			return false;
		}
		
		return grid[coords[0]][coords[1]];
	}
	
	//this accessor is used by zombies who require the full game grid to plan their movements
	//using a deep copy prevents side effects
	public boolean[][] getGridDeepCopy(){
		boolean[][] gridCopy = new boolean[DIMENSION_X][DIMENSION_Y];
		
		for(int x = 0; x < DIMENSION_X; x++){
			for(int y = 0; y < DIMENSION_Y; y++){
				gridCopy[x][y] = grid[x][y];
			}
		}
		return gridCopy;
	}
	
	public void incrementFruitsCollected(){
		fruitsCollected++;
	}
	
	public int getFruitsCollected(){
		return fruitsCollected;
	}
	
	public int getTotalFruits(){
		return totalFruits;
	}
}