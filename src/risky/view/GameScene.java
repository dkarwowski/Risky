package risky.view;

import risky.controller.GameController;

/**
 * Scene of the game. Will hold the views for the various parts of gameplay
 * Throws user input into the controller
 * Created by davidkarwowski on 5/12/15.
 */
public class GameScene {
    private final GameController controller;

    /**
     * Create a new scene for the game
     *
     * @param controller the controller that will handle input
     */
    public GameScene(GameController controller) {
        this.controller = controller;
    }

    // TODO: create system for viewing the board, check notebook
}
