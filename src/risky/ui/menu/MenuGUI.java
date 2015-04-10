package risky.ui.menu;

import risky.ui.menu.InfoBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO(adam): Legit everything. Why did you even push this?

public class MenuGUI extends JFrame implements ActionListener{

    private InfoBar infoBar;

    public MenuGUI() {
        super("Risky Main Menu"); // initialize with name
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Risky Main Menu");

        this.infoBar = new InfoBar(this);
        this.add(this.infoBar, BorderLayout.SOUTH);

        this.setSize(200, this.infoBar.getHeight() + 9);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("userCommandCancel")) {
            this.setVisible(false);
            this.dispose();
        }

        if (e.getActionCommand().equals("userCommandEnter")) {
            // TODO(adam): save menu selections
        }
    }
}