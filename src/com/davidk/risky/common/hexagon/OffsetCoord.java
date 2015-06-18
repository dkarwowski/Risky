package com.davidk.risky.common.hexagon;

/**
 * The offset row, column coordinate system
 *
 * Created by David Karwowski on 5/25/2015.
 */
public class OffsetCoord {
    public static final int EVEN = 1;
    public static final int ODD = -1;

    private final int col;
    private final int row;

    /**
     * Create a new offset coordinate from the column and row
     *
     * @param col column of the new coordinate
     * @param row row of the new coordinate
     */
    public OffsetCoord(int col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * Get the column
     *
     * @return integer column value
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Get the row
     *
     * @return integer row value
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Use the R's offset, get the offset from cubic coordinates
     *
     * @param h the hexagon
     * @return the offset coordinates
     */
    public static OffsetCoord rOffsetFromCube(Hex h) {
        int col = h.getQ() + (int)((h.getR() + EVEN * (h.getR() & 1)) / 2.0);
        int row = h.getR();
        return new OffsetCoord(col, row);
    }

    /**
     * Use the R's offset, get the cubic coordinates
     *
     * @param row row
     * @param col col
     * @return the new hexagon
     */
    public static Hex rOffsetToCube(int row, int col) {
        int q = col - (int)((row + EVEN * (row & 1)) / 2.0);
        return new Hex(q, row);
    }
}
