
public enum Representation {
	ZOMBIE, HUMAN, WALL;
	
	public char getCharRep() {
		switch(this) {
		
		case ZOMBIE:
			return 'Z';
		case HUMAN:
			return 'H';
		case WALL:
			return 'W';
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
		default:
			return null;
		}
	}
}
