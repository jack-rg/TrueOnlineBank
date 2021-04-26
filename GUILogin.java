
import javax.swing.*;
import java.awt.event.*;

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

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logged in...");
                System.out.println(userText.getText());
                System.out.println(passwordText.getPassword());
            }
        });

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
}
