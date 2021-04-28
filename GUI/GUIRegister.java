package GUI;

import Objects.User;
import Util.DataManager;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GUIRegister extends JFrame {
    JPanel panel;
    JTextField userText;
    JPasswordField passwordText;
    JButton registerButton;
    JButton goToLoginButton;

    public GUIRegister() {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(200, 200, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(300, 200, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200, 250, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(300, 250, 165, 25);
        panel.add(passwordText);

        registerButton = new JButton("Register");
        registerButton.setBounds(270, 310, 80, 40);
        panel.add(registerButton);

        goToLoginButton = new JButton("Already have an account? \nLogin.");
        goToLoginButton.setBounds(170, 370, 280, 40);
        panel.add(goToLoginButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getGoToLoginButton() {
        return goToLoginButton;
    }

    public User execute() {
        String userName = userText.getText();
        String password = String.valueOf(passwordText.getPassword());

        return DataManager.userExists(userName, password, true);
    }
}
