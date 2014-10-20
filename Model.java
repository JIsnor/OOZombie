import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Benji
 *
 */
public class Model {

	// resolution of our game
	final int DIMENSION_X = 20;
	final int DIMENSION_Y = 20;
	
	//grid of traversable space
	private boolean[][] grid = new boolean[DIMENSION_X][DIMENSION_Y];

	//all moving entities
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	//dummy constructor

	public Model(){
		
		//paint array with 1's
		for(boolean[] row : grid){
			Arrays.fill(row, true);
		}
		
		//initial state for testing
		entities.add(new Human(7, 7, "Benji"));
		entities.add(new Fruit(DIMENSION_X / 2, DIMENSION_Y / 2));
		entities.add(new Zombie(DIMENSION_X - 10, DIMENSION_Y - 10, "James"));
		
	}
	
	public Model(File f) {		
		getModelFromFile(f);
	}
	
	/**
	 * Edits the "grid" and "entities" field of this Model by
	 * reading the required information from a file
	 * @param inFile the file to be read
	 */
	public void getModelFromFile(File inFile) {
		
		int numEntities, numDirtyTiles, x, y;
		
		try {
			Scanner sc = new Scanner(inFile);
			
			numEntities = sc.nextInt();
			
			//create entities, place them in the array list
			for(int i = 0; i < numEntities; i++) {
				Entity e = new Entity(Representation.stringToRep(sc.next()), sc.nextInt(), sc.nextInt(), sc.next());
				entities.add(e);
			}
			
			numDirtyTiles = sc.nextInt();
			//dirty some tiles
			for(int i = 0; i < numDirtyTiles; i++) {
				x = sc.nextInt();
				y = sc.nextInt();
				
				grid[x][y] = false;
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getTileTraversible(int[] coords){
		if(coords.length != 2){
			System.out.println("error in getTileTraversible:\n  method must take a coordinate pair of length 2, instead received array " + coords);
			System.exit(-1);
		}
		
		if(coords[0] > grid.length - 1 || coords[1] > grid[0].length - 1){
			System.out.println("error in getTileTraversible: attempted to access tile out of bounds:\n  dimensions are " + 
					grid.length + ", " + grid[0].length + ", and attempted to access index " + coords[0] + ", " + coords[1]);
		}
		
		return grid[coords[0]][coords[1]];
	}
}
