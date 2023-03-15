package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import user_interface.FinalWindow;
import user_interface.UsernameWindow;

public class ScoreManager {

	private static ScoreManager instance = null;

	private ScoreManager() {
	}

	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}

	public void addScore(int points, String name) {
		UsernameWindow usernameWindow = new UsernameWindow();
		while (!usernameWindow.getComplete()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
		String username = usernameWindow.getName();
		Score score = new Score(username, points);
		
		name.replace(" ","").replace(".", "").replace(" ", "");
		File file = new File (name + "scores.dat");
		List<Score> scores = getPersistedScores(file);
		scores.add(score);
		FinalWindow finalWindow = new FinalWindow(score, scores);
		finalWindow.setVisible(true);
		Collections.sort(scores);
		persistScores(scores,file);

	}

	private void persistScores(List<Score> scores, File file) {
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Score> getPersistedScores(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			try (ObjectInputStream ois = new ObjectInputStream(fis)) {
				Object readObject = ois.readObject();
				if (readObject instanceof List) {
					List<Score> list = (List<Score>) readObject;
					return list;
				} else {
					return new ArrayList<Score>();
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			return new ArrayList<Score>();
		}
	}
}