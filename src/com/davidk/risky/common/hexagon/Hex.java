package com.davidk.risky.common.hexagon;

/**
 * Create a Hexagon base class for other spots afterwards
 *
 * Created by David Karwowski on 5/25/2015.
 */
public class Hex {
    public static Hex[] DIRECTIONS = new Hex[]{
            new Hex(1, 0, -1), new Hex(1, -1, 0), new Hex(0, -1, 1),
            new Hex(-1, 0, 1), new Hex(-1, 1, 0), new Hex(0, 1, -1)
    };

    private int q;
    private int r;
    private int s;

    /**
     * Create a new Hexagon from cubic coordinates
     *
     * @param q x direction
     * @param r y direction
     * @param s z direction
     */
    public Hex(int q, int r, int s) {
        assert q + r + s == 0;

        this.q = q;
        this.r = r;
        this.s = s;
    }

    /**
     * Create a new Hexagon from axial coordinates
     *
     * @param q x direction
     * @param r y direction
     */
    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = - q - r;
    }

    /**
     * Get the x coordinate
     *
     * @return x coordinate
     */
    public int getQ() {
        return this.q;
    }

    /**
     * Get the y coordinate
     *
     * @return y coordinate
     */
    public int getR() {
        return this.r;
    }

    /**
     * Get the z coordinate
     *
     * @return z coordinate
     */
    public int getS() {
        return this.s;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Hex))
            return false;

        Hex o = (Hex) other;
        return o.getQ() == this.getQ()
                && this.getR() == o.getR()
                && this.getS() == o.getS();
    }

    /**
     * Add a Hex to the current hex in the form of (q+q, r+r, s+s)
     *
     * @param other the amount of movement for hexagon
     * @return new hexagon
     */
    public Hex add(Hex other) {
        return new Hex(
                this.getQ() + other.getQ(),
                this.getR() + other.getR(),
                this.getS() + other.getS()
        );
    }

    /**
     * Subtract a Hex from the current hex in the form of (q-q, r-r, s-s)
     *
     * @param other the amount of movement back from the hexagon
     * @return new hexagon
     */
    public Hex subtract(Hex other) {
        return new Hex(
                this.getQ() - other.getQ(),
                this.getR() - other.getR(),
                this.getS() - other.getS()
        );
    }

    /**
     * Multiply a constant into the hexagon
     *
     * @param k constant to multiply by
     * @return new hexagon
     */
    public Hex multiply(int k) {
        return new Hex(
                this.getQ() * k,
                this.getR() * k,
                this.getS() * k
        );
    }

    /**
     * How far the hexagon goes out
     *
     * @return integer length of the hexagon
     */
    public int length() {
        return (int)(
                (Math.abs(this.getQ()) + Math.abs(this.getR()) + Math.abs(this.getS()))
                        / 2.0
        );
    }

    /**
     * Get the distance between hexagons
     *
     * @param other the hexagon comparing to
     * @return integer distance between hexagons
     */
    public int distance(Hex other) {
        return this.subtract(other).length();
    }

    /**
     * Get the neighboring hexagon
     *
     * @param direction direction to go in
     * @return the hexagon in that direction
     */
    public Hex neighbor(int direction) {
        return this.add(Hex.direction(direction));
    }

    /**
     * Get the directional hex
     *
     * @param direction direction to go in
     * @return the hexagon that moves in that direction
     */
    public static Hex direction(int direction) {
        return DIRECTIONS[(6 + (direction % 6)) % 6];
    }
}
