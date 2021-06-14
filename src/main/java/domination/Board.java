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

public class Board {
	public final static int width = 5;
	public final static int height = 5;
	public int cx=0;
	public int cy=0;
	public Case[][] grid = new Case[width][height];

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
					if (x==cx && y==cy) {
						subpane.setStyle("-fx-padding: 1;" +
													"-fx-border-style: solid inside;" +
													"-fx-border-width: 2;" +
													"-fx-border-insets: 1;" +
													"-fx-border-radius: 5;" +
													"-fx-border-color: red;");
					}
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
			ImageView view = new ImageView(castleTileImage);
			view.setFitWidth(caseWidth);
			view.setFitHeight(caseHeight);
			pane.add(view, width/2, height/2);
		}
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
	 * Check if the move is valid.
	 */
	public boolean isValidMove(int x1, int y1, int x2, int y2) {
		// easy check
		return true;
	}
}
