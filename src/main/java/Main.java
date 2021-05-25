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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import domination.Domination;
import domination.Card;
import domination.Deck;

/*
 * Main application of the game.
 */
public class Main extends Application {
	protected String title = "King Domino";
	protected boolean fullscreen = false;
	protected int width = 800;
	protected int height = 600;
	protected Stage stage;
	protected Circle circle = new Circle();
	protected String csvPath = "../../../assets/dominos.csv";
	protected ArrayList<Image> images = new ArrayList<Image>();
	protected int sceneMode = 2;
	protected int playerNumber = 2;

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
			case 10 : { // Return
				stage.setWidth( stage.getWidth() * 2);
			}
			case 74: {
				circle.setCenterY(circle.getCenterY() + 10);
				break;
			}
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

		Deck deck;
		deck = buildDeck();
		ArrayList<Player> players;
		players.add(new Player());
		players.add(new Player());
		Domination game = new Domination(playerNumber, deck);
		loadImages();

		// Scene scene = new Scene(pane, 1024, 800, true);
		// stage.show();

		stage.setFullScreen(fullscreen);
		Scene scene = getScene();
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
	}

	/*
	 * Choose the scene to load depending on the scene mode.
	 */
	public Scene getScene() {
		Scene scene;
		if (sceneMode == 0) {
			scene = buildMenu();
		// } else if (sceneMode == 1) {
		// 	Scene scene = mainScene();
		} else if (sceneMode == 2) {
			scene = testScene();
		}
		return scene;
	}


	/*
	 * Test scene.
	 */
	public Scene testScene() {
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: #000000;");

		circle.setCenterX(100);
		circle.setCenterY(100);
		circle.setRadius(125);
		circle.setStroke(Color.valueOf("#ff00ff"));
		circle.setStrokeWidth(5);
		circle.setFill(Color.TRANSPARENT);

		Rectangle rectangle = new Rectangle();
		rectangle.setX(200);
		rectangle.setY(200);
		rectangle.setWidth(300);
		rectangle.setHeight(400);
		rectangle.setStroke(Color.TRANSPARENT);
		rectangle.setFill(Color.valueOf("#00ffff"));

		pane.getChildren().add(circle);
		pane.getChildren().add(rectangle);
		return scene;
	}

	/*
	 * Main function to launch the game.
	 */
	public static void main(String[] args) {
		// Integer playerNumber = Integer.valueOf(args[0]);
		System.out.println(args[0]);
		Application.launch(args);
	}

	/*
	 * Menu scene, first scene of the game.
	 */
	public Scene buildMenu() {
		Label label = new Label("Menu");
		Button button1 = new Button("2 Players");
		Button button2 = new Button("3 Players");
		Button button3 = new Button("4 Players");

		FlowPane flowpane = new FlowPane();

		flowpane.getChildren().add(button1);
		flowpane.getChildren().add(button2);
		flowpane.getChildren().add(button3);

		return new Scene(flowpane, width, height);
	}

	/*
	 * Load the images.
	 */
	public static void loadImages() {
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
