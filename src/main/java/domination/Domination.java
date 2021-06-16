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
	public int mode = 0; // selection and placement  = 2 modes
	public int previousMode = 1;
	public int turn = 0;
	public int playerTurn = 0;
	public int n = 0;
	public static int maxTurn;
	public ArrayList<Integer> playersOrder = new ArrayList<Integer>();
	public boolean[] selectedDominos;

	/*
	 * Skip to the next step.
	 */
	public void next() {
		n++;
		turn = n%maxTurn;
		playerTurn = turn%players.size();
		round = 2 * n/maxTurn;
		previousMode = mode;
		mode = (n/maxTurn) % 2;
		System.out.println(String.format("maxTurn=%d", maxTurn));
		printState();
		if (previousMode==1 && mode==0) {
			for (int i=0; i<maxTurn; i++) {
				selectedDominos[i] = false;
			}
			clearPlayerDominos();
			deck.pick(maxTurn);
		} else if (mode==1){
			fillBoardDominos();
		}
		if (deck.dominos.size()==0) {
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
		if (player.board.canInsert(player.board.cx, player.board.cy, player.board.cr)) {
			player.board.insert();
			fillBoardDominos();
			next();
		} else {
			System.out.println("Invalid move!");
		}
	}

	/*
	 * Fill board dominos.
	 */
	public void fillBoardDominos() {
		for (Player player: players) {
			if (player.board.nextDomino && player.dominos.size()>0) {
				System.out.println(String.format("player dominos=%d", player.dominos.size()));
				player.board.domino = player.dominos.remove(0);
				player.board.nextDomino = false;
			}
		}
	}

	/*
	 * Return the current player.
	 */
	public Player getPlayer() {
		return players.get(playerTurn);
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
		System.out.println(String.format("players.size=%d", players.size()));
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
		return players.get(playerTurn).board;
	}

	/*
	 * Show the state of the game.
	 */
	public void printState() {
		System.out.println(String.format("Game State: n=%d, round=%d, mode=%d, turn=%d, playerTurn=%d", n, round, mode, turn, playerTurn));
	}
}
