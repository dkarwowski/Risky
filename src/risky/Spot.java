package risky;

public class Spot 
{
	private Player _player;
	private int _resources;
	private String _country;
	
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
}
