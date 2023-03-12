package main;

import playable.Configurations;
import playable.IPlayable;
import playable.Score;

public class Main {

	public static void main(String[] args) {
		// 1- Seleccionar playable mediante interfaz gráfica
		IPlayable playable = selectGame();
		// 2- Pedir configuraciones al playable
		Configurations configurations = playable.getConfigurations();
		// 3- Preparar todo con las configuraciones
		prepareConsole(configurations);
		// 4- Jugar
		playable.play();
		// 5- Recibir resultados
		Score score = playable.getScore();
		// 6- Mostrar resultados y persistirlos en orden
		showResultsAndPersist();
		// 7- Repeat.
		askForAnotherRound();
		
		// TODO Auto-generated method stub

	}

	private static void askForAnotherRound() {
		// TODO Auto-generated method stub
		
	}

	private static void showResultsAndPersist() {
		// TODO Auto-generated method stub
		
	}

	private static void prepareConsole(Configurations configurations) {
		// TODO Auto-generated method stub
		
	}

	private static IPlayable selectGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
