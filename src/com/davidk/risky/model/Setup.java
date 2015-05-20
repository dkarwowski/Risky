package com.davidk.risky.model;

/**
 * Holds the information necessary to create a new instance of the game
 * Created by davidkarwowski on 5/11/15.
 */
public class Setup {
    private String mapFileName;
    private int numberOfPlayers;
    private String[] namesOfPlayers;

    /**
     * Create the variables, ensuring they are all wrong initially
     */
    public Setup() {
        this.mapFileName = null;
        this.numberOfPlayers = -1;
        this.namesOfPlayers = null;
    }

    /**
     * Set the file name of the map to use
     *
     * @param mapFileName file name chosen
     */
    public void setMapFileName(String mapFileName) {
        this.mapFileName = mapFileName;
    }

    /**
     * Set the number of players to be used
     *
     * @param numberOfPlayers people playing the game
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Set the names of the players for the game
     *
     * @param namesOfPlayers names of the players who will be active
     */
    public void setNamesOfPlayers(String... namesOfPlayers) {
        this.namesOfPlayers = namesOfPlayers;
    }

    /**
     * Get the name of the map
     *
     * @return file name for the map chosen
     */
    public String getMapFileName() {
        return this.mapFileName;
    }

    /**
     * Get the names of the players active
     *
     * @return list of player names
     */
    public String[] getNamesOfPlayers() {
        return this.namesOfPlayers;
    }

    /**
     * Check if all the setup variables have been initialized
     *
     * @return true if all variables are alright
     */
    public boolean variablesCorrect() {
        return (this.mapFileName != null
                && 2 <= this.numberOfPlayers
                && this.numberOfPlayers <= 6
                && this.namesOfPlayers != null);
    }
}
