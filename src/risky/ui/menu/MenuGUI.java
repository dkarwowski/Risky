package risky.ui.menu;

import risky.Risky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    private InfoBar infoBar;

    public MenuGUI() {
        super("Risky Main Menu"); // initialize with name
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Risky Main Menu");

        this.infoBar = new InfoBar(this);
        this.add(this.infoBar, BorderLayout.SOUTH);

        this.setSize(300, 400);
        this.getContentPane().setBackground(Color.green);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("userCommandExit")) {
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }

        if (e.getActionCommand().equals("userCommandStart")) {
            this.setVisible(false);
            new Risky(false);
        }

        if (e.getActionCommand().equals("userCommandSettings")) {
            // TODO: settings menu
            JOptionPane.showMessageDialog(this, "Oops! This feature is missing. Check back in future versions!",
                    "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MenuGUI();
    }
}
