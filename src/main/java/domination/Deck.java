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
	protected ArrayList<Domino> pickedDominos;

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
		n = dominos.size() - n;
		for (int i=0; i<n; i++) {
			dominos.remove(dominos.size()-1);
		}
	}

	/*
	 * Pick dominos for the next turn.
	 * Might fail if not enough dominos left.
	 */
	public void pick(int n) {
		pickedDominos.clear();
		for (int i=0; i<n; i++) {
			Domino domino = dominos.remove(dominos.size()-1);
			pickedDominos.add(domino);
		}
	}
}


