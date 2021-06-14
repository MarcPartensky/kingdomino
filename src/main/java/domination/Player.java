package domination;

import java.util.ArrayList;

import domination.Board;

/*
 * Main player class.
 */
public class Player {
	public Board board;
	public ArrayList<Domino> dominos = new ArrayList<Domino>();
	public static int count=0;
	public int n=0;
	public String name;

	/*
	 * Initialize a player.
	 */
	public Player(Board board) {
		this.board = board;
		n = count++;
		name = String.format("Player %d", n);
	}
}
