import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class Bank extends JFrame {
    Container c;
    CardLayout cards;

    public Bank() {
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

        addHome();

        // Turn it on
        frame.setVisible(true);
    }

    private void addLogin() {
        GUILogin login = new GUILogin();

        login.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cards.show(c, "Home");
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
                cards.show(c, "Home");
            }
        });

        register.getGoToLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cards.show(c, "Login");
            }
        });

        c.add(register.getPanel(), "Register");
    }

    private void addHome() {
        GUIHome home = new GUIHome();
        c.add(home.getTabbedPane(), "Home");
    }

    public static void run() {
        Bank b = new Bank();
    }

    public static void main(String[] args) {
        run();
    }
}
