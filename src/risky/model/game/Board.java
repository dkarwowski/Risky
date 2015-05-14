package risky.model.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Model for the Board of the game
 * Water spot: null
 * Blank spot: instantiated, empty
 * Users spot: instantiated, taken
 *
 * Created by davidkarwowski on 5/12/15.
 */
public class Board {
    // TODO: possibly make board hold country, country hold spots?
    private Spot[] spots;   // single dimensional array of spots
    private ArrayList<Country> countries;

    private int[] dimensions = new int[2];

    /**
     * Initialize the board variables
     */
    public Board() {
        this.spots = null;
        this.countries = new ArrayList<>();
    }

    /**
     * Create a blank board
     *
     * @param width  width of the new board
     * @param height height of the new board
     */
    public Board(int width, int height) {
        this.dimensions = new int[]{width, height};
        this.spots = new Spot[width * height];
        this.countries = new ArrayList<>();
    }

    public Spot[] getSpots() {
        return this.spots;
    }

    public int[] getDimensions() {
        return this.dimensions;
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

    /**
     * Reset the board back to it's initialized state
     * TODO: implement
     *
     * @return true if the reset is successful
     */
    public boolean reset() {
        return false;
    }
}
