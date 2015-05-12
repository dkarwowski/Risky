package riskyold.common;

import java.util.ArrayList;

public class Player {
    private String name = "no name";
    private int id;

    // The player should know the board it belongs to?

    // resources the player can place
    private int availableResources;
    private ArrayList<Spot> spotsOwned;
    private ArrayList<Country> countriesOwned;

    /**
     * Initialize a player with just a name and ID, sets the resources to defaults of 10&10
     *
     * @param setName name of the player
     * @param setID   id of the player for the game
     */
    public Player(String setName, int setID) {
        // TODO(david): set initial resources to be based on number of players
        this(setName, setID, 10);
    }

    /**
     * Initialize the player with all of the information necessary
     *
     * @param setName        name of the player
     * @param setID          id of the player for the game
     * @param startResources resources the game starts with
     */
    public Player(String setName, int setID, int startResources) {
        this.name = setName;
        this.id = setID;

        // initialize resources to the amount each player should start with
        this.availableResources = startResources;

        // spots Owned is initialized to be empty, first turn will add the first
        // spot(s)
        this.spotsOwned = new ArrayList<>();
        this.countriesOwned = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return (true);

        if (!(o instanceof Player))
            return (false);

        Player other = (Player) o;
        return (this.getID() == other.getID());
    }

    //--Getters Start-------------------------------------------------------------

    /**
     * Get the ID of the player
     *
     * @return integer id
     */
    public int getID() {
        return this.id;
    }

    /**
     * Get the name of the player
     *
     * @return String with name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the resources this player can use
     *
     * @return integer with resource amount
     */
    public int getAvailableResources() {
        return this.availableResources;
    }

    /**
     * Get all of the spots this player owns
     * TODO: check if we need to keep track of this here
     *
     * @return ArrayList of spots belonging to the player
     */
    public ArrayList<Spot> getSpotsOwned() {
        return this.spotsOwned;
    }

    /**
     * Get all of the countries owned by the player
     * TODO: check if we need to keep track of this here
     *
     * @return ArrayList of the countries owned
     */
    public ArrayList<Country> getCountriesOwned() {
        return this.countriesOwned;
    }

    //--Getters End---------------------------------------------------------------
    //--Debugging Functions-------------------------------------------------------

    @Override
    public String toString() {
        return "Player: " + this.name + " with id: " + this.id;
    }

    //--Debugging Functions End---------------------------------------------------
    //--Game Related Functions Start----------------------------------------------

    /**
     * Add a specific number of resources to the player
     *
     * @param resources Number of resources the game determines should be added
     */
    public void addResources(int resources) {
        this.availableResources += resources;
    }

    /**
     * Add a country to the list of owned
     *
     * @param c Country to add
     */
    public void addCountry(Country c) {
        this.countriesOwned.add(c);
    }

    /**
     * Add a spot to the list of owned Spots
     *
     * @param s Spots to be owned
     */
    public void addSpot(Spot s) {
        this.spotsOwned.add(s);
    }

    //--Game Related Functions End------------------------------------------------
}
