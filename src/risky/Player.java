package risky;

import java.util.ArrayList;

public class Player {
    private String name = "no name";
    private int id;

    // The player should know the board it belongs to?

    // resources the player can place
    private int availableResources;
    private ArrayList<Spot> spotsOwned;

    public Player(String setName, int setID) {
        this.name = setName;
        this.id = setID;

        // initialize resources to the amount each player should start with
        this.availableResources = 10;

        // spots Owned is initialized to be empty, first turn will add the first
        // spot(s)
        this.spotsOwned = new ArrayList<Spot>();
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
