package com.davidk.risky.common.hexagon;

import javafx.geometry.Point2D;

/**
 * Created by David Karwowski on 5/25/2015.
 */
public class Layout {
    private final Orientation orientation;
    private final Point2D size;
    private final Point2D origin;

    public Layout(Orientation orientation, Point2D size, Point2D origin) {
        this.orientation = orientation;
        this.size = size;
        this.origin = origin;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point2D getSize() {
        return size;
    }

    public Point2D getOrigin() {
        return origin;
    }

    public Point2D hexToPixel(Hex h) {
        Orientation o = this.orientation;
        double x = (o.getF0() * h.getQ() + o.getF1() * h.getR()) * this.size.getX();
        double y = (o.getF2() * h.getQ() + o.getF3() * h.getR()) * this.size.getY();

        return this.origin.add(x, y);
    }

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

    public Point2D hexCornerOffset(int corner) {
        double angle = 2.0 * Math.PI * (corner + this.orientation.getAngle()) / 6.0;
        return new Point2D(
                this.size.getX() * Math.cos(angle),
                this.size.getY() * Math.sin(angle)
        );
    }

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
