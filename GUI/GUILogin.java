package GUI;

import Util.State;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GUILogin extends JFrame {
    JPanel panel;
    JTextField userText;
    JPasswordField passwordText;
    JButton loginButton;
    JButton goToRegisterButton;

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

        goToRegisterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Going to registration");
            }
        });
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

    public boolean execute() {
        String userName = userText.getText();
        String password = String.valueOf(passwordText.getPassword());

        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + userName + " |") && line.contains("| " + password + " |")) {
                    return true;
                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return false;
    }
}
