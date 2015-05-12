package risky.controller;

import risky.common.GameManager;
import risky.model.Game;
import risky.view.GameScene;

/**
 * Controls the game, handles the user input from the view.
 * Converts user input to Game-related data structures.
 *
 * Created by davidkarwowski on 5/12/15.
 */
public class GameController {
    private final GameManager manager;
    private final GameScene scene;
    private final Game game;

    /**
     * Create a controller for the overall game
     *
     * @param manager Game Manager for reference when quitting
     */
    public GameController(GameManager manager) {
        this.manager = manager;
        this.scene = new GameScene(this);
        this.game = new Game(this.scene);
    }

    /**
     * Create a new game that does not choose a new board or players
     */
    public void newGame() {
        this.game.init();
    }

    /**
     * Create a new game using a new board and player names
     *
     * @param mapFileName name of the map file to use
     * @param playerNames names of the players in the game
     */
    public void newGame(String mapFileName, String[] playerNames) {
        this.game.init(mapFileName, playerNames);
    }
}
