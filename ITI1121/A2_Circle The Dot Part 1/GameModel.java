import java.util.LinkedList;
import java.util.Random;

//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 2

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. It
 * stores the following information: - the current location of the blue dot -
 * the state of all the dots on the board (available, selected or occupied by
 * the blue dot - the size of the board - the number of steps since the last
 * reset
 * <p>
 * The model provides all of this informations to the other classes through
 * appropriate Getters. The controller can also update the model through
 * Setters. Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {

	/**
	 * predefined values to capture the state of a point
	 */
	public static final int AVAILABLE = 0; // this dot is not the blue dot, and it hasn't been selected
											
	public static final int SELECTED = 1; // this dot is not the blue dot, but it has been selected
											
	public static final int DOT = 2; // blue dot

	private int numberOfSteps;
	private int[][] board; // 2D array (row, col)
	private Point blueDot;
	LinkedList<Point> targets = new LinkedList<Point>();
	LinkedList<Point> blocked = new LinkedList<Point>();

	/**
	 * Constructor to initialize the model to a given size of board and add
	 * target points into the linked list targets.
	 *
	 * @param size
	 *            the size of the board
	 */
	public GameModel(int size) {
		this.board = new int[size][size];

		for (int i = 0; i < size; i++) {
			targets.add(new Point(0, i));
			targets.add(new Point(size - 1, i));
		}
		reset();
	}

	/**
	 * method that finds the central dots in the board
	 * 
	 * @param size
	 *            - size of the board
	 * @return the coordinates of the blue dot in the center of the board
	 */
	private Point findOriginalBlueDot(int size) {
		int mid = size / 2;

		// Checks to see if the board is too small
		if (size < 2) {
			return null;
		}

		if (size % 2 == 0) { // even length

			Point[] evenDots = new Point[4];

			Random rand = new Random();
			int indexEven = rand.nextInt(4);

			evenDots[0] = new Point(mid - 1, mid - 1);
			evenDots[1] = new Point(mid - 1, mid);
			evenDots[2] = new Point(mid, mid - 1);
			evenDots[3] = new Point(mid, mid);
			return evenDots[indexEven];

		} else { // odd length

			Point[] oddDots = new Point[9];

			Random rand = new Random();
			int indexOdd = rand.nextInt(9);

			oddDots[0] = new Point(mid - 1, mid - 1);
			oddDots[1] = new Point(mid - 1, mid);
			oddDots[2] = new Point(mid - 1, mid + 1);

			oddDots[3] = new Point(mid + 1, mid - 1);
			oddDots[4] = new Point(mid + 1, mid);
			oddDots[5] = new Point(mid + 1, mid + 1);

			oddDots[6] = new Point(mid, mid - 1);
			oddDots[7] = new Point(mid, mid);
			oddDots[8] = new Point(mid, mid + 1);

			return oddDots[indexOdd];
		}
	}

	/**
	 * Resets the model to (re)start a game. The previous game (if there is one)
	 * is cleared up . The blue dot is positioned as per instructions, and each
	 * dot of the board is either AVAILABLE, or SELECTED (with a probability
	 * 1/INITIAL_PROBA). The number of steps is reset.
	 */
	public void reset() {

		Random rand = new Random();

		// find initial location of the dot
		blueDot = findOriginalBlueDot(board.length);
		if (blueDot == null) {
			System.out.println("Board size is too small.");
			return;
		}

		// Clears the board
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {

				int probab = rand.nextInt(10);

				if (probab == 0) {
					board[row][col] = SELECTED;
					blocked.add(new Point(row, col));
				} else {
					board[row][col] = AVAILABLE;
				}
			}
		}
		setCurrentDot(blueDot.getX(), blueDot.getY());
		numberOfSteps = 0;
		blocked.clear(); // empties the list of blocked points
	}

	/**
	 * Getter <b>class</b> method for the size of the game
	 *
	 * @return the value of the attribute sizeOfGame
	 */
	public int getSize() {
		return board.length;
	}

	/**
	 * returns the current status (AVAILABLE, SELECTED or DOT) of a given dot in
	 * the game
	 *
	 * @param i
	 *            the x coordinate of the dot
	 * @param j
	 *            the y coordinate of the dot
	 * @return the status of the dot at location (i,j)
	 */
	public int getCurrentStatus(int i, int j) {
		return board[i][j];
	}

	/**
	 * Sets the status of the dot at coordinate (i,j) to SELECTED, increases the
	 * number of steps by one and, adds the selected dot to the linked list
	 * blocked
	 *
	 * @param i
	 *            the x coordinate of the dot
	 * @param j
	 *            the y coordinate of the dot
	 */
	public void select(int i, int j) {
		board[i][j] = SELECTED;
		numberOfSteps++;
		blocked.add(new Point(i, j));
	}

	/**
	 * Puts the blue dot at coordinate (i,j). Clears the previous location of
	 * the blue dot. If the i coordinate is "-1", it means that the blue dot
	 * exits the board (the player lost) Sets the old coordinates of the blue
	 * dot to AVAILABLE
	 * 
	 * @param i
	 *            the new x coordinate of the blue dot
	 * @param j
	 *            the new y coordinate of the blue dot
	 */

	public void setCurrentDot(int i, int j) {

		Point newBlueDot = new Point(i, j);
		releaseDot(blueDot.getX(), blueDot.getY());
		board[i][j] = DOT;
		blueDot = newBlueDot; // assign old blue dot to new one
		getCurrentDot();
		blueDot.reset(i, j);
	}

	/**
	 * Releases the blue dot from its old position and sets the status of the
	 * dot at coordinate (i,j) to AVAILABLE
	 *
	 * @param i
	 *            the x coordinate of the dot
	 * @param j
	 *            the y coordinate of the dot
	 */
	public void releaseDot(int i, int j) {
		board[i][j] = AVAILABLE;
	}

	/**
	 * Getter method for the current blue dot
	 *
	 * @return the location of the current blue dot
	 */
	public Point getCurrentDot() {
		return blueDot;
	}

	/**
	 * Getter method for the current number of steps
	 *
	 * @return the current number of steps
	 */
	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	/**
	 * Getter method for a list of target points.
	 * 
	 * @return a linked list of target points
	 */
	public LinkedList<Point> getTargets() {
		return targets;
	}

	/**
	 * Getter method for a list of blocked points.
	 * 
	 * @return a linked list of blocked points
	 */
	public LinkedList<Point> getBlocked() {
		return blocked;
	}
}
