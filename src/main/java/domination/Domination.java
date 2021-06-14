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
	public ArrayList<Integer> playersOrder = new ArrayList<Integer>();

	/*
	 * Skip to the next step.
	 */
	public void next() {
		if (deck.dominos.size()>0) {
			if (turn<maxTurn-1) {
				turn++;
			} else {
				turn = 0;
				if (mode==0) {
					mode = 1;
					clearPlayerDominos();
					deck.pick(maxTurn);
				} else {
					mode = 0;
					fillBoardDominos();
					round++;
				}
			}
		} else {
			done = true;
		}
	}

	/*
	 * Setup player order.
	 */
	protected void setupPlayerOrder() {
		int i = 0;
		for (Domino domino : deck.pickedDominos) {
			if (deck.pickedDominos.contains(domino)) {
				playersOrder.add(++i);
			}
		}
	}

	/*
	 * Insert a domino.
	 */
	public void insertDomino() {
		Player player = getPlayer();
		player.board.insert();
		fillBoardDominos();
		player.board.domino = player.dominos.remove(player.dominos.size() - 1);
		player.board.nextDomino = true;
	}

	/*
	 * Fill board dominos.
	 */
	public void fillBoardDominos() {
		for (Player player: players) {
			if (player.board.nextDomino) {
				System.out.println(player.name);
				player.board.domino = player.dominos.remove(player.dominos.size() - 1);
			}
		}
	}

	/*
	 * Return the current player.
	 */
	public Player getPlayer() {
		return players.get(turn%players.size());
	}

	/*
	 * Initalize the game the game.
	 */
	public Domination(ArrayList<Player> players, Deck deck) {
		this.players = players;
		this.deck = deck;
	}

	/*
	 * Load the game (not in constructor).
	 */
	public void load() {
		if (players.size() == 2) {
			maxTurn = 4;
		} else {
			maxTurn =  players.size();
		}
		deck.pick(maxTurn);
	}

	/*
	 * Clear player dominos.
	 */
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
