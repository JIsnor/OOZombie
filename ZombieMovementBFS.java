import java.util.ArrayList;


public class ZombieMovementBFS {
	
	public static Direction getDirectionTowardsHuman(boolean[][] grid, int[] start, int[] end){

	//in order to slow down the zombie, it will have a 50% chance of standing still each turn
	if(Math.random() > .5){
		return null;
	}
		
	//initialize our BFS queue with the cardinal directions
	ArrayList<BFSNode> searchQueue = new ArrayList<BFSNode>();
	searchQueue.add(new BFSNode(start[0] + 1, start[1], Direction.RIGHT));
	searchQueue.add(new BFSNode(start[0] - 1, start[1], Direction.LEFT));
	searchQueue.add(new BFSNode(start[0], start[1] + 1, Direction.DOWN));
	searchQueue.add(new BFSNode(start[0], start[1] - 1, Direction.UP));
	
	Direction result = searchForDirection(searchQueue, grid, end);
	
	return result;
	}
	
	private static Direction searchForDirection(ArrayList<BFSNode> searchQueue, boolean[][] grid, int[] end){

		//if the first element in our queue matches our endpoint, return its direction, otherwise add its children to the end of our queue
		//and remove pop the first element
		while(searchQueue.size() > 0){
			
			BFSNode currNode = searchQueue.get(0);
			
			if(end[0] == currNode.x && end[1] == currNode.y)
				return currNode.direction;
			
			searchQueue.addAll(currNode.getNeighbors(grid));
			searchQueue.remove(0);	
		}
		return null;
	}
}
