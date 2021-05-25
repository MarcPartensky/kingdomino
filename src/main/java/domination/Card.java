package domination;

import javafx.scene.image.Image;

/*
 * Card of the domination game.
 */
public class Card {
	protected int case1;
	protected int case2;
	protected int king1;
	protected int king2;
	protected Image image;

	// static public Card build() {
	// 	return new Card();
	// }

	/*
	 * Create a card with its 2 cases number and number of kings,
	 * and the image of the card.
	 */
	public Card(int case1, int case2, int king1, int king2, Image image) {
		this.case1 = case1;
		this.case2 = case2;
		this.king1 = king1;
		this.king2 = king2;
		this.image = image;
	}
}
