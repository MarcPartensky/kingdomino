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
	final static int width = 5;
	final static int height = 5;
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
	public static Board random(int max) {
		Board board = new Board();
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				board.grid[x][y].n = (int)(Math.random() * (max + 1));
			}
		}
		return board;
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
	public void show(GridPane pane, HashMap<String, Image> images, Image castleImage, Image castleTileImage, int caseWidth, int caseHeight) {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				// System.out.println(x);
				// System.out.println(y);
				if (x!=width/2 || y!=height/2) {
					Case c = grid[x][y];
					StackPane subpane = new StackPane();
					subpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(10))));
					// System.out.println("c.n:" + String.valueOf(c.n) + ", x:" + String.valueOf(x) + ", y:" + String.valueOf(y));
					if (c.n == 0) {
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
						ImageView view = new ImageView(images.get(c.n));
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

	// Randomize the board.
	public void randomize(int max) {
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				grid[x][y].n = (int)(Math.random() * (max + 1));
			}
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
	public isValidMove(int x1, int y1, int x2, int y2) {

	}
}
