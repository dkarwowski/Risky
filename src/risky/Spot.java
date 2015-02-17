package risky;

public class Spot 
{
	private Player _player;
	private int _resources;
	private String _country;
	private int[] _coordinates;
	
	//Constructors
	public Spot()
	{
		_player = null;
		_resources = 0;
		_country = null;
		_coordinates = new int[2];
	}
	public Spot(String country, int[] coords)
	{
		this.setPlayer(null);
		this.setResources(0);
		this.setCountry(country);
		this.setCoordinates(coords);
	}
	
	//Getters
	public Player getPlayer() 
	{
		return _player;
	}
	public int getResources()
	{
		return _resources;
	}
	public String getCountry()
	{
		return _country;
	}
	public int[] getCoordinateArray()
	{
		return _coordinates;
	}
	public int getX()
	{
		return _coordinates[0];
	}
	public int getY()
	{
		return _coordinates[1];
	}
	
	//Setters
	public void setPlayer(Player player)
	{
		_player = player;
	}
	public void setResources(int resources)
	{
		_resources = resources;
	}
	public void setCountry(String country)
	{
		_country = country;
	}
	public void setCoordinates(int[] coords)
	{
		_coordinates = coords;
	}
	
	//More methods
	
	
}
