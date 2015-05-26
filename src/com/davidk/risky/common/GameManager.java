package com.davidk.risky.common;

import com.davidk.risky.Risky;
import com.davidk.risky.controller.SetupController;
import com.davidk.risky.model.Setup;
import javafx.scene.Scene;

/**
 * Manages the scenes and the menu states
 *
 * Created by davidkarwowski on 5/11/15.
 */
public class GameManager {
    private final Risky app;
    private Scene scene;

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
        Setup setup = new Setup();
        SetupController setupController = new SetupController(this, setup);

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
