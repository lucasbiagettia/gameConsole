package user_interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UsernameWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6834607007656610483L;
	private JTextField usernameField;
	String username = "";
	boolean complete = false;

	public UsernameWindow() {
		super("Introducir nombre de usuario");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel usernameLabel = new JLabel("Nombre de usuario:");
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameLabel.setHorizontalAlignment(JLabel.CENTER);

		usernameField = new JTextField(20);
		usernameField.addActionListener(this);

		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(usernameLabel);
		panel.add(usernameField);

		getContentPane().add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = usernameField.getText();
	}
	
	public String getUserName() {
		return username;
	}
	public boolean getComplete() {
		return complete;
	}
}