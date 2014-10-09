
public class Zombie extends Entity {
	
	protected final int damageInflicted = 10;
	
	public Zombie(int x, int y, String name) {
		super(Representation.ZOMBIE, x, y, name);
	}
	
	public void saunter() {
		//the non-determinism, tho
		this.changeCoordsBy((int) (Math.random()*10), (int) (Math.random()*10));
	}
	
	public int getDamageInflicted() {
		return damageInflicted;
	}

}
