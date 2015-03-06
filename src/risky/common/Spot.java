package risky.common;

public class Spot {
    private Player _player;
    private int _resources;
    private Country _country;
    private Coords _coordinates;
    private Spot[] _exits;

//--Constructors Start-------------------------------------------------------
    
    public Spot() {
        _player = null;
        _resources = 0;
        _country = null;
        _coordinates = new Coords(0,0); //Default Coords of 0,0
        _exits = new Spot[6];
    }
    
    public Spot(Country country, Coords coords) {
        this.setPlayer(null);
        this.setResources(0);
        this.setCountry(country);
        this.setCoords(coords);
        _exits = new Spot[6];
    }
    
//--Constructors End---------------------------------------------------------

//--Getters Start------------------------------------------------------------
    
    public Player getPlayer() {
        return _player;
    }
    
    public int getResources() {
        return _resources;
    }
    
    public Country getCountry() {
        return _country;
    }
    
    public Coords getCoords() {
        return _coordinates;
    }
    
    public int getX() {
    	return _coordinates.getX();
    }
    
    public int getY() {
    	return _coordinates.getY();
    }
    
    public Spot[] getExits() {
    	return _exits;
    }
    
//--Getters End---------------------------------------------------------------     

//--Setters Start-------------------------------------------------------------     
    
    public void setPlayer(Player player) {
        _player = player;
    }
    
    public void setResources(int resources) {
        _resources = resources;
    }
    
    public void setCountry(Country country) {
        _country = country;
    }
    
    public void setCoords(Coords coords) {
        _coordinates = coords;
    }
    
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
    	System.out.println("Has Coordinates of (" + this.getX() + this.getY() + ")");
    	System.out.println("Exits:");
    	for(int i = 0; i < 6; i++) {
    		if(this._exits[i] == null)
    			System.out.println("Exit " + i + ": No exit");
    		else
    			System.out.println("Exit " + i + ": " + this._exits[i]);
    	}
    }
    
    
    
//--To String Functions End---------------------------------------------------
    
//--Game Functions Start------------------------------------------------------
    
    public Boolean exitInDirection(int direction) {
    	if(_exits[direction] != null)
    		return true;
    	else
    		return false;
    }

    
}