import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameDriver {
	
	//sleep time between game frames
	private final static int FRAME_LENGTH = 150;
	
	//contains game state and methods to initialize game
	Model model = new Model(new File("src/GameData"));
	
	//view is an extension of a JFrame
	GameFrame view = new GameFrame(model.DIMENSION_X, model.DIMENSION_Y); 

	//contains coordinates of squares that need to be redrawn
	ArrayList<int[]> dirtySquares = new ArrayList<int[]>();
	
	//fill the view with the contents of the model
	void initializeBoard(){
		
		for(int x = 0; x < model.DIMENSION_X; x++){
			for(int y = 0; y < model.DIMENSION_Y; y++){
				int[] coords = new int[]{x, y};
				view.addSquare(coords, model.getTileTraversible(coords));
			}
		}
		view.repaint();
	}
	
	private void oneRound(){
		moveEntities();
		updateView();
		eatHumansAndFruit();
	}
	
	private void updateView(){
		
		for (int[] coords : dirtySquares){
			view.addSquare(coords, model.getTileTraversible(coords));
		}
	
		dirtySquares.clear();
		
		for (Entity entity : model.entities){
//			System.out.println(entity.getCharRepresentation());
			view.addCharacterImage(entity.getCharRepresentation(), entity.getCoords());
		}
		view.repaint();
	}
	
	private void moveEntities(){
		
		for(Entity entity : model.entities){
			
			//keep track of entity's original position so we can clean it later
			int[] originalCoords = new int[]{entity.coords[0], entity.coords[1]};
			int newX = 0, newY = 0;
			
			if(entity instanceof Zombie){
				//Keep Zombie on the board
				int[] potentialOffsets;
				potentialOffsets = ((Zombie) entity).saunter();
				newX = originalCoords[0] + potentialOffsets[0];
				newY = originalCoords[1] + potentialOffsets[1];
				
				//remove this if - zombies can walk through walls
				if (canMove(newX, newY)) {
					entity.setCoords(newX, newY, model.DIMENSION_X,
							model.DIMENSION_Y);
					dirtySquares.add(originalCoords);
				}
			}
			
			if(entity instanceof Human){
				Direction movement = Direction.intToDirection(view.getKey());
				
				if(movement != null){
					
					//Wall detection
					switch(movement) {
					
					case UP:
						newX = originalCoords[0];
						newY = originalCoords[1] - 1;
						break;
					case RIGHT:
						newX = originalCoords[0] + 1;
						newY = originalCoords[1];
						break;
					case LEFT:
						newX = originalCoords[0] - 1;
						newY = originalCoords[1];
						break;
					case DOWN:
						newX = originalCoords[0];
						newY = originalCoords[1] + 1;
						break;
					}
					
					//wall detection cntd.
					if (canMove(newX, newY)) {
						//					System.out.println("Moved: " + movement);
						//keep humans in check
						((Human) entity).move(movement, model.DIMENSION_X, model.DIMENSION_Y);
						
					}
					dirtySquares.add(originalCoords);
				}
			}
		}
	}
	
	//Here to prevent movement into walls
	boolean canMove(int potentialX, int potentialY) {
		
		for(int[] wall : model.walls) {
			
			if(potentialX == wall[0] && potentialY == wall[1]) {
				return false;
			}
		}
		
		return true;
	}

	void eatHumansAndFruit() {
		
		ArrayList<Entity> toRemove = new ArrayList<Entity>();
		
		for(Entity e : model.entities) {
			
			if(e instanceof Human) {
				
				for(Entity e2 : model.entities) {
					if(e2 instanceof Zombie 
							&& e.getCoords()[0] == e2.getCoords()[0] 
							&& e.getCoords()[1] == e2.getCoords()[1]) {
						((Human) e).getBitten((Zombie) e2);
						toRemove.add(e);
//						zombieBitesHuman((Zombie) e2, (Human) e); concurrent modification
						
					}
					
					if(e2 instanceof Fruit 
							&& e.getCoords()[0] == e2.getCoords()[0] 
							&& e.getCoords()[1] == e2.getCoords()[1]) {
						((Human) e).eatFruit((Fruit) e2);;
						toRemove.add(e2);
						dirtySquares.add(((Fruit) e2).coords);
//						humanEatsFruit((Human) e, (Fruit) e2);
					}
				}
			}
		}
		
		//This is necessary to avoid concurrent modification
		for(Entity e : toRemove) {
			
			model.entities.remove(e);
		}
	}

	void zombieBitesHuman(Zombie zombie, Human human){
		System.out.println("GET BIT");
		human.getBitten(zombie);
		if (human.getHealth() <= 0){
			model.entities.remove(human);
		}
	}
	
	public void humanEatsFruit(Human fauna, Fruit flora) {
		fauna.eatFruit(flora);
		model.entities.remove(flora);
	}
	
	public boolean gameOver(){
		
		//I arbitrarily decided the following loss conditions:
		//if no human is on the board, game over man (loss)
		//if no fruit is on the board, game over man (win)

		boolean anyHumans = false;
		for(Entity entity: model.entities){
			anyHumans = anyHumans || entity instanceof Human;
		}
		
		System.out.println("Any Humans? "+ anyHumans);
		
		boolean anyFruits = false;
		for(Entity entity: model.entities){
			anyFruits = anyFruits || entity instanceof Fruit;
		}
		
		System.out.println("Any Fruit? "+ anyFruits);
		
		return !anyFruits || !anyHumans;
	}
	
	public static void main(String[] args) {
		GameDriver driver = new GameDriver();
		driver.initializeBoard();
		int frames = 0;
		
		while(!driver.gameOver()){
			driver.oneRound();

			try {
				Thread.sleep(FRAME_LENGTH);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("frame " + frames++);
		}
	}	
}
