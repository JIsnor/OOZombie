import java.util.ArrayList;
import java.util.Arrays;

/**
 * Whoop de fucking doo, I made a javadoc comment
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
	//TODO: write a constructor that gets initial data from a parsed file
	public Model(){
		
		//paint array with 1's
		for(boolean[] row : grid){
			Arrays.fill(row, true);
		}
		
		//place a single zombie and human at opposite corners
		entities.add(new Entity(Representation.HUMAN, 0, 0, "Benji"));
		entities.add(new Entity(Representation.ZOMBIE, DIMENSION_X - 1, DIMENSION_Y - 1, "James"));
		
	}	
}
