package GUI;

import javax.swing.*;

import Objects.Person;
import Objects.User;
import Util.DataManager;

import java.awt.*;
import java.util.ArrayList;

public class GUIUsersOverview {
    JPanel panel, usersPanel;
    CardLayout cards;

    GUIHome home;

    public GUIUsersOverview(GUIHome home) {
        this.home = home;

        panel = new JPanel();
        cards = new CardLayout();
        panel.setLayout(cards);

        update();
    }

    public void update() {
        panel.removeAll();

        ArrayList<User> users = DataManager.loadUsers();

        usersPanel = new JPanel();
        usersPanel.setLayout(new GridLayout(users.size(), 1));
        for (User u : users) {
            JButton aBtn = new JButton(u.toString());
            usersPanel.add(aBtn);

            GUIUser user = new GUIUser(u, home);
            panel.add(user.getPanel(), u.getUserID());

            user.getGoBackButton().addActionListener(e -> cards.show(panel, "UserPanel"));

            aBtn.addActionListener(e -> cards.show(panel, u.getUserID()));
        }

        panel.add(new JScrollPane(usersPanel), "UserPanel");
        cards.show(panel, "UserPanel");
    }

    public JPanel getPanel() {
        return panel;
    }
}
