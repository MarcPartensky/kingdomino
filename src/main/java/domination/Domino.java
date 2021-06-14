package domination;
import java.lang.Math;
import java.util.Random;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import domination.Domino;
import domination.Case;

/*
 * Card of the domination game.
 */
public class Domino {
	protected char type1;
	protected char type2;
	protected int crown1;
	protected int crown2;
	public int n;

	/*
	 * Return a random card with ugly hard coded random values.
	 */
	static public Domino random(int dominosNumber) {
	 return new Domino(
				 (int)(dominosNumber * Math.random()),
				 (char)(49 * Math.random()),
				 (char)(49 * Math.random()),
				 (int)(4 * Math.random()),
				 (int)(4 * Math.random())
			 );
	}

	/*
	 * Create a domino with its 2 types number and number of crowns,
	 * and the image of the domino.
	 */
	public Domino(int n, char type1, char type2, int crown1, int crown2) {
		this.n = n;
		this.type1 = type1;
		this.type2 = type2;
		this.crown1 = crown1;
		this.crown2 = crown2;
	}

	/*
	 * Gives the string representation of a domino.
	 */
	public String toString() {
		return String.format("Domino(n=%d, type1=%c, type2=%c, crown1=%d, crown2=%d)", n, type1, type2, crown1, crown2);
	}

	/*
	 * Return the domino image name.
	 */
	public String getImageName() {
		return String.valueOf(type1) + String.valueOf(crown1) + "-" + String.valueOf(type2) + String.valueOf(crown2) + ".png";
	}

	/*
	 * Return the first case generated with a domino.
	 */
	public Case getCase1() {
		return new Case(type1, crown1);
	}

	/*
	 * Return the first case generated with a domino.
	 */
	public Case getCase2() {
		return new Case(type2, crown2);
	}

	/*
	 * Show the domino.
	 */
	public Node getNode(
			HashMap<String, Image> tiles,
			HashMap<String, Image> monotiles,
			int width,
			int height
	) {
		System.out.println(toString());
		String name = getImageName();
		System.out.println(name);
		// if (tiles.containsKey(name)) {
		if (false) {
			System.out.println("domino image found");
			Image image = tiles.get(name);
			ImageView view = new ImageView(image);
			view.setRotate(90);
			view.setFitWidth(2*width);
			view.setFitHeight(height);
			return view;
		} else {
			System.out.println("domino image not found");
			// StackPane pane = new StackPane();
			String monotileName1 = Case.getRandomMonotileName(type1, crown1, monotiles.keySet());
			String monotileName2 = Case.getRandomMonotileName(type2, crown2, monotiles.keySet());
			Image image1 = monotiles.get(monotileName1);
			ImageView view1 = new ImageView(image1);
			view1.setFitWidth(width);
			view1.setFitHeight(height);
			Image image2 = monotiles.get(monotileName2);
			ImageView view2 = new ImageView(image2);
			view2.setFitWidth(width);
			view2.setFitHeight(height);
			Label label = new Label(String.format("Domino %d", n));
			return new VBox(label, view1, view2);
		}
	}

	/*
	 * Return the numero of the domino.
	 */
	public int getN() {
		return n;
	}

}
