import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameDriver {
	
	//sleep time between game frames
	private final static int FRAME_LENGTH = 150;
	
	Model model = new Model();
	GameGUI view = new GameGUI(model.DIMENSION_X, model.DIMENSION_Y); 
	ArrayList<int[]> dirtySquares = new ArrayList<int[]>();
	
	//draws empty game board with walls and floors
	private void initializeBoard(){
		for(int x = 0; x < model.DIMENSION_X; x++){
			for(int y = 0; y < model.DIMENSION_Y; y++){
				view.addSquare(new int[]{x, y}, model.grid[x][y]);
			}
		}
		view.repaint();
	}
	
	private void oneRound(){
//		moveEntities();
		updateView();
	}
	
	private void updateView(){
		
		for (int[] coords : dirtySquares){
			view.addSquare(coords, true);
		}
		dirtySquares.clear();
		
		for (Entity entity : model.entities){		
			view.addCharacter(entity.getRepresentation(), entity.getCoords());
		}
		view.repaint();
	}
	
//	private void moveEntities(){
//		for(Entity entity : model.entities){
//			
//			//keep track of entity's original position so we can clean it later
//			int[] originalCoords = new int[]{entity.coords[0], entity.coords[1]};
//			
//			if(entity.representation == Representation.ZOMBIE){
//				//continue attempting to move the zombie in randomly-generated directions until successful
//				while( !entity.move(Direction.randomDirection(), model.grid) );
//				dirtySquares.add(originalCoords);
//			}
//			
//			if(entity.representation == Representation.HUMAN){
//				if(entity.move(Direction.intToDirection(view.getKey()), model.grid)){
//					//if the player moved successfully, their previous position is now dirty
//					dirtySquares.add(originalCoords);
//				}
//			}
//		}
//	}
	
	private boolean gameOver(){
		return false;
//		TODO: put in a real terminating condition
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
