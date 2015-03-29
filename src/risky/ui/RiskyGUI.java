package risky.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import risky.common.Board;

public class RiskyGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private Board gameBoard;
    
    private BoardPanel boardPanel;
    private InfoPanel infoPanel;

    public RiskyGUI(Board board) {
        super("Risky"); // initialize with name
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Risky");

        this.gameBoard = board;
        this.boardPanel = new BoardPanel(gameBoard);
        this.add(this.boardPanel, BorderLayout.CENTER);

        this.infoPanel = new InfoPanel();
        this.add(this.infoPanel, BorderLayout.SOUTH);

        this.setSize(
                this.boardPanel.getPanelDimension().width,
                this.boardPanel.getPanelDimension().height + this.infoPanel.getHeight());
        this.setVisible(true);
    }

    public void boardRepaint(Board board) {
        this.boardPanel.boardUpdate(board);
        this.boardPanel.validate();
        this.boardPanel.repaint();
    }
}
