
public class Fruit extends Entity {
	
	private final int nutrition = 5;
	
	public Fruit(int x, int y) {
		super(Representation.FRUIT, x, y, "pear");
	}
	
	public int getNutrition() {
		return nutrition;
	}
}
