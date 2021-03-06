package com.kingdomino;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.DoubleStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Dictionary;
import javafx.scene.Node;
import javafx.scene.layout.CornerRadii;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import java.lang.Integer;
import java.lang.Character;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	protected boolean skipMenu = false;
	protected int width = 800;
	protected int height = 600;
	protected int tileWidth = 100;
	protected int tileHeight = 100;
	protected Stage stage;
	protected final int dominosMaxNumber = 48;
	protected int dominosNumber = 48;
	protected int sceneMode = 0;
	protected int playerNumber = 2;
	final protected int maxPlayerNumber = 4;
	protected String csvPath = "assets/dominos.csv";
	protected String tilesPath = "assets/img/tiles";
	protected HashMap<String, Image> tiles = new HashMap<String, Image>();
	protected String monotilesPath = "assets/img/monotiles";
	protected HashMap<String, Image> monotiles = new HashMap<String, Image>();
	protected String playersPath = "assets/img/player";
	protected ArrayList<Image> castleImages = new ArrayList<Image>();
	protected ArrayList<Image> castleTileImages = new ArrayList<Image>();
	protected String backgroundImagePath = "assets/img/deco/fond.png";
	protected BackgroundImage backgroundImage;
	protected Domination game;
	protected HashMap<String, Character> nameToLetters = new HashMap();
	protected int focusedDomino = 0;
	protected int[] boardCursor = { 0, 0 };
	protected boolean debugMode = false;

	/*
	 * Main function to launch the game.
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	/*
	 * Build some static attributes not to messup the main function.
	 */
	public void build() {
		nameToLetters.put("Champs", 'c');
		nameToLetters.put("Foret", 'f');
		nameToLetters.put("Mer", 'o');
		nameToLetters.put("Prairie", 'p');
		nameToLetters.put("Mine", 'i');
		nameToLetters.put("Montagne", 'm');
	}

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
			case 81: { // q
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
			case 83: { // s
				 if (game.mode==1) {
					Player player = game.getPlayer();
					if (player.dominos.size()==1) {
						System.out.println("Switch player's dominos");
						player.switchDomino();
					} else {
						System.out.println("Error: No other domino to switch with.");
					}
					show();
				 }
				break;
			}
			case 82: { // r
				if (game.mode==1) {
					System.out.println("Rotate the domino");
					game.getBoard().rotate();
					show();
				}
				break;
			}
			case 49: { // 1
				System.out.println("Focus first domino");
				focusDomino(0);
				show();
				break;
			}
			case 50: { // 2
				System.out.println("Focus second domino");
				focusDomino(1);
				show();
				break;
			}
			case 51: { // 3
				System.out.println("Focus third domino");
				focusDomino(2);
				show();
				break;
			}
			case 52: { // 4
				System.out.println("Focus fourth domino");
				focusDomino(3);
				show();
				break;
			}
			case 78: { // n
				System.out.println("Skip");
				game.next();
				show();
				break;
			}
			case 10: { // enter
				if (game.mode==0) {
					System.out.println("Select focused domino");
					selectDomino();
				} else {
					System.out.println("Insert domino");
					insertDomino();
				}
				show();
				break;
			}
			case 32: { // space
				if (game.mode==0) {
					System.out.println("Select focused domino");
					selectDomino();
				} else {
					System.out.println("Insert domino");
					insertDomino();
				}
				show();
				break;
			}
			case 37: { // left
				if (game.mode==1) {
					System.out.println("Move board cursor left");
					game.getBoard().move(-1, 0);
					show();
				} else {
					System.out.println("Focus left side domino");
					focusedDomino = (focusedDomino + (game.maxTurn-1)) % game.maxTurn;
					show();
				}
				break;
			}
			case 38: { // up
				 if (game.mode==1) {
					System.out.println("Move board cursor up");
					game.getBoard().move(0, -1);
					show();
				 }
				break;
			}
			case 39: { // right
				if (game.mode==1) {
					System.out.println("Move board cursor right");
					game.getBoard().move(1, 0);
					show();
				} else {
					System.out.println("Focus right side domino");
					focusedDomino = (focusedDomino + 1) % game.maxTurn;
					show();
				}
				break;
			}
			case 40: { // down
				 if (game.mode==1) {
					System.out.println("Move board cursor down");
					game.getBoard().move(0, 1);
					show();
				 }
				break;
			}
			case 100: { // d
				System.out.println("Toggle debug mode");
				debugMode =! debugMode;
				if (debugMode) {
					System.out.println("Debug mode on!");
				} else {
					System.out.println("Debug mode off!");
				}
			}
			default:  {
				System.out.println("Unrecognized key");
			}
		}
		// debug mode
		if (debugMode) {
			switch (event.getCode().getCode()) {
				case 80: { // p
					System.out.println("Printing the board");
					game.getBoard().print();
					break;
				}
				case 74: { // j
					System.out.println("Show the menu");
					Scene scene = buildMenu();
					stage.setScene(scene);
					break;
				}
				case 75: { // k
					System.out.println("Show the board");
					Scene scene = buildBoardScene();
					stage.setScene(scene);
					break;
				}
				case 76: { // l
					System.out.println("Show the deck");
					Scene scene = buildDeckScene();
					stage.setScene(scene);
					break;
				}
				case 65: { // a
					System.out.println("Switch the board");
					game.turn = (game.turn + 1) % playerNumber;
					Scene scene = buildDeckScene();
					stage.setScene(scene);
					break;
				}
				case 72: { // h
					System.out.println("Shuffle the board");
					game.getBoard().randomize();
					show();
					break;
				}
				case 84: { // t
					System.out.println("Reset the game");
					loadGame(playerNumber);
					show();
					break;
				}
				case 87 : { // w
					System.out.println("Compute worth");
					System.out.println(String.format("Board Worth is=%d", game.getBoard().computeWorth()));
					break;
				}
				case 69: { // e
					System.out.println("End scene");
					game.done = true;
					show();
					stage.setFullScreen(fullscreen);
					break;
				}
				default:  {
					System.out.println("Unrecognized key");
				}
			}
		}
		System.out.println("Key pressed: " + event.getCode().getCode());
	}

	/*
	 * Start the game.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		build();
		stage.setTitle(title);
		stage.setFullScreen(fullscreen);
		loadResources();

		Scene scene = buildMenu();
		stage.setScene(scene);

		stage.addEventHandler(KeyEvent.KEY_RELEASED, (event) -> eventHandlerKeyPressed(event, stage));
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
		Button buttonTwoPlayers = new Button("2 Players");
		Button buttonThreePlayers = new Button("3 Players");
		Button buttonFourPlayers = new Button("4 Players");

		buttonTwoPlayers.setOnAction(actionEvent ->  loadGame(2));
		buttonThreePlayers.setOnAction(actionEvent ->  loadGame(3));
		buttonFourPlayers.setOnAction(actionEvent ->  loadGame(4));

		VBox vbox = new VBox(buttonTwoPlayers, buttonThreePlayers, buttonFourPlayers);
		vbox.setBackground(new Background(backgroundImage));
		vbox.setSpacing(5);
		vbox.setFillWidth(true);
		vbox.setAlignment(Pos.CENTER);

		return new Scene(vbox, width, height);
	}

	/*
	 * Build the game object.
	 */
	public void loadGame(int playerNumber) {
		this.playerNumber = playerNumber;
		Label label = new Label("Load the game.");
		System.out.println(String.format("Players number = %d", playerNumber));
		ArrayList<Player> players = new ArrayList<Player>();
		dominosNumber = playerNumber * (dominosMaxNumber/4);

		for (int i=0; i<playerNumber; i++) {
			players.add(new Player(new Board()));
		}

		// The game object in itself does not posesses the images.
		Deck deck = buildDeck();
		System.out.println("Deck size = " + String.valueOf(deck.dominos.size()));
		game = new Domination(players, deck);
		game.load();
		game.selectedDominos = new boolean[game.maxTurn];

		show();
	}

	/*
	 * Show the game at its current state.
	 */
	protected void show() {
		Scene scene;
		System.out.println("Deck size = " + String.valueOf(game.deck.dominos.size()));
		if (game.done) {
			scene = buildEndScene();
		} else if (game.mode==0) {
			scene = buildDeckScene();
		} else {
			scene = buildBoardScene();
		}
		stage.setScene(scene);
	}

	/*
	 * Show the end scene.
	 */
	protected Scene buildEndScene() {
		stage.setTitle("Game over!");
		StackPane root = new StackPane();
		StackPane pane = new StackPane();
		pane.setPrefSize(width/2, height/2);
		TableView tableView = new TableView();
		TableColumn<Player, String> column1 = new TableColumn<>("Player");
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Player, Integer> column2 = new TableColumn<>("Score");
		column2.setCellValueFactory(new PropertyValueFactory<>("score"));
		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		for (Player player: game.players) {
			tableView.getItems().add(player);
		}
		pane.setAlignment(Pos.CENTER);
		pane.setMaxSize(width/2, height/2);
		root.setBackground(new Background(backgroundImage));
		root.getChildren().add(pane);
		pane.getChildren().add(tableView);
		return new Scene(root, width, height);
	}

	/*
	 * Focus the focused domino.
	 */
	protected void focusDomino(int n) {
		if (game.mode == 0) {
			if (n<=game.maxTurn) {
				focusedDomino = n;
			} else {
				System.out.println(String.format("Domino %d does not exist.", n));
			}
		} else {
			System.out.println("Can not focus domino while in board scene.");
		}
	}

	/*
	 * Select the focused domino.
	 */
	protected void selectDomino() {
		Player player = game.getPlayer();
		Domino domino = game.deck.pickedDominos.get(focusedDomino);
		if (!game.selectedDominos[focusedDomino]) {
			game.selectedDominos[focusedDomino] = true;
			player.dominos.add(domino);
			game.next();
		} else {
			System.out.println(String.format("%d Domino already selected", domino.n));
			game.printState();
		}
	}

	/*
	 * Insert the board domino inside the board.
	 */
	protected void insertDomino() {
		game.insertDomino();
	}

	/*
	 * Return the scene of the board.
	 */
	protected Scene buildBoardScene() {
		stage.setTitle(game.players.get(game.turn%playerNumber).name);
		StackPane root = new StackPane();
		root.setBackground(new Background(backgroundImage));
		GridPane gridPane = new GridPane();
		root.getChildren().add(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		gridPane.setGridLinesVisible(true);
		Board board = game.getBoard();
		gridPane.prefWidthProperty().bind(root.widthProperty());
		Image castleImage = castleImages.get(game.playerTurn);
		Image castleTileImage = castleTileImages.get(game.playerTurn);
		board.show(gridPane, monotiles, castleImage, castleTileImage, tileWidth, tileHeight);
		return new Scene(root, width, height);
	}

	/*
	 * Show the deck.
	 */
	public Scene buildDeckScene() {
		StackPane root = new StackPane();
		stage.setTitle(game.players.get(game.turn%playerNumber).name);
		root.setBackground(new Background(backgroundImage));
		GridPane gridPane = new GridPane();
		root.getChildren().add(gridPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		for (int i=0; i<game.deck.pickedDominos.size(); i++) {
			Domino domino = game.deck.pickedDominos.get(i);
			Node node = domino.getNode(tiles, monotiles, tileWidth, tileHeight);
			if (game.selectedDominos[i]) {
				node.setStyle("-fx-padding: 2;" +
											"-fx-border-style: solid inside;" +
											"-fx-border-width: 2;" +
											"-fx-border-insets: 5;" +
											"-fx-border-radius: 5;" +
											"-fx-border-color: blue;");
			}
			if (focusedDomino==i) {
				node.setStyle("-fx-padding: 3;" +
											"-fx-border-style: solid inside;" +
											"-fx-border-width: 2;" +
											"-fx-border-insets: 5;" +
											"-fx-border-radius: 5;" +
											"-fx-border-color: red;");
			}
			gridPane.add(node, i, 0, 1, 2);
		}
		gridPane.prefWidthProperty().bind(root.widthProperty());
		return new Scene(root, width, height);
	}

	/*
	 * Load the images.
	 */
	public void loadImages() throws Exception {

		// load the monotiles
		File monotilesDirectory = new File(monotilesPath);
		File[] monotilesFiles = monotilesDirectory.listFiles();
		for (int i = 0; i < monotilesFiles.length; i++) {
			String name = monotilesFiles[i].getName();
			String path = monotilesPath + "/" + name;
			System.out.println(path);
			FileInputStream monotileStream = new FileInputStream(path);
			monotiles.put(name, new Image(monotileStream));
		}

		// load the tiles
		File tilesDirectory = new File(tilesPath);
		File[] tilesFiles = tilesDirectory.listFiles();
		for (int i = 0; i < tilesFiles.length; i++) {
			String name = tilesFiles[i].getName();
			String path = tilesPath + "/" + name;
			System.out.println(path);
			FileInputStream tileStream = new FileInputStream(path);
			tiles.put(name, new Image(tileStream));
		}

		// load the castleImages
	 for (int i=1; i<=maxPlayerNumber; i++) {
		String path = playersPath + String.valueOf(i) + "/castle.png";
		System.out.println(path);
		FileInputStream castleImageStream = new FileInputStream(path);
		castleImages.add(new Image(castleImageStream));
	 }

		// load the castleTileImages
	 for (int i=1; i<=maxPlayerNumber; i++) {
		String path = playersPath + String.valueOf(i) + "/tile.png";
		System.out.println(path);
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
	 * Build the deck smoothly.
	 */
	protected Deck buildDeck() {
		ArrayList<Domino> dominos = new ArrayList<Domino>();
		System.out.println("csvPath: " + csvPath);
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
			String row;
			int i = 0;
			while ((row = csvReader.readLine()) != null) {
				i++;
				if (i==1) { continue; }
				String[] data = row.split(",");
				dominos.add(new Domino(
							i,
							nameToLetters.get(data[1]),
							nameToLetters.get(data[3]),
							Integer.parseInt(data[0]),
							Integer.parseInt(data[2])
				));
			}
			csvReader.close();
		} catch (IOException e) {
			System.out.println("Csv path not found.");
			System.out.println(e.getClass());
		}
		Deck deck = new Deck(dominos);
		deck.shuffle();
		deck.truncate(dominosNumber);

		return deck;
	}

}
