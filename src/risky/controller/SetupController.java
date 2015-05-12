package risky.controller;

import risky.common.GameManager;
import risky.model.Setup;
import risky.view.SetupScene;

/**
 * Handles the buttons and choices made in the Setup Menu
 * Created by davidkarwowski on 5/11/15.
 */
public class SetupController {
    private final GameManager gameManager;
    private final SetupScene setupScene;
    private final Setup setup;

    public SetupController(GameManager manager, Setup setup) {
        setupScene = new SetupScene(this);
        gameManager = manager;
        this.setup = setup;
    }

    public SetupController(GameManager manager, SetupScene scene, Setup setup) {
        gameManager = manager;
        setupScene = scene;
        this.setup = setup;
    }

    public void setMapSelect(String newValue) {
        setup.setMapFileName(newValue);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        setup.setNumberOfPlayers(numberOfPlayers);

        // TODO: create a name chooser
        String[] names = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            names[i] = String.format("Player %d", i + 1);
        }

        setup.setNamesOfPlayers(names);
    }

    public void setSetupFinished() {
        gameManager.initGame();
    }

    public void quitToMenu() {
        gameManager.quitToMenu();
    }

    public SetupScene getScene() {
        return setupScene;
    }
}
