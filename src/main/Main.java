package main;

import java.io.IOException;

import javax.swing.JFrame;
import games.maze.commons.MazeGame;
import playable.Configurations;
import playable.IPlayable;
import playable.Score;
import playable.SocketClientPlayable;

public class Main {
	private static JFrame gameWindow;
	private static IPlayable playable;

	public static void main(String[] args) {
		try {
			// 1- Seleccionar playable mediante interfaz gráfica
			IPlayable playable = selectGame();
			// 2- Pedir configuraciones al playable
			MyConsole console = new MyConsole(playable);
			console.start();
			// 3- Preparar todo con las configuraciones
			// 4- Jugar
			// 5- Recibir resultados
			Score score;
			score = playable.getScore();
			// 6- Mostrar resultados y persistirlos en orden
			showResultsAndPersist(score);
			// 7- Repeat.
			askForAnotherRound();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	private static void askForAnotherRound() {
		// TODO Auto-generated method stub

	}

	private static void showResultsAndPersist(Score score) {
		// TODO Auto-generated method stub

	}

	private static IPlayable selectGame() {
		return new MazeGame();
	}

}
