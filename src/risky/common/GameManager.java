package risky.common;

import javafx.scene.Scene;
import risky.Risky;
import risky.controller.GameSetupController;
import risky.model.GameSetup;

/**
 * Manages the scenes and the menu states
 * Created by davidkarwowski on 5/11/15.
 */
public class GameManager {
    private Risky app;
    private Scene scene;

    public GameManager(Risky app) {
        this.app = app;
        startSetup();
    }

    public void startSetup() {
        GameSetup setup = new GameSetup();
        GameSetupController setupController = new GameSetupController(this, setup);

        if (scene == null)
            scene = new Scene(setupController.getScene());
        else
            scene.setRoot(setupController.getScene());
    }

    public void initGame() {
        System.out.println("Initializing Game");
    }

    public Scene getScene() {
        return this.scene;
    }

    public void quitToMenu() {
        app.menu();
    }
}
