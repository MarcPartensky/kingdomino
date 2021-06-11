import java.io.FileInputStream;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.DoubleStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
// import org.apache.commons.io.FileUtils;
// import java.net.URL;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.SplitPane;
// import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import domination.Domination;
import domination.Domino;
import domination.Deck;
import domination.Player;
import domination.Board;

/*
 * Main application of the game.
 */
public class Main extends Application {
	protected String title = "King Domino";
	protected boolean fullscreen = false;
	protected boolean skipMenu = true;
	protected int width = 800;
	protected int height = 600;
	protected int tileWidth = 100; // temp
	protected int tileHeight = 100;
	protected Stage stage;
	protected final int dominosMaxNumber = 48;
	protected int dominosNumber = 48;
	protected int sceneMode = 0;
	protected int playerNumber = 2;
	protected String csvPath = "assets/dominos.csv";
	protected String tilesPath = "assets/img/tiles";
	protected ArrayList<Image> tiles = new ArrayList<Image>();
	protected String monotilesPath = "assets/img/monotiles";
	protected ArrayList<Image> monotiles = new ArrayList<Image>();
	// protected String emptyMonotilePath = "assets/img/empty.png";
	// protected Image emptyMonotile;
	protected String playersPath = "assets/img/player";
	protected ArrayList<Image> castleImages = new ArrayList<Image>();
	protected ArrayList<Image> castleTileImages = new ArrayList<Image>();
	protected String backgroundImagePath = "assets/img/deco/fond.png";
	protected BackgroundImage backgroundImage;
	protected Domination game;

