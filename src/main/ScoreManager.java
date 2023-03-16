package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import user_interface.FinalWindow;

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
		String username = askName();
		Score score = new Score(username, points);
		String replace = name.replace(" ", "").replace(".", "").replace(" ", "");
		File file = new File(replace + "scores.dat");
		List<Score> scores = getPersistedScores(file);
		scores.add(score);
		FinalWindow finalWindow = new FinalWindow(score, scores);
		finalWindow.setVisible(true);
		Collections.sort(scores);
		persistScores(scores, file);
	}

	private String askName() {
		String userName = "";
		userName = JOptionPane.showInputDialog(null, "Introduce tu nombre de usuario:", "Nombre de usuario",
				JOptionPane.PLAIN_MESSAGE);
		return userName;
	}

	private void persistScores(List<Score> scores, File file) {
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(scores);
			}
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
					@SuppressWarnings("unchecked")
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