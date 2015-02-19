package risky.common;

/**
 * The coordinates for hexagonal grid, allows proper moving around
 * Using axial movement between 
 *        -y
 *         0
 *       _____
 * -x 5 /     \ 1
 *     /       \ 
 *     \       /
 *    4 \_____/ 2 +x
 *         3
 *        +y
 */
public class Coords {
	// these coordinates are in axial
	private int x;
	private int y;

	/**
	 * Take coordinates in axial by default
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Coords(int x, int y) {
		this(x, y, true);
	}

	/**
	 * Initialize the coordinates with either axial or cartesian coordinates
	 * @param x the x value
	 * @param y the y value
	 * @param isAxial are the coordinates in the the proper format
	 */
	public Coords(int x, int y, boolean isAxial) {
		if (isAxial) {
			this.x = x;
			this.y = y;
		} else {
			this.x = x;
			this.y = y - x/2;
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other)
			return(true);
		
		if(!(other instanceof Coords))
			return(false);
		
		Coords o = (Coords) other;
		return(this.getX(true) == o.getX(true) &&
				this.getY(true) == o.getY(true));
	}
	
	@Override
	public String toString() {
		return("Coords: (" + this.x + ", " + this.y + ")");
	}
	
//--Getters Start-------------------------------------------------------------

	/**
	 * Get the x value (doesn't change regardless)
	 * @param isAxial useless variable
	 * @return the x value
	 */
	public int getX(boolean isAxial) {
		return(this.x);
	}
	
	/**
	 * Get the axial X
	 * @return the x value
	 */
	public int getX() {
		return(this.getX(true));
	}

	/**
	 * Get the y value (changes depending on which x value we're in
	 * @param isAxial whether we're using the proper axial format
	 * @return the necessary format of the y variable
	 */
	public int getY(boolean isAxial) {
		if (isAxial)
			return(this.y);
		return(this.y + x/2);
	}
	
	/**
	 * Get the Axial Y coordinate
	 * @return axial version of the y coordinate
	 */
	public int getY() {
		return(this.getY(true));
	}
	
//--Getters End---------------------------------------------------------------
//--Game Related Functions Start----------------------------------------------
	
	/**
	 * Get a hex one space in the specified direction
	 * @param dir number between 0..5 inclusive for the direction, 0 being top going clockwise
	 * @return the Coordinates of the hex
	 */
	public Coords hexInDir(int dir) {
		return(hexInDir(dir, 1));
	}
	
	/**
	 * Get a hex a variable distance in a straight line through the direction
	 * @param dir number between 0..5 inclusive for the direction, 0 being top going clockwise
	 * @param dist variable distance to go
	 * @return the Coordinates of the hex
	 */
	public Coords hexInDir(int dir, int dist) {
		return(new Coords(xInDir(this.x, dir, dist), yInDir(this.y, dir, dist)));
	}
	
//--Game Related Functions End------------------------------------------------
//--Static Functions Start----------------------------------------------------

	public static int xInDir(int x, int dir) {
		return(xInDir(x, dir, 1));
	}
	
	public static int xInDir(int x, int dir, int dist) {
		switch(dir) {
		case 1:
		case 2:
			return(x + dist);
		case 4:
		case 5:
			return(x - dist);
		default:
			return(x);
		}
	}

	public static int yInDir(int y, int dir) {
		return(yInDir(y, dir, 1));
	}

	public static int yInDir(int y, int dir, int dist) {
		switch(dir) {
		case 0:
		case 1:
			return(y - dist);
		case 3:
		case 4:
			return(y + dist);
		default:
			return(y);
		}
	}

//--Static Functions End------------------------------------------------------
}
