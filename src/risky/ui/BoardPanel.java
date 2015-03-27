package risky.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import risky.common.Board;
import risky.common.Spot;

public class BoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Board board;

    public BoardPanel(Board setBoard) {
        this.setBackground(Color.WHITE);
        this.board = setBoard;
        
        this.setSize(this.getPanelDimension());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // go through each of the spots
        Spot[] spots = this.board.getAllSpots();
        int width = this.board.getWidth();
        int height = this.board.getHeight();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                Spot spot = spots[x + y * width];
                Polygon p = new Polygon();
                
                // determine where the hex will be drawn
                if (x % 2 == 0) {
                    p.addPoint(9  + 28 * x, 25 + 32 * y);
                    p.addPoint(18 + 28 * x, 9  + 32 * y);
                    p.addPoint(37 + 28 * x, 9  + 32 * y);
                    p.addPoint(46 + 28 * x, 25 + 32 * y);
                    p.addPoint(37 + 28 * x, 41 + 32 * y);
                    p.addPoint(18 + 28 * x, 41 + 32 * y);
                }
                else {
                    p.addPoint(9  + 28 * x, 41 + 32 * y);
                    p.addPoint(18 + 28 * x, 25 + 32 * y);
                    p.addPoint(37 + 28 * x, 25 + 32 * y);
                    p.addPoint(46 + 28 * x, 41 + 32 * y);
                    p.addPoint(37 + 28 * x, 57 + 32 * y);
                    p.addPoint(18 + 28 * x, 57 + 32 * y);
                }
                
                // color of the square
                // add selection color here?
                if (spot == null)
                    g.setColor(new Color(0.3f, 0.3f, 0.9f));
                else
                    g.setColor(new Color(0.1f, 0.6f, 0.2f));
                
                g.fillPolygon(p);
                
                // draw the outline
                g.setColor(Color.BLACK);
                g.drawPolygon(p);
            }
        }
    }
    
    /**
     * Get the dimensions of the board itself in pixels
     * @return the dimensions
     */
    public Dimension getPanelDimension() {
        Dimension result = new Dimension(
                (this.board.getWidth() + 1)  * 28 + 18,
                (this.board.getHeight() + 2) * 32);
        return (result);
    }
}
