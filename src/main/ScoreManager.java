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

import playable.Score;
import user_interface.FinalWindow;

public class ScoreManager {
	File file = new File("scores.dat");

	private static ScoreManager instance = null;

	private ScoreManager() {
	}

	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}

	public void addScore(Score score) {
		List<Score> scores = getPersistedScores();
		scores.add(score);
		FinalWindow finalWindow = new FinalWindow(score, scores);
		finalWindow.setVisible(true);
		Collections.sort(scores);
		persistScores(scores);

	}

	private void persistScores(List<Score> scores) {
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(scores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Score> getPersistedScores() {
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