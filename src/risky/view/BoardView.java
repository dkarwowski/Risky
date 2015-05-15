package risky.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import risky.model.game.Board;
import risky.model.game.Spot;

import java.awt.*;

/**
 * Shows the Board as its own view, for use in multiple places
 * TODO: move things into here from CreateBoardScene
 *
 * Created by davidkarwowski on 5/13/15.
 */
public class BoardView extends Canvas {
    private Board board;
    private double radius; // synonymous with side length

    /**
     * Create a new Board View
     *
     * @param board      board to draw initially
     * @param sideLength the length of a side on the hexagon (radius)
     */
    public BoardView(Board board, double sideLength) {
        super(
                sideLength * 3.0 / 2.0 * board.getDimensions()[0] + 20 + sideLength / 2.0,
                sideLength * Math.sqrt(3.0) * board.getDimensions()[1] + 20 + sideLength * Math.sqrt(3.0) / 2.0
        );

        this.board = board;
        this.radius = sideLength;
    }

    /**
     * Draw the board, including all spots and outlines
     */
    public void drawBoard() {
        GraphicsContext gc = this.getGraphicsContext2D();
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
            }
        }
    }

    /**
     * Draw a spot as a Polygon. Assume that color has been preset
     * TODO: add highlight drawing
     *
     * @param gc graphics context used to draw
     * @param x  the x center of the spot
     * @param y  the y center of the spot
     */
    private void drawSpot(GraphicsContext gc, double x, double y) {
        double[] xPoints = new double[6];
        double[] yPoints = new double[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = x + this.radius * Math.sin(i * Math.PI / 3.0 + Math.PI / 2.0);
            yPoints[i] = y + this.radius * Math.cos(i * Math.PI / 3.0 + Math.PI / 2.0);
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
     * Get the center of a hexagon from x and y index
     *
     * @param x index along the width
     * @param y index along the height
     * @return  array with {x, y} coords for the center
     */
    private double[] getCenter(int x, int y) {
        double xCenter = 10 + this.radius + x * this.radius * 3.0 / 2.0 - x * 0.5;
        double yHeight = this.radius * Math.cos(Math.PI / 6.0); // determine half the height of a spot
        double yCenter = 10 + ((x % 2) + 1) * yHeight + y * 2 * yHeight - y * 0.7; // determine center of the spot

        return new double[]{xCenter, yCenter};
    }
}
