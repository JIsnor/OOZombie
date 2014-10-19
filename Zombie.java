
public class Zombie extends Entity {
	
	final int damageInflicted = 10;
	
	public Zombie(int x, int y, String name) {
		super(Representation.ZOMBIE, x, y, name);
	}
	
	public void saunter() {
		//generate random x and y offsets in the range (-1, 1)
		int xOffset = (int) (Math.ceil(Math.random() * 3) - 1.5);
		int yOffset = (int) (Math.ceil(Math.random() * 3) - 1.5);
		this.changeCoordsBy(xOffset, yOffset);
	}
	
	public int getDamageInflicted() {
		return damageInflicted;
	}

}
