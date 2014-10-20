/**
 * Not sure if I could make this any more clear if I tried.
 * 
 * @author James Isnor and Benji
 * 
 */

public enum Direction {
	UP, LEFT, DOWN, RIGHT;

	public static Direction randomDirection() {
		Direction[] directions = Direction.values();
		return directions[(int) (Math.random() * directions.length)];
	}
	
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
			return null;
		}
	}
}
