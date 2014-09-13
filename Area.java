
public class Area {
	
	int lowerX, upperX, lowerY, upperY;
	
	/**
	 * A simple wrapper class, designed to help deal with illegal movements,
	 * i.e. walls (Zombies and Humans cannot walk through walls).
	 * @param x 
	 * @param x2
	 * @param y
	 * @param y2
	 */
	public Area(int lowerX, int upperX, int lowerY, int upperY) {
		
		this.lowerX = lowerX;
		this.upperX = upperX;
		this.lowerY = lowerY;
		this.upperY = upperY;
	}
	
	/**
	 * Takes in two ints, x, y, and checks if both of them are
	 * within this Area.
	 * @param x the specified x
	 * @param y the specified y
	 * @return whether or not both of the numbers given is within
	 * this Area.
	 */
	public boolean hasInArea(int x, int y) {
		//if either the input x or y is in the area
		return (x >= lowerX && x <= upperX) && (y >= lowerY && y <= upperY);
	}

}
