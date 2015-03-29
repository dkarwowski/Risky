package risky.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import risky.Risky;
import risky.common.Board;
import risky.common.Coords;
import risky.common.Player;

public class RiskyGUI extends JFrame implements MouseListener, ActionListener {
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
        this.boardPanel = new BoardPanel(this, gameBoard);
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
                        String.format("Enter Player %d Name", i));
                players[i] = new Player(dialogInput, i);
            }

            this.game.createPlayers(players);
        }
        catch (NumberFormatException e) {
            createPlayers();
        }
    }

    public void firstMove() {
        // first move, each player places until all spots have been occupied
    }

    public void boardRepaint() {
        this.boardPanel.update(this.game.getBoard(), this.game.getCurrentPlayer());
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
            // TODO(david): set next move
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
        int fixedX = e.getX() - 9 - 4;
        int squareX = (int)(fixedX / 28);
        int fixedY = e.getY() - 9 - ((squareX % 2 == 0) ? 0 : 16);
        int squareY = (int)(fixedY / 32);
        this.boardPanel.update(new Coords(squareX, squareY, false));
    }
}
