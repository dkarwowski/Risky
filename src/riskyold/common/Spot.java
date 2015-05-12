package riskyold.common;

public class Spot {
    private Player _player;
    private int _resources;
    private Country _country;
    private Coords _coordinates;

    //--Constructors Start-------------------------------------------------------

    /**
     * Generic constructor for spots
     */
    public Spot() {
        _player = null;
        _resources = 0;
        _country = null;
        _coordinates = new Coords(0, 0); //Default Coords of 0,0
    }

    /**
     * Construct spots with their Country and position
     *
     * @param country Country to be set
     * @param coords  Coords of location
     */
    public Spot(Country country, Coords coords) {
        this.setPlayer(null);
        this.setResources(0);
        this._country = country;
        this._coordinates = coords;
    }

    //--Constructors End---------------------------------------------------------

    //--Getters Start------------------------------------------------------------

    /**
     * Get the player who owns the spot
     *
     * @return Player instance or null
     */
    public Player getPlayer() {
        return _player;
    }

    /**
     * Set the player on the spot
     *
     * @param player Player who now owns the spot
     */
    public void setPlayer(Player player) {
        _player = player;
    }

    /**
     * Get the resources placed on the spot
     *
     * @return Integer value of resources
     */
    public int getResources() {
        return _resources;
    }

    /**
     * Set the resources this spot has
     *
     * @param resources integer of resources the spot should have
     */
    public void setResources(int resources) {
        _resources = resources;
    }

    /**
     * Get the country the spot belongs to
     *
     * @return Country that contains this spot
     */
    public Country getCountry() {
        return _country;
    }

    /**
     * Get the location of the spot
     *
     * @return Coords value specifying where the spot is
     */
    public Coords getCoords() {
        return _coordinates;
    }

    //--Getters End---------------------------------------------------------------     

    //--Setters Start-------------------------------------------------------------     

    /**
     * Get the X location of the spot
     *
     * @param cartesian determines whether the X value should be cartesian
     * @return Integer of the X value
     */
    public int getX(boolean cartesian) {
        return _coordinates.getX(!cartesian);
    }

    /**
     * Get the Y location of the spot
     *
     * @param cartesian determines whether the Y value should be cartesian
     * @return Integer of the Y value
     */
    public int getY(boolean cartesian) {
        return _coordinates.getY(!cartesian);
    }

    //--Setters End---------------------------------------------------------------     

    //--To String Functions Start-------------------------------------------------

    public void printSpotInfo() {
        System.out.println("---Begin Spot Statistics Print---");
        System.out.println("Belongs to " + this.getPlayer());
        System.out.println("Located in " + this.getCountry());
        System.out.println("Has " + this.getResources() + " resources");
        System.out.println("Has Coordinates of (" + this.getX(false) + this.getY(false) + ")");
    }

    public String simpleString() {
        String result = "";
        if (this._player != null)
            result += Integer.toString(this._player.getID());
        else
            result += "x";
        return result;
    }

    @Override
    public String toString() {
        String result = "Spot: ";
        result += (this._player != null) ? this._player.getName() : "Unowned";
        result += " with ";
        result += this._resources;
        result += " resources";
        return (result);
    }

    //--To String Functions End---------------------------------------------------

    //--Game Functions Start------------------------------------------------------

    /**
     * Add resources to the spot
     *
     * @param resources integer of resources to add
     */
    public void addResources(int resources) {
        this._resources += resources;
    }

    /**
     * Check if a given position is directly attached to this spot
     *
     * @param coords Coords of the position
     * @return boolean whether or not the position is directly attached
     */
    public boolean connectedTo(Coords coords) {
        // TODO(david): fix this. currently assumes the coords are a spot on the board
        for (int i = 0; i < 6; ++i) // hard codes 6 directions
            if (coords.equals(this._coordinates.hexInDir(i)))
                return (true);
        return (false);
    }

    //--Game Functions End--------------------------------------------------------

}
