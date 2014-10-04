
public class Zombie extends Entity {
	
	private final int damageInflicted = 10;
	
	public Zombie(int x, int y, String name) {
		super(Representation.ZOMBIE, x, y, name);
	}
	
	public void saunter() {
		
	}
	
	public int getDamageInflicted() {
		return damageInflicted;
	}

}
