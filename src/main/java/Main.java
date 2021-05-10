import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

	// @Override
	// protected void initSettings(GameSettings settings) {
	// 		settings.setWidth(800);
	// 		settings.setHeight(600);
	// 		settings.setTitle("Basic Game App");
	// }

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Test");

		Label label = new Label("Hello World, JavaFX !");
		Scene scene = new Scene(label, 400, 200);
		stage.setScene(scene);

		stage.show();
	}


	public static void main(String[] args) {
		// Integer playerNumber = Integer.valueOf(args[0]);
		// Domination game = Domination.build(playerNumber);
		// loop(game);
		// System.out.println(args[0]);
		Application.launch(args);
	}

	// protected static void loop(Domination game) {
	// 	while (!game.done) {
	// 		update(game);
	// 		draw(game);
	// 	}

	// }

	// protected static void update(Domination game) {

	// }

	// protected static void draw(Domination game) {

	// }
}

