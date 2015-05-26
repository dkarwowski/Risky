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
     * Use the Q's offset, get the offset from the cubic coordinates
     *
     * @param offset the even or odd offset
     * @param h      the hexagon
     * @return the offset coordinates
     */
    public static OffsetCoord qOffsetFromCube(int offset, Hex h) {
        int col = h.getQ();
        int row = h.getR() + (int)((h.getQ() + offset * (h.getQ() & 1)) / 2.0);
        return new OffsetCoord(col, row);
    }

    /**
     * Use the Q's offset, get the cubic coordinates
     *
     * @param offset the offset to convert
     * @return the new hexagon
     */
    public Hex qOffsetToCube(int offset) {
        int q = this.getCol();
        int r = this.getRow() - (int)((this.getCol() + offset * (this.getCol() & 1)) / 2.0);
        return new Hex(q, r);
    }

    /**
     * Use the R's offset, get the offset from cubic coordinates
     *
     * @param offset the even or odd offset
     * @param h      the hexagon
     * @return the offset coordinates
     */
    public static OffsetCoord rOffsetFromCube(int offset, Hex h) {
        int col = h.getQ() + (int)((h.getR() + offset * (h.getR() & 1)) / 2.0);
        int row = h.getR();
        return new OffsetCoord(col, row);
    }

    /**
     * Use the R's offset, get the cubic coordinates
     *
     * @param offset the even or odd offset
     * @return the new hexagon
     */
    public Hex rOffsetToCube(int offset) {
        int q = this.getCol() - (int)((this.getRow() + offset * (this.getRow() & 1)) / 2.0);
        int r = this.getRow();
        return new Hex(q, r);
    }
}
