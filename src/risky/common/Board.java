package risky.common;

import java.util.ArrayList;

public class Board {
    private String name;
    private int width;
    private int height;

    private Spot[] spots;
    private Country[] countries;

    private int numSpots;

    private ArrayList<Coords> freeSpots;

    /**
     * Initialize the board with no dimensions
     */
    public Board() {
        this(0, 0);
    }

    /**
     * Initialize the board to be empty with specific dimensions
     * @param setWidth the determined width
     * @param setHeight the determined height
     */
    public Board(int setWidth, int setHeight) {
        this("Generic", setWidth, setHeight);
    }

    /**
     * Initialize the board with a name and dimensions
     * @param setName the name of the board
     * @param setWidth the determined width
     * @param setHeight the determined height
     */
    public Board(String setName, int setWidth, int setHeight) {
        this(setName, setWidth, setHeight, new Spot[setWidth * setHeight]);
    }

    /**
     * Initialize the board with specific spots
     * @param setName the name of the board
     * @param setWidth the width of the board
     * @param setHeight the height of the board
     * @param setSpots the spots to be initialized
     */
    public Board(String setName, int setWidth, int setHeight, Spot[] setSpots){
        this.name = setName;
        this.width = setWidth;
        this.height = setHeight;
        this.freeSpots = new ArrayList<Coords>();
        this.numSpots = 0;

        this.spots = new Spot[this.width * this.height];
        if (setSpots != null) {
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    this.spots[x + y * this.width] = setSpots[x + y * this.width];
                    if (setSpots[x + y * this.width] != null) {
                        this.freeSpots.add(this.spots[x + y * this.width].getCoords());
                        this.numSpots += 1;
                    }
                }
            }
        }

        this.countries = null;
        this.setCountries();
    }

    /**
     * Set a spot on the board (from empty to not)
     * TODO(david): make this able to work with setting a position to empty
     * @param spot what to set the position on the board to
     */
    public void setSpot(Spot spot) {
        Coords coords = spot.getCoords();
        int x = coords.getXCart();
        int y = coords.getYCart();
        this.numSpots += 1;
        this.spots[x + y * this.width] = spot;
        if (spot.getPlayer() == null)
            this.freeSpots.add(this.spots[x + y * this.width].getCoords());

        this.setCountries();
    }

    //--Getters Start----------------------------------------------------------

    /**
     * Get the name of the board
     * @return String with the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get the width of the board
     * @return integer width
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Get the height of the board
     * @return integer height
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * Get a spot at specified coordinates
     * @param c Coords to grab
     * @return the Spot or null
     */
    public Spot getSpot(Coords c) {
        return getSpot(c.getXCart(), c.getYCart());
    }

    /**
     * Get a spot with x, y values. Cartesian only
     * @param x integer of Cartesian X Coordinate
     * @param y integer of Cartesian Y Coordinate
     * @return Spot located there
     */
    public Spot getSpot(int x, int y) {
        if (contains(x, y))
            return this.spots[x + y * this.width];
        return null;
    }
    
    /**
     * Get the board of all spots
     * @return Array of every spot
     */
    public Spot[] getAllSpots() {
        return this.spots;
    }

    /**
     * Get the country with a specific spot inside of it
     * @param s Spot in said country
     * @return Country
     */
    public Country getCountry(Spot s) {
        return s.getCountry();
    }

    /**
     * Get a country with specific coordinates
     * @param x integer of Cartesian X coordinate
     * @param y integer of Cartesian Y coordinate
     * @return Country
     */
    public Country getCountry(int x, int y) {
        if (this.getSpot(x, y) != null)
            return this.getCountry(this.getSpot(x, y));
        return null;
    }

    /**
     * Get a country based on its coordinates
     * @param c Coords in country
     * @return Country
     */
    public Country getCountry(Coords c) {
        return this.getCountry(c.getXCart(), c.getYCart());
    }

    /**
     * Get the number of spots on the board
     * @return integer of spots
     */
    public int getNumSpots() {
        return (this.numSpots);
    }

    //--Getters End------------------------------------------------------------

    //--Game Related Functions Start------------------------------------------------------------

    /**
     * Checks if the spot is within bounds based on Cartesian Coordinates
     * @param x Cartesian X
     * @param y Cartesian Y
     * @return whether the spot is within bounds
     */
    public boolean contains(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < width) && (y < height);
    }

    /**
     * Check if the coordinates exist
     * @param c The coordinates of the space
     * @return whether the spot is within bounds
     */
    public boolean contains(Coords c) {
        if (c == null) {
            return false;
        }
        return contains(c.getXCart(), c.getYCart());
    }
    
    /**
     * Check if a spot is playable based on Cartesian Coordinates
     * @param x Cartesian X
     * @param y Cartesian Y
     * @return whether the spot exists
     */
    public boolean containsSpot(int x, int y) {
        if (!this.contains(x,y))
            return (false);
        return (this.spots[x + y * this.width] != null);
    }
    
    /**
     * Check if a spot is playable based on coordinates
     * @param c Coordinates of the spot
     * @return whether the spot exists
     */
    public boolean containsSpot(Coords c) {
        if (c == null)
            return (false);
        return (this.containsSpot(c.getXCart(), c.getYCart()));
    }

    /**
     * Check if a single player owns the whole board
     * @return whether a player has won or not
     */
    public Player playerOwnsAll() {
        this.claimCountries();

        boolean singlePlayerOwnsAll = true;
        Player player = countries[0].getOwner();
        for (Country country : countries) {
            singlePlayerOwnsAll &= (country.getOwner().equals(player));
        }

        if (singlePlayerOwnsAll)
            return (countries[0].getOwner());
        return (null);
    }

    /**
     * Set all the countries based on the spots array that should be initialized
     * Used to update the board.
     */
    private void setCountries() {
        ArrayList<Country> toSet = new ArrayList<Country>();
        for (Spot spot : this.spots) {
            if (spot == null) continue;
            boolean shouldSkip = false;
            for (Country country : toSet) {
                if (country.equals(spot.getCountry())) {
                    shouldSkip = true;
                    break;
                }
            }

            if (shouldSkip) continue;
            if (spot != null) toSet.add(spot.getCountry());
        }

        this.countries = new Country[toSet.size()];
        for (int i = 0; i < toSet.size(); ++i) {
            this.countries[i] = toSet.get(i);
        }
    }

    /**
     * Claim a spot for a player based on cartesian coordinates
     * @param player who is claiming
     * @param x Cartesian X
     * @param y Cartesian Y
     * @param resources number of resources being placed
     */
    public void claimSpot(Player player, int x, int y, int resources) {
        Coords coords = new Coords(x, y, false);
        this.claimSpot(player, coords, resources);
    }
    
    /**
     * Claim a spot based on the coordinates of the space
     * @param player who is claiming
     * @param coords the space being claimed
     * @param resources number being placed
     */
    public void claimSpot(Player player, Coords coords, int resources) {
        if (this.contains(coords)) {
            this.getSpot(coords).setPlayer(player);
            this.getSpot(coords).setResources(resources);
            this.freeSpots.remove(this.getSpot(coords).getCoords());
        }
    }

    /**
     * Check if a spot has been claimed already based on Cartesian Coordinates
     * @param x Cartesian X
     * @param y Cartesian Y
     * @return whether the spot is empty
     */
    public boolean spotFree(int x, int y) {
        Coords coords = new Coords(x, y, false);
        return (this.spotFree(coords));
    }
    
    /**
     * Check if a spot has been claimed already
     * @param c coordinates of the space
     * @return whether the spot is empty
     */
    public boolean spotFree(Coords c) {
        if (this.contains(c))
            return (this.getSpot(c).getPlayer() == null);
        return false;
    }

    /**
     * Update the countries to be claimed if necessary
     */
    public void claimCountries() {
        for (Country c : this.countries)
            c.claimCountry();
    }

    /**
     * Check if the setup is done by if there are any empty spots
     * @return boolean regarding state of spots
     */
    public boolean isSetupDone() {
        boolean result = this.freeSpots.isEmpty();
        return (result);
    }

    //--Game Related Functions End------------------------------------------------------------	 

    //--Other Functions-----------------------------------------------------------------------
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.width; i++)
            result += (i % 2 == 0) ? " __" : "   ";

        result += "\n";

        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                String spotName = (this.spots[x + y * this.width] != null) ? 
                        this.spots[x + y * this.width].simpleString() : " ";
                        result += (x % 2 == 0) ? 
                                String.format("/ %s", spotName)  : "\\__";
            }

            result += "/\n";

            for (int x = 0; x < this.width; ++x) {
                String spotName = (this.spots[x + y * this.width] != null) ? 
                        this.spots[x + y * this.width].simpleString() : " ";
                        result += (x % 2 == 0) ? 
                                "\\__" : String.format("/ %s", spotName);
            }

            result += "\\\n";
        }

        for (int i = 0; i < this.width; i++)
            result += (i % 2 == 0) ? "/  " : "\\__";

        result += "/\n";

        return (result);
    }

}
