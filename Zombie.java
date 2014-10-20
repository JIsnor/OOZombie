
public class Zombie extends Entity {
	
	final int damageInflicted = 10;
	
	public Zombie(int x, int y, String name) {
		super(Representation.ZOMBIE, x, y, name);
	}
	
	public int[] saunter() {
		//generate random x and y offsets in the range (-1, 1)
		int xOffset = ((int) (Math.random()*2.5)) - 1;
		int yOffset = ((int) (Math.random()*2.5)) - 1;
		
		return new int[]{xOffset, yOffset};
	}
	
	public int getDamageInflicted() {
		return damageInflicted;
	}

}
