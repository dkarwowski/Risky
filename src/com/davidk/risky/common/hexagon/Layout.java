package com.davidk.risky.common.hexagon;

import javafx.geometry.Point2D;

/**
 * Create the proper layouts and deal with the hexagon system
 *
 * Created by David Karwowski on 5/25/2015.
 */
public class Layout {
    private final Orientation orientation;
    private final Point2D size;
    private final Point2D origin;

    /**
     * Construct a new layout with an orientation, size for each hexagon, and origin point
     *
     * @param orientation generally either pointy or flat orientation
     * @param size        size in x, y directions for squeezing
     * @param origin      the (0, 0) center point
     */
    public Layout(Orientation orientation, Point2D size, Point2D origin) {
        this.orientation = orientation;
        this.size = size;
        this.origin = origin;
    }

    /**
     * Get the orientation in this layout
     *
     * @return relevant orientation
     */
    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * Get the size of the hexagons being used
     *
     * @return relevant size
     */
    public Point2D getSize() {
        return this.size;
    }

    /**
     * Get the origin point based on pixels
     *
     * @return relevant origin
     */
    public Point2D getOrigin() {
        return this.origin;
    }

    /**
     * Convert a hexagon into a pixel center
     *
     * @param h the hexagon being used
     * @return the point at the center
     */
    public Point2D hexToPixel(Hex h) {
        Orientation o = this.orientation;
        double x = (o.getF0() * h.getQ() + o.getF1() * h.getR()) * this.size.getX();
        double y = (o.getF2() * h.getQ() + o.getF3() * h.getR()) * this.size.getY();

        return this.origin.add(x, y);
    }

    /**
     * Turn a pixel into a hexagon
     *
     * @param p point that was grabbed from the screen
     * @return fractional hexagon that contains the point
     */
    public FractionalHex pixelToHex(Point2D p) {
        Orientation o = this.orientation;
        Point2D point = new Point2D(
                (p.getX() - this.origin.getX()) / this.size.getX(),
                (p.getY() - this.origin.getY()) / this.size.getY()
        );

        double q = o.getB0() * point.getX() + o.getB1() * point.getY();
        double r = o.getB2() * point.getX() + o.getB3() * point.getY();

        return new FractionalHex(q, r, - q - r);
    }

    /**
     * Get a specific corner of a hexagon
     * TODO: see if necessary
     *
     * @param corner the corner to look at
     * @return point of the corner
     */
    public Point2D hexCornerOffset(int corner) {
        double angle = 2.0 * Math.PI * (corner + this.orientation.getAngle()) / 6.0;
        return new Point2D(
                this.size.getX() * Math.cos(angle),
                this.size.getY() * Math.sin(angle)
        );
    }

    /**
     * Get the collection of points for the corners
     *
     * @param h hexagon to create the points for
     * @return array of x, y points in the form of {{x1, x2, ...}, {y1, y2, ...}}
     */
    public double[][] polygonCorners(Hex h) {
        Point2D center = this.hexToPixel(h);

        double[][] result = new double[2][6];
        for (int i = 0; i < 6; i++) {
            Point2D offset = this.hexCornerOffset(i);
            Point2D corner = offset.add(center);

            result[0][i] = corner.getX();
            result[1][i] = corner.getY();
        }

        return result;
    }
}
