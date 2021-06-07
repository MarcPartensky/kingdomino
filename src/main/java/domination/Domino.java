package domination;
import java.lang.Math;
import java.util.Random;

import javafx.scene.image.Image;

/*
 * Card of the domination game.
 */
public class Domino {
	protected int case1;
	protected int case2;
	protected int king1;
	protected int king2;
	protected int n;

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
	 * Create a domino with its 2 cases number and number of kings,
	 * and the image of the domino.
	 */
	public Domino(int n, int case1, int case2, int king1, int king2) {
		this.n = n;
		this.case1 = case1;
		this.case2 = case2;
		this.king1 = king1;
		this.king2 = king2;
	}
}
