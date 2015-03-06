package risky.common;

public class Board {

	private String name = "Untitled Board";
	private int continents = 6; // default number of continents is 6
	private int countries = 42; // default number of countries/territories is 42
	private int width;
	private int height;
	
	public Board(String setName, int setWidth, int setHeight, int setContinents, int setCountries){
		this.name = setName;
		this.width = setWidth;
		this.height = setHeight;
		this.continents = setContinents;
		this.countries = setCountries;
	}

//--Getters Start----------------------------------------------------------

	public String getName(){
		return this.name;
	}
	
	public int getCountries(){
		return this.countries;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	public int getContinents(){
		return this.continents;
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

//--Game Related Functions End------------------------------------------------------------	 

}