	/*
	 * Main function to launch the game.
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

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
		System.out.println("---");

		switch(event.getCode().getCode()) {
			case 27 : { // ESC
				System.out.println("Closing the stage");
				stage.close();
				break;
			}
			case 70: { // f
				System.out.println("Fullscreen mode");
				fullscreen =! fullscreen;
				stage.setFullScreen(fullscreen);
				break;
			}
			case 80: { // p
				System.out.println("Printing the board");
				game.getBoard().print();
				break;
			}
			case 81: { // q
				System.out.println("Closing the stage");
				stage.close();
				break;
			}
			case 82: { // 3
				System.out.println("Reset the game");
				// game.reset();
				Scene scene = buildBoardScene();
				stage.setScene(scene);
				break;
			}
			case 83: { // s
				System.out.println("Shuffle the board");
				game.getBoard().randomize(dominosNumber);
				Scene scene = buildBoardScene();
				stage.setScene(scene);
				break;
			}
			case 49: { // 1
				System.out.println("Show the menu");
				Scene scene = buildMenu();
				stage.setScene(scene);
				break;
			}
			case 50: { // 2
				System.out.println("Show the board");
				Scene scene = buildBoardScene();
				stage.setScene(scene);
				break;
			}
			case 51: { // 3
				System.out.println("Show the deck");
				Scene scene = buildDeckScene();
				stage.setScene(scene);
				break;
			}
			case 65: { // a
				System.out.println("Switch the board");
				game.turn = (game.turn + 1) % playerNumber;
				Scene scene = buildBoardScene();
				stage.setScene(scene);
				break;
			}
			default:  {
				System.out.println("Unrecognized key");
			}
		}
		System.out.println("Key pressed: " + event.getCode().getCode());
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

		dominosNumber = playerNumber * (dominosMaxNumber/4);

		Scene scene = buildMenu();
		// stage.centerOnScreen();
		// stage.setResizable(false);
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

	  // gridPane.heightProperty().addListener(new ChangeListener<Number>() {
			// @Override public void changed(ObservableValue<? extends Number>
				// observableValue, Number oldSceneHeight, Number newSceneHeight) {
				// this.height = newSceneHeight;
				// System.out.println("height: " + newSceneHeight);
			// }
    // });

    // gridPane.widthProperty().addListener(new ChangeListener<Number>() {
			// @Override public void changed(ObservableValue<? extends Number>
				// observableValue, Number oldSceneWidth, Number newSceneWidth) {
					// this.width = newSceneWidth;
					// System.out.println("Width: " + newSceneWidth);
        // }
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
		Label label = new Label("Load the game.");
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i=0; i<playerNumber; i++) {
			players.add(new Player(new Board()));
		}

		// The game object in itself does not posesses the images.
		Deck deck = buildDeck();
		System.out.println("deck size:" + String.valueOf(deck.dominos.size()));
		game = new Domination(players, deck);
		game.nextRound();

		// Scene scene = new Scene(pane, width, height);
		Scene scene = buildBoardScene();
		stage.setScene(scene);
	}

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

	protected Scene buildBoardScene() {
		// AnchorPane root = new AnchorPane();
		// VBox root = new VBox();
		// Pane root = new Pane();
		// root.setMinSize(500, 500);
		// root.setFillWidth(true);
		// pane.setSpacing(20);
		StackPane root = new StackPane();
		root.setBackground(new Background(backgroundImage));
		// pane.setFillWidth(true);
		GridPane gridPane = new GridPane();
		// gridPane.setBackground(new Background(
		// 			new BackgroundFill(Color.web("#964B00"), CornerRadii.EMPTY, new Insets(10))));
		// gridPane.getColumnConstraints().addAll(DoubleStream.of(20, 20, 20, 20, 20)
		// 		.mapToObj(width -> {
		// 			ColumnConstraints constraints = new ColumnConstraints();
		// 			System.out.println(width);
		// 			constraints.setPercentWidth(width);
		// 			constraints.setFillWidth(true);
		// 			return constraints;
		// 		}).toArray(ColumnConstraints[]::new));
		// gridPane.getRowConstraints().addAll(DoubleStream.of(20, 20, 20, 20, 20)
		// 		.mapToObj(height -> {
		// 			RowConstraints constraints = new RowConstraints();
		// 			constraints.setPercentHeight(height);
		// 			constraints.setFillHeight(true);
		// 			return constraints;
		// 		}).toArray(RowConstraints[]::new));
		// RowConstraints rowConstraints = new RowConstraints();
		// ColumnConstraints columnConstraints = new ColumnConstraints();
		// rowConstraints.setVgrow(Priority.ALWAYS);
		// columnConstraints.setHgrow(Priority.ALWAYS);
		// gridPane.getRowConstraints().add(rowConstraints);
		// gridPane.getColumnConstraints().add(columnConstraints);
		// // pane.getChildren().add(new Label("Pane"));
		root.getChildren().add(gridPane);
		// AnchorPane.setLeftAnchor(gridPane, 0.0);
		// AnchorPane.setRightAnchor(gridPane, 0.0);
		// gridPane.setHgrow(Priority.ALWAYS);
		// root.setFillWidth(true);
		// gridPane.setHgap(10);
		// gridPane.setVgap(10);
		// gridPane.setAlignment(Pos.CENTER);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		gridPane.setGridLinesVisible(true);
		// gridPane.setMinSize(150.0, Control.USE_PREF_SIZE);
		// gridPane.setMaxSize(150.0, Control.USE_PREF_SIZE);
		// pane.setPadding(new Insets(15));
		Board board = game.getBoard();
		// gridPane.setFitHeight(100);
		// gridPane.setFitWidth(100);
		// gridPane.setPreserveRatio(true);
		// gridPane.setFillWidth(true);
		// GridPane.setConstraints(gridPane, 8, 8);
		//gridPane.setHgrow(Priority.ALWAYS);
		gridPane.prefWidthProperty().bind(root.widthProperty());
		Image castleImage = castleImages.get(game.turn);
		Image castleTileImage = castleTileImages.get(game.turn);
		board.show(gridPane, monotiles, castleImage, castleTileImage, tileWidth, tileHeight);
		return new Scene(root, width, height);
	}

	/*
	 * Show the deck.
	 */
	public Scene buildDeckScene() {
		StackPane root = new StackPane();
		root.setBackground(new Background(backgroundImage));
		GridPane gridPane = new GridPane();
		root.getChildren().add(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		// gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		// gridPane.setGridLinesVisible(true);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		System.out.println(game.deck.pickedDominos.size());
		for (int i=0; i<game.deck.pickedDominos.size(); i++) {
			System.out.println(game.deck.pickedDominos.get(i).n);
			Image image = tiles.get(game.deck.pickedDominos.get(i).n);
			ImageView view = new ImageView(image);
			view.setRotate(90);
			gridPane.add(view, i, 0, 1, 2);
		}
		gridPane.prefWidthProperty().bind(root.widthProperty());
		return new Scene(root, width, height);
	}

	/*
	 * Load the images.
	 */
	public void loadImages() throws Exception {
		File monotilesDirectory = new File(monotilesPath);
		File[] monotilesFiles = monotilesDirectory.listFiles();

		// load the monotiles
		String path;
		for (int i = 0; i < monotilesFiles.length; i++) {
			path = monotilesPath + "/" + monotilesFiles[i].getName();
			// System.out.println(path);
			FileInputStream monotileStream = new FileInputStream(path);
			monotiles.add(new Image(monotileStream));
		}

		// load the tiles
		File tilesDirectory = new File(tilesPath);
		File[] tilesFiles = tilesDirectory.listFiles();
		for (int i = 0; i < tilesFiles.length; i++) {
			path = tilesPath + "/" + tilesFiles[i].getName();
			System.out.println(path);
			FileInputStream tileStream = new FileInputStream(path);
			tiles.add(new Image(tileStream));
		}

		// load the castleImages
	 for (int i=1; i<=playerNumber; i++) {
		path = playersPath + String.valueOf(i) + "/castle.png";
		// System.out.println(path);
		FileInputStream castleImageStream = new FileInputStream(path);
		castleImages.add(new Image(castleImageStream));
	 }

		// load the castleTileImages
	 for (int i=1; i<=playerNumber; i++) {
		path = playersPath + String.valueOf(i) + "/tile.png";
		// System.out.println(path);
		FileInputStream castleTileImageStream = new FileInputStream(path);
		castleTileImages.add(new Image(castleTileImageStream));
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
	 * Build the deck of cards.
	 */
	protected Deck buildDeck() {
		// Do not even try to understand.
		ArrayList<int[]> data = new ArrayList<int[]>();
		ArrayList<Domino> dominos = new ArrayList<Domino>();
		HashSet<String> dominosTypeHashset = new HashSet();

		System.out.println("csvPath: " + csvPath);
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
			String row;
			boolean first = true;
			while ((row = csvReader.readLine()) != null) {
				if (first) { first=false; continue; }
				// System.out.println(row);
				String[] rowData = row.split(",");
				int[] rowDataInt = new int[5];
				rowDataInt[0] = Integer.parseInt(rowData[0]);
				rowDataInt[2] = Integer.parseInt(rowData[2]);
				rowDataInt[4] = Integer.parseInt(rowData[4]);
				data.add(rowDataInt);
				dominosTypeHashset.add(rowData[1]);
				dominosTypeHashset.add(rowData[3]);
			}
			csvReader.close();
		} catch (IOException e) {
			System.out.println("Csv path not found.");
			System.out.println(e.getClass());
		}

		// System.out.println("data size:" + String.valueOf(data.size()));
		List<String> dominosTypeList = new ArrayList<String>(dominosTypeHashset);
		for (int i=0; i<data.size(); i++) {
			// System.out.print(data.get(i) + " ");
			dominos.add(new Domino(
						data.get(i)[4],
						dominosTypeList.indexOf(data.get(i)[1]),
						dominosTypeList.indexOf(data.get(i)[3]),
						data.get(i)[0],
						data.get(i)[2]
					));
		}

		Deck deck = new Deck(dominos);
		deck.shuffle();
		deck.truncate(dominosNumber);

		return deck;
	}
}
