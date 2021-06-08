package domination;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import domination.Player;
import domination.Board;
import domination.Deck;

/*
 * Main game class.
 */
public class Domination {
	public ArrayList<Player> players;
	public Deck deck;
	public boolean done = false;
	public int round = 0;
	public int turn = 0;

	/*
	 * Switch to next round.
	 */
	public void nextRound() {
		round++;
		deck.pick(players.size());
		System.out.println("picked dominos for next round");
	}

	/*
	 * Initalize the game the game.
	 */
	public Domination(ArrayList<Player> players, Deck deck) {
		this.players = players;
		this.deck = deck;
	}

	/*
	 * Get the board at the set turn.
	 */
	public Board getBoard() {
		return players.get(turn).board;
	}
}
