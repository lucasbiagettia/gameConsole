package user_interface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import playable.Score;

public class FinalWindow extends JFrame implements ActionListener  {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8298629326610278703L;
	JButton exitButton = new JButton("Exit");
    JButton replayButton = new JButton("Replay");

	public FinalWindow(Score score, List<Score> scores) {
        super("Finalización del juego");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel scoreLabel = new JLabel(score.getUserName()+ " has obtenido " + score.getScore() + " puntos.");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelPuntuacion = new JPanel(new BorderLayout());
        panelPuntuacion.add(scoreLabel, BorderLayout.CENTER);

        JLabel etiquetaUsuarios = new JLabel("Usuarios y Puntajes:");
        etiquetaUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaUsuarios.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);
        buttonPanel.add(replayButton);
        exitButton.addActionListener(this);
        replayButton.addActionListener(this);

        getContentPane().add(buttonPanel);
        
        int grid = 5;
        if (scores.size() <5) {
        	grid = scores.size();
        }
        JPanel panelUsuarios = new JPanel(new GridLayout(grid, 2));
        for (int i = 0; i < scores.size() && i <5 ; i++) {
            JLabel userLabel = new JLabel(scores.get(i).getUserName());
            JLabel scoreIntLabel = new JLabel(String.valueOf(scores.get(i).getScore().toString()));
            panelUsuarios.add(userLabel);
            panelUsuarios.add(scoreIntLabel);
        }

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelPuntuacion, BorderLayout.NORTH);
        panelPrincipal.add(etiquetaUsuarios, BorderLayout.CENTER);
        panelPrincipal.add(panelUsuarios, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == replayButton) {
			dispose();
			Main.main(null);
			
		}
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
		
	}
}