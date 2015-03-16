package risky.common;

import java.util.ArrayList;

public class Board {
	private String name;
	private int width;
	private int height;
	
	private Spot[] spots;
	private Country[] countries;
	
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
		
		this.spots = new Spot[this.width * this.height];
		if (setSpots != null) {
			for (int x = 0; x < this.width; x++) {
				for (int y = 0; y < this.height; y++) {
					this.spots[x + y * this.width] = setSpots[x + y * this.width];
                }
			}
        }

		this.countries = null;
		this.setCountries();
	}
	
	public void setSpot(Spot spot) {
		Coords coords = spot.getCoords();
		int x = coords.getX(false);
		int y = coords.getY(false);
		this.spots[x + y * this.width] = spot;

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
		return getSpot(c.getX(false), c.getY(false));
	}
	
	public Spot getSpot(int x, int y) {
		if (contains(x, y))
			return this.spots[x + y * this.width];
		return null;
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
		return this.getCountry(c.getX(), c.getY());
	}

//--Getters End------------------------------------------------------------

//--Game Related Functions Start------------------------------------------------------------

	public boolean contains(int x, int y) {
		return (x >= 0) && (y >= 0) && (x < width) && (y < height);
	}

	public boolean contains(Coords c) {
		if (c == null) {
			return false;
		}
		return contains(c.getX(), c.getY());
	}
	
	public Player playerOwnsAll() {
		boolean singlePlayerOwnsAll = true;
		for (Country country : countries) {
			singlePlayerOwnsAll &= country.doesPlayerOwnAllSpots();
		}
		
		if (singlePlayerOwnsAll)
			return (countries[0].getOwner());
		return (null);
	}
	
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
	
	public void claimSpot(Player player, int x, int y, int resources) {
		Coords coords = new Coords(x, y);
		if (this.contains(coords)) {
			this.getSpot(coords).setPlayer(player);
			this.getSpot(coords).setResources(resources);
		}
		
	}
	
	public boolean spotFree(int x, int y) {
		Coords coords = new Coords(x, y);
		if (this.contains(coords))
			return (this.getSpot(coords).getPlayer() == null);
		return false;
	}
	
	public void claimCountries() {
		for (Country c : this.countries)
			c.claimCountry();
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
