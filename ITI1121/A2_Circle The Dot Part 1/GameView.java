import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 2

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It
 * extends <b>JFrame</b> and lays out an instance of <b>BoardView</b> (the
 * actual game) and two instances of JButton. The action listener for the
 * buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {
	private static final long serialVersionUID = 1L;
	private BoardView bView;

	/**
	 * Constructor used for initializing the Frame
	 * 
	 * @param model
	 *            the model of the game (already initialized)
	 * @param gameController
	 *            the controller
	 */

	public GameView(GameModel model, GameController gameController) {

		JFrame mainFrame = new JFrame("Dot");

		bView = new BoardView(model, gameController);
		mainFrame.add(bView, BorderLayout.CENTER);

		JButton buttonReset = new JButton("Reset");
		buttonReset.setFocusPainted(false);
		buttonReset.addActionListener(gameController);

		JButton buttonQuit = new JButton("Quit");
		buttonQuit.setFocusPainted(false);
		buttonQuit.addActionListener(gameController);

		JPanel controlReset = new JPanel();
		controlReset.setBackground(Color.WHITE);
		controlReset.add(buttonReset);

		JPanel controlQuit = new JPanel();
		controlQuit.setBackground(Color.WHITE);
		controlQuit.add(buttonQuit);

		// Adds both Reset and Quit buttons at bottom of frame
		JPanel controlPanel = new JPanel();
		controlPanel.add(controlReset);
		controlPanel.add(controlQuit);
		mainFrame.add(controlPanel, BorderLayout.SOUTH);

		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Getter method for the attribute board.
	 * 
	 * @return a reference to the BoardView instance
	 */

	public BoardView getBoardView() {
		return bView;
	}
}
