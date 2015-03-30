package risky.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import risky.Risky;
import risky.common.Board;
import risky.common.Coords;
import risky.common.Player;
import risky.common.Spot;

public class RiskyGUI extends JFrame implements MouseListener, ActionListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    private Risky game;
    
    private BoardPanel boardPanel;
    private InfoPanel infoPanel;

    public RiskyGUI(Risky risky) {
        super("Risky"); // initialize with name
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Risky");

        this.game = risky;

        // TODO(david): have board selection here?
        Board gameBoard = this.game.getBoard();
        this.boardPanel = new BoardPanel(this, this, gameBoard);
        this.add(this.boardPanel, BorderLayout.CENTER);

        this.infoPanel = new InfoPanel(this);
        this.add(this.infoPanel, BorderLayout.SOUTH);

        this.setSize(
                this.boardPanel.getPanelDimension().width,
                this.boardPanel.getPanelDimension().height + this.infoPanel.getHeight() + 9);
        this.setVisible(true);
    }

    public void runGame() {
    }

    // TODO(david): have this run for main menu things?
    // TODO(david): create players with colors?
    public void createPlayers() {
        String dialogInput = JOptionPane.showInputDialog(
                this,
                "Enter number of players");
        try {
            int numPlayers = Integer.parseInt(dialogInput);
            if (numPlayers < 2 || numPlayers > 6)
                throw (new NumberFormatException("num out of bounds"));

            Player[] players = new Player[numPlayers];
            for (int i = 0; i < numPlayers; ++i) {
                dialogInput = JOptionPane.showInputDialog(
                        this,
                        String.format("Enter Player %d Name", (i + 1)));
                players[i] = new Player(dialogInput, i);
            }

            this.game.createPlayers(players);
        }
        catch (NumberFormatException e) {
            createPlayers();
        }
    }

    public void boardRepaint() {
        this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        this.boardPanel.validate();
        this.boardPanel.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("userCommandQuit")) {
            // TODO(david): find proper way to close
            this.setVisible(false);
            this.dispose();
        }
        if (e.getActionCommand().equals("userCommandEnter")) {
            if (this.boardPanel.isSelected((this.game.isSetup() ? 
                            BoardPanel.BOARD_SETUP : BoardPanel.BOARD_GENERAL))) {
                if (this.game.isSetup()) {
                    this.game.makeMove(this.boardPanel.getSelected(), null, 1);
                    this.game.switchPlayer();
                }
                this.game.checkSetup();
            }
            // TODO(david): set next move
            this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
            this.boardPanel.repaint();
        }
        if (e.getActionCommand().equals("userCommandCancel")) {
            // TODO(david): reset player's choices
            this.boardPanel.boardUpdate(null);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
        Coords temp = this.boardPanel.coordsFromPosition(e.getX(), e.getY());
        if (!this.game.getBoard().containsSpot(temp))
            return;

        // setup state where the players are establishing positions
        if (this.game.isSetup())
            this.boardPanel.select(temp, BoardPanel.BOARD_SETUP);
        else
            this.boardPanel.select(temp, BoardPanel.BOARD_GENERAL);
    }

    public void mouseMoved(MouseEvent e) {
        // TODO(david): add setting to have this append?
        Coords position = this.boardPanel.coordsFromPosition(e.getX(), e.getY());
        Spot temp = this.game.getBoard().getSpot(position);
        if (temp != null) 
            this.infoPanel.appendToPanel(temp.toString());
    }

    public void mouseDragged(MouseEvent e) {
    }
}
