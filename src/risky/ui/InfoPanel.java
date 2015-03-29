package risky.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class InfoPanel extends JPanel {
    private static final long serialVersionUID = 2L;

    private JTextArea textBox;
    private JButton enterButton;
    private JButton quitButton;
    
    public InfoPanel(ActionListener listener) {
        this.setLayout(new FlowLayout());

        textBox = new JTextArea(1, 50);
        textBox.setEditable(false);
        this.add(textBox);

        // TODO(david): add proper button listeners
        enterButton = new JButton("Enter");
        enterButton.setActionCommand("userCommandEnter");
        enterButton.addActionListener(listener);
        this.add(enterButton);

        quitButton = new JButton("Quit");
        quitButton.setActionCommand("userCommandQuit");
        quitButton.addActionListener(listener);
        this.add(quitButton);
        
        this.setSize(this.enterButton.getMinimumSize());
    }

    public void writeToPanel(String message) {
        this.textBox.setText(message);
    }
}
