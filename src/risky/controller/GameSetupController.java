package risky.controller;

import risky.common.GameManager;
import risky.model.GameSetup;
import risky.view.GameSetupScene;

/**
 * Handles the buttons and choices made in the Setup Menu
 * Created by davidkarwowski on 5/11/15.
 */
public class GameSetupController {
    private final GameManager gameManager;
    private final GameSetupScene setupScene;
    private final GameSetup gameSetup;

    public GameSetupController(GameManager manager, GameSetup setup) {
        setupScene = new GameSetupScene(this);
        gameManager = manager;
        gameSetup = setup;
    }

    public GameSetupController(GameManager manager, GameSetupScene scene, GameSetup setup) {
        gameManager = manager;
        setupScene = scene;
        gameSetup = setup;
    }

    public void setMapSelect(String newValue) {
        gameSetup.setMapFileName(newValue);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        gameSetup.setNumberOfPlayers(numberOfPlayers);

        // TODO: create a name chooser
        String[] names = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            names[i] = String.format("Player %d", i + 1);
        }

        gameSetup.setNamesOfPlayers(names);
    }

    public void setSetupFinished() {
        gameManager.initGame();
    }

    public void quitToMenu() {
        gameManager.quitToMenu();
    }

    public GameSetupScene getScene() {
        return setupScene;
    }
}
