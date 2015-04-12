package risky.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import risky.common.Board;
import risky.common.Coords;
import risky.common.Spot;
import risky.common.Player;

public class BoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final int BOARD_SETUP = 0;
    public static final int BOARD_PLAY_GAIN = 1;
    public static final int BOARD_PLAY_PUTS = 2;
    public static final int BOARD_PLAY_ATTK = 3;

    private Board board;
    private Player currPlayer;
    private Coords selected;
    private Coords source;

    public BoardPanel(MouseListener listener, MouseMotionListener listener2, Board setBoard) {
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener2);
        this.setBackground(Color.WHITE);
        this.board = setBoard;
        
        this.setSize(this.getPanelDimension());

        this.currPlayer = null;
        this.selected = null;
        this.source = null;
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
                Polygon p = createPolygon(x, y);
                
                // color of the square
                // TODO(david): make squares highlight per player properly
                // TODO(david): highlight squares based on player's colors
                if (spot == null)
                    g.setColor(new Color(0.3f, 0.3f, 0.9f));
                else {
                    if (spot.getPlayer() == null)
                        g.setColor(new Color(0.1f, 0.6f, 0.2f));
                    else {
                        float mod = spot.getPlayer().getID() * 0.2f;
                        mod = 1.0f - mod;
                        g.setColor(new Color(mod * 0.9f, mod * mod * 0.9f, mod * 0.9f));
                    }
                    /*
                    else if (spot.getPlayer() == this.currPlayer)
                        g.setColor(new Color(0.1f, 0.9f, 0.9f));
                    else
                        g.setColor(new Color(0.6f, 0.1f, 0.2f));
                    */
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
            Polygon p = createPolygon(this.selected.getXCart(), this.selected.getYCart());
            g.drawPolygon(p);
        }
        if (this.board.containsSpot(this.source)) {
            g.setColor(Color.MAGENTA);
            Polygon p = createPolygon(this.source.getXCart(), this.source.getYCart());
            g.drawPolygon(p);
        }
    }

    // TODO(david): clean this out with proper board functionality?
    public void boardUpdate(Board board, Player player) {
        this.board = board;
        this.currPlayer = player;
        this.selected = null;
        this.source = null;
    }

    // TODO(david): move this properly?
    public Polygon createPolygon(int x, int y) {
        Polygon result = new Polygon();
        int sx = 9;
        int sy = (x % 2 == 0) ? 25 : 41;

        result.addPoint(sx + 0  + 28 * x, sy + 0  + 32 * y);
        result.addPoint(sx + 9  + 28 * x, sy - 16 + 32 * y);
        result.addPoint(sx + 28 + 28 * x, sy - 16 + 32 * y);
        result.addPoint(sx + 37 + 28 * x, sy + 0  + 32 * y);
        result.addPoint(sx + 28 + 28 * x, sy + 16 + 32 * y);
        result.addPoint(sx + 9  + 28 * x, sy + 16 + 32 * y);

        return (result);
    }

    public boolean boardUpdate(Coords playerSelect) {
        boolean result = false;
        if (this.board.containsSpot(playerSelect) || playerSelect == null) {
            if (this.source == null)
                result = true;
            else
                this.source = this.selected;
            this.selected = playerSelect;
        }
        this.repaint();

        return (result);
    }

    public void select(Coords select, int type) {
        if (type == BoardPanel.BOARD_SETUP || type == BoardPanel.BOARD_PLAY_PUTS) {
            if (this.board.isSetupDone()) {
                if (this.currPlayer.equals(this.board.getSpot(select).getPlayer()))
                    this.selected = select;
            }
            else {
                if (this.board.spotFree(select))
                    this.selected = select;
            }
        }
        else if (type == BoardPanel.BOARD_PLAY_ATTK) {
            if (this.source == null 
                    && this.currPlayer.equals(this.board.getSpot(select).getPlayer()))
                this.source = select;
            else if (this.board.getSpot(select).connectedTo(this.source))
                this.selected = select;
        }

        this.repaint();
    }

    public boolean isSelected(int type) {
        if (type == BoardPanel.BOARD_SETUP || type == BoardPanel.BOARD_PLAY_PUTS)
            return (this.selected != null);
        else if (type == BoardPanel.BOARD_PLAY_ATTK)
            return (this.selected != null && this.source != null);
        else if (type == BoardPanel.BOARD_PLAY_GAIN)
            return (true);
        return (false);
    }

    public Coords getSelected() {
        return (this.selected);
    }

    public Coords getSource() {
        return (this.source);
    }

    public Coords coordsFromPosition(int x, int y) {
        int squareX = (int)((x - 13) / 28);
        int squareY = (int)((y - 9 - ((squareX % 2 == 0) ? 0 : 16)) / 32);
        return (new Coords(squareX, squareY, false));
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
