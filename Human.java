/**
 * A class designed to Simulate a human
 * @author James Isnor
 *
 */
public class Human extends Entity {

	/**
	 * Creates a Human Entity
	 * @param x - x position of Human
	 * @param y - y position of Human
	 * @param name - name of Human
	 */
	public Human(int x, int y, String name) {
		super(Representation.HUMAN, x, y, name);
	}
	
	/**
	 * Move the Human by the specified amount in the specified 
	 * Direction
	 * @param amount - amount to move by (should be > 0)
	 * @param d - Direction to move in
	 * @return true or false, based on whether or not the move
	 * was successful (we don't want to be able to walk into walls)
	 */
	public boolean move(int amount, Direction d) {
		switch(d) {
		case UP:
			//increase y
		case LEFT:
			//decrease x
		case RIGHT:
			//decrease y
		case DOWN:
			//increase x
		default:
			System.err.println("Unknown direction found when moving human: " + name);
			return false;
		}
	}
	
	/**
	 * Presumably moving by 1 will be a fairly common expression.
	 * @param d - Direction to move in
	 * @return true or false, based on return of move(1, d). 
	 * SEE move(int, Direction) for more
	 */
	public boolean move(Direction d) {
		return move(1, d);
	}
}
