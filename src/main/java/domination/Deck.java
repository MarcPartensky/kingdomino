package domination;


import java.util.ArrayList;
import java.util.Collections;
import domination.Domino;

/*
 * Deck of cards of the game.
 */
public class Deck {
	protected String path;
	protected ArrayList<Domino> dominos;

	/*
	 * Initialize the deck.
	 */
	public Deck(ArrayList<Domino> dominos) {
		this.dominos = dominos;
	}

	/*
	 *  Shuffle the deck.
	 */
	public void shuffle() {
		Collections.shuffle(dominos);
	}

	/*
	 * Trancate the deck.
	 */
	public void truncate(int n) {
		System.out.println(n);
		dominos.subList(0, n);
	}
}


