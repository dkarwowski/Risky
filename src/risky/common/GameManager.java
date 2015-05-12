package risky.common;

import javafx.scene.Scene;
import risky.Risky;
import risky.controller.SetupController;
import risky.model.Setup;

/**
 * Manages the scenes and the menu states
 * Created by davidkarwowski on 5/11/15.
 */
public class GameManager {
    private Risky app;
    private Scene scene;
    private Setup setup;

    public GameManager(Risky app) {
        this.app = app;
        startSetup();
    }

    public void startSetup() {
        setup = new Setup();
        SetupController setupController = new SetupController(this, setup);

        if (scene == null)
            scene = new Scene(setupController.getScene());
        else
            scene.setRoot(setupController.getScene());
    }

    public void initGame() {
        // get data from the setup (player names, map file)
        // apply data into structure for the new game
        // set the scene from the new game
        // game(controller|view|) handles user input and controls
    }

    public Scene getScene() {
        return this.scene;
    }

    public void quitToMenu() {
        app.menu();
    }
}
