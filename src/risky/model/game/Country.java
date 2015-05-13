package risky.model.game;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyListWrapper;

import java.util.List;

/**
 * Country model, holds all information pertaining the country
 * Created by davidkarwowski on 5/13/15.
 */
public class Country {
    private static int nextID = 1;

    private ReadOnlyIntegerWrapper id;
    private ReadOnlyListWrapper<Spot> spots;

    /**
     * Create a new country
     */
    public Country() {
        this.id = new ReadOnlyIntegerWrapper(Country.nextID++);
        this.spots = new ReadOnlyListWrapper<>();
    }

    /**
     * Get the unique id of this instance
     *
     * @return integer unique id
     */
    public int getId() {
        return this.id.get();
    }

    /**
     * Get the spots in this country
     *
     * @return List of spots
     */
    public List<Spot> getSpots() {
        return this.spots.get();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Country))
            return false;

        Country o = (Country) other;
        return (o.getId() == this.getId());
    }
}
