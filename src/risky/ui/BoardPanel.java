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
                
                int initialX = 9;
                int initialY = (x % 2 == 0) ? 25 : 41;

                p.addPoint( initialX + 0  + 28 * x, initialY + 0  + 32 * y);
                p.addPoint( initialX + 9  + 28 * x, initialY - 16 + 32 * y);
                p.addPoint( initialX + 28 + 28 * x, initialY - 16 + 32 * y);
                p.addPoint( initialX + 37 + 28 * x, initialY + 0  + 32 * y);
                p.addPoint( initialX + 28 + 28 * x, initialY + 16 + 32 * y);
                p.addPoint( initialX + 9  + 28 * x, initialY + 16 + 32 * y);
                
                // color of the square
                // add selection color here?
                if (spot == null)
                    g.setColor(new Color(0.3f, 0.3f, 0.9f));
                else {
                    if (spot.getPlayer() == null)
                        g.setColor(new Color(0.1f, 0.6f, 0.2f));
                    else
                        g.setColor(new Color(0.2f, 0.7f, 0.3f));
                }
                
                g.fillPolygon(p);
                
                // draw the outline
                g.setColor(Color.BLACK);
                g.drawPolygon(p);
            }
        }
    }

    public void boardUpdate(Board board) {
        this.board = board;
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
