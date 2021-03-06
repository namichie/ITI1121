import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 3

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

	/**
	 * Reference to the view of the board
	 */
	private BoardView board;
	private GameModel gameModel;
	
	/**
	 * Reference to the Redo & Undo buttons
	 */
	private JButton buttonRedo;
	private JButton buttonUndo;

	/**
	 * Constructor used for initializing the Frame
	 * 
	 * @param model
	 *            the model of the game (already initialized)
	 * @param gameController
	 *            the controller
	 */

	public GameView(GameModel model, GameController gameController) {
		super("Circle the Dot");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);

		gameModel = model;
		board = new BoardView(model, gameController);
		add(board, BorderLayout.CENTER);

		JButton buttonReset = new JButton("Reset");
		buttonReset.setFocusPainted(false);
		buttonReset.addActionListener(gameController);

		JButton buttonExit = new JButton("Quit");
		buttonExit.setFocusPainted(false);
		buttonExit.addActionListener(gameController);

		buttonUndo = new JButton("Undo");
		buttonUndo.setFocusPainted(false);
		buttonUndo.setEnabled(false); 
		buttonUndo.addActionListener(gameController);

		buttonRedo = new JButton("Redo");
		buttonRedo.setFocusPainted(false);
		buttonRedo.setEnabled(false); 
		buttonRedo.addActionListener(gameController);

		JPanel control = new JPanel();
		control.setBackground(Color.WHITE);
		control.add(buttonUndo);
		control.add(buttonRedo);
		control.add(buttonReset);
		control.add(buttonExit);
		add(control, BorderLayout.SOUTH);

		pack();
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Setter method for the Game Model. Sets the GameModel
	 * 
	 * @param model
	 */
	public void setModel(GameModel model) {
		gameModel = model;
		board.setModel(model);
	}

	/**
	 * Resets (disables) the Undo and Redo buttons
	 * 
	 */
	public void resetButton() {
		buttonUndo.setEnabled(false);
		buttonRedo.setEnabled(false);
	}

	/**
	 * Enables the Undo button
	 * 
	 */
	public void setUndo(boolean enabledValue) {
		buttonUndo.setEnabled(enabledValue);
	}

	/**
	 * Enables the Redo button
	 * 
	 */
	public void setRedo(boolean enabledValue) {
		buttonRedo.setEnabled(enabledValue);
	}

	/**
	 * Updates the board view
	 */
	public void update() {
		board.update();
	}
	
	/**
	 * Updates the board view with the current model
	 * @param gameModel
	 */
	public void update(GameModel gameModel) {
		board.update(gameModel);
	}
}
