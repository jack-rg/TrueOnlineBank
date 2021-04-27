package GUI;

import Objects.Account;
import Objects.User;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFrame;

public class GUIBank extends JFrame {
    Container c;
    CardLayout cards;

//    GUILogin login;
//    GUIRegister register;
//    GUIHome home;

    public GUIBank() {
        // Create a new frame
        JFrame frame = new JFrame();

        // Initialize frame information
        frame.setTitle("Bank");
        frame.setSize(600, 600);
        frame.setLocation(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = frame.getContentPane();
        cards = new CardLayout();
        
        c.setLayout(cards);

        addLogin();
        addRegister();

        // Turn it on
        frame.setVisible(true);
    }

    private void addLogin() {
        GUILogin login = new GUILogin();

        login.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = login.execute();
                if (user != null) {
                    user.loadAccounts();

                    addHome(user.getRawAccounts());
//                    home.setAccounts(user.getRawAccounts());
                    cards.show(c, "Home");
                } else {
                    System.out.println("Incorrect password or username.");
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
                    addHome(user.getRawAccounts());
                    cards.show(c, "Home");
                } else {
                    System.out.println("User already exists. Please login.");
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

    private void addHome(ArrayList<Account> accounts) {
        GUIHome home = new GUIHome(accounts);
        c.add(home.getTabbedPane(), "Home");
    }

    public static void run() {
        GUIBank b = new GUIBank();
    }
}
