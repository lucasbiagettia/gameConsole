package games.maze.commons;

import java.awt.Color;
import java.util.Optional;

import games.maze.maze_generator.Block;
import games.maze.maze_generator.MazeGenerator;
import playable.Configurations;
import playable.IPlayable;
import playable.MyKeyEvent;
import playable.Pixel;
import playable.Score;
import playable.Shape;

public class MazeGame implements IPlayable {
	private MazeGenerator mazeGenerator;
	private Block[][] maze;
	private Position characterPosition;
	private final Position mapExit;
	private Integer movements;
	private final Configurations configurations;
	private boolean isPlaying;
	private boolean isFinished;

	public MazeGame() {
		mazeGenerator = new MazeGenerator();
		configurations = new Configurations("maze game", Optional.empty(), 11, 15);
		maze = mazeGenerator.generateNewMaze(configurations.getScreenWidht(), configurations.getScreenHeight());
		movements = 0;
		isPlaying = false;
		characterPosition = new Position(0, 1);
		mapExit = new Position(configurations.getScreenWidht() - 1, configurations.getScreenHeight() - 2);
	}

	public void paintMaze() {

	}

	@Override
	public Configurations getConfigurations() {
		return configurations;
	}

	@Override
	public void play() {
		isPlaying = true;
	}

	@Override
	public Score getScore() {
		return new Score(Optional.empty(), movements);
	}

	@Override
	public Pixel[][] getBitMap() {
		Pixel[][] bitMap = new Pixel[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				switch (maze[i][j]) {
				case FREE:
					bitMap[i][j] = new Pixel(Shape.SQUARE, Color.WHITE);
					break;
				case WALL:
					bitMap[i][j] = new Pixel(Shape.SQUARE, Color.BLUE);
					break;
				}
			}
		}
		bitMap[mapExit.getX()][mapExit.getY()] = new Pixel(Shape.SQUARE, Color.RED);
		bitMap[characterPosition.getX()][characterPosition.getY()] = getPaintOfCharacter();
		return bitMap;
	}

	private Pixel getPaintOfCharacter() {
		return new Pixel(Shape.CIRCLE, Color.YELLOW);
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void receiveEvent(MyKeyEvent keyEvent) {
		if (!isPlaying) {
			return;
		}
		Position potentialPosition = null;
		switch (keyEvent) {
		case UP:
			potentialPosition = new Position(characterPosition.getX(), characterPosition.getY() - 1);
			break;
		case DOWN:
			potentialPosition = new Position(characterPosition.getX(), characterPosition.getY() + 1);
			break;
		case LEFT:
			potentialPosition = new Position(characterPosition.getX() - 1, characterPosition.getY());
			break;
		case RIGHT:
			potentialPosition = new Position(characterPosition.getX() + 1, characterPosition.getY());
			break;
		}
		if (checkWin(potentialPosition)) {
			win();
			characterPosition = potentialPosition;
		}
		boolean possible = checkPossible(potentialPosition);
		if (possible) {
			characterPosition = potentialPosition;
		}
	}

	private void win() {
		isPlaying = false;
		isFinished = true;
	}

	private boolean checkWin(Position potentialPosition) {
		return potentialPosition.getX() == mapExit.getX() && potentialPosition.getY() == mapExit.getY();
	}

	private boolean checkPossible(Position potentialPosition) {
		int x = potentialPosition.getX();
		int y = potentialPosition.getY();
		try {
			if (maze[x][y] == Block.FREE) {
				return true;
			} else {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

}
