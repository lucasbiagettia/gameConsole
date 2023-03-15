package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FirstWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3596971360279837509L;
	JButton fileButton;
	JLabel fileLabel;
	JFileChooser fileChooser;
	JButton mazeGameButton;
	File file;

	public Boolean getMazeGameSelected() {
		return mazeGameSelected;
	}

	public File getFile() {
		return file;
	}

	Boolean mazeGameSelected = false;

	public FirstWindow(String title) {
		super(title);
		setSize(400, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mazeGameButton = new JButton("Juego del Laberinto");
		mazeGameButton.addActionListener(this);

		fileButton = new JButton("Seleccionar Archivo");
		fileButton.addActionListener(this);

		fileLabel = new JLabel("Archivo seleccionado: ");

		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(fileButton, BorderLayout.WEST);
		panel.add(fileLabel, BorderLayout.SOUTH);
		panel.add(mazeGameButton, BorderLayout.EAST);

		getContentPane().add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileButton) {
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				fileLabel.setText("Archivo seleccionado: " + file.getName());
			} else {
				fileLabel.setText("Archivo no seleccionado.");
			}
		} else if (e.getSource() == mazeGameButton) {
			mazeGameSelected = true;
		}

	}
}