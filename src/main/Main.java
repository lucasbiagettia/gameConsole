package main;

import java.io.IOException;

import connection.SocketClientPlayable;
import games.maze.commons.MazeGame;
import playable.IPlayable;
import user_interface.FirstWindow;
import user_interface.MyConsole;

public class Main {
	public static void main(String[] args) {
		try {
			IPlayable playable = selectGame();
			@SuppressWarnings("resource")
			MyConsole console = new MyConsole(playable);
			console.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static IPlayable selectGame() {
		FirstWindow firstWindow = new FirstWindow("Por favor seleccione un Juego o Archivo");
		firstWindow.setVisible(true);

		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
			}
			if (firstWindow.getMazeGameSelected()) {
				firstWindow.dispose();
				return new MazeGame();
			}
			if (firstWindow.getFile() != null) {
				firstWindow.dispose();
				try {
					return new SocketClientPlayable(firstWindow.getFile());
				} catch (IOException e) {
					return selectGame();
				}
			}
		}
	}
}
