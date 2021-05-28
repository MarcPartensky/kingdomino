package domination;

import domination.Board;

/*
 * Main player class.
 */
public class Player {
	public Board board;

	/*
	 * Build a player with default arguments.
	 */
	public static Player build() {
		Board board = new Board();
		return new Player(board);
	}

	/*
	 * Initialize a player.
	 */
	public Player(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}

}
