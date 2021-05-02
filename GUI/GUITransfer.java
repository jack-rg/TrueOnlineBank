package GUI;

import Objects.User;

import javax.swing.*;

public class GUITransfer extends JPanel {
    JPanel panel;

    public GUITransfer(User user, GUIAccountsOverview accountsOverview, JTabbedPane tabbedPane) {
        panel = new JPanel();
        panel.setLayout(null);
    }

    public JPanel getPanel() { return panel; }
}
