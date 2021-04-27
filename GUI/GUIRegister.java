package GUI;

import Objects.User;
import Util.State;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
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

        goToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Going to login");
            }
        });
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

    public boolean execute() {
        String userName = userText.getText();
        String password = String.valueOf(passwordText.getPassword());

        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + userName + " |") && line.contains("| " + password + " |")) {
                    return false;
                }
                else {
                    traceFile.add(line);
                }
            }

            br.close();

            User user = new User(userText.getText(), String.valueOf(passwordText.getPassword()), traceFile.size() + 1);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return true;
    }
}
