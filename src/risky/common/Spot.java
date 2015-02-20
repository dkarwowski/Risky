package risky.common;

public class Spot {
    private Player _player;
    private int _resources;
    private Country _country;
    private Coords _coordinates;

//--Constructors Start-------------------------------------------------------
    
    public Spot() {
        _player = null;
        _resources = 0;
        _country = null;
        _coordinates = new Coords(0,0); //Default Coords of 0,0
    }
    
    public Spot(Country country, Coords coords) {
        this.setPlayer(null);
        this.setResources(0);
        this.setCountry(country);
        this.setCoords(coords);
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

//--Setters End---------------------------------------------------------------     

}