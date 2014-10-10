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
	boolean[][] grid = new boolean[DIMENSION_X][DIMENSION_Y];

	//all moving entities
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	//dummy constructor
	public Model(){
		
		//paint array with 1's
		for(boolean[] row : grid){
			Arrays.fill(row, true);
		}
		
		//place a single zombie and human at opposite corners
		//entities.add(new Entity(Representation.HUMAN, 0, 0, "Benji"));
		//entities.add(new Entity(Representation.ZOMBIE, DIMENSION_X - 1, DIMENSION_Y - 1, "James"));
		
	}
	
	//Dunno if separating this from the method itself is good or not.
	//figure it's flexible, if a little redundant.
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
}
