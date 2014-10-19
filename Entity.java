/*
 * Idea for avoiding unit-collision: create array of objects
 * which are ranges of x, y positions (i.e. 4 numbers to make a rectangular
 * area which is the unit, be it a wall or whatever). The Entity
 * is passed this array from the controller as illegalAreas ("Wilderness")
 * and when setCoords is called, it makes sure that the x and y arguments
 * are not both in any of the illegal ranges.
 */

/**
 * The top of the hierarchy, an Entity is a char with an x,y position
 * and a name. The Representation denotes what the entity is, e.g. Zombie, Human.
 * Additionally, they have names, e.g. Hunter Bill --> (Representation.HUMAN, x, y, "Bill")
 * @author James Isnor and Benji
 *
 */
public class Entity {
	
	//to use with our array of coords
	private final int X = 0;
	private final int Y = 1;
	
	protected Representation representation;
	protected int[] coords;
	protected String name = null;
	
	/**
	 * The only constructor for Entities.
	 * @param representation - the character associated with what kind of Entity
	 * this is, e.g. 'Z', 'H', etc.
	 * @param x - the coordinate
	 * @param y - the y coordinate
	 * @param name - the Entity's name
	 */
	public Entity(Representation representation, int x, int y, String name) {
		this.representation = representation;
		this.name = name;
		coords = new int[2];
		coords[X] = x;
		coords[Y] = y;
	}
	
	/**
	 * Change the coordinates of the Entity by the specified
	 * amounts. 
	 * @param x the value to add to the x coord of this Entity
	 * @param y the value to add to the y coord of this Entity
	 */
	public void changeCoordsBy(int xOffset, int yOffset) {
		coords[X] += xOffset;
		coords[Y] += yOffset;
	}
	
	//BELOW THIS LINE LIE GETTERS AND SETTERS (AND RED-SWEATERS)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void setCoords(int[] coords) {
		this.coords = coords;
	}
		
	public char getCharRepresentation() {
		return representation.getCharRep();
	}
	
	public int[] getCoords() {
		return coords;
	}
}
