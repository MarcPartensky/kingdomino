package domination;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import domination.Player;
import domination.Deck;

/*
 * Main game class.
 */
public class Domination {
	public ArrayList<Player> players;
	protected Deck deck;
	protected boolean done = false;

	/*
	 * Build the game with default arguments.
	 */
	public static Domination build(Integer playerNumber, Deck deck) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i=0; i<playerNumber; i++) {
			players.add(Player.build());
		}
		return new Domination(players, deck);
	}

	/*
	 * Initalize the game the game.
	 */
	public Domination(ArrayList<Player> players, Deck deck) {
		this.players = players;
		this.deck = deck;
	}
}
