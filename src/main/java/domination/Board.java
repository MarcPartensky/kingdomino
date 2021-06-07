package domination;

import java.util.ArrayList;
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
	static int width = 5;
	static int height = 5;
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
	public void show(GridPane pane, ArrayList<Image> images, int caseWidth, int caseHeight) {
		Case c;
		// Label label = new Label("Board");
		// pane.getChildren().add(label);
		// StackPane subpane;

		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				c = grid[x][y];
				StackPane subpane = new StackPane();
				// Pane subpane = new Pane();
				subpane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(10))));
				System.out.println("c.n:" + String.valueOf(c.n) + ", x:" + String.valueOf(x) + ", y:" + String.valueOf(y));
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
					// subpane.getChildren().add(new ImageView(images.get(c.n)));
					// pane.add(subpane, x, y);
					ImageView view = new ImageView(images.get(c.n));
					view.setFitWidth(caseWidth);
					view.setFitHeight(caseHeight);
					subpane.getChildren().add(view);
				}
				// pane.getChildren().add(subpane, x, y);
				pane.add(subpane, x, y);
				// subpane.setFitHeight(100);
				// subpane.setFitWidth(100);
				// subpane.setPreserveRatio(true);
				// pane.add(subpane, x, y);
				// Button button = new Button("Button");
				// pane.add(button, x, y);
			}
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
}
