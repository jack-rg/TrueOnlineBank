package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIInvestmentHome extends JPanel {
    JPanel panel;

    public GUIInvestmentHome(Person person) {
        panel = new JPanel();
        panel.setLayout(null);

        // TODO: 2021/5/2 design the Invest Interface

    }

    public Component getPanel() {
        return this.panel;
    }
}
