package risky.controller;

import risky.common.GameManager;
import risky.model.Setup;
import risky.view.SetupSkin;

/**
 * Handles the buttons and choices made in the Setup Menu
 * Created by davidkarwowski on 5/11/15.
 */
public class SetupController {
    private final GameManager gameManager;
    private final SetupSkin setupSkin;
    private final Setup setup;

    /**
     * Input controller setup for the game.
     *
     * @param manager Game Manager for reference when returning
     * @param setup Model for the controller
     */
    public SetupController(GameManager manager, Setup setup) {
        this.setupSkin = new SetupSkin(this);
        this.gameManager = manager;
        this.setup = setup;

        // TODO: see if this is necessary
        this.setNumberOfPlayers(2);
    }

    /**
     * Set the map in the model, called by the setup scene
     *
     * @param newValue the new map file name
     */
    public void setMapSelect(String newValue) {
        this.setup.setMapFileName(newValue);
    }

    /**
     * Set the number of players in the model, called by setup scene
     *
     * @param numberOfPlayers the number of players used
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.setup.setNumberOfPlayers(numberOfPlayers);

        // TODO: create a name chooser
        String[] names = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            names[i] = String.format("Player %d", i + 1);
        }

        this.setup.setNamesOfPlayers(names);
    }

    /**
     * Set the game that setup is completely done
     * TODO: see if model has correct values
     */
    public void setSetupFinished() {
        this.gameManager.initGame();
    }

    /**
     * Quit the game back to the menu (When cancelled)
     */
    public void quitToMenu() {
        this.gameManager.quitToMenu();
    }

    /**
     * Get the scene for the main app to be fixed
     *
     * @return SetupScene being used at the moment
     */
    public SetupSkin getSkin() {
        return this.setupSkin;
    }
}
