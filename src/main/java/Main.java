import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
// import java.net.URL;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import domination.Domination;
import domination.Card;
import domination.Deck;
import domination.Player;

/*
 * Main application of the game.
 */
public class Main extends Application {
	protected String title = "King Domino";
	protected boolean fullscreen = false;
	protected int width = 800;
	protected int height = 600;
	protected Stage stage;
	protected String csvPath = "../../../assets/dominos.csv";
	final int cardsNumber = 48;
	protected Image[] images = new Image[cardsNumber];
	protected int sceneMode = 0;
	protected int playerNumber = 2;
	protected Domination game;

	// @Override
	// protected void initSettings(GameSettings settings) {
	// 		settings.setWidth(800);
	// 		settings.setHeight(600);
	// 		settings.setTitle("Basic Game App");
	// }

	/*
	 * Deal with events.
	 */
	public void eventHandlerKeyPressed(KeyEvent event, Stage stage) {
		System.out.println("Key pressed: " + event.toString());

		switch(event.getCode().getCode()) {
			case 27 : { // ESC key
				stage.close();
				break;
			}
			case 81 : { // q key
				stage.close();
				break;
			}
			// case 65 : { // a
			// 	Scene scene = buildMenu();
			// 	stage.setScene(scene);
			// 	System.out.println("a");
			// 	break;
			// }
			// case 66 : { // b
			// 	Scene scene = testScene();
			// 	stage.setScene(scene);
			// 	System.out.println("b");
			// 	break;
			// }
			default:  {
				System.out.println("Unrecognized key");
			}
		}
	}

	/*
	 * Start the game.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(title);
		stage.setFullScreen(fullscreen);

		Scene scene = buildMenu();
		stage.setScene(scene);
		System.out.println("stage exists");

		// scene.setCursor(Cursor.OPEN_HAND);
		// stage.setOnCloseRequest(close);

		// ReadOnlyDoubleProperty widthProperty = pane.widthProperty();
		// widthProperty.addListener( new ChangeListener<Number> (){
		//   @Override
		//   public void changed(
		//     ObservableValue<? extends Number> observableValue,
		//     Number oldVal, Number newVal) {
		//       System.out.println("widthProperty changed from "
		//         + oldVal.doubleValue() + " to " + newVal.doubleValue());
		//   }
		// });

		stage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> eventHandlerKeyPressed(event, stage));
		stage.show();
	}

	/*
	 * Menu scene, first scene of the game.
	 */
	public Scene buildMenu() {
		Label label = new Label("Menu");
		Button buttonTwoPlayers = new Button("2 Players");
		Button buttonThreePlayers = new Button("3 Players");
		Button buttonFourPlayers = new Button("4 Players");

		buttonTwoPlayers.setOnAction(actionEvent ->  {
			playerNumber = 2;
			System.out.println("2 players");
			loadTheGame();
		});

		buttonThreePlayers.setOnAction(actionEvent ->  {
			playerNumber = 3;
			System.out.println("3 players");
			loadTheGame();
		});

		buttonFourPlayers.setOnAction(actionEvent ->  {
			playerNumber = 4;
			System.out.println("4 players");
			loadTheGame();
		});

		FlowPane flowpane = new FlowPane();
		flowpane.getChildren().add(label);
		flowpane.getChildren().add(buttonTwoPlayers);
		flowpane.getChildren().add(buttonThreePlayers);
		flowpane.getChildren().add(buttonFourPlayers);

		return new Scene(flowpane, width, height);
	}

	/*
	 * Build the game object.
	 */
	public void loadTheGame() {
		Label label = new Label("Menu");
		Deck deck = buildDeck();
		// ArrayList<Player> players = new ArrayList<Player>();
		// players.add(new Player());
		// players.add(new Player());
		loadImages();

		// The game in itself does not posesses the images.
		// Deck deck = buildDeck();
		// game = Domination.build(playerNumber, deck);
		FlowPane flowpane = new FlowPane();
		flowpane.getChildren().add(label);
		Scene scene = new Scene(flowpane, width, height);
		stage.setScene(scene);
	}

	/*
	 * Main function to launch the game.
	 */
	public static void main(String[] args) {
		// Integer playerNumber = Integer.valueOf(args[0]);
		// System.out.println(args[0]);
		Application.launch(args);
	}

	/*
	 * Load the images.
	 */
	public void loadImages() {
		// FileInputStream input = new FileInputStream("resources/images/iconmonstr-home-6-48.png");
		// Image image = new Image(input);
		// ImageView imageView = new ImageView(image);
	}

	/*
	 * Load the csv.
	 */
	public void loadCsv() {
		// FileInputStream input = new FileInputStream("resources/images/iconmonstr-home-6-48.png");
		// Image image = new Image(input);
		// ImageView imageView = new ImageView(image);
	}

	/*
	 * Build the deck of cards.
	 */
	protected Deck buildDeck() {
		ArrayList<Card> cards = new ArrayList<Card>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
			String row;
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				System.out.println(data);
				// cards.add(new Card());
			}
			csvReader.close();
		} catch (IOException e) {
			System.out.println("Csv path not found.");
			System.out.println(e.getClass());
		}
		return new Deck(cards);
	}
}
