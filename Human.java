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

	public void move(Direction d, int boundX, int boundY) {
		switch (d) {
			case UP:
				changeCoordsBy(0, -1, boundX, boundY);
//				System.out.println("Moved up!");
				break;
			case DOWN:
				changeCoordsBy(0, 1, boundX, boundY);
//				System.out.println("Moved down!");
				break;
			case LEFT:
				changeCoordsBy(-1, 0, boundX, boundY);
//				System.out.println("Moved left!");
				break;
			case RIGHT:
				changeCoordsBy(1, 0, boundX, boundY);
//				System.out.println("Moved right!");
				break;
		}
	}
}
