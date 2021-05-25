package domination;

import java.util.ArrayList;

/*
 * Deck of cards of the game.
 */
public class Deck {
	protected String path;
	protected ArrayList<Card> cards;

	/*
	 * Initialize the deck.
	 */
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
}


