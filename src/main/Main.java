package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

import games.maze.commons.MazeGame;
import games.maze.maze_generator.MazeGenerator;
import playable.Configurations;
import playable.IPlayable;
import playable.Pixel;
import playable.Score;

public class Main {
	private static JFrame gameWindow;
	private static IPlayable playable;

	public static void main(String[] args) {
		// 1- Seleccionar playable mediante interfaz gráfica
		playable = selectGame();;
		// 2- Pedir configuraciones al playable
		Configurations configurations = playable.getConfigurations();
		// 3- Preparar todo con las configuraciones
		gameWindow = prepareConsole(configurations);
		// 4- Jugar
		playable.play();
		gameWindow.setVisible(true);
		gameWindow.repaint();
		// 5- Recibir resultados
		Pixel[][] bitMap = playable.getBitMap();

		Score score = playable.getScore();
		// 6- Mostrar resultados y persistirlos en orden
		showResultsAndPersist(score);
		// 7- Repeat.
		askForAnotherRound();

		// TODO Auto-generated method stub

	}

	private static void askForAnotherRound() {
		// TODO Auto-generated method stub

	}

	private static void showResultsAndPersist(Score score) {
		// TODO Auto-generated method stub

	}

	private static JFrame prepareConsole(Configurations configurations) {
		JFrame gameWindow = new JFrame(configurations.getName());
		gameWindow.setSize(configurations.getScreenWidht() * 40, (configurations.getScreenHeight()+1) * 40);
		gameWindow.setLocation(300, 200);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameComponent gameComponent = new GameComponent(playable);
		gameWindow.addKeyListener(gameComponent);
		gameWindow.add(gameComponent);
		return gameWindow;
	}

	private static IPlayable selectGame() {
		return new MazeGame();
	}

}
