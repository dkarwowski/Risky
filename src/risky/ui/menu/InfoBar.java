package risky.ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoBar extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton saveButton;
    private JButton cancelButton;

    public InfoBar(ActionListener listener) {
        this.setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        saveButton.setActionCommand("userCommandSave");
        saveButton.addActionListener(listener);
        this.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("userCommandCancel");
        cancelButton.addActionListener(listener);
        this.add(cancelButton);
    }
}
