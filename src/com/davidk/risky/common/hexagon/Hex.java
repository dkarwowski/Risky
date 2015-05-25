package com.davidk.risky.common.hexagon;

/**
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

    public Hex(int q, int r, int s) {
        assert q + r + s == 0;

        this.q = q;
        this.r = r;
        this.s = s;
    }

    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = - q - r;
    }

    public int getQ() {
        return this.q;
    }

    public int getR() {
        return this.r;
    }

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

    public Hex add(Hex other) {
        return new Hex(
                this.getQ() + other.getQ(),
                this.getR() + other.getR(),
                this.getS() + other.getS()
        );
    }

    public Hex subtract(Hex other) {
        return new Hex(
                this.getQ() - other.getQ(),
                this.getR() - other.getR(),
                this.getS() - other.getS()
        );
    }

    public Hex multiply(int k) {
        return new Hex(
                this.getQ() * k,
                this.getR() * k,
                this.getS() * k
        );
    }

    public int length() {
        return (int)(
                (Math.abs(this.getQ()) + Math.abs(this.getR()) + Math.abs(this.getS()))
                        / 2.0
        );
    }

    public int distance(Hex other) {
        return this.subtract(other).length();
    }

    public Hex neighbor(int direction) {
        return this.add(Hex.direction(direction));
    }

    public static Hex direction(int direction) {
        assert (0 <= direction && direction < 6);
        return DIRECTIONS[direction];
    }
}
