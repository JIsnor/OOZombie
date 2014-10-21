public class Human extends Entity {

	int health;

	public Human(int[] initCoords) {
		super(Representation.HUMAN, initCoords);
		health = 100;		
	}

	public void eatFruit(Fruit fruit) {
		health += fruit.getNutrition();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int newHealth) {
		health = newHealth;
	}

	public void getBitten(Zombie zombie) {
		setHealth(health - zombie.getDamageInflicted());
	}
}
