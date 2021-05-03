package GUI;

import Objects.Person;
import Util.DataManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIBank extends JFrame {
    Container c;
    CardLayout cards;

    JButton logOutBtn;

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
                Person person = login.execute();
                if (person != null) {
                    DataManager.loadAccounts(person);
                    addHome(person);
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
                Person person = register.execute();
                if (person != null) {
                    addHome(person);
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

    private void addHome(Person user) {
        GUIHome home = new GUIHome(user);
        c.add(home.getTabbedPane(), "Home");

        logOutBtn = home.getLogOutBtn();
        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(c, "Login");
            }
        });
    }

    public static void run() {
        GUIBank b = new GUIBank();
    }
}
