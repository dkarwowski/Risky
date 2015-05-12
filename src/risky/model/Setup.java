package risky.model;

/**
 * Holds the information necessary to create a new instance of the game
 * Created by davidkarwowski on 5/11/15.
 */
public class Setup {
    private String mapFileName;
    private int numberOfPlayers;
    private String[] namesOfPlayers;

    public Setup() {
        mapFileName = null;
        numberOfPlayers = -1;
        namesOfPlayers = null;
    }

    public void setMapFileName(String mapFileName) {
        this.mapFileName = mapFileName;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setNamesOfPlayers(String... namesOfPlayers) {
        this.namesOfPlayers = namesOfPlayers;
    }
}
