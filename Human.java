public class Human extends Entity {

	int health;

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

	public void getBitten(Zombie zombie) {
		setHealth(health - zombie.getDamageInflicted());
	}

	// TODO: find a way to handle moving off the board or into walls
	public void move(Direction d) {
		
		switch (d) {
			case UP:
				changeCoordsBy(0, 1);
			case DOWN:
				changeCoordsBy(0, -1);
			case LEFT:
				changeCoordsBy(-1, 0);
			case RIGHT:
				changeCoordsBy(1, 0);
		}
	}
}
