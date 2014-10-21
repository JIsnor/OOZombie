
public enum Representation {
	ZOMBIE, HUMAN, FRUIT, WALL;
	
	//convert a string to its corresponding Representation
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
			System.out.println("error in Representation.stringToRep():\n  invalid string " + s);
			System.exit(-1);
			return null;
		}
	}
}
