package GUI;

import javax.swing.*;
import java.awt.event.*;

public class GUIAccount {
    JPanel panel;

    public GUIAccount() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel accountNameLabel = new JLabel("DUMMY NAME");
        accountNameLabel.setBounds(200, 200, 80, 25);
        panel.add(accountNameLabel);
    }

    public JPanel getPanel() {
        return panel;
    }
}