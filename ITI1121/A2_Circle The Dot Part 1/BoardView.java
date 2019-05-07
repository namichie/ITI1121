import java.awt.*;
import javax.swing.*;

//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 2

/**
 * The class <b>BoardView</b> provides the current view of the board. It extends
 * <b>JPanel</b> and lays out a two dimensional array of <b>DotButton</b>
 * instances.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class BoardView extends JPanel {
	private static final long serialVersionUID = 1L;
	private DotButton[][] board;
	private JPanel[] panels;
	private GameModel gameModel;


	/**
	 * Constructor used for initializing the board. The action listener for the
	 * board's DotButton is the game controller
	 * 
	 * @param gameModel
	 *            the model of the game (already initialized)
	 * @param gameController
	 *            the controller
	 */

	public BoardView(GameModel gameModel, GameController gameController) {
		this.gameModel = gameModel;
		int size = gameModel.getSize();

		panels = new JPanel[size];
		board = new DotButton[size][size];

		setBackground(Color.WHITE);
		setLayout(new GridLayout(size, size));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setVisible(true);

		for (int row = 0; row < size; row++) {

			panels[row] = new JPanel();

			for (int column = 0; column < size; column++) {
				board[row][column] = new DotButton(row, column, gameModel.getCurrentStatus(row, column));
				panels[row].add(board[row][column]); // adds rows of buttons to JPanel
				add(panels[row]).setBackground(Color.WHITE); // adds row of DotButtons to BoardView and sets BG color
				board[row][column].addActionListener(gameController);
			}

			if (row % 2 == 0) {
				panels[row].setLayout(new FlowLayout(FlowLayout.LEFT));
				panels[row].setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

			} else if (row % 2 != 0) {
				panels[row].setLayout(new FlowLayout(FlowLayout.RIGHT));
				panels[row].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
			}
		}
	}

	/**
	 * update the status of the board's DotButton instances based on the current
	 * game model
	 */
	public void update() {
		int size = gameModel.getSize();
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				board[row][column].setType(gameModel.getCurrentStatus(row, column));
			}
		}
	}
}


