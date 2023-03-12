package games.maze.maze_generator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {

	private Stack<Node> stack = new Stack<>();
	private Random rand = new Random();

	public MazeGenerator() {}

	public Block[][] generateNewMaze(int theColumns, int theRows) {
		Block[][] maze = new Block[theColumns][theRows];
		stack.push(new Node(0, 0));
		while (!stack.empty()) {
			Node next = stack.pop();
			if (validNextNode(next, maze, theColumns, theRows)) {
				maze[next.y][next.x] = Block.WALL;

				ArrayList<Node> neighbors = findNeighbors(next, theColumns, theRows);
				randomlyAddNodesToStack(neighbors);
			}
		}
		return maze;
	}

	private boolean validNextNode(Node node, Block[][] maze, int columns, int rows) {
		int numNeighboringOnes = 0;
		for (int y = node.y - 1; y < node.y + 2; y++) {
			for (int x = node.x - 1; x < node.x + 2; x++) {
				if (pointOnGrid(x, y, columns, rows) && pointNotNode(node, x, y) && maze[y][x] == Block.WALL) {
					numNeighboringOnes++;
				}
			}
		}
		return (numNeighboringOnes < 3) && maze[node.y][node.x] != Block.WALL;
	}

	private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
		int targetIndex;
		while (!nodes.isEmpty()) {
			targetIndex = rand.nextInt(nodes.size());
			stack.push(nodes.remove(targetIndex));
		}
	}

	private ArrayList<Node> findNeighbors(Node node, int columns, int rows) {
		ArrayList<Node> neighbors = new ArrayList<>();
		for (int y = node.y - 1; y < node.y + 2; y++) {
			for (int x = node.x - 1; x < node.x + 2; x++) {
				if (pointOnGrid(x, y, columns, rows) && pointNotCorner(node, x, y) && pointNotNode(node, x, y)) {
					neighbors.add(new Node(x, y));
				}
			}
		}
		return neighbors;
	}

	private Boolean pointOnGrid(int x, int y, int columns, int rows) {
		return x >= 0 && y >= 0 && x < columns && y < rows;
	}

	private Boolean pointNotCorner(Node node, int x, int y) {
		return (x == node.x || y == node.y);
	}

	private Boolean pointNotNode(Node node, int x, int y) {
		return !(x == node.x && y == node.y);
	}
}