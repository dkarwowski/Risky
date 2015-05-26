package com.davidk.risky.common.hexagon;

/**
 * The orientation built off of the types of polygons
 *
 * Created by David Karwowski on 5/25/2015.
 */
public class Orientation {
    private final static Orientation pointy = new Orientation(
            Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0,
            Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0,
            0.5
    );
    private final static Orientation flat = new Orientation(
            3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0),
            2.0 / 3.0, 0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0,
            0.0
    );

    private final double f0, f1, f2, f3;
    private final double b0, b1, b2, b3;
    private final double angle;

    public Orientation(double f0, double f1, double f2, double f3,
                       double b0, double b1, double b2, double b3, double angle) {
        this.f0 = f0;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;

        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;

        this.angle = angle;
    }

    public double getF0() {
        return f0;
    }

    public double getF1() {
        return f1;
    }

    public double getF2() {
        return f2;
    }

    public double getF3() {
        return f3;
    }

    public double getB0() {
        return b0;
    }

    public double getB1() {
        return b1;
    }

    public double getB2() {
        return b2;
    }

    public double getB3() {
        return b3;
    }

    public double getAngle() {
        return angle;
    }
}
