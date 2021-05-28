import java.io.FileInputStream;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
// import org.apache.commons.io.FileUtils;
// import java.net.URL;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
// import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.control.SplitPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;

import domination.Domination;
import domination.Card;
import domination.Deck;
import domination.Player;
import domination.Board;

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
	protected int cardsNumber = 48;
	protected String monotilesPath = "assets/img/monotiles";
	protected ArrayList<Image> monotiles = new ArrayList<Image>();
	protected String tilesPath = "assets/img/tiles";
	protected ArrayList<Image> tiles = new ArrayList<Image>();
	protected String backgroundImagePath = "assets/img/deco/fond.png";
	protected BackgroundImage backgroundImage;
	protected int sceneMode = 0;
	protected int playerNumber = 2;
	protected int playerTurn = 0;
	protected Domination game;
	protected boolean skipMenu = true;

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
		// stage.setResizable(true);
		loadResources();

		Scene scene = buildMenu();
		stage.setScene(scene);

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
		this.stage = stage;

		if (skipMenu) {
			loadGame(playerNumber);
		}
	}

	/*
	 * Menu scene, first scene of the game.
	 */
	public Scene buildMenu() {
		Label label = new Label("Menu");

		Button buttonTwoPlayers = new Button("2 Players");
		Button buttonThreePlayers = new Button("3 Players");
		Button buttonFourPlayers = new Button("4 Players");

		buttonTwoPlayers.setOnAction(actionEvent ->  loadGame(2));
		buttonThreePlayers.setOnAction(actionEvent ->  loadGame(3));
		buttonFourPlayers.setOnAction(actionEvent ->  loadGame(4));

		// buttonTwoPlayers.setMaxWidth(Double.MAX_VALUE);
		// buttonThreePlayers.setMaxWidth(Double.MAX_VALUE);
		// buttonFourPlayers.setMaxWidth(Double.MAX_VALUE);

		VBox vbox = new VBox(buttonTwoPlayers, buttonThreePlayers, buttonFourPlayers);
		vbox.setBackground(new Background(backgroundImage));
		vbox.setSpacing(5);
		vbox.setFillWidth(true);
		vbox.setAlignment(Pos.CENTER);

		// SplitPane pane = new SplitPane();
		// pane.getChildren().add(label);
		// pane.getChildren().add(buttonTwoPlayers);
		// pane.getChildren().add(buttonThreePlayers);
		// pane.getChildren().add(buttonFourPlayers);
		// pane.getChildren().add(label);

		return new Scene(vbox, width, height);
	}

	/*
	 * Build the game object.
	 */
	public void loadGame(int playerNumber) {
		Label label = new Label("Menu");
		// Deck deck = buildDeck();
		// ArrayList<Player> players = new ArrayList<Player>();
		// players.add(new Player());
		// players.add(new Player());

		// The game in itself does not posesses the images.
		Deck deck = buildDeck();
		game = Domination.build(playerNumber, deck);

		// Button button1 = new Button("Button 1");
		// Button button2 = new Button("Button 2");
		// Button button3 = new Button("Button 3");
		// Button button4 = new Button("Button 4");
		// Button button5 = new Button("Button 5");
		// Button button6 = new Button("Button 6");

		// GridPane pane = new GridPane();
		// pane.setHgap(10);
		// pane.setVgap(10);
		// pane.setSpacing(5);
		// pane.setFillWidth(true);
		// pane.setAlignment(Pos.CENTER);
		// pane.setBackground(new Background(backgroundImage));
		// pane.setSpacing(5);
		// pane.setFillWidth(true);
		// pane.setAlignment(Pos.CENTER);
		// pane.setSpacing(10);


		// pane.add(button1, 0, 0, 1, 1);
		// pane.add(button2, 1, 0, 1, 1);
		// pane.add(button3, 2, 0, 1, 1);
		// pane.add(button4, 0, 1, 1, 1);
		// pane.add(button5, 1, 1, 1, 1);
		// pane.add(button6, 2, 1, 1, 1);

		// Scene scene = new Scene(pane, width, height);
		Scene scene = buildBoardScene();
		stage.setScene(scene);
	}

	/*
	 * Main function to launch the game.
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	protected Scene buildBoardScene() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		// pane.setSpacing(5);
		// pane.setFillWidth(true);
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(backgroundImage));
		Board board = game.players.get(playerTurn).board;
		board.show(pane, tiles);
		return new Scene(pane, width, height);
	}

	/*
	 * Load the images.
	 */
	public void loadImages() throws Exception {
		File monotilesDirectory = new File(monotilesPath);
		File[] monotilesFiles = monotilesDirectory.listFiles();

		String path;
		for (int i = 0; i < monotilesFiles.length; i++) {
			path = monotilesPath + "/" + monotilesFiles[i].getName();
			System.out.println(path);
			FileInputStream monotileStream = new FileInputStream(path);
			monotiles.add(new Image(monotileStream));
		}

		File tilesDirectory = new File(tilesPath);
		File[] tilesFiles = tilesDirectory.listFiles();

		for (int i = 0; i < tilesFiles.length; i++) {
			path = tilesPath + "/" + tilesFiles[i].getName();
			System.out.println(path);
			FileInputStream tileStream = new FileInputStream(path);
			tiles.add(new Image(tileStream));
		}
	}


	/*
	 * Load the resources of the game.
	 */
	public void loadResources() throws Exception {
		loadImages();
		FileInputStream backgroundImageStream = new FileInputStream(backgroundImagePath);
		backgroundImage = new BackgroundImage(new Image(backgroundImageStream, width, height, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
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
