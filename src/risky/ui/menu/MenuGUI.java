package risky.ui.menu;

import risky.Risky;
import risky.ui.RiskyGUI;
import risky.ui.menu.InfoBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO(adam): Legit everything. Why did you even push this?

public class MenuGUI extends JFrame implements ActionListener{

    private RiskyGUI gameGUI;
    private InfoBar infoBar;

    public MenuGUI(RiskyGUI GUI) {
        super("Risky Main Menu"); // initialize with name
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Risky Main Menu");
        this.gameGUI = GUI;

        this.infoBar = new InfoBar(this);
        this.add(this.infoBar, BorderLayout.SOUTH);

        this.setSize(400, 300);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("userCommandQuit")) {
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }

        if (e.getActionCommand().equals("userCommandStart")) {
            this.setVisible(false);
            this.dispose();
            this.gameGUI.setVisible(true);
            this.gameGUI.createPlayers();
        }

        if (e.getActionCommand().equals("userCommandSave")) {
            // TODO(adam): save menu selections
        }
    }
}