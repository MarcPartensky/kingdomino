package domination;

import java.util.ArrayList;

public class Deck {
	protected String path;
	protected ArrayList<Card> cards;

	/*
	 * Initialize the deck.
	 */
	public Deck(ArrayList<Card> cards, String path) {
		this.cards = cards;
		this.path = path;
	}
}


