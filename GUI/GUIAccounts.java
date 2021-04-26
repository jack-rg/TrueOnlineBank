package GUI;
import javax.swing.*;
import java.awt.event.*;

public class GUIAccounts {
    JPanel panel;

    public GUIAccounts() {
        panel = new JPanel(); 
        panel.setLayout(null);

        // for all the accounts a user has
        GUIAccount ac1 = new GUIAccount();
        panel.add(ac1.getPanel());
    }

    public JPanel getPanel() {
        return panel;
    }
}
