import java.util.ArrayList;


	//represents a single node in a breadth-first search
	class BFSNode{
		int x, y;
		Direction direction;
		
		public BFSNode(int x, int y, Direction direction){
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
		
		//if this node is legal and unvisited, mark it as visited and return its neighbors,
		//presumably to append to the search queue
		ArrayList<BFSNode> getNeighbors(boolean[][] grid){
			ArrayList<BFSNode> neighbors = new ArrayList<BFSNode>();
			if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y]){
				grid[x][y] = false;
				neighbors.add(new BFSNode(x + 1, y, direction));
				neighbors.add(new BFSNode(x - 1, y, direction));
				neighbors.add(new BFSNode(x, y + 1, direction));
				neighbors.add(new BFSNode(x, y - 1, direction));
			}
		return neighbors;
		}
	}