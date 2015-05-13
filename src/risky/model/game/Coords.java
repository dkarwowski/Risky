package risky.model.game;

import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Coordinates class for dealing with locations on the board
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class Coords {
    private ReadOnlyIntegerWrapper x;
    private ReadOnlyIntegerWrapper y;

    /**
     * Create a new coordinate
     *
     * @param x integer x coordinate
     * @param y integer y coordinate
     */
    public Coords(int x, int y) {
        this.x = new ReadOnlyIntegerWrapper(x);
        this.y = new ReadOnlyIntegerWrapper(y);
    }

    /**
     * Get the X value of the coordinate
     *
     * @return integer value of the coordinate
     */
    public int getX() {
        return this.x.get();
    }

    /**
     * Get the Y Value of the coordinate
     *
     * @return integer value of the coordinate
     */
    public int getY() {
        return this.y.get();
    }

    /**
     * Get the integer index in the one dimensional array of spots
     *
     * @param width width of the array
     * @return index value
     */
    public int toIndex(int width) {
        return this.getX() + this.getY() * width;
    }
}
