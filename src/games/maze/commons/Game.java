package games.maze.commons;

import java.awt.Color;
import java.util.Optional;

import games.maze.elements.YellowBall;
import games.maze.maze_generator.Block;
import games.maze.maze_generator.MazeGenerator;
import playable.Configurations;
import playable.IPlayable;
import playable.Pixel;
import playable.Score;
import playable.Shape;

public class Game implements IPlayable {
	MazeGenerator mazeGenerator;
	Block[][] maze;
	YellowBall character;
	Integer movements;
	Configurations configurations;

	public Game() {
		mazeGenerator = new MazeGenerator();
		maze = mazeGenerator.getMaze();
		character = new YellowBall();
		configurations = new Configurations("maze game", Optional.empty(), 15, 21);
		movements = 0;
	}

	public void paintMaze() {

	}

	@Override
	public Configurations getConfigurations() {
		return configurations;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

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
		// Todo: personaje y objetivo;
		return bitMap;
	}

}
