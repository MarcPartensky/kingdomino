package domination;


import java.util.ArrayList;
import java.util.Collections;
import domination.Domino;

/*
 * Deck of cards of the game.
 */
public class Deck {
	protected String path;
	public ArrayList<Domino> dominos;
	public ArrayList<Domino> pickedDominos = new ArrayList<Domino>();

	/*
	 * Initialize the deck.
	 */
	public Deck(ArrayList<Domino> dominos) {
		this.dominos = dominos;
	}

	protected int getPickNumber(int playerNumber) {
		if (playerNumber == 2) {
			return 4;
		} else {
			return playerNumber;
		}
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
	public void pick(int playerNumber) {
		pickedDominos.clear();
		int pickNumber = getPickNumber(playerNumber);
		for (int i=0; i<pickNumber; i++) {
			Domino domino = dominos.remove(dominos.size()-1);
			System.out.println(domino.toString());
			pickedDominos.add(domino);
		}
	}
}


