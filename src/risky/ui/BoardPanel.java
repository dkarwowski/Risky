package risky.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Spot;
import risky.common.Player;

public class BoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Board board;
    private Player currPlayer;
    private Coords selected;

    public BoardPanel(MouseListener listener, Board setBoard) {
        this.addMouseListener(listener);
        this.setBackground(Color.WHITE);
        this.board = setBoard;
        
        this.setSize(this.getPanelDimension());

        this.currPlayer = null;
        this.selected = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // go through each of the spots
        Spot[] spots = this.board.getAllSpots();
        int width = this.board.getWidth();
        int height = this.board.getHeight();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
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
                // TODO(david): make squares highlight per player properly
                // TODO(david): highlight selected square!!!
                if (spot == null)
                    g.setColor(new Color(0.3f, 0.3f, 0.9f));
                else {
                    if (spot.getPlayer() == null)
                        g.setColor(new Color(0.1f, 0.6f, 0.2f));
                    else if (spot.getPlayer() == this.currPlayer)
                        g.setColor(new Color(0.1f, 0.7f, 0.5f));
                    else
                        g.setColor(new Color(0.6f, 0.1f, 0.2f));
                }
                
                g.fillPolygon(p);
                
                // draw the outline
                g.setColor(Color.BLACK);
                g.drawPolygon(p);
            }
        }

        // TODO(david): see about moving polygon drawing elsewhere
        // draw the selected square's outline only if land
        if (this.board.containsSpot(this.selected)) {
            g.setColor(Color.YELLOW);
            Polygon p = new Polygon();
            int x = this.selected.getXCart();
            int y = this.selected.getYCart();
            int sx = 9;
            int sy = (x % 2 == 0) ? 25 : 41;
            p.addPoint(sx + 0  + 28 * x, sy + 0  + 32 * y);
            p.addPoint(sx + 9  + 28 * x, sy - 16 + 32 * y);
            p.addPoint(sx + 28 + 28 * x, sy - 16 + 32 * y);
            p.addPoint(sx + 37 + 28 * x, sy + 0  + 32 * y);
            p.addPoint(sx + 28 + 28 * x, sy + 16 + 32 * y);
            p.addPoint(sx + 9  + 28 * x, sy + 16 + 32 * y);
            g.drawPolygon(p);
        }
    }

    // TODO(david): clean this out with proper board functionality?
    public void update(Board board, Player player) {
        this.board = board;
        this.currPlayer = player;
        this.selected = null;
    }

    public void update(Coords playerSelect) {
        if (this.board.containsSpot(playerSelect))
            this.selected = playerSelect;
        this.repaint();
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
