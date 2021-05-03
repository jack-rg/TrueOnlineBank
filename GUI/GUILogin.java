package GUI;

import Objects.Manager;
import Objects.Person;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;

public class GUILogin extends JFrame {
    JPanel panel;
    JTextField userText;
    JPasswordField passwordText;
    JButton loginButton;
    JButton goToRegisterButton;
    JLabel errorLabel;

    public GUILogin() {
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

        loginButton = new JButton("Login");
        loginButton.setBounds(270, 310, 80, 40);
        panel.add(loginButton);

        goToRegisterButton = new JButton("New user? \nCreate an account.");
        goToRegisterButton.setBounds(170, 370, 280, 40);
        panel.add(goToRegisterButton);

        errorLabel = new JLabel("Incorrect password or username.");
        errorLabel.setBounds(210, 420, 280, 40);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        panel.add(errorLabel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getGoToRegisterButton() {
        return goToRegisterButton;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public Person execute() {
        String userName = userText.getText();
        String password = String.valueOf(passwordText.getPassword());

        Manager m = DataManager.isManager(userName, password);
        if (m != null) {
            return m;
        } else {
            return DataManager.userExists(userName, password, false);
        }
    }
}
