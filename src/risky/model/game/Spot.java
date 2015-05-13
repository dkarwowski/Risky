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

    private ReadOnlyIntegerWrapper id; // used to compare two spots
    private ReadOnlyIntegerWrapper troops; // troops that have been placed
    private ReadOnlyObjectWrapper<Player> player; // person who owns the Spot

    /**
     * Create a new Spot for the player
     */
    public Spot() {
        this.id = new ReadOnlyIntegerWrapper(Spot.nextID++);
        this.troops = new ReadOnlyIntegerWrapper(0);
        this.player = new ReadOnlyObjectWrapper<>(null);
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Spot))
            return false;
        Spot o = (Spot) other;
        return o.getId() == this.getId();
    }
}
