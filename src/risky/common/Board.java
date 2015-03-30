package risky.common;

import java.util.ArrayList;

public class Board {
    private String name;
    private int width;
    private int height;

    private Spot[] spots;
    private Country[] countries;

    private ArrayList<Coords> freeSpots;

    public Board() {
        this(0, 0);
    }

    public Board(int setWidth, int setHeight) {
        this("Generic", setWidth, setHeight);
    }

    public Board(String setName, int setWidth, int setHeight) {
        this(setName, setWidth, setHeight, new Spot[setWidth * setHeight]);
    }

    public Board(String setName, int setWidth, int setHeight, Spot[] setSpots){
        this.name = setName;
        this.width = setWidth;
        this.height = setHeight;
        this.freeSpots = new ArrayList<Coords>();

        this.spots = new Spot[this.width * this.height];
        if (setSpots != null) {
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    this.spots[x + y * this.width] = setSpots[x + y * this.width];
                    if (setSpots[x + y * this.width] != null)
                        this.freeSpots.add(this.spots[x + y * this.width].getCoords());
                }
            }
        }

        this.countries = null;
        this.setCountries();
    }

    public void setSpot(Spot spot) {
        Coords coords = spot.getCoords();
        int x = coords.getXCart();
        int y = coords.getYCart();
        this.spots[x + y * this.width] = spot;
        if (spot.getPlayer() == null)
            this.freeSpots.add(this.spots[x + y * this.width].getCoords());

        this.setCountries();
    }

    //--Getters Start----------------------------------------------------------

    public String getName(){
        return this.name;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Spot getSpot(Coords c) {
        return getSpot(c.getXCart(), c.getYCart());
    }

    public Spot getSpot(int x, int y) {
        if (contains(x, y))
            return this.spots[x + y * this.width];
        return null;
    }
    
    public Spot[] getAllSpots() {
        return this.spots;
    }

    public Country getCountry(Spot s) {
        return s.getCountry();
    }

    public Country getCountry(int x, int y) {
        if (this.getSpot(x, y) != null)
            return this.getCountry(this.getSpot(x, y));
        return null;
    }

    public Country getCountry(Coords c) {
        return this.getCountry(c.getXCart(), c.getYCart());
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
        boolean singlePlayerOwnsAll = true;
        for (Country country : countries) {
            singlePlayerOwnsAll &= country.doesPlayerOwnAllSpots();
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
     * TODO(david): deal with combat
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
