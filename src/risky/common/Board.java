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
	
	private void setCountries() {
		ArrayList<Country> toSet = new ArrayList<Country>();
		for (Spot spot : this.spots) {
			boolean shouldSkip = false;
			for (Country country : toSet) {
				if (country.equals(spot.getCountry())) {
					shouldSkip = true;
					break;
				}
			}
			
			if (shouldSkip) continue;
			toSet.add(spot.getCountry());
		}
		
		this.countries = new Country[toSet.size()];
		for (int i = 0; i < toSet.size(); ++i) {
			this.countries[i] = toSet.get(i);
		}
	}

//--Game Related Functions End------------------------------------------------------------	 

}
