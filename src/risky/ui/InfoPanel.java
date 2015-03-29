package risky.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel {
    private static final long serialVersionUID = 2L;

    private JTextArea textBox;
    
    public InfoPanel() {
        textBox = new JTextArea(1, 50);
        textBox.setEditable(false);
        this.add(textBox);
        
        this.setSize(this.textBox.getMinimumSize());
    }

    public void writeToPanel(String message) {
        this.textBox.setText(message);
    }
}
