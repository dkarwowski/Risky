package risky.model.game;

import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Coordinates class for dealing with locations on the board
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class Coords {
    public enum Dir {
        UP_RIGHT, UP_LEFT, UP, DOWN_RIGHT, DOWN, DOWN_LEFT
    }

    private final ReadOnlyIntegerWrapper x;
    private final ReadOnlyIntegerWrapper y;

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
     * Get the coordinates in a direction
     *
     * @param dir the direction
     * @return    Coordinates to move to
     */
    public Coords coordsInDir(Dir dir) {
        switch(dir) {
            case UP_RIGHT:
                return new Coords(this.getX() + 1, this.getY() - (this.getX() % 2 - 1));
            case UP:
                return new Coords(this.getX(), this.getY() - 1);
            case UP_LEFT:
                return new Coords(this.getX() - 1, this.getY() - (this.getX() % 2 - 1));
            case DOWN_RIGHT:
                return new Coords(this.getX() + 1, this.getY() + (this.getX() % 2));
            case DOWN:
                return new Coords(this.getX(), this.getY() + 1);
            case DOWN_LEFT:
                return new Coords(this.getX() - 1, this.getY() + (this.getX() % 2));
        }

        return null;
    }

    /**
     * Get the opposite direction
     *
     * @param dir direction original
     * @return    new direction
     */
    public static Dir oppositeDir(Dir dir) {
        switch(dir) {
            case UP_RIGHT:
                return Dir.DOWN_LEFT;
            case UP_LEFT:
                return Dir.DOWN_RIGHT;
            case UP:
                return Dir.DOWN;
            case DOWN_RIGHT:
                return Dir.UP_LEFT;
            case DOWN:
                return Dir.UP;
            case DOWN_LEFT:
                return Dir.UP_RIGHT;
        }

        return null;
    }

    /**
     * Get the integer index in the one dimensional array of spots
     *
     * @param width width of the array
     * @return index value, null if out of bounds
     */
    public int toIndex(int width, int height) {
        int result = this.getX() + this.getY() * width;
        return (result < 0 || result >= width * height) ? -1 : result;
    }
}
