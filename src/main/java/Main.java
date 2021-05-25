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
import javafx.scene.layout.Pane;
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

public class Main extends Application {
	protected String title = "King Domino";
	protected boolean fullscreen = false;
	protected int width = 800;
	protected int height = 600;
	protected Stage stage;
	protected Circle circle = new Circle();
	protected static String csvPath = "../../../assets/dominos.csv";

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
		// Domination game = new Domination();
	 // FileInputStream input = new FileInputStream("resources/images/iconmonstr-home-6-48.png");
	 stage.setTitle(title);

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

		// Pane pane = new Pane();
		pane.getChildren().add(circle);
		pane.getChildren().add(rectangle);

		// Scene scene = new Scene(pane, 1024, 800, true);

		// stage.show();

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

		// Label label = new Label("test");
		Scene scene = new Scene(pane, width, height);

		stage.setFullScreen(fullscreen);
		stage.setScene(scene);
		// scene.setCursor(Cursor.OPEN_HAND);
		// stage.setOnCloseRequest(close);

		stage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> eventHandlerKeyPressed(event, stage));

		stage.show();
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
	public static void loadImages() {

	}

	/*
	 * Build the deck of cards.
	 */
	public static Deck buildDeck() {
		ArrayList<Card> cards = new ArrayList<Card>();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
			// while ((row = csvReader.readLine()) != null) {
			// 	String[] data = row.split(",");
			// 	System.out.println(data);
			// 	cards.add(new Card());
			// }
			csvReader.close();
		} catch (IOException e) {
			System.out.println("Csv path not found.");
			System.out.println(e.getClass());
		}
		return new Deck(cards);
	}
}
