package risky.ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoBar extends JPanel {

    private static final long serialVersionUID = 1L;

    private JButton saveButton;
    private JButton startButton;
    private JButton cancelButton;

    public InfoBar(ActionListener listener) {
        this.setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        saveButton.setActionCommand("userCommandSave");
        saveButton.addActionListener(listener);
        this.add(saveButton);

        startButton = new JButton("Start");
        startButton.setActionCommand("userCommandStart");
        startButton.addActionListener(listener);
        this.add(startButton);

        cancelButton = new JButton("Quit");
        cancelButton.setActionCommand("userCommandQuit");
        cancelButton.addActionListener(listener);
        this.add(cancelButton);
    }
}
