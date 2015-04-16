package risky.common;

public class Spot {
    private Player _player;
    private int _resources;
    private Country _country;
    private Coords _coordinates;
    private Spot[] _exits;

    //--Constructors Start-------------------------------------------------------

    /**
     * Generic constructor for spots
     */
    public Spot() {
        _player = null;
        _resources = 0;
        _country = null;
        _coordinates = new Coords(0,0); //Default Coords of 0,0
        _exits = new Spot[6];
    }

    /**
     * Construct spots with their Country and position
     * @param country Country to be set
     * @param coords Coords of location
     */
    public Spot(Country country, Coords coords) {
        this.setPlayer(null);
        this.setResources(0);
        this.setCountry(country);
        this.setCoords(coords);
        _exits = new Spot[6];
    }

    //--Constructors End---------------------------------------------------------

    //--Getters Start------------------------------------------------------------

    /**
     * Get the player who owns the spot
     * @return Player instance or null
     */
    public Player getPlayer() {
        return _player;
    }

    /**
     * Get the resources placed on the spot
     * @return Integer value of resources
     */
    public int getResources() {
        return _resources;
    }

    /**
     * Get the country the spot belongs to
     * @return Country that contains this spot
     */
    public Country getCountry() {
        return _country;
    }

    /**
     * Get the location of the spot
     * @return Coords value specifying where the spot is
     */
    public Coords getCoords() {
        return _coordinates;
    }

    /**
     * Get the X location of the spot
     * @param cartesian determines whether the X value should be cartesian
     * @return Integer of the X value
     */
    public int getX(boolean cartesian) {
        return _coordinates.getX(!cartesian);
    }

    /**
     * Get the Y location of the spot
     * @param cartesian determines whether the Y value should be cartesian
     * @return Integer of the Y value
     */
    public int getY(boolean cartesian) {
        return _coordinates.getY(!cartesian);
    }

    /**
     * Get the connected spots that are exits
     * @return Spot array of the 6 exits
     */
    public Spot[] getExits() {
        return _exits;
    }

    /**
     * Get an exit in a specific direction
     * @param direction a 0-5 integer direction relating to spot
     * @return Spot in that direction
     */
    public Spot getExitInDirection(int direction) {
        return _exits[direction];
    }

    //--Getters End---------------------------------------------------------------     

    //--Setters Start-------------------------------------------------------------     

    /**
     * Set the player on the spot
     * @param player Player who now owns the spot
     */
    public void setPlayer(Player player) {
        _player = player;
    }

    /**
     * Set the resources this spot has
     * @param resources integer of resources the spot should have
     */
    public void setResources(int resources) {
        _resources = resources;
    }

    /**
     * Set the country who owns the spot
     * TODO: see if this is used, remove it properly
     * @param country Country that owns it
     */
    public void setCountry(Country country) {
        _country = country;
    }

    /**
     * Set the coordinates of the spot
     * TODO: see if this is used, remove it properly
     * @param coords Coords that determine new location
     */
    public void setCoords(Coords coords) {
        _coordinates = coords;
    }

    /**
     * Set the exits for the spot
     * TODO: see if this is used, remove is properly
     * @param other Spot to set the exit to
     * @param direction 0-5 integer that determines where to set
     */
    public void setExit(Spot other, int direction) {
        //Direction = int 0-5, as shown in Coords.java
        this._exits[direction] = other;
        if(direction < 3)
            other._exits[direction + 3] = this;
        else if(direction < 6)
            other._exits[direction - 3] = this;
        else
            System.out.println("Invalid direction.");
        /* 	
    	if(direction == 0)
    		other._exits[3] = this;
    	else if(direction == 1)
    		other._exits[4] = this;
    	else if(direction == 2)
    		other._exits[5] = this;
    	else if(direction == 3)
    		other._exits[0] = this;
    	else if(direction == 4)
    		other._exits[1] = this;
    	else if(direction == 5)
    		other._exits[2] = this;
    	else
    		System.out.println("Invalid direction.");
         */
    }

    //--Setters End---------------------------------------------------------------     

    //--To String Functions Start-------------------------------------------------

    public void printSpotInfo() {
        System.out.println("---Begin Spot Statistics Print---");
        System.out.println("Belongs to " + this.getPlayer());
        System.out.println("Located in " + this.getCountry());
        System.out.println("Has " + this.getResources() + " resources");
        System.out.println("Has Coordinates of (" + this.getX(false) + this.getY(false) + ")");
        System.out.println("Exits:");
        for(int i = 0; i < 6; i++) {
            if(this._exits[i] == null)
                System.out.println("Exit " + i + ": No exit");
            else
                System.out.println("Exit " + i + ": " + this.getExitInDirection(i));
        }
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
     * check if a spot can exit in a specific direction
     * TODO: check if used anywhere and remove properly
     * @param direction where to try moving
     * @return boolean that determines whether the spot is free
     */
    public boolean exitInDirection(int direction) {
        if(_exits[direction] != null)
            return true;
        else
            return false;
    }

    /**
     * Add resources to the spot
     * @param resources integer of resources to add
     */
    public void addResources(int resources) {
        this._resources += resources;
    }

    /**
     * Check if a given position is directly attached to this spot
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
