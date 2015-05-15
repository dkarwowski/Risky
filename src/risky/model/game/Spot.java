package risky.model.game;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * Holds information regarding the spots themselves
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class Spot {
    private static int nextID = 1;

    private final ReadOnlyIntegerWrapper id;              // used to compare two spots
    private final ReadOnlyIntegerWrapper troops;          // troops that have been placed
    private final ReadOnlyObjectWrapper<Player> player;   // person who owns the Spot
    private final Coords coords;   // coordinate location of the spot
    private Spot[] exits;

    /**
     * Create a new Spot for the board
     *
     * @param x integer x coordinate
     * @param y integer y coordinate
     */
    public Spot(int x, int y) {
        this(new Coords(x, y));
    }

    /**
     * Create a new Spot for the board
     *
     * @param coords coordinates of the spot
     */
    public Spot(Coords coords) {
        this.id = new ReadOnlyIntegerWrapper(Spot.nextID++);
        this.troops = new ReadOnlyIntegerWrapper(0);
        this.player = new ReadOnlyObjectWrapper<>(null);
        this.coords = coords;
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
     * Get the Coordinate location of the Spot
     *
     * @return Coordinates of where the spot is located
     */
    public Coords getCoords() {
        return this.coords;
    }

    /**
     * Get the coordinates for a direction
     *
     * @param dir the direction to try
     * @return    the coordinates
     */
    public Coords coordsInDir(Coords.Dir dir) {
        return this.coords.coordsInDir(dir);
    }

    /**
     * Set a specific exit for the spot
     *
     * @param dir  direction of the exit
     * @param exit the spot to exit to
     */
    public void setExit(Coords.Dir dir, Spot exit) {
        if (exit == null)
            return;

        exit.setExit(Coords.oppositeDir(dir), this, true);
        this.setExit(dir, exit, true);
    }

    /**
     * Set an exit for the spot spot, callback for inner functions
     *
     * @param dir      direction to go in
     * @param exit     the spot to exit to
     * @param callback whether called internally
     */
    public void setExit(Coords.Dir dir, Spot exit, boolean callback) {
        if (callback)
            this.exits[Spot.exitIndex(dir)] = exit;
        else
            this.setExit(dir, exit); // exit needs exit also set
    }

    /**
     * Get rid of an exit between two spots
     *
     * @param dir  the direction
     * @param exit the current exit
     */
    public void removeExit(Coords.Dir dir, Spot exit) {
        if (exit == null)
            return;

        exit.removeExit(Coords.oppositeDir(dir), this, true);
        this.removeExit(dir, exit, true);
    }

    /**
     * Get rid of an exit between two spots, internal callback
     *
     * @param dir      direction of spot
     * @param exit     exit being removed
     * @param callback whether called internally
     */
    public void removeExit(Coords.Dir dir, Spot exit, boolean callback) {
        if (callback)
            this.exits[Spot.exitIndex(dir)] = null;
        else
            this.removeExit(dir, exit);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Spot))
            return false;
        Spot o = (Spot) other;
        return o.getId() == this.getId();
    }

    /**
     * Get the exit index
     *
     * @param dir direction to look in
     * @return    index of exit
     */
    private static int exitIndex(Coords.Dir dir) {
        switch(dir) {
            case UP_RIGHT:
                return 0;
            case UP_LEFT:
                return 2;
            case UP:
                return 1;
            case DOWN_RIGHT:
                return 5;
            case DOWN:
                return 4;
            case DOWN_LEFT:
                return 3;
        }

        return -1;
    }
}
