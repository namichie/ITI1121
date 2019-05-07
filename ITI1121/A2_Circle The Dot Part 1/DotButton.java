import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import java.awt.Color;

//Authors: NamChi Nguyen & Zhengguo Wang    
//Student number: 7236760 & 7278242  
//Course: ITI 1121-A
//Assignment: 2

/**
 * In the application <b>Circle the dot</b>, a <b>DotButton</b> is a specialized
 * type of <b>JButton</b> that represents a dot in the game. It uses different
 * icons to visually reflect its state: a blue icon if the blue dot is currently
 * on this location an orange icon is the dot has been selected and a grey icon
 * otherwise.
 * 
 * The icon images are stored in a subdirectory ``data''. They are:
 * data/ball-0.png => grey icon data/ball-1.png => orange icon data/ball-2.png
 * => blue icon
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
	private static final long serialVersionUID = 1L;

	private int row, column, type;
	private static final ImageIcon[] icons = new ImageIcon[3];

	/**
	 * Constructor used for initializing a cell of a specified type.
	 * 
	 * @param row
	 *            the row of this Cell
	 * @param column
	 *            the column of this Cell
	 * @param type
	 *            specifies the type of this cell
	 */

	public DotButton(int row, int column, int type) {
		this.row = row;
		this.column = column;
		this.type = type;

		setIcon(getImageIcon()); // shows the images of the dots
		setFocusPainted(false);
		setBackground(Color.WHITE);
		Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		setBorder(emptyBorder);
		setBorderPainted(false);
	}

	/**
	 * Changes the cell type of this cell. The image is updated accordingly.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
		setIcon(getImageIcon());
	}

	/**
	 * Method that gets the image of the dot Referenced from the Puzzler game
	 * 
	 * @return icon of the dot
	 */
	private ImageIcon getImageIcon() {
		if (icons[type] == null) {
			String strId = Integer.toString(type);
			icons[type] = new ImageIcon(DotButton.class.getResource("/data/ball-" + strId + ".png"));
		}
		return icons[type];
	}

	/**
	 * Getter method for the attribute row.
	 * 
	 * @return the value of the attribute row
	 */

	public int getRow() {
		return row;
	}

	/**
	 * Getter method for the attribute column.
	 * 
	 * @return the value of the attribute column
	 */

	public int getColumn() {
		return column;
	}
}
