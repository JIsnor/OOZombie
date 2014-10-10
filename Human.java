
public class Human extends Entity {
	
	private int health;

	public Human(int x, int y, String name) {
		super(Representation.HUMAN, x, y, name);
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
	
	public void getBitten(Zombie zombie){
		setHealth(health - zombie.getDamageInflicted());
	}
}
