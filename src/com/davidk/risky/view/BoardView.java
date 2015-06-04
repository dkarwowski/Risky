package com.davidk.risky.view;

import com.davidk.risky.common.hexagon.Layout;
import com.davidk.risky.common.hexagon.Orientation;
import com.davidk.risky.model.game.Board;
import com.davidk.risky.model.game.Spot;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;

/**
 * Shows the Board as its own view, for use in multiple places
 *
 * Created by davidkarwowski on 5/13/15.
 */
class BoardView extends Canvas {
    private Board board;
    private Layout layout;
    private final double radius; // synonymous with side length

    /**
     * Create a new Board View
     *
     * @param board      board to draw initially
     * @param sideLength the length of a side on the hexagon (radius)
     */
    public BoardView(Board board, double sideLength) {
        super(
                sideLength * 3.0 / 2.0 * board.getDimensions()[0] + 40 + sideLength / 2.0,
                sideLength * Math.sqrt(3.0) * board.getDimensions()[1] + 40 + sideLength * Math.sqrt(3.0) / 2.0
        );

        this.board = board;
        this.radius = sideLength;

        // create a layout based off of the side length, and the center
        // currently mixing the code a lot, should be reworked?
        this.layout = new Layout(
                Orientation.pointy,
                new Point2D(sideLength, sideLength),
                new Point2D(this.getCenter(0, 0)[0], this.getCenter(0, 0)[1])
        );
    }

