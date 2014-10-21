import java.util.ArrayList;


public class Zombie extends Entity {
	
	final int damageInflicted = 10;
	
	
	public Zombie(int[] coords) {
		super(Representation.ZOMBIE, coords);
	}
	
	public Zombie(int x, int y) {
		super(Representation.ZOMBIE, new int[]{x,y});
	}
	//breadth-first search to find first movement along shortest path from zombie to human
	public int[] getPotentialSaunter(boolean[][] grid, int[] humanCoords){

		Direction direction = ZombieMovementBFS.getDirectionTowardsHuman(grid, coords, humanCoords);
		return getMoveInDirection(direction);
	}
	
	
	public int getDamageInflicted() {
		return damageInflicted;
	}

}
