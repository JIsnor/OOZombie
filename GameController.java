import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameController {
	
	//sleep time between game frames, smaller values increase the speed/difficulty of our game
	private final static int FRAME_LENGTH = 150;
	
	//contains game state
	Model model = new Model(new File("src/GameData"));
	
	//view is an extended JFrame
	GameFrame view = new GameFrame(model.DIMENSION_X, model.DIMENSION_Y); 

	//contains coordinates of squares that need to be redrawn
	//increases redraw efficiency to prevent flicker
	ArrayList<int[]> dirtySquares = new ArrayList<int[]>();
	
	//fill the view with the contents of the model
	void initializeBoard(){		
		for(int x = 0; x < model.DIMENSION_X; x++){
			for(int y = 0; y < model.DIMENSION_Y; y++){
				int[] coords = new int[]{x, y};
				view.addSquare(coords, model.getTileTraversable(coords));
			}
		}
		view.repaint();
	}

	//execute one round of moves and interactions, then redraw the view
	private void oneRound(){
		moveEntities();
		executeAttacks();
		executeFruitConsumption();
		updateView();
	}
	
	//redraw the view to reflect moved entities, and also paint over their old positions
	//finally, update the health/progress information at the bottom of the frame
	private void updateView(){
		
		for (int[] coords : dirtySquares){
			view.addSquare(coords, model.getTileTraversable(coords));
		}
	
		dirtySquares.clear();
		
		for (Entity entity : model.entities){
			view.addCharacterImage(entity.getRepresentation(), entity.getCoords());
		}
		view.repaint();
		
		view.setText(model.player.getHealth(), model.getFruitsCollected(), model.getTotalFruits());
	}
	
	//iterate through the ArrayList of entities and move each Human and Zombie
	//check each move's legality before actually moving that entity
	private void moveEntities(){
		
		Direction direction = Direction.intToDirection(view.getKey());
		
		for(Entity entity : model.entities){
			
			
			boolean isZombie = entity instanceof Zombie;
			boolean isHuman = entity instanceof Human;
			
			if(isZombie || isHuman){
				//keep track of entity's original position so we can clean it later
				int[] originalCoords = new int[]{entity.coords[0], entity.coords[1]};
				int[] potentialCoords = null;
				
				if(isZombie){
					potentialCoords = ((Zombie) entity).getPotentialSaunter(model.getGridDeepCopy(), model.player.getCoords());
				}
				
				else if(isHuman){
					potentialCoords = ((Human) entity).getMoveInDirection(direction);		
				}
				
				//only move if the new position is a non-wall within the game board
				if (model.getTileTraversable(potentialCoords)) {
					entity.setCoords(potentialCoords);
					//mark the original square for cleanup
					dirtySquares.add(originalCoords);
				}
			}
		}
	}

	//inflict any applicable damage to the Human and remove them from the board if they are killed
	void executeAttacks(){

		//this will be set to true if the player meets a death condition
		boolean killPlayerFlag = false;
		
		for(Entity entity : model.entities){
			if(entity instanceof Zombie){
				int distanceToHuman = Tools.getDistance(entity.getCoords(), model.player.getCoords());

				//if the zombie is in biting range, it bites
				if(distanceToHuman == 1){
					killPlayerFlag = killPlayerFlag || zombieBitesHuman((Zombie) entity, model.player);
				}
				
				//if the zombie is on top of the human, it kills them outright
				killPlayerFlag = killPlayerFlag || distanceToHuman == 0;
			}
		}
		
		//remove the human from the board
		//it is necessary to do removal outside the loop lest we incur a ConcurrentModificationException
		if(killPlayerFlag){
			model.player.setHealth(0);
			model.entities.remove(model.player);
		}
	}

	//if the human is standing on top of a fruit, that fruit is consumed and removed from the board
	void executeFruitConsumption(){
		Fruit targetFruit = null;
		
		for(Entity entity: model.entities){
			if(entity instanceof Fruit){
				if(Tools.getDistance(entity.getCoords(), model.player.getCoords()) == 0){
					targetFruit = (Fruit) entity;
					break;
				}
			}
		}
		
		//it is necessary to do removal outside the loop lest we incur a ConcurrentModificationException
		if(targetFruit != null){
			humanEatsFruit(model.player, targetFruit);
		}
	}
	
	//bite interaction between a human and zombie
	boolean zombieBitesHuman(Zombie zombie, Human human){
		human.getBitten(zombie);		
		return human.getHealth() <= 0;
	}
	
	//consume interaction between a human and a fruit
	public void humanEatsFruit(Human fauna, Fruit flora) {
		fauna.eatFruit(flora);
		model.entities.remove(flora);
		model.incrementFruitsCollected();
	}
	
	//detect terminating conditions and trigger the end screens
	public boolean gameOver(){
		
		boolean anyHumans = false;
		for(Entity entity: model.entities){
			anyHumans = anyHumans || entity instanceof Human;
		}
		
		boolean anyFruits = false;
		for(Entity entity: model.entities){
			anyFruits = anyFruits || entity instanceof Fruit;
		}
		
		if(!anyFruits){
			view.showEndScreen(true);
		}
		
		if(!anyHumans){
			view.showEndScreen(false);
		}
		
		
		return !anyFruits || !anyHumans;
	}
	
	//main method, performs game rounds until the game ends
	public static void main(String[] args) {
		GameController driver = new GameController();
		driver.initializeBoard();
		int frames = 0;

		while(!driver.gameOver()){
			driver.oneRound();

			//pause after each round so the game plays at a reasonable speed
			try {
				Thread.sleep(FRAME_LENGTH);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
