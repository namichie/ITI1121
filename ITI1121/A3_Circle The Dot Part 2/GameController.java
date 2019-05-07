import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.*;

//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 3

/**
 * The class <b>GameController</b> is the controller of the game. It implements
 * the interface ActionListener to be called back when the player makes a move.
 * It computes the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameController implements ActionListener {
	
	/**
	 * Reference to the saved file of the game 
	 */

	private static final String SAVED_FILE = "savedGame.ser";
	
	/**
	 * Reference to the view of the game
	 */
	private GameView gameView;

	/**
	 * Reference to the model of the game
	 */
	private GameModel gameModel;

	/**
	 * Reference to the GameModel stacks
	 */
	private LinkedStack<GameModel> undoStack;
	private LinkedStack<GameModel> redoStack;

	/**
	 * Constructor used for initializing the controller. It creates the game's
	 * view and the game's model instances
	 * 
	 * @param size
	 *            the size of the board on which the game will be played
	 */
	public GameController(int size) {
		// A new model is created if there is no saved game
		// Otherwise, the saved game will be loaded
		gameModel = loadGame();
		if (gameModel == null) {
			gameModel = new GameModel(size);
		}
		gameView = new GameView(gameModel, this);
		gameView.update();

		undoStack = new LinkedStack<GameModel>();
		redoStack = new LinkedStack<GameModel>();

	}

	/**
	 * resets the game
	 */
	public void reset() {

		// Clears the stack
		undoStack = new LinkedStack<GameModel>();
		redoStack = new LinkedStack<GameModel>();

		// Resets the model, Undo & Redo buttons and updates the view
		gameModel.reset();
		gameView.resetButton();
		gameView.update();
	}

	/**
	 * Method that clones the current state of the GameModel
	 * 
	 * @return a cloned model of the GameModel
	 */
	private GameModel cloneCurrentModel() {

		try {
			GameModel clonedModel = (GameModel) gameModel.clone();
			return clonedModel;

		} catch (CloneNotSupportedException e) {
			System.err.println("Cannot clone the model.");
		}
		return null;
	}

	/**
	 * Method that checks to see if the undo option is possible
	 * 
	 * @return true if undoing a move is possible and false otherwise
	 */
	private boolean canUndo() {
		return !undoStack.isEmpty();
	}

	/**
	 * Undo method Player is able to undo a move. The view of the model is also
	 * updated.
	 * 
	 */
	private void undo() {

		if (canUndo()) { 

			// Sets the current model to the cloned model
			GameModel currentState = cloneCurrentModel();

			// Push the cloned/saved state of the GameModel onto the redo stack
			redoStack.push(currentState);
			gameView.setRedo(canRedo());

			// Store the previous model and pop it from the undo stack
			GameModel prevState = undoStack.pop();

			// Update the view of the previous state
			gameView.setModel(prevState);
			gameModel = prevState;
			gameView.update(prevState);
		}
		gameView.setUndo(canUndo());
	}

	/**
	 * Method that checks to see if the redo option is possible
	 * 
	 * @return true if redoing a move is possible and false otherwise
	 */
	private boolean canRedo() {
		return !redoStack.isEmpty();
	}

	/**
	 * Redo method Player is able to redo a move The view of the model is also
	 * updated.
	 * 
	 */
	private void redo() {

		if (canRedo()) { 

			// Push the cloned/saved state of the GameModel onto the undo stack
			GameModel currentState = cloneCurrentModel();
			undoStack.push(currentState);

			// Store the previous model and pop it from the redo stack
			GameModel prevState = redoStack.pop();

			// Update the view of the previous state
			gameView.setModel(prevState);
			gameModel = prevState;
			gameView.update(prevState);
		}
		gameView.setRedo(canRedo());
	}

	/**
	 * Callback used when the user clicks a button or one of the dots.
	 * Implements the logic of the game
	 *
	 * @param e
	 *            the ActionEvent
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof DotButton) {

			DotButton clicked = (DotButton) (e.getSource());

			if (gameModel.getCurrentStatus(clicked.getColumn(), clicked.getRow()) == GameModel.AVAILABLE) {

				// Saves previous states of the model and pushes onto the undo stack
				undoStack.push(cloneCurrentModel());

				gameModel.select(clicked.getColumn(), clicked.getRow());

				oneStep();
			}

		} else if (e.getSource() instanceof JButton) {
			JButton clicked = (JButton) (e.getSource());

			if (clicked.getActionCommand().equals("Quit")) {
				saveGame();
				System.exit(0);

			} else if (clicked.getActionCommand().equals("Reset")) {
				reset();

			} else if (clicked.getActionCommand().equals("Undo")) {
				undo();

			} else if (clicked.getActionCommand().equals("Redo")) {
				redo();

			}
		}
	}

	/**
	 * Computes the next step of the game. If the player has lost, it shows a
	 * dialog offering to replay. If the user has won, it shows a dialog showing
	 * the number of steps that had been required in order to win. Else, it
	 * finds one of the shortest path for the blue dot to exit the board and
	 * moves it one step in that direction.
	 */
	private void oneStep() {
		Point currentDot = gameModel.getCurrentDot();

		// Enables the Undo & Redo buttons
		if (canUndo()) {
			gameView.setUndo(canUndo());
		}

		if (canRedo()) {
			gameView.setRedo(canRedo());
		}

		// Shows the results of the player (if they won or lost)
		if (isOnBorder(currentDot)) {
			gameModel.setCurrentDot(-1, -1);
			gameView.update();

			Object[] options = { "Play Again", "Quit" };
			int n = JOptionPane.showOptionDialog(gameView, "You lost! Would you like to play again?", "Lost",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (n == 0) {
				reset();
			} else {
				System.exit(0);
			}
		} else {
			Point direction = findDirection();
			if (direction.getX() == -1) {
				gameView.update();
				Object[] options = { "Play Again", "Quit" };
				int n = JOptionPane.showOptionDialog(gameView,
						"Congratulations, you won in " + gameModel.getNumberOfSteps()
								+ " steps!\n Would you like to play again?",
						"Won", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (n == 0) {
					reset();
				} else {
					System.exit(0);
				}
			} else {
				gameModel.setCurrentDot(direction.getX(), direction.getY());
				gameView.update();
			}
		}
	}

	/**
	 * Does a ``breadth-first'' search from the current location of the blue dot
	 * to find one of the shortest available path to exit the board.
	 *
	 * @return the location (as a Point) of the next step for the blue dot
	 *         toward the exit. If the blue dot is encircled and cannot exit,
	 *         returns an instance of the class Point at location (-1,-1)
	 */

	private Point findDirection() {
		boolean[][] blocked = new boolean[gameModel.getSize()][gameModel.getSize()];

		for (int i = 0; i < gameModel.getSize(); i++) {
			for (int j = 0; j < gameModel.getSize(); j++) {
				blocked[i][j] = !(gameModel.getCurrentStatus(i, j) == GameModel.AVAILABLE);
			}
		}

		Queue<Pair<Point>> myQueue = new LinkedQueue<Pair<Point>>();

		LinkedList<Point> possibleNeighbours = new LinkedList<Point>();

		// start with neighbours of the current dot
		// (note: we know the current dot isn't on the border)
		Point currentDot = gameModel.getCurrentDot();

		possibleNeighbours = findPossibleNeighbours(currentDot, blocked);

		// adding some non determinism into the game !
		java.util.Collections.shuffle(possibleNeighbours);

		for (int i = 0; i < possibleNeighbours.size(); i++) {
			Point p = possibleNeighbours.get(i);
			if (isOnBorder(p)) {
				return p;
			}
			myQueue.enqueue(new Pair<Point>(p, p));
			blocked[p.getX()][p.getY()] = true;
		}

		// start the search
		while (!myQueue.isEmpty()) {
			Pair<Point> pointPair = myQueue.dequeue();
			possibleNeighbours = findPossibleNeighbours(pointPair.getFirst(), blocked);

			for (int i = 0; i < possibleNeighbours.size(); i++) {
				Point p = possibleNeighbours.get(i);
				if (isOnBorder(p)) {
					return pointPair.getSecond();
				}
				myQueue.enqueue(new Pair<Point>(p, pointPair.getSecond()));
				blocked[p.getX()][p.getY()] = true;
			}

		}

		// could not find a way out. Return an outside direction
		return new Point(-1, -1);

	}

	/**
	 * Helper method: checks if a point is on the border of the board
	 *
	 * @param p
	 *            the point to check
	 *
	 * @return true iff p is on the border of the board
	 */

	private boolean isOnBorder(Point p) {
		return (p.getX() == 0 || p.getX() == gameModel.getSize() - 1 || p.getY() == 0
				|| p.getY() == gameModel.getSize() - 1);
	}

	/**
	 * Helper method: find the list of direct neighbours of a point that are not
	 * currently blocked
	 *
	 * @param point
	 *            the point to check
	 * @param blocked
	 *            a 2 dimensional array of booleans specifying the points that
	 *            are currently blocked
	 *
	 * @return an instance of a LinkedList class, holding a list of instances of
	 *         the class Points representing the neighbours of parameter point
	 *         that are not currently blocked.
	 */
	private LinkedList<Point> findPossibleNeighbours(Point point, boolean[][] blocked) {

		LinkedList<Point> list = new LinkedList<Point>();
		int delta = (point.getY() % 2 == 0) ? 1 : 0; 
		
		if (!blocked[point.getX() - delta][point.getY() - 1]) {
			list.add(new Point(point.getX() - delta, point.getY() - 1));
		}
		if (!blocked[point.getX() - delta + 1][point.getY() - 1]) {
			list.add(new Point(point.getX() - delta + 1, point.getY() - 1));
		}
		if (!blocked[point.getX() - 1][point.getY()]) {
			list.add(new Point(point.getX() - 1, point.getY()));
		}
		if (!blocked[point.getX() + 1][point.getY()]) {
			list.add(new Point(point.getX() + 1, point.getY()));
		}
		if (!blocked[point.getX() - delta][point.getY() + 1]) {
			list.add(new Point(point.getX() - delta, point.getY() + 1));
		}
		if (!blocked[point.getX() - delta + 1][point.getY() + 1]) {
			list.add(new Point(point.getX() - delta + 1, point.getY() + 1));
		}
		return list;
	}

	/**
	 * Method that saves the game (current instance of the GameModel). The
	 * GameModel object is serialized. The game is saved when the player clicks
	 * 'Quit'.
	 */
	private void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream(SAVED_FILE); 
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameModel); 
			out.close();
			fileOut.close();
			System.out.printf("Serialized game is saved in savedGame.ser");
		} catch (IOException e) {
			System.out.println("Couldn't write to a file named " + SAVED_FILE);
		}
	}

	/**
	 * Method that loads the saved game.
	 * 
	 * @return GameModel instance if a game has been saved, otherwise returns
	 *         null
	 */
	private GameModel loadGame() {
		GameModel model = null;
		try {
			FileInputStream fileIn = new FileInputStream(SAVED_FILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			model = (GameModel) in.readObject();
			in.close();
			fileIn.close(); 
			return model;
		} catch (IOException e) {
			System.out.println();
			System.out.println("Couldn't read from a file named " + SAVED_FILE);
			System.out.println();
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("GameModel class not found");
			return null;
		}
	}

}
