
public enum Representation {
	ZOMBIE, HUMAN, WALL, FRUIT;
	
	public char getCharRep() {
		switch(this) {
		
		case ZOMBIE:
			return 'Z';
		case HUMAN:
			return 'H';
		case WALL:
			return 'W';
		case FRUIT:
			return 'F';
		default:
			return 'U';
		}
	}
	
	public static Representation stringToRep(String s) {
		
		switch(s) {
		
		case "Z":
		case "z":
			return ZOMBIE;
		case "H":
		case "h":
			return HUMAN;
		case "F":
		case "f":
			return FRUIT;
		case "W":
		case "w":
			return WALL;
		default:
			return null;
		}
	}
}
