/**
 * Another entity to play in the game, fruits restore the health
 * of the human, as well as are the objective of the game
 * @author James
 *
 */
public class Fruit extends Entity {
	
	final int nutrition = 25;
	
	public Fruit(int[] coords) {
		super(Representation.FRUIT, coords);
	}
	
	public int getNutrition() {
		return nutrition;
	}
}
