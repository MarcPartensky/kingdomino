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
	final int width = 5;
	final int height = 5;
	public Case[][] grid = new Case[width][height];

	/*
	 * Build a random board.
	 */
	public static Board random() {
		Board board = new Board();
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				board.grid[x][y].n = (Math.random() * ((max - min) + 1)) + min;
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
	public void show(GridPane pane, ArrayList<Image> images) {
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
					Rectangle rect = new Rectangle();
					// rect.setFill(Color.TRANSPARENT);
					rect.setFill(Color.WHITE);
					rect.setStroke(Color.WHITE);
					// rect.setFitWidth(100);
					// rect.setFitHeight(100);
					// rect.setPreserveRatio(true);
					// rect.setSmooth(true);
					// rect.setCache(true);
					pane.add(rect, x, y);
				} else {
					// subpane.getChildren().add(new ImageView(images.get(c.n)));
				}
				// pane.getChildren().add(subpane, x, y);
				// subpane.setFitHeight(100);
				// subpane.setFitWidth(100);
				// subpane.setPreserveRatio(true);
				// pane.add(subpane, x, y);
				Button button = new Button("Button");
				pane.add(button, x, y);
			}
		}
	}
}
