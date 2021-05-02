package GUI;

import Objects.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISettings {
    JPanel panel;

    public GUISettings(User user) {
        panel = new JPanel();
        panel.setLayout(null);

        JLabel currentUsernameLabel = new JLabel("Current username: ");
        currentUsernameLabel.setBounds(30, 25, 120, 25);
        panel.add(currentUsernameLabel);

        JLabel usernameLabel = new JLabel(user.getUserName());
        usernameLabel.setBounds(180, 25, 70, 25);
        panel.add(usernameLabel);

        JTextField usernameTxt = new JTextField(20);
        usernameTxt.setBounds(180, 25, 70, 25);
        usernameTxt.setVisible(false);
        panel.add(usernameTxt);

        JButton changeUsernameBtn = new JButton("Change");
        changeUsernameBtn.setBounds(250, 25, 80, 25);

        JButton usernameSubmitBtn = new JButton("Submit");
        usernameSubmitBtn.setBounds(250, 25, 80, 25);
        usernameSubmitBtn.setVisible(false);

        changeUsernameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameLabel.setVisible(false);
                usernameTxt.setVisible(true);

                changeUsernameBtn.setVisible(false);
                usernameSubmitBtn.setVisible(true);
            }
        });

        usernameSubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTxt.getText();
                if (!username.equals("")) {
                    user.setUserName(username);
                    usernameLabel.setText(username);
                }

                usernameTxt.setText("");

                usernameLabel.setVisible(true);
                usernameTxt.setVisible(false);

                changeUsernameBtn.setVisible(true);
                usernameSubmitBtn.setVisible(false);
            }
        });

        panel.add(changeUsernameBtn);
        panel.add(usernameSubmitBtn);

        String password = user.getPassword();
        JLabel currentPasswordLabel = new JLabel("Current password: ");
        currentPasswordLabel.setBounds(30, 50, 120, 25);
        panel.add(currentPasswordLabel);

        JLabel passwordLabel = new JLabel(password.substring(0, 2) + "*".repeat(password.length() - 2));
        passwordLabel.setBounds(180, 50, 70, 25);
        panel.add(passwordLabel);

        JPasswordField passwordTxt = new JPasswordField(20);
        passwordTxt.setBounds(180, 50, 70, 25);
        passwordTxt.setVisible(false);
        panel.add(passwordTxt);

        JButton changePasswordBtn = new JButton("Change");
        changePasswordBtn.setBounds(250, 50, 80, 25);

        JButton passwordSubmitBtn = new JButton("Submit");
        passwordSubmitBtn.setBounds(250, 50, 80, 25);
        passwordSubmitBtn.setVisible(false);

        panel.add(changePasswordBtn);
        panel.add(passwordSubmitBtn);

        changePasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordLabel.setVisible(false);
                passwordTxt.setVisible(true);

                changePasswordBtn.setVisible(false);
                passwordSubmitBtn.setVisible(true);
            }
        });

        passwordSubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(passwordTxt.getPassword());
                if (!password.equals("")) {
                    user.setPassword(password);
                    passwordLabel.setText(password.substring(0, 2) + "*".repeat(password.length() - 2));
                }

                passwordTxt.setText("");

                passwordLabel.setVisible(true);
                passwordTxt.setVisible(false);

                changePasswordBtn.setVisible(true);
                passwordSubmitBtn.setVisible(false);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
