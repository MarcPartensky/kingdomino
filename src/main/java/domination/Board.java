package domination;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Button;

import domination.Case;
import domination.Domino;

/*
 * Board of a player.
 */
public class Board {
	public final static int width = 5;
	public final static int height = 5;
	public int cx=0; // pivot x position
	public int cy=0; // pivot y position
	public int cr=0; // pivot rotation
	public Domino domino;
	public Case[][] grid = new Case[width][height];

	/*
	 * Pad zeros on the left.
	 */
	static public String padLeftZeros(String inputString, int length) {
    if (inputString.length() >= length) {
        return inputString;
    }
    StringBuilder sb = new StringBuilder();
    while (sb.length() < length - inputString.length()) {
        sb.append(' ');
    }
    sb.append(inputString);
    return sb.toString();
	}



	/*
	 * Build a random board.
	 */
	public static Board random() {
		Board board = new Board();
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				board.grid[x][y] = Case.random();
			}
		}
		return board;
	}

	/*
	 * Randomize a board.
	 */
	public void randomize() {
		Board board = Board.random();
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				grid[x][y] = board.grid[x][y];
			}
		}
	}

	/*
	 * Initialize the board of a player.
	 */
	public Board() {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				grid[x][y] = new Case();
			}
		}
	}

	/*
	 * Return the position of the case of the domino that is not the pivot.
	 */
	public int getX2(int x, int r) {
		if (r==0) {
			return x + 1;
		} else if (r==1) {
			return x;
		} else if (r==2) {
			return x - 1 ;
		} else {
			return x;
		}
	}
	/*
	 * Return the position of the case of the domino that is not the pivot.
	 */
	public int getY2(int y, int r) {
		if (r==0) {
			return y;
		} else if (r==1) {
			return y - 1;
		} else if (r==2) {
			return y ;
		} else {
			return y + 1;
		}
	}

	/*
	 * Show the board given on the given pane with the given images.
	 */
	public void show(GridPane pane, HashMap<String, Image> monotiles, Image castleImage, Image castleTileImage, int caseWidth, int caseHeight) {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				// System.out.println(x);
				// System.out.println(y);
				if (x!=width/2 || y!=height/2) {
					Case c = grid[x][y];
					StackPane subpane = new StackPane();
					subpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(10))));
					// System.out.println("c.n:" + String.valueOf(c.n) + ", x:" + String.valueOf(x) + ", y:" + String.valueOf(y));
					if (c.isNull == true) {
						Rectangle rect = new Rectangle(caseWidth, caseHeight);
						rect.setFill(Color.WHITE);
						rect.setStroke(Color.BLACK);
						// rect.setFitWidth(100);
						// rect.setFitHeight(100);
						// rect.setPreserveRatio(true);
						// rect.setSmooth(true);
						// rect.setCache(true);
						pane.add(rect, x, y);
					} else {
						String monotileName = Case.getRandomMonotileName(c.type, c.crown, monotiles.keySet());
						ImageView view = new ImageView(monotiles.get(monotileName));
						view.setFitWidth(caseWidth);
						view.setFitHeight(caseHeight);
						subpane.getChildren().add(view);
					}
					pane.add(subpane, x, y);
					// subpane.setFitHeight(100);
					// subpane.setFitWidth(100);
					// subpane.setPreserveRatio(true);
				}
			}
			showDomino(pane, monotiles, width, height);
			ImageView view = new ImageView(castleTileImage);
			view.setFitWidth(caseWidth);
			view.setFitHeight(caseHeight);
			pane.add(view, width/2, height/2);
		}
	}

	/*
	 * Show a domino on a pane.
	 */
	protected void showDomino(GridPane pane, HashMap<String, Image> monotiles, int width, int height) {
		int cx2 = getX2(cx, cr);
		int cy2 = getX2(cy, cr);
		StackPane pane1 = new StackPane();
		StackPane pane2 = new StackPane();
		pane1.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(10))));
		pane2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(10))));
		String monotileName1 = Case.getRandomMonotileName(domino.type1, domino.crown1, monotiles.keySet());
		String monotileName2 = Case.getRandomMonotileName(domino.type2, domino.crown2, monotiles.keySet());
		ImageView view1 = new ImageView(monotiles.get(monotileName1));
		ImageView view2 = new ImageView(monotiles.get(monotileName2));
		view1.setFitWidth(width);
		view1.setFitHeight(height);
		view2.setFitWidth(width);
		view2.setFitHeight(height);
		pane1.getChildren().add(view1);
		pane2.getChildren().add(view1);
		pane1.setStyle("-fx-padding: 1;" +
									"-fx-border-style: solid inside;" +
									"-fx-border-width: 2;" +
									"-fx-border-insets: 1;" +
									"-fx-border-radius: 5;" +
									"-fx-border-color: red;");
		pane1.setStyle("-fx-padding: 1;" +
									"-fx-border-style: solid inside;" +
									"-fx-border-width: 2;" +
									"-fx-border-insets: 1;" +
									"-fx-border-radius: 5;" +
									"-fx-border-color: red;");
		pane.add(pane1, cx, cy);
		pane.add(pane2, cx2, cy2);
	}

	/*
	 * Print the board content on the console.
	 */
	public void print() {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				System.out.print(padLeftZeros(String.valueOf(grid[x][y].n), 2) + " ");
			}
			System.out.println();
		}
	}

	/*
	 * Compute the worth of the board following the rules.
	 */
	public int computeWorth() {
		// annoying work to be done here
		return 0;
	}

	/*
	 * Check if the domino can be inserted.
	 */
	public boolean canInsert(int x1, int y1, int r) {
		int x2 = getX2(x1, r);
		int y2 = getY2(y1, r);
		if (!isInBorders(x1, y1, x2, y2)) {
			return false;
		} else if (!isValidMove(x1, y1, x2, y2, domino.type1, domino.type2)) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Check if the domino position is inside the borders.
	 */
	public boolean isInBorders(int x1, int y1, int x2, int y2) {
		if (x1<0 || x1>=width) {
			return false;
		} else if (x2<0 || x2>=width) {
			return false;
		} else if (y1<0 || y1>=height) {
			return false;
		} else if (y2<0 || y2>=height) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Check if the domino position respects position rules assuming
	 * it is within the borders.
	 */
	public boolean isValidMove(int x1, int y1, int x2, int y2, char t1, char t2) {
		// true work needs to be done
		return true;
	}

	/*
	 * Tries to move the domino at the given relative position.
	 */
	public void move(int mx, int my) {
		if (canInsert(cx + mx, cy + my, cr)) {
			cx += mx;
			cy += my;
		}
	}
	/*
	 * Tries to rotate the domino at the given relative position.
	 */
	public void rotate() {
		if (canInsert(cx, cy, cr+1)) {
			cr += 1;
		}
	}
}
