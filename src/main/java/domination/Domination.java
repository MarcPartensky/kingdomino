import java.util.ArrayList;
import domination.*;

// Main game class.
public class Domination {
	public ArrayList<Player> players = new ArrayList<Player>();
	public boolean done = false;

	// Initalize the game the game.
	public Domination(ArrayList<Player> players) {
		this.players = players;
	}

	// Build the game with default arguments.
	public static Domination build(Integer playerNumber) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i=0; i<playerNumber; i++) {
			players.add(Player.build());
		}
		Domination game = new Domination(players);
		return game;
	}

	// draw the game with the canvas
	public void draw() {

	}


}
