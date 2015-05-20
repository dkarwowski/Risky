package com.davidk.risky.model.game;

import java.util.ArrayList;

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
    private final Spot[] spots;   // single dimensional array of spots
    private final ArrayList<Country> countries;

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

    /**
     * Get the spots on the board
     *
     * @return array of spots
     */
    public Spot[] getSpots() {
        return this.spots;
    }

    /**
     * Get a specific spot from the board
     * TODO: use coords
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return  the specified spot
     */
    public Spot getSpot(int x, int y) {
        return this.spots[x + y * this.getWidth()];
    }

    /**
     * Get the dimensions of the board
     *
     * @return dimensions {width, height}
     */
    public int[] getDimensions() {
        return this.dimensions;
    }

    /**
     * Get the width of the board
     *
     * @return number of spots across
     */
    public int getWidth() {
        return this.dimensions[0];
    }

    /**
     * Get the height of the board
     *
     * @return number of spots high
     */
    public int getHeight() {
        return this.dimensions[1];
    }

    /**
     * Create a new spot on the board
     *
     * @param x x index for spot
     * @param y y index for spot
     */
    public void createSpot(int x, int y) {
        assert this.getSpot(x, y) == null;
        Spot insert = new Spot(x, y);
        // iterate over exits
        for (Coords.Dir dir : Coords.Dir.values()) {
            Coords exit = insert.coordsInDir(dir);
            // TODO: clean this up to a better check
            int exitI = exit.toIndex(this.getWidth(), this.getHeight());
            if (exitI == -1) continue;

            insert.setExit(dir, this.spots[exitI]);
        }

        this.spots[x + y * this.getWidth()] = insert;
    }

    /**
     * Remove a spot on the board
     *
     * @param x x index for spot
     * @param y y index for spot
     */
    public void removeSpot(int x, int y) {
        assert this.getSpot(x, y) != null;
        Spot remove = this.spots[x + y * this.getWidth()];
        // iterate over exits
        for (Coords.Dir dir : Coords.Dir.values()) {
            Coords exit = remove.coordsInDir(dir);
            // TODO: clean this up to a better check
            int exitI = exit.toIndex(this.getWidth(), this.getHeight());
            if (exitI == -1) continue;

            remove.removeExit(dir, this.spots[exitI]);
        }

        this.spots[x + y * this.getWidth()] = null;
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
