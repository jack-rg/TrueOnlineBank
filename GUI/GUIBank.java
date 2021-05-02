package GUI;

import Objects.User;
import Util.DataManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class GUIBank extends JFrame {
    Container c;
    CardLayout cards;

    public GUIBank() {
        JFrame frame = new JFrame();

        frame.setTitle("Bank");
        frame.setSize(600, 600);
        frame.setLocation(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = frame.getContentPane();
        cards = new CardLayout();

        c.setLayout(cards);

        addLogin();
        addRegister();

        frame.setVisible(true);
    }

    private void addLogin() {
        GUILogin login = new GUILogin();

        login.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = login.execute();
                if (user != null) {
                    DataManager.loadAccounts(user);
                    addHome(user);
                    cards.show(c, "Home");
                } else {
                    login.getErrorLabel().setVisible(true);
                }
            }
        });

        login.getGoToRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cards.show(c, "Register");
            }
        });

        c.add(login.getPanel(), "Login");
    }

    private void addRegister() {
        GUIRegister register = new GUIRegister();

        register.getRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = register.execute();
                if (user != null) {
                    addHome(user);
                    cards.show(c, "Home");
                } else {
                    register.getErrorLabel().setVisible(true);
                }
            }
        });

        register.getGoToLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cards.show(c, "Login");
            }
        });

        c.add(register.getPanel(), "Register");
    }

    private void addHome(User user) {
        GUIHome home = new GUIHome(user);
        c.add(home.getTabbedPane(), "Home");
    }

    public static void run() {
        GUIBank b = new GUIBank();
    }
}
