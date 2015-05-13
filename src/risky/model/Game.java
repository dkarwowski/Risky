package risky.model;

import risky.model.game.Board;
import risky.model.game.Player;
import risky.view.GameSkin;

/**
 * Model of the game. Holds the logic for the game and will handle
 * all data structures presented
 *
 * Created by davidkarwowski on 5/12/15.
 */
public class Game {
    private GameSkin skin;
    // TODO: implement these
    private Board board;
    private Player[] players;

    /**
     * Initialize the new Game with its scene
     *
     * @param skin the scene to draw to
     */
    public Game(GameSkin skin) {
        this.skin = skin;

        this.board = null;
        this.players = null;
    }

    /**
     * Reinitialize the game using the same board layout and
     * the same number of players. Goes through and resets values
     *
     * @return true if the initialization worked
     */
    public boolean init() {
        assert this.board != null && this.players != null;

        boolean result = this.board.reset();
        for (Player player : players) { result &= player.reset(); }
        return result;
    }

    /**
     * Initialize the game with the given map file name (handled by board)
     * and the given player names (handled here)
     *
     * @param mapFileName name of the map to use
     * @param playerNames names of the players in the game
     * @return true if the initialization worked
     */
    public boolean init(String mapFileName, String[] playerNames) {
        if (mapFileName == null || playerNames == null)
            return false;
        assert playerNames.length > 1 && playerNames.length < 7;

        // create the board
        this.board = new Board();
        boolean result = this.board.loadMapFile(mapFileName);

        // create the players
        this.players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++)
            this.players[i] = new Player(playerNames[i]);

        return result;
    }
}
