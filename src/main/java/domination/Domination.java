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
	public int mode = 1; // selection and placement  = 2 modes
	public int turn = 0;
	public static int maxTurn;

	/*
	 * Switch to next round.
	 */
	public void nextRound() {
		round++;
		System.out.println("picked dominos for next round");
	}

	/*
	 * Skip to the next step.
	 */
	public void next() {
		if (deck.dominos.size()>0) {
			if (turn<maxTurn-1) {
				turn++;
			} else {
				turn=0;
				if (mode==0) {
					mode++;
				} else {
					clearPlayerDominos();
					deck.pick(maxTurn);
					mode = 0;
					round++;
				}
			}
		} else {
			done = true;
		}
	}

	public Player getCurrentPlayer() {
		return players.get(turn%players.size());
	}

	/*
	 * Initalize the game the game.
	 */
	public Domination(ArrayList<Player> players, Deck deck) {
		this.players = players;
		this.deck = deck;
	}

	public void load() {
		if (players.size() == 2) {
			maxTurn = 4;
		} else {
			maxTurn =  players.size();
		}
		deck.pick(maxTurn);
	}

	protected void clearPlayerDominos() {
		for (Player player: players) {
			player.dominos.clear();
		}
	}

	/*
	 * Get the board at the set turn.
	 */
	public Board getBoard() {
		return players.get(turn).board;
	}

	/*
	 * Show the state of the game.
	 */
	public void printState() {
		System.out.println(String.format("Game State: round=%d, mode=%d, turn=%d", round, mode, turn));
	}
}
