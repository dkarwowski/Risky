package com.davidk.risky.model.game;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;

import java.util.List;

/**
 * Country model, holds all information pertaining the country
 * Created by davidkarwowski on 5/13/15.
 */
public class Country {
    private static int nextID = 1;

    private final ReadOnlyIntegerWrapper id;
    private final ReadOnlyListWrapper<Spot> spots;
    private final ReadOnlyStringWrapper name;

    /**
     * Create a new country
     *
     * @param name name of the country, must be unique
     */
    public Country(String name) {
        assert(name != null);
        this.name = new ReadOnlyStringWrapper(name);
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

    /**
     * Get the Country's name, which should be unique
     *
     * @return String containing the non-null name
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * Adds a spot to the country so long as there's nothing to overwrite
     *
     * @param spot       What should be added
     * @throws Exception If the spot already has an assigned country (should be dealt with elsewhere)
     */
    public void add(Spot spot) throws Exception {
        if (spots.contains(spot))
            return;
        if (spot.getCountry() != null)
            throw new Exception("Spot has country");

        spots.add(spot);
        spot.setCountry(this);
    }

    /**
     * Remove a spot from the country
     *
     * @param spot The spot to remove
     */
    public void remove(Spot spot) {
        if (!spots.contains(spot))
            return;

        spots.remove(spot);
        spot.setCountry(null);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Country))
            return false;

        Country o = (Country) other;
        return (o.getId() == this.getId() && o.getName().equals(this.getName()));
    }
}
