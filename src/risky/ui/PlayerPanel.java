package risky.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextArea textBox;

    private String constMessage;

    public PlayerPanel() {
        this.textBox = new JTextArea(1, 40);
        this.textBox.setEditable(false);
        this.add(this.textBox);

        this.setSize(this.textBox.getMinimumSize());
        this.constMessage = "";
    }

    public void writeToPanel(String message) {
        this.constMessage = message;
        this.textBox.setText(this.constMessage);
    }
}
