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
    private Setup setup; // TODO: see about removing the setup class from here?

    /**
     * Create the game manager
     *
     * @param app reference for quitting and the menu
     */
    public GameManager(Risky app) {
        this.app = app;
        startSetup();
    }

    /**
     * Setup a new game using the setup view and controller
     */
    public void startSetup() {
        this.setup = new Setup();
        SetupController setupController = new SetupController(this, this.setup);

        if (this.scene == null)
            this.scene = new Scene(setupController.getSkin());
        else
            this.scene.setRoot(setupController.getSkin());
    }

    /**
     * Initialize a game and have it ready to start running
     */
    public void initGame() {
        // get data from the setup (player names, map file)
        // apply data into structure for the new game
        // set the scene from the new game
        // game(controller|view|) handles user input and controls
    }

    /**
     * Get the scene currently being used
     *
     * @return Scene being used
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Quit the app to the menu again
     */
    public void quitToMenu() {
        this.app.menu();
    }
}
