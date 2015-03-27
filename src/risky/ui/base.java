package risky.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by AdamToris on 3/24/15.
 */
public class base extends JFrame implements ActionListener{
    private JFrame window;
    private JTextArea gameOutput = new JTextArea();
    private JTextField input = new JTextField();
    private JTextField textOutput = new JTextField();

    // TODO(david): remove this solution
    private String actionString;

    public base(){
        window = new JFrame("Risky");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Risky");
        window.setSize(800,400);

        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Map"));
        BoxLayout layout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(layout);

        // Modify output component to behave as intended
        Font font = new Font("Monospaced", Font.PLAIN, 14);
        gameOutput.setFont(font);
        gameOutput.setEditable(false);
        gameOutput.setHighlighter(null);

        textOutput.setEditable(false);

        input.addActionListener(this);

        Box box = Box.createVerticalBox();
        box.add(gameOutput, TOP_ALIGNMENT);
        box.add(textOutput);
        box.add(input, BOTTOM_ALIGNMENT);
        window.add(box);

        window.setVisible(true);
    }

    public void printGameOutput(String str){
        gameOutput.setText(str);
    }

    public void printTextOutput(String str){
        textOutput.setText(str);
    }

    public void actionPerformed(ActionEvent e) {
        this.actionString = returnText();
    }

    public String returnText(){
        String text = input.getText();
        input.setText("");
        return text;
    }

    public String getActionString() {
        String result = this.actionString;
        this.actionString = null;
        return (result);
    }

    // TODO(david): clean this out to it's own class?
    public String getDialog(String message) {
        String result = JOptionPane.showInputDialog(this.window, message);
        return result;
    }

    // Main method for testing
    public static void main(String args[]) {
        base app = new base();
    }
}
