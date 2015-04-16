package risky.ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextArea textBox;
    private JButton finishTurn;

    private String constMessage;

    /**
     * Construct the Player Panel
     * TODO: add action listener
     */
    public PlayerPanel() {
        this.textBox = new JTextArea(1, 40);
        this.textBox.setEditable(false);
        this.add(this.textBox);

        this.finishTurn = new JButton("End Turn");
        this.finishTurn.setActionCommand("userCommandEndTurn");
        this.add(this.finishTurn);

        this.setSize(max(this.textBox.getMinimumSize(), this.finishTurn.getMinimumSize()));
        this.constMessage = "";
    }

    /**
     * Write a message to the panel
     * @param message String to write
     */
    public void writeToPanel(String message) {
        this.constMessage = message;
        this.textBox.setText(this.constMessage);
    }

    /**
     * Get the highest dimension
     * @param d1 first Dimension to compare
     * @param d2 second Dimension to compare
     * @return Dimension (either d1 or d2)
     */
    private Dimension max(Dimension d1, Dimension d2) {
        return ((d1.getHeight() < d2.getHeight()) ? d2 : d1);
    }
}
