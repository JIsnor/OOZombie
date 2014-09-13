/**
 * A class designed to simulate a Zombie
 * @author James Isnor
 *
 */
public class Zombie extends Entity {

	/**
	 * Creates a Zombie
	 * @param x - x position of the Zombie
	 * @param y - y position of the Zombie
	 * @param name - Zombie's name
	 */
	public Zombie(int x, int y, String name) {
		super(Representation.ZOMBIE, x, y, name);
	}

	/**
	 * Move the Zombie in a random direction. Considers and upper bound
	 * for the x and y position, which is to take into account the 
	 * dimensions of the game board
	 * @param upperBoundX - The upper bound on the x coordinate
	 * @param upperBoundY - the upper bound on the y coordinate
	 */
	public void move(int upperBoundX, int upperBoundY) {
		int randX = (int) (Math.random() * upperBoundX);
		int randY = (int) (Math.random() * upperBoundY);
		
		//TODO: add if statements for illegal moves
		setCoords(randX, randY);
	}
}
