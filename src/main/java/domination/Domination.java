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
	public int playerTurn = 0;
	public int n = 0;
	public static int maxTurn;
	public ArrayList<Integer> playersOrder = new ArrayList<Integer>();

	/*
	 * Skip to the next step.
	 */
	public void next() {
		n++;
		turn = n%maxTurn;
		playerTurn = turn%players.size();
		round = 2 * n/maxTurn;
		mode = (n/maxTurn) % 2;
		if (mode==0) {
			fillBoardDominos();
		} else {
			clearPlayerDominos();
			deck.pick(maxTurn);
		}
		if (deck.dominos.size()>0) {
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
		next();
		System.out.println(String.format("turn=%d", turn));
	}

	/*
	 * Fill board dominos.
	 */
	public void fillBoardDominos() {
		for (Player player: players) {
			if (player.board.nextDomino) {
				System.out.println(String.format("player dominos=%d", player.dominos.size()));
				player.board.domino = player.dominos.remove(player.dominos.size() - 1);
				player.board.nextDomino = false;
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
