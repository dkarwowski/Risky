package risky.model.game;

/**
 * Model for the Board of the game
 *
 * Created by davidkarwowski on 5/12/15.
 */
public class Board {
    // TODO: possibly make board hold country, country hold spots?
    private Spot[] spots;   // single dimensional array of spots
    private Country[] countries;

    /**
     * Initialize the board variables
     */
    public Board() {
        this.spots = null;
        this.countries = null;
    }

    /**
     * Reset the board back to it's initialized state
     * TODO: implement
     *
     * @return true if the reset is successful
     */
    public boolean reset() {
        return false;
    }

    /**
     * Initialize the board off of a map file
     * TODO: implement
     *
     * @param mapFileName filename of the map
     * @return true if loading is successful
     */
    public boolean loadMapFile(String mapFileName) {
        return false;
    }
}
