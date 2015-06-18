package com.davidk.risky.view;

import com.davidk.risky.common.hexagon.FractionalHex;
import com.davidk.risky.common.hexagon.Layout;
import com.davidk.risky.common.hexagon.OffsetCoord;
import com.davidk.risky.common.hexagon.Orientation;
import com.davidk.risky.model.game.Board;
import com.davidk.risky.model.game.Spot;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;

/**
 * Creates the view of the board using the hexagon classes
 *
 * Created by davidkarwowski on 6/17/15.
 */
public class BoardView extends Canvas {
    private Board board;
    private Layout layout;

    /**
     * Create a new Board View
     *
     * @param board      board to draw initially
     * @param sideLength the length of a side on the hexagon (radius)
     */
    public BoardView(Board board, int sideLength) {
        super(500, 500);

        this.board = board;
        this.layout = new Layout(
                Orientation.pointy,
                new Point2D(sideLength, sideLength),
                new Point2D(2 * sideLength + 20, sideLength + 20)
        );

        this.setWidth(40 + sideLength +
                this.layout.hexToPixel(
                        OffsetCoord.rOffsetToCube(this.board.getHeight() - 1, this.board.getWidth() - 1)
                ).getX()
        );
        this.setHeight(40 + sideLength +
                this.layout.hexToPixel(
                        OffsetCoord.rOffsetToCube(this.board.getHeight() - 1, this.board.getWidth() - 1)
                ).getY()
        );
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
     * Draw the board, including all spots and outlines
     */
    public void drawBoard() {
        GraphicsContext gc = this.getGraphicsContext2D();
        this.reset(gc);
        this.outlineBoard(gc);

        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                Spot spot = this.board.getSpot(x, y);
                Color color;
                Point2D center = this.layout.hexToPixel(OffsetCoord.rOffsetToCube(y, x));

                if (spot == null)
                    color = Color.color(0.2f, 0.2f, 0.7f, 1.0f);
                else
                    color = Color.color(0.2f, 0.7f, 0.2f, 1.0f);

                gc.setFill(color);
                this.drawSpot(gc, center);
                this.drawHighlight(gc, center, color);
                this.drawShadow(gc, center, color);

                if (spot == null)
                    continue;

                for (int i = 0; i < 6; i++) {
                    if (spot.getExits()[i] == null)
                        continue;

                    this.drawConnection(gc, center, i);
                }
            }
        }
    }

    /**
     * Get a hex at a canvas coordinate
     *
     * @param x canvas x
     * @param y canvas y
     * @return  pair of indexes {x, y}
     */
    public int[] getHex(double x, double y) {
        FractionalHex fHex = this.layout.pixelToHex(new Point2D(x, y));
        OffsetCoord offset = OffsetCoord.rOffsetFromCube(fHex.round());
        if (offset.getCol() < 0 || offset.getCol() >= this.board.getWidth()
                || offset.getRow() < 0 || offset.getRow() >= this.board.getHeight())
            return null;
        return new int[]{offset.getCol(), offset.getRow()};
    }

    /**
     * Outline the board completely with drop shadow
     *
     * @param gc graphics context
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
                if (x != 0 && y != 0 && y != this.board.getHeight() - 1 && x != this.board.getWidth() - 1)
                    continue;

                this.dropShadow(gc, this.layout.hexToPixel(OffsetCoord.rOffsetToCube(y, x)));
            }
        }
    }

    /**
     * Create a drop shadow for the board spot
     *
     * @param gc graphics context
     * @param center the center of the point for the drop shadow
     */
    private void dropShadow(GraphicsContext gc, Point2D center) {
        gc.setFill(new Color(0.1f, 0.1f, 0.1f, 1.0f));
        gc.setEffect(new GaussianBlur(20));

        this.drawSpot(gc, center);

        gc.setEffect(null);
    }

    /**
     * Draw a spot as a Polygon. Assume that color has been preset
     *
     * @param gc     graphics context used to draw
     * @param center center position
     */
    private void drawSpot(GraphicsContext gc, Point2D center) {
        drawSpot(gc, center, 0);
    }

    /**
     * Draw a spot as a Polygon. Assume color has been preset
     *
     * @param gc     graphics context
     * @param center center position
     * @param offset the offset for drawing larger/smaller spots
     */
    private void drawSpot(GraphicsContext gc, Point2D center, double offset) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = center.add(this.layout.hexCornerOffset(i, offset)).getX();
            yPoints[i] = center.add(this.layout.hexCornerOffset(i, offset)).getY();
        }

        gc.fillPolygon(xPoints, yPoints, 6);
    }

    /**
     * Draw the highlight on the spot
     *
     * @param gc     the graphics context for drawing
     * @param orig   the color of the spot
     * @param center center position
     */
    private void drawHighlight(GraphicsContext gc, Point2D center, Color orig) {
        gc.setStroke(orig.brighter());
        gc.setLineWidth(2.0);
        this.drawLines(gc, center, new int[]{2, 3}, -1.5);
        gc.setLineWidth(1.0);
        this.drawLine(gc, center, 4, -1.5);
    }

    /**
     * Draw the shadow on the spot
     *
     * @param gc     graphics context for drawing
     * @param orig   the color on the spot
     * @param center center position
     */
    private void drawShadow(GraphicsContext gc, Point2D center, Color orig) {
        gc.setStroke(orig.darker());
        gc.setLineWidth(2.0);
        this.drawLines(gc, center, new int[]{5, 0}, -1.5);
        gc.setLineWidth(1.0);
        this.drawLine(gc, center, 1, -1.5);
    }

    /**
     * Draw multiple lines on the canvas, relative to a hexagon
     *
     * @param gc     graphics context for drawing
     * @param center center position
     * @param sides  the sides to be drawn - see spot class
     * @param offset +/- offset for the radius, determining out or in, respectively
     */
    private void drawLines(GraphicsContext gc, Point2D center, int[] sides, double offset) {
        for (int side : sides)
            this.drawLine(gc, center, side, offset);
    }

    /**
     * Draw a single line on the canvas, relative to a hexagon
     *
     * @param gc     graphics context for drawing
     * @param center center position
     * @param side   the side to be drawn - see spot class
     * @param offset +/- offset for the radius, determining out or in, respectively
     */
    private void drawLine(GraphicsContext gc, Point2D center, int side, double offset) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];
        for (int i = 0; i < 2; i++) {
            xPoints[i] = center.add(this.layout.hexCornerOffset(side + i, offset)).getX();
            yPoints[i] = center.add(this.layout.hexCornerOffset(side + i, offset)).getY();
        }

        gc.strokePolyline(xPoints, yPoints, 2);
    }

    /**
     * Draw a connection to an exit directly adjacent
     * TODO: have it dependent on final spot
     *
     * @param gc     graphics context
     * @param center center position
     * @param side   the side to go out
     */
    private void drawConnection(GraphicsContext gc, Point2D center, int side) {

    }
}
