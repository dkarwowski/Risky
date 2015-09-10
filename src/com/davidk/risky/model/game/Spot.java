package com.davidk.risky.model.game;

import com.davidk.risky.common.hexagon.Hex;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Holds information regarding the spots themselves
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class Spot extends Hex {
    private static int nextID = 1;

    private final ReadOnlyIntegerWrapper id;              // used to compare two spots
    private final ReadOnlyIntegerWrapper troops;          // troops that have been placed
    private final ReadOnlyObjectWrapper<Player> player;   // person who owns the Spot
    private final ReadOnlyObjectWrapper<Country> country; // country
    private Spot[] exits;

    /**
     * Create a new Spot for the board
     *
     * @param x integer x coordinate
     * @param y integer y coordinate
     */
    public Spot(int x, int y) {
        super(x, y);
        this.id = new ReadOnlyIntegerWrapper(Spot.nextID++);
        this.troops = new ReadOnlyIntegerWrapper(0);
        this.player = new ReadOnlyObjectWrapper<>(null);
        this.country = new ReadOnlyObjectWrapper<>(null);
        this.exits = new Spot[6];
    }

    /**
     * Get the spot's current ID
     *
     * @return integer value of the unique Id
     */
    public int getId() {
        return this.id.get();
    }

    /**
     * Get the number of troops placed on the Spot
     *
     * @return Integer value for the troops
     */
    public int getTroops() {
        return this.troops.get();
    }

    /**
     * Get the Player who owns the Spot
     *
     * @return Player object if exists, null otherwise
     */
    public Player getPlayer() {
        return this.player.get();
    }

    /**
     * Get the exits from this spot
     *
     * @return list of exits
     */
    public Spot[] getExits() {
        return this.exits;
    }

    /**
     * Set a specific exit for the spot
     *
     * @param dir  direction of the exit
     * @param exit the spot to exit to
     */
    public void setExit(int dir, Spot exit) {
        if (exit == null)
            return;

        exit.setExit(-dir, this, true);
        this.setExit(dir, exit, true);
    }

    /**
     * Set an exit for the spot spot, callback for inner functions
     *
     * @param dir      direction to go in
     * @param exit     the spot to exit to
     * @param callback whether called internally
     */
    public void setExit(int dir, Spot exit, boolean callback) {
        if (callback)
            this.exits[dir] = exit;
        else
            this.setExit(dir, exit); // exit needs exit also set
    }

    /**
     * Get rid of an exit between two spots
     *
     * @param dir  the direction
     * @param exit the current exit
     */
    public void removeExit(int dir, Spot exit) {
        if (exit == null)
            return;

        exit.removeExit(-dir, this, true);
        this.removeExit(dir, exit, true);
    }

    /**
     * Get rid of an exit between two spots, internal callback
     *
     * @param dir      direction of spot
     * @param exit     exit being removed
     * @param callback whether called internally
     */
    public void removeExit(int dir, Spot exit, boolean callback) {
        if (callback)
            this.exits[dir] = null;
        else
            this.removeExit(dir, exit);
    }

    /**
     * Get the country the spot belongs to
     *
     * @return Country object
     */
    public Country getCountry() {
        return this.country.get();
    }

    /**
     * Set the country to be a new value
     *
     * @param country Country to be set
     */
    public void setCountry(Country country) {
        this.country.set(country);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Spot))
            return false;
        Spot o = (Spot) other;
        return o.getId() == this.getId();
    }
}
