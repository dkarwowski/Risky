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
    private final ReadOnlyObjectWrapper<Coords> coords;   // coordinate location of the spot

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
        this.coords = new ReadOnlyObjectWrapper<>(coords);
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
        return this.coords.get();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Spot))
            return false;
        Spot o = (Spot) other;
        return o.getId() == this.getId();
    }
}
