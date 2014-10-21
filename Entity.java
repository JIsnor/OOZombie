/*
 * Idea for avoiding unit-collision: create array of objects
 * which are ranges of x, y positions (i.e. 4 numbers to make a rectangular
 * area which is the unit, be it a wall or whatever). The Entity
 * is passed this array from the controller as illegalAreas ("Wilderness")
 * and when setCoords is called, it makes sure that the x and y arguments
 * are not both in any of the illegal ranges.
 */

/**
 * The top of the hierarchy, an Entity is a char with an x,y position.
 * The Representation denotes what the entity is, e.g. Zombie, Human.
 * @author James Isnor and Benji
 *
 */
public class Entity {
	
	//to cleanly index into our coord 2-tuples
	final int X = 0;
	final int Y = 1;
	
	protected Representation representation;
	protected int[] coords;

	/**
	 * The only constructor for Entities.
	 * @param representation - the character associated with what kind of Entity
	 * this is, e.g. 'Z', 'H', etc.
	 * @param coords - the initial coordinates
	 */
	public Entity(Representation representation, int[] coords) {

		if(coords == null || coords.length != 2){
			Thread.currentThread().getStackTrace();
			System.out.println("hit an error 1");
			System.exit(-1);
		}
		
		this.representation = representation;
		this.coords = coords;
	}
	
	/**
	 * Calculate new coordinates as though the entity had moved in some direction.
	 * Note that this does not actually move the Entity.
	 * @param d - the direction of motion
	 */

public int[] getMoveInDirection(Direction d) {
		
		if(d == null){
			return getCoords();			
		}
		
		switch (d) {
			case UP:
				return getShiftedCoords(0, -1);
			case DOWN:
				return getShiftedCoords(0, 1);
			case LEFT:
				return getShiftedCoords(-1, 0);
			case RIGHT:
				return getShiftedCoords(1, 0);
			default:
				Tools.printStackTrace();
				System.exit(-1);
				//unreachable, but satisfies the compiler
				return null;
		}		
	}
	
	public int[] getShiftedCoords(int xOffset, int yOffset) {
		return new int[] {coords[X] + xOffset, coords[Y] + yOffset};
	}

	
	//BELOW THIS LINE LIE GETTERS AND SETTERS (AND RED-SWEATERS)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void setCoords(int[] coords) {

		if(coords == null || coords.length != 2){
			Thread.currentThread().getStackTrace();
			System.out.println("hit an error 2");
			System.exit(-1);
		}
		
		this.coords = coords;
	}

	public int[] getCoords() {
		return new int[] {coords[X], coords[Y]};
	}
	
	public Representation getRepresentation() {
		return representation;
	}
}
