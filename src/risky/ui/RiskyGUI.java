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
    private PlayerPanel playerPanel;

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

        this.playerPanel = new PlayerPanel();
        this.add(this.playerPanel, BorderLayout.NORTH);

        this.setSize(
                this.boardPanel.getPanelDimension().width,
                this.boardPanel.getPanelDimension().height 
                + this.infoPanel.getHeight() 
                + this.playerPanel.getHeight() + 9);
        this.setVisible(true);
    }

    public void runGame() {
    }

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
            this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        }
        catch (NumberFormatException e) {
            createPlayers();
        }
    }

    public void boardRepaint() {
        // TODO(david): start using this properly again
        this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        this.boardPanel.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("userCommandQuit")) {
            // TODO(david): find proper way to close
            this.setVisible(false);
            this.dispose();
        }
        
        // the user has pressed enter to end the specific decision in their turn
        if (e.getActionCommand().equals("userCommandEnter")) {
            // check if the the selections have been made properly based on stage in game
            if (this.boardPanel.isSelected((this.game.isSetup() ? 
                            BoardPanel.BOARD_SETUP : BoardPanel.BOARD_GENERAL))) {
                if (this.game.isSetup()) {
                    // if the spot is free, we're in setup 1; just place a single resource there
                    if (this.game.getBoard().spotFree(this.boardPanel.getSelected()))
                        this.game.makeMove(this.boardPanel.getSelected(), null, 1);
                    // otherwise we have the player place however many resources they want
                    else {
                        // gotta make sure that the input resources are valid
                        // TODO(david): make a function for this?
                        boolean outOfBounds = false;
                        int input = 0;
                        do {
                            try {
                                input = Integer.parseInt(JOptionPane.showInputDialog(
                                            this, "Number of Resources to Use"));
                                int t = this.game.getCurrentPlayer().getAvailableResources();
                                if (1 < input && input < (t + 1))
                                    outOfBounds = false;
                                else
                                    outOfBounds = true;
                            } catch (NumberFormatException exception) {
                                outOfBounds = true;
                            }
                        } while (outOfBounds);
                        
                        // actually place the resources
                        this.game.makeMove( this.boardPanel.getSelected(), null, input);
                    }
                    
                    // TODO(david): move this to an end turn button
                    this.game.switchPlayer();
                    
                    // check if setup is done
                    this.game.checkSetup();
                }
                else {
                    // TODO(david): create general playing rules
                    // - Stage one   (gain new resources)
                    // - Stage two   (place gained resources)
                    // - Stage three (attack/move placed resources
                }
            }
            
            // properly update things
            // TODO(david): move these back to their own function?
            this.playerPanel.writeToPanel(
                    "Player: " + this.game.getCurrentPlayer().getName() + 
                    " with " + this.game.getCurrentPlayer().getAvailableResources() +
                    " resources");
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
        // ensure the clicked spot is a valid spot
        Coords temp = this.boardPanel.coordsFromPosition(e.getX(), e.getY());
        if (!this.game.getBoard().containsSpot(temp))
            return;

        // have the boardpanel determine whether the spot is valid for the turn
        // setup state where the players are establishing positions
        if (this.game.isSetup())
            this.boardPanel.select(temp, BoardPanel.BOARD_SETUP);
        else
            this.boardPanel.select(temp, BoardPanel.BOARD_GENERAL);
    }

    public void mouseMoved(MouseEvent e) {
        // displays information for the last spot that was hovered over
        // TODO(david): have each spot display number of resources on it?
        Coords position = this.boardPanel.coordsFromPosition(e.getX(), e.getY());
        Spot temp = this.game.getBoard().getSpot(position);
        if (temp != null) 
            this.infoPanel.appendToPanel(temp.toString());
    }

    public void mouseDragged(MouseEvent e) {
    }
}
