package risky.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class InfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextArea textBox;
    private JButton enterButton;
    private JButton cancelButton;
    private JButton quitButton;

    public static final String[] setupLoop = {
        "Take turns placing resources in empty spots, then your own"};
    public static final String[] gameLoop = {
        "Hit Enter to gain resources for the turn",
        "Place your gained resources on any red lettered spot",
        "Attack or adjust resource placement, hit End Game when done"};
    private String[] currentLoop;
    private int currentPos;
    
    /**
     * Construct the Info Panel
     * @param listener ActionListener for the buttons
     */
    public InfoPanel(ActionListener listener) {
        this.setLayout(new FlowLayout());

        textBox = new JTextArea(1, 40);
        textBox.setEditable(false);
        this.add(textBox);

        enterButton = new JButton("Enter");
        enterButton.setActionCommand("userCommandEnter");
        enterButton.addActionListener(listener);
        this.add(enterButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("userCommandCancel");
        cancelButton.addActionListener(listener);
        this.add(cancelButton);

        quitButton = new JButton("Quit");
        quitButton.setActionCommand("userCommandQuit");
        quitButton.addActionListener(listener);
        this.add(quitButton);
        
        this.setSize(this.enterButton.getMinimumSize());

        this.currentLoop = InfoPanel.setupLoop;
        this.currentPos = 0;
        this.textBox.setText(this.currentLoop[this.currentPos]);
    }

    /**
     * Progress the tooltip text
     */
    public void progressText() {
        if (++this.currentPos == this.currentLoop.length) {
            if (this.currentLoop.length == InfoPanel.setupLoop.length) {
                this.currentLoop = InfoPanel.gameLoop;
            }
            this.currentPos = 0;
        }

        this.textBox.setText(this.currentLoop[this.currentPos]);
    }
}
