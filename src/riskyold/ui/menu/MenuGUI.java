package riskyold.ui.menu;

import riskyold.Risky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MenuGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private InfoBar infoBar;

    public MenuGUI() {
        super("Risky Main Menu"); // initialize with name
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Risky Main Menu");

        this.infoBar = new InfoBar(this);
        this.add(this.infoBar, BorderLayout.SOUTH);

        this.setSize(300, 400);
        this.getContentPane().setBackground(Color.green);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new MenuGUI();
    }

    public String chooseBoard() {
        File boardDirectory = new File("data");

        File[] flist = boardDirectory.listFiles();
        ArrayList<String> fnames = new ArrayList<>();
        assert flist != null;
        for (File f : flist) {
            if (f.getName().matches(".*\\.map"))
                fnames.add(f.getName());
        }

        String dialogInput = (String) JOptionPane.showInputDialog(
                this,
                "Choose a board from the following",
                "Choose Board",
                JOptionPane.PLAIN_MESSAGE,
                null,
                fnames.toArray(),
                fnames.get(0));

        if ((dialogInput != null) && (dialogInput.length() > 0))
            return (dialogInput);
        return (null);
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
            String boardName = this.chooseBoard();
            if (boardName != null)
                new Risky(boardName);
            else
                this.setVisible(true);
        }
/**
 if (e.getActionCommand().equals("userCommandSettings")) {
 // TODO: settings menu
 JOptionPane.showMessageDialog(this, "Oops! This feature is missing. Check back in feature versions!",
 "Error", JOptionPane.PLAIN_MESSAGE);
 }
 **/
    }
}
