/*
 * Idea for avoiding unit-collision: create array of objects
 * which are ranges of x, y positions (i.e. 4 numbers to make a rectangular
 * area which is the unit, be it a wall or whatever). The Entity
 * is passed this array from the controller as illegalAreas ("Wilderness")
 * and when setCoords is called, it makes sure that the x and y arguments
 * are not both in any of the illegal ranges.
 */

/**
 * The top of the hierarchy, an Entity is a char with an x,y position and a
 * name. The Representation denotes what the entity is, e.g. Zombie, Human.
 * Additionally, they have names, e.g. Hunter Bill --> (Representation.HUMAN, x,
 * y, "Bill")
 * 
 * @author James Isnor
 * 
 */
public class Entity {

	// to use with our array of coords
	private final int X = 0;
	private final int Y = 1;

	protected Representation representation;
	protected int[] coords;
	protected String name = null;

	/**
	 * The only constructor for Entities.
	 * 
	 * @param representation
	 *            - the character associated with what kind of Entity this is,
	 *            e.g. 'Z', 'H', etc.
	 * @param x
	 *            - the coordinate
	 * @param y
	 *            - the y coordinate
	 * @param name
	 *            - the Entity's name
	 */
	public Entity(Representation representation, int x, int y, String name) {
		this.representation = representation;
		this.name = name;
		coords = new int[2];
		coords[X] = x;
		coords[Y] = y;
	}

	public boolean move(Direction direction, boolean[][] grid) {
		if (direction == null) {
			return false;
		}

		boolean valid = false;
		switch (direction) {
		case UP:
			// valid we aren't at the top edge and the square above us isn't a wall
			valid = (coords[Y] != grid[0].length - 1)
					&& (grid[coords[X]][coords[Y] + 1]);
			if (valid) {
				coords[Y]++;
			}
			break;
		case DOWN:
			// valid we aren't at the bottom edge and the square below us isn't a wall
			valid = (coords[Y] != 0) && (grid[coords[X]][coords[Y] - 1]);
			if (valid) {
				coords[Y]--;
			}
			break;
		case LEFT:
			// valid we aren't at the left edge and the square to our left isn't a wall
			valid = (coords[X] != 0) && (grid[coords[X] - 1][coords[Y]]);
			if (valid) {
				coords[X]--;
			}
			break;
		case RIGHT:
			// valid we aren't at the right edge and the square to our right isn't a wall
			valid = (coords[X] != grid[0].length - 1) && (grid[coords[X] + 1][coords[Y]]);
			if (valid) {
				coords[X]++;
			}
			break;
		}
		return valid;
	}

	// GETTERS AND SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public char getRepresentation() {
		return representation.getCharRep();
	}

	public int[] getCoords() {
		return new int[] { coords[X], coords[Y] };
	}
}
