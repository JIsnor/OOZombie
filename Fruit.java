
public class Fruit extends Entity {
	
	final int nutrition = 25;
	
	public Fruit(int[] coords) {
		super(Representation.FRUIT, coords);
	}
	
	public int getNutrition() {
		return nutrition;
	}
}
