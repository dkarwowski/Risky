package com.davidk.risky.common.hexagon;

/**
 * Deals with Hexagons in a fractional manner to get the proper hexagon selection
 *
 * Created by David Karwowski on 5/25/2015.
 */
public class FractionalHex {
    private final double q;
    private final double r;
    private final double s;

    /**
     * Construct a new fractional hex with the q, r, s
     *
     * @param q x direction
     * @param r y direction
     * @param s z direction
     */
    public FractionalHex(double q, double r, double s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    /**
     * Round this fractional hex to a regular Hex
     *
     * @return the proper Hex
     */
    public Hex round() {
        int q = (int)(Math.round(this.q));
        int r = (int)(Math.round(this.r));
        int s = (int)(Math.round(this.s));

        double qDiff = Math.abs(q - this.q);
        double rDiff = Math.abs(r - this.r);
        double sDiff = Math.abs(s - this.s);

        if (qDiff > rDiff && qDiff > sDiff)
            q = - r - s;
        else if (rDiff > sDiff)
            r = - q - s;
        else
            s = - q - r;

        return new Hex(q, r, s);
    }
}
