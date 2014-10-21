/**
 * Not sure if I could make this any more clear if I tried.
 * 
 * @author James Isnor and Benji
 * 
 */

public enum Direction {
	UP, LEFT, DOWN, RIGHT;
	
	/**
	 * Deals with keyboard input
	 * @param key keystroke int, passed from KeyEvent
	 * @return direction corresponding to keystroke int, if applicable int
	 */
	public static Direction intToDirection(int key){
		switch(key){
		case 37:
			return Direction.LEFT;
		case 38:
			return Direction.UP;
		case 39:
			return Direction.RIGHT;
		case 40:
			return Direction.DOWN;
		default:
			//this case is permissible because our game should be able to ignore garbage key inputs
			return null;
		}
	}
}
