package domination;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import domination.Case;

public class Board {
	final int width = 9;
	final int height = 9;
	protected Case[][] grid = new Case[width][height];

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
		Label label = new Label("Menu");
		pane.getChildren().add(label);

		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				c = grid[x][y];
				if (c.n == 0) {
					Rectangle rect = new Rectangle();
					rect.setFill(Color.TRANSPARENT);
					rect.setStroke(Color.BLACK);
					pane.getChildren().add(rect);
				} else {
					pane.add(new ImageView(images.get(c.n)), x, y, 1, 1);
				}
			}
		}
	}
}
