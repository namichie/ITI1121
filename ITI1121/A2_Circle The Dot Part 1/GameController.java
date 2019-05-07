import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 2

/**
 * The class <b>GameController</b> is the controller of the game. It implements
 * the interface ActionListener to be called back when the player makes a move.
 * It computes the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameController implements ActionListener {

	private GameView gameView;
	private GameModel gameModel;
	boolean won = false;
	boolean lost = false;

	/**
	 * Constructor used for initializing the controller. It creates the game's
	 * view and the game's model instances
	 * 
	 * @param size
	 *            the size of the board on which the game will be played
	 */
	public GameController(int size) {
		gameModel = new GameModel(size);
		gameView = new GameView(gameModel, this);
	}

	/**
	 * Starts the game
	 */
	public void start() {
	}

	/**
	 * resets the game
	 */
	public void reset() {
		gameModel.reset();
		gameView.getBoardView().update();
	}

	/**
	 * Method gets the neighboring positions surrounding the blue dot
	 * 
	 * @param p
	 *            - the position of the blue dot on the board
	 * @return list of points (next potential moves for the blue dot)
	 */
	private LinkedList<Point> getNeighboringPositions(Point p) {

		LinkedList<Point> neighbor = new LinkedList<Point>();

		// Checks if the coordinates are even or odd
		if (p.getX() % 2 == 0) { // even coordinates
			neighbor.add(new Point(p.getX(), p.getY() + 1));
			neighbor.add(new Point(p.getX() + 1, p.getY()));
			neighbor.add(new Point(p.getX() + 1, p.getY() - 1));
			neighbor.add(new Point(p.getX() - 1, p.getY() - 1));
			neighbor.add(new Point(p.getX(), p.getY() - 1));
			neighbor.add(new Point(p.getX() - 1, p.getY()));

		} else {
			neighbor.add(new Point(p.getX() - 1, p.getY() + 1));
			neighbor.add(new Point(p.getX(), p.getY() + 1));
			neighbor.add(new Point(p.getX() + 1, p.getY()));
			neighbor.add(new Point(p.getX(), p.getY() - 1));
			neighbor.add(new Point(p.getX() + 1, p.getY() + 1));
			neighbor.add(new Point(p.getX() - 1, p.getY()));
		}
		return neighbor;
	}

	/**
	 * Method that compares if a point is contained in a linked list of points
	 * 
	 * @param list
	 *            - linked list of points that has a path for the blue dot
	 * @param p
	 *            - a point on the board
	 * @return true if the given point is in the list and false otherwise
	 */
	private boolean isContained(LinkedList<Point> list, Point p) {
		for (Point a : list) {
			if (a.getX() == p.getX() && a.getY() == p.getY()) {
				return true;
			}
		}
		return false;
	}

	// Breadth first search
	/**
	 * The method bfs implements the breadth-first search algorithm to find the
	 * shortest path for the blue dot to exit(reach the border of) the board.
	 * 
	 * @param start
	 *            - linked list of points containing an initial path for the
	 *            blue dot
	 * @param targets
	 *            - linked list of points that the blue dot can use in its path
	 * @param blocked
	 *            - linked list of points that the blue dot can't use in its
	 *            path
	 * @return a linked list of points
	 */
	private LinkedList<Point> bfs(LinkedList<Point> start, LinkedList<Point> targets, LinkedList<Point> blocked) {

		Queue<LinkedList<Point>> queue = new LinkedList<LinkedList<Point>>();

		LinkedList<Point> checked = new LinkedList<Point>();

		queue.add(start);

		while (!queue.isEmpty()) {
			LinkedList<Point> q = queue.remove();
			Point c = q.peekLast();

			LinkedList<Point> neighbor = getNeighboringPositions(c);

			for (Point p : neighbor) {

				if (p.getX() >= 0 && p.getX() < gameModel.getSize() && p.getY() >= 0
						&& p.getY() < gameModel.getSize()) {

					if (!isContained(blocked, p) && !isContained(checked, p)) {

						boolean containsTarget = isContained(targets, p);

						if (containsTarget) {
							LinkedList<Point> q2 = new LinkedList<Point>(q);
							q2.add(p);
							return q2;

						} else {
							LinkedList<Point> q3 = new LinkedList<Point>(q);
							q3.add(p);
							queue.add(q3);
							checked.add(p);
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Callback used when the user clicks a button or one of the dots.
	 * Implements the logic of the game
	 *
	 * @param e
	 *            the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		}

		if (e.getActionCommand().equals("Reset")) {
			this.reset();
		}

		// Changing dot states
		if (e.getSource() instanceof DotButton) {
			DotButton dot = (DotButton) e.getSource();
			int x = dot.getColumn();
			int y = dot.getRow();

			// Change from available to selected (gray -> orange)
			gameModel.select(y, x);
			gameView.getBoardView().update();

			LinkedList<Point> start = new LinkedList<Point>();
			start.add(gameModel.getCurrentDot());
			LinkedList<Point> targets = gameModel.getTargets();
			LinkedList<Point> blocked = gameModel.getBlocked();
			LinkedList<Point> path = bfs(start, targets, blocked);

			if (path.size() > 1) {
				Point nextDot = path.get(1);
				// Moves blue dot
				gameModel.setCurrentDot(nextDot.getX(), nextDot.getY());
				gameView.getBoardView().update();

				if (nextDot.getX() == 0 || nextDot.getX() == 8 || nextDot.getY() == 0 || nextDot.getY() == 8) {
					won = true;
					showResults();
				}
			} else {
				lost = true;
				showResults();
			}
		}
	}

	/**
	 * Method that shows the results at the end of the game If the player wins,
	 * it displays the number of steps the player took to win along with a
	 * message Otherwise, just displays a message that the player lost if they
	 * lose Also gives the option to play again or quit the game
	 */
	private void showResults() {
		Object[] options = { "Quit", "Play Again" };
		if (!won) { // Won dialog
			int wonReply = JOptionPane.showOptionDialog(null,
					"Congratulations, you won in " + gameModel.getNumberOfSteps()
							+ " steps! Would you like to play again?",
					"Won", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			if (wonReply == JOptionPane.YES_OPTION) { // YES to quit game
				System.exit(0);

			} else { // NO to replay
				this.reset();
			}
		}
		if (!lost) { // Lost dialog
			int lostReply = JOptionPane.showOptionDialog(null, "You lost! Would you like to play again?", "Lost",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			if (lostReply == JOptionPane.YES_OPTION) {
				System.exit(0);

			} else {
				this.reset();
			}
		}
	}
}
