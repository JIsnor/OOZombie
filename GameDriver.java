import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameDriver {
	
	//sleep time between game frames
	private final static int FRAME_LENGTH = 150;
	
	//contains game state and methods to initialize game
	Model model = new Model();
	
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
	}
	
	private void updateView(){
		
		for (int[] coords : dirtySquares){
			view.addSquare(coords, model.getTileTraversible(coords));
		}
	
		dirtySquares.clear();
		
		for (Entity entity : model.entities){		
			view.addCharacter(entity.getCharRepresentation(), entity.getCoords());
		}
		view.repaint();
	}
	
	private void moveEntities(){
		for(Entity entity : model.entities){
			
			//keep track of entity's original position so we can clean it later
			int[] originalCoords = new int[]{entity.coords[0], entity.coords[1]};
			
			if(entity instanceof Zombie){
				((Zombie) entity).saunter();
				dirtySquares.add(originalCoords);
			}
			
			if(entity instanceof Human){
				Direction movement = Direction.intToDirection(view.getKey());
				if(movement != null){
					((Human) entity).move(movement);
					dirtySquares.add(originalCoords);
				}
			}
		}
	}
	
	void zombieBitesHuman(Zombie zombie, Human human){
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
		
		boolean anyFruits = false;
		for(Entity entity: model.entities){
			anyFruits = anyFruits || entity instanceof Fruit;
		}
		
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
			System.out.println("frame " + frames++);
		}
	}	
}
