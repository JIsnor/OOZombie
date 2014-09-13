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
 * @author James Isnor
 *
 */
public class Entity {
	
	protected Representation representation;
	protected int x, y;
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
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	//BELOW THIS LINE LIE GETTERS AND SETTERS (AND RED-SWEATERS)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//TODO: Change to Benji's wacky array of coords
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public char getRepresentation() {
		return representation.getCharRep();
	}
	
	public int[] getCoords() {
		return new int[]{x,y};
	}

}
