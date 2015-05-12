package risky;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import risky.common.GameManager;
import risky.view.MenuScene;

/**
 * Main Loop to run the game from
 * Works as a controller for the main menu and game setup
 * Created by David Karwowski on 5/11/15.
 */
public class Risky extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        // TODO: create pieces of the game to be placed here
        // TODO: add stylesheet loading here

        this.menu();

        this.stage.setTitle("Risky");
        this.stage.show();
    }

    /**
     * Instantiate and set the menu up
     */
    public void menu() {
        MenuScene menuScene = new MenuScene(this);

        Scene scene = new Scene(menuScene);
        this.stage.setScene(scene);
    }

    /**
     * Instantiate and setup a new game
     */
    public void newGame() {
        GameManager manager = new GameManager(this);

        Scene scene = manager.getScene();
        this.stage.setScene(scene);
    }

    /**
     * Exit the application entirely
     */
    public void quitAll() {
        Platform.exit();
    }
}
