package domination;
import java.lang.Math;
import java.util.Random;

import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	 * Return the cases generated with a domino.
	 */
	public Case[] getCases() {
		Case case1 = new Case(type1, crown1);
		Case case2 = new Case(type2, crown2);
		return new Case[] {case1, case2};
	}

	/*
	 * Show the domino.
	 */
	public Pane getPane(HashMap<String, Image> tiles, HashMap<String, Image> monotiles) {
		System.out.println(toString());
		String name = getImageName();
		System.out.println(name);
		if (tiles.containsKey(name)) {
			Image image = tiles.get(n);
			ImageView view = new ImageView(image);
			view.setRotate(90);
			return view;
		} else {
			StackPane pane = new StackPane();
			// HBox = new Hbox();
			// String monotilef1 = ;
			Image image1 = monotiles.get(String.format(String.valueOf(type1) + String.valueOf(type2)));
			ImageView view = new ImageView(image);
			pane.add(view1);
			pane.add(view2);
			return pane;
		}
	}
}
