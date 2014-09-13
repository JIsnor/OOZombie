
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
}
