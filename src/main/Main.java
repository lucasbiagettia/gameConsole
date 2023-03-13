package main;


import javax.swing.JFrame;
import games.maze.commons.MazeGame;
import playable.Configurations;
import playable.IPlayable;
import playable.Score;

public class Main {
	private static JFrame gameWindow;
	private static IPlayable playable;

	public static void main(String[] args) {
		// 1- Seleccionar playable mediante interfaz gráfica
		IPlayable playable = selectGame();
		// 2- Pedir configuraciones al playable
		MyConsole console = new MyConsole(playable);
		console.start();
		// 3- Preparar todo con las configuraciones
		// 4- Jugar
		// 5- Recibir resultados
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


	private static IPlayable selectGame() {
		return new MazeGame();
	}

}
