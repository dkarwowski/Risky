package riskyold.ui;

import riskyold.Risky;
import riskyold.common.Board;
import riskyold.common.Coords;
import riskyold.common.Player;
import riskyold.common.StateContext;
import riskyold.ui.menu.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RiskyGUI extends JFrame implements MouseListener, ActionListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    private Risky game;

    private BoardPanel boardPanel;
    private InfoPanel infoPanel;
    private PlayerPanel playerPanel;

    /**
     * Create the pieces of the GUI individualy
     *
     * @param risky instance of the game
     */
    public RiskyGUI(Risky risky) {
        super("Risky"); // initialize with name
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    /**
     * Create the players through dialog boxes
     *
     * @return whether the player was created properly or not
     */
    public boolean createPlayers() {
        String dialogInput = JOptionPane.showInputDialog(
                this,
                "Enter number of players");
        try {
            int numPlayers = Integer.parseInt(dialogInput);
            if (numPlayers < 2 || numPlayers > 6)
                throw (new NumberFormatException("num out of bounds"));

            int startResources = ((this.game.getBoard().getNumSpots() / 3) * 4) / numPlayers;

            Player[] players = new Player[numPlayers];
            int i = 0;
            while (i < numPlayers) {
                dialogInput = JOptionPane.showInputDialog(
                        this,
                        String.format("Enter Player %d Name", (i + 1)));

                if (dialogInput != null) {
                    players[i] = new Player(dialogInput, i, startResources);
                    i++;
                }
            }

            this.game.createPlayers(players);
            this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        } catch (NumberFormatException e) {
            if (dialogInput == null)
                return (false);
            createPlayers();
        }

        return (true);
    }

    /**
     * Have the game progress move forward properly
     */
    public void progressGame() {
        this.game.progressGame();
        this.infoPanel.progressText();
    }

    /**
     * Update the view information on the board
     */
    public void boardUpdate() {
        this.playerPanel.writeToPanel(
                "Player: " + this.game.getCurrentPlayer().getName() +
                        " with " + this.game.getCurrentPlayer().getAvailableResources() +
                        " resources");
        this.boardPanel.boardUpdate(this.game.getBoard(), this.game.getCurrentPlayer());
        this.boardPanel.repaint();
    }

    @Override
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
                    if (this.game.getBoard().isSetupDone()) {
                        int input = getResourcesBox(false);
                        if (input != -1) {
                            this.game.makeMove(this.boardPanel.getSelected(), null, input);
                        }
                    } else
                        this.game.makeMove(this.boardPanel.getSelected(), null, 1);
                    this.game.switchPlayer();

                    // check if setup is done
                    this.game.checkSetup();
                } else {
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
            } else {
                if (this.game.checkPlayerWon()) {
                    this.endGame();
                    return;
                }
                JOptionPane.showMessageDialog(this, "You need to select properly!");
            }

            this.boardUpdate();
        }

        if (e.getActionCommand().equals("userCommandCancel")) {
            // TODO(david): reset player's choices
            this.boardPanel.boardUpdate(null);
        }
    }

    /**
     * Dialog box after ending the game
     */
    public void endGame() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Play Again?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            this.game.newGame();
        } else {
            this.actionPerformed(new ActionEvent(
                    this.infoPanel,
                    ActionEvent.ACTION_PERFORMED,
                    "userCommandQuit"));
        }
    }

    /**
     * Get a resource box for the game
     *
     * @param attack whether the person is attacking or moving/placing resources
     * @return number of resources or -1 if cancelled
     */
    public int getResourcesBox(boolean attack) {
        boolean outOfBounds;
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
                    outOfBounds = !(0 < input && input < srcResources);
                } else {
                    outOfBounds = !(0 < input && input < (t + 1));
                }
            } catch (NumberFormatException exception) {
                outOfBounds = true;
            }
        } while (outOfBounds);

        return (input);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
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

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
