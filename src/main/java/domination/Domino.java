package domination;
import java.lang.Math;
import java.util.Random;

import javafx.scene.image.Image;

/*
 * Card of the domination game.
 */
public class Domino {
	final public char[] types={ 'c', 'f', 'o', 'p' };
	protected int type1;
	protected int type2;
	protected int crown1;
	protected int crown2;
	public int n;

	/*
	 * Return a random card with ugly hard coded random values.
	 */
	static public Domino random(int dominosNumber) {
	 return new Domino(
				 (int)(dominosNumber * Math.random()),
				 (int)(49 * Math.random()),
				 (int)(49 * Math.random()),
				 (int)(4 * Math.random()),
				 (int)(4 * Math.random())
			 );
	}

	/*
	 * Create a domino with its 2 types number and number of crowns,
	 * and the image of the domino.
	 */
	public Domino(int n, int type1, int type2, int crown1, int crown2) {
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
		return String.format("Domino(n=%d, type1=%d, type2=%d, crown1=%d, crown2=%d)", n, type1, type2, crown1, crown2);
	}

	/*
	 * Return the domino image name.
	 */
	public String getImageName() {
		return types[type1] + String.valueOf(crown1) + "-" + types[type2] + String.valueOf(crown2) + ".png";
	}
}
