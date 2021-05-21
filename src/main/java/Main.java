import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
// import javafx.event.KeyEvent;

public class Main extends Application {
	protected String title = "King Domino";
	// protected String label = ""
	// protected boolean fullscreen = true;
	protected boolean fullscreen = false;
	// protected int width = 2560;
	// protected int height = 1600;
	protected int width = 800;
		protected int height = 600;

	// @Override
	// protected void initSettings(GameSettings settings) {
	// 		settings.setWidth(800);
	// 		settings.setHeight(600);
	// 		settings.setTitle("Basic Game App");
	// }

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(title);

		Label label = new Label("test");
		Scene scene = new Scene(label, width, height);
		stage.setFullScreen(fullscreen);
		stage.setScene(scene);

		stage.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
			System.out.println("Key pressed: " + event.toString());

			switch(event.getCode().getCode()) {
					case 27 : { // 27 = ESC key
							stage.close();
							break;
					}
					case 81 : { // 81 = q key
							stage.close();
							break;
					}
					case 10 : { // 10 = Return
							stage.setWidth( stage.getWidth() * 2);
					}
					default:  {
							System.out.println("Unrecognized key");
					}
			}
	});

		// stage.setOnCloseRequest(close);
		stage.show();
	}


	public static void main(String[] args) {
		// Integer playerNumber = Integer.valueOf(args[0]);
		// Domination game = Domination.build(playerNumber);
		// loop(game);
		// System.out.println(args[0]);
		Application.launch(args);
	}

	// protected void close(event) {
	// 	System.out.println("closing game");
	// }


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
