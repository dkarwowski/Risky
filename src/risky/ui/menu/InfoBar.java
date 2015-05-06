package risky.ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoBar extends JPanel {

    private static final long serialVersionUID = 1L;

    private JButton settingsButton;
    private JButton startButton;
    private JButton exitButton;

    public InfoBar(ActionListener listener) {
        this.setLayout(new FlowLayout());

        this.setBackground(Color.blue);

        startButton = new JButton("Start");
        startButton.setActionCommand("userCommandStart");
        startButton.addActionListener(listener);
        this.add(startButton);
/**
        settingsButton = new JButton("Settings");
        settingsButton.setActionCommand("userCommandSettings");
        settingsButton.addActionListener(listener);
        this.add(settingsButton);
**/
        exitButton = new JButton("Exit");
        exitButton.setActionCommand("userCommandExit");
        exitButton.addActionListener(listener);
        this.add(exitButton);
    }
}
