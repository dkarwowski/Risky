package risky;

import java.util.ArrayList;

public class Player {
    private String name = "no name";
    private int id;

    // The player should know the board it belongs to?

    // resources the player can place
    private int availableResources;
    private int resourcesPerTurn;
    private ArrayList<Spot> spotsOwned;
    private ArrayList<Country> countriesOwned;
    
    /**
     * Initialize a player with just a name and ID, sets the resources to defaults of 10&10
     * @param setName name of the player
     * @param setID id of the player for the game
     */
    public Player(String setName, int setID) {
        this(setName, setID, 10, 10);
    }

    /**
     * Initialize the player with all of the information necessary
     * @param setName name of the player
     * @param setID id of the player for the game
     * @param startResources resources the game starts with
     * @param resourcesPerTurn resources automatically gained per turn
     */
    public Player(String setName, int setID, int startResources, int resourcesPerTurn) {
        this.name = setName;
        this.id = setID;

        // initialize resources to the amount each player should start with
        this.availableResources = startResources;
        this.resourcesPerTurn = resourcesPerTurn;

        // spots Owned is initialized to be empty, first turn will add the first
        // spot(s)
        this.spotsOwned = new ArrayList<Spot>();
        this.countriesOwned = new ArrayList<Country>();
    }
    
//--Getters Start-------------------------------------------------------------
    
    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    
    public int getAvailableResources() {
        return this.availableResources;
    }
    
    public ArrayList<Spot> getSpotsOwned() {
        return this.spotsOwned;
    }
    
    public ArrayList<Country> getCountriesOwned() {
        return this.countriesOwned;
    }
    
//--Getters End---------------------------------------------------------------
//--Game Related Functions----------------------------------------------------
    
    // should add default 
    public void addResources() {
        this.availableResources += this.resourcesPerTurn;
    }
}