    /**
     * Draw the board, including all spots and outlines
     */
    public void drawBoard() {
        GraphicsContext gc = this.getGraphicsContext2D();
        this.reset(gc);
        this.outlineBoard(gc);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Spot spot = board.getSpots()[x + y * board.getWidth()];
                Color color;
                double[] center = this.getCenter(x, y);

                if (spot == null) {
                    color = Color.color(0.2f, 0.2f, 0.7f, 1.0f);
                }
                else {
                    color = Color.color(0.2f, 0.7f, 0.2f, 1.0f);
                }

                gc.setFill(color);
                this.drawSpot(gc, center[0], center[1]);
                this.drawHighlight(gc, color, center[0], center[1]);
                this.drawShadow(gc, color, center[0], center[1]);

                if (spot == null)
                    continue;

                for (int i = 0; i < 6; i++) {
                    if (spot.getExits()[i] == null)
                        continue;

                    this.drawConnection(gc, center[0], center[1], i);
                }
            }
        }
    }

    /**
     * Resets the board to a clear white
     *
     * @param gc the graphics context
     */
    private void reset(GraphicsContext gc) {
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Outline the board completely with drop shadow
     *
     * @param gc graphics context
     */
    private void outlineBoard(GraphicsContext gc) {
        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                if (y != 0 && x != 0 && y != this.board.getHeight() - 1 && x != this.board.getWidth() - 1)
                    continue;

                this.dropShadow(gc, x, y);
            }
        }
    }

    /**
     * Create a drop shadow for the board spot
     *
     * @param gc graphics context
     * @param x  index x
     * @param y  index y
     */
    private void dropShadow(GraphicsContext gc, int x, int y) {
        double[] center = this.getCenter(x, y);
        gc.setFill(Color.color(0.1f, 0.1f, 0.1f, 1.0f));
        gc.setEffect(new GaussianBlur(20));

        this.drawSpot(gc, center[0], center[1], 0);

        gc.setEffect(null);
    }

    /**
     * Draw an updated board
     * TODO: see about properties?
     *
     * @param board the new board to use
     */
    public void drawBoard(Board board) {
        this.board = board;
        this.drawBoard();
    }

    /**
     * Draw a spot as a Polygon. Assume that color has been preset
     *
     * @param gc graphics context used to draw
     * @param x  the x center of the spot
     * @param y  the y center of the spot
     */
    private void drawSpot(GraphicsContext gc, double x, double y) {
        drawSpot(gc, x, y, 0);
    }

    /**
     * Draw a spot as a Polygon. Assume color has been preset
     *
     * @param gc     graphics context
     * @param x      the x center
     * @param y      the y center
     * @param offset the offset for drawing larger/smaller spots
     */
    private void drawSpot(GraphicsContext gc, double x, double y, double offset) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = x + (this.radius + offset) * Math.sin(i * Math.PI / 3.0 + Math.PI / 2.0);
            yPoints[i] = y + (this.radius + offset) * Math.cos(i * Math.PI / 3.0 + Math.PI / 2.0);
        }

        gc.fillPolygon(xPoints, yPoints, 6);
    }

    /**
     * Draw the highlight on the spot
     *
     * @param gc   the graphics context for drawing
     * @param orig the color of the spot
     * @param x    the x center coord
     * @param y    the y center coord
     */
    private void drawHighlight(GraphicsContext gc, Color orig, double x, double y) {
        gc.setStroke(orig.brighter());
        gc.setLineWidth(2.0);
        this.drawLines(gc, x, y, new int[]{1, 2}, -1.5);
        gc.setLineWidth(1.0);
        this.drawLine(gc, x, y, 3, -1.5);
    }

    /**
     * Draw the shadow on the spot
     *
     * @param gc   graphics context for drawing
     * @param orig the color on the spot
     * @param x    the x center coord
     * @param y    the y center coord
     */
    private void drawShadow(GraphicsContext gc, Color orig, double x, double y) {
        gc.setStroke(orig.darker());
        gc.setLineWidth(2.0);
        this.drawLines(gc, x, y, new int[]{4, 5}, -1.5);
        gc.setLineWidth(1.0);
        this.drawLine(gc, x, y, 0, -1.5);
    }

    /**
     * Draw multiple lines on the canvas, relative to a hexagon
     *
     * @param gc     graphics context for drawing
     * @param x      the x center coord
     * @param y      the y center coord
     * @param sides  the sides to be drawn - see spot class
     * @param offset +/- offset for the radius, determining out or in, respectively
     */
    private void drawLines(GraphicsContext gc, double x, double y, int[] sides, double offset) {
        for (int side : sides) {
            this.drawLine(gc, x, y, side, offset);
        }
    }

    /**
     * Draw a single line on the canvas, relative to a hexagon
     *
     * @param gc     graphics context for drawing
     * @param x      the x center coord
     * @param y      the y center coord
     * @param side   the side to be drawn - see spot class
     * @param offset +/- offset for the radius, determining out or in, respectively
     */
    private void drawLine(GraphicsContext gc, double x, double y, int side, double offset) {
        double[] xPoints = new double[2];
        double[] yPoints = new double[2];
        for (int i = 0; i < 2; i++) {
            xPoints[i] = x + (this.radius + offset) * Math.sin((side + i) * Math.PI / 3.0 + Math.PI / 2.0);
            yPoints[i] = y + (this.radius + offset) * Math.cos((side + i) * Math.PI / 3.0 + Math.PI / 2.0);
        }

        gc.strokePolyline(xPoints, yPoints, 2);
    }

    /**
     * Draw a connection to an exit directly adjacent
     * TODO: have it dependent on final spot
     *
     * @param gc   graphics context
     * @param x    center x
     * @param y    center y
     * @param side the side to go out
     */
    private void drawConnection(GraphicsContext gc, double x, double y, int side) {
        double[] xPoints = new double[2];
        double[] yPoints = new double[2];
        for (int i = 0; i < 2; i++) {
            xPoints[i] = x + 3 + (this.radius / 2.0 + this.radius * i)
                    * Math.sin(side * Math.PI / 3.0 + 2.0 * Math.PI / 3.0);
            yPoints[i] = y + 3 + (this.radius / 2.0 + this.radius * i)
                    * Math.cos(side * Math.PI / 3.0 + 2.0 * Math.PI / 3.0);
        }

        gc.setStroke(Color.BLACK);
        gc.strokePolyline(xPoints, yPoints, 2);
    }

    /**
     * Get the center of a hexagon from x and y index
     *
     * @param x index along the width
     * @param y index along the height
     * @return  array with {x, y} coords for the center
     */
    private double[] getCenter(int x, int y) {
        double xCenter = 20 + this.radius + x * this.radius * 3.0 / 2.0 - x * 0.5;
        double yHeight = this.radius * Math.cos(Math.PI / 6.0); // determine half the height of a spot
        double yCenter = 20 + ((x % 2) + 1) * yHeight + y * 2 * yHeight - y * 0.7; // determine center of the spot

        return new double[]{xCenter, yCenter};
    }

    /**
     * Get a hex at a canvas coordinate
     *
     * @param x canvas x
     * @param y canvas y
     * @return  pair of indexes {x, y}
     */
    public int[] getHex(double x, double y) {
        double xRange = this.radius + this.radius / 2.0;
        double xOffset = 20.0 + this.radius / 4.0;
        int xIndex = (int) ((x - xOffset) / xRange);

        double yRange = this.radius * Math.cos(Math.PI / 6.0) * 2.0;
        double yOffset = 20.0 + (xIndex % 2) * yRange / 2.0;
        int yIndex = (int) ((y - yOffset) / yRange);

        return new int[]{xIndex, yIndex};
    }
}
