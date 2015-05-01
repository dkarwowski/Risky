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
import risky.common.StateContext;
import risky.ui.menu.MenuGUI;

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

        this.playerPanel = new PlayerPanel(this);
        this.add(this.playerPanel, BorderLayout.NORTH);

        this.setSize(
                this.boardPanel.getPanelDimension().width,
                this.boardPanel.getPanelDimension().height 
                + this.infoPanel.getHeight() 
                + this.playerPanel.getHeight() + 9);
    }

    public boolean createPlayers() {
        String dialogInput = JOptionPane.showInputDialog(
                this,
                "Enter number of players");
        try {
            int numPlayers = Integer.parseInt(dialogInput);
            if (numPlayers < 2 || numPlayers > 6)
                throw (new NumberFormatException("num out of bounds"));

            Player[] players = new Player[numPlayers];
            int i = 0;
            while (i < numPlayers) {
                dialogInput = JOptionPane.showInputDialog(
                        this,
                        String.format("Enter Player %d Name", (i + 1)));
                if (dialogInput != null)
                    players[i++] = new Player(dialogInput, i);
            }

            this.game.createPlayers(players);
            this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        } 
        catch (NumberFormatException e) {
            if (dialogInput == null)
                return (false);
            createPlayers();
        }

        return (true);
    }

    public void progressGame() {
        this.game.progressGame();
        this.infoPanel.progressText();
    }

    public void boardUpdate() {
        this.playerPanel.writeToPanel(
                "Player: " + this.game.getCurrentPlayer().getName() + 
                " with " + this.game.getCurrentPlayer().getAvailableResources() +
                " resources");
        this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        this.boardPanel.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("userCommandQuit")) {
            this.setVisible(false);
            this.dispose();
            new MenuGUI();
        }

        if (e.getActionCommand().equals("userCommandEndTurn")) {
            if (this.game.gameStateEquals(StateContext.PLAY_ATTK)) {
                this.progressGame();
                this.game.switchPlayer();
                this.boardUpdate();
            }
        }
        
        // the user has pressed enter to end the specific decision in their turn
        if (e.getActionCommand().equals("userCommandEnter")) {
            // check if the the selections have been made properly based on stage in game
            if (this.boardPanel.isSelected(this.game.getGameState())) {
                if (this.game.gameStateEquals(StateContext.SETUP_BOARD)) {
                    // if the spot is free, we're in setup 1; just place a single resource there
                    this.game.makeMove(this.boardPanel.getSelected(), null, 1);
                    this.game.switchPlayer();
                    
                    // check if setup is done
                    this.game.checkSetup();
                }
                else {
                    // - Stage one   (gain new resources)
                    if (this.game.gameStateEquals(StateContext.PLAY_GAIN)) {
                        this.game.addStateResources();
                        this.progressGame();
                    }
                    // - Stage two   (place gained resources)
                    else if (this.game.gameStateEquals(StateContext.PLAY_PUTS)) {
                        int input = getResourcesBox(false);
                        if (input != -1) {
                            this.game.makeMove(
                                    this.boardPanel.getSelected(),
                                    null,
                                    input);
                            this.progressGame();
                        }
                    }
                    // - Stage three attack/move placed resources
                    else if (this.game.gameStateEquals(StateContext.PLAY_ATTK)) {
                        // TODO(david): end turn if not enough resources in spots
                        int input = getResourcesBox(true);
                        if (input != -1) {
                            this.game.makeMove(
                                    this.boardPanel.getSource(),
                                    this.boardPanel.getSelected(),
                                    input);
                        }
                    }
                }
            }
            else {
                if (this.game.checkPlayerWon())
                    this.endGame();
                JOptionPane.showMessageDialog(this, "You need to select properly!");
            }
            
            this.boardUpdate();
        }
        
        if (e.getActionCommand().equals("userCommandCancel")) {
            // TODO(david): reset player's choices
            this.boardPanel.boardUpdate(null);
        }
    }

    public void endGame() {
        int option = JOptionPane.showConfirmDialog(
                this, 
                "Play Again?", 
                "Play Again?", 
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            this.game.newGame();
        }
        else {
            this.actionPerformed(new ActionEvent(
                        this.infoPanel, 
                        ActionEvent.ACTION_PERFORMED, 
                        "userCommandQuit"));
        }
    }

    public int getResourcesBox(boolean attack) {
        boolean outOfBounds = false;
        int input = 0;
        do {
            try {
                String dialogInput = JOptionPane.showInputDialog(
                            this, "Number of Resources to Use");
                // exit with a negative value if the player hits cancel
                if (dialogInput == null) {
                    input = -1;
                    break;
                }

                input = Integer.parseInt(dialogInput);
                int t = this.game.getCurrentPlayer().getAvailableResources();
                if (attack) {
                    // TODO(david): limit resources properly in attack mode
                    int srcResources = this.game.getBoard().getSpot(this.boardPanel.getSource()).getResources();
                    if (0 < input && input < srcResources)
                        outOfBounds = false;
                    else
                        outOfBounds = true;
                }
                else {
                    if (0 < input && input < (t + 1))
                        outOfBounds = false;
                    else
                        outOfBounds = true;
                }
            } 
            catch (NumberFormatException exception) {
                outOfBounds = true;
            }
        } while (outOfBounds);

        return (input);
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
        this.boardPanel.select(temp, this.game.getGameState());

        if (e.getClickCount() == 2) {
            this.actionPerformed(new ActionEvent(
                        this.infoPanel, 
                        ActionEvent.ACTION_PERFORMED, 
                        "userCommandEnter"));
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}