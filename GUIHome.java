
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GUIHome extends JPanel {
    JTabbedPane tabbedPane;

    public GUIHome() {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Accounts", icon, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Pay and Transfer", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Invest", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
    
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        // java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        // if (imgURL != null) {
        //     return new ImageIcon(imgURL);
        // } else {
        //     System.err.println("Couldn't find file: " + path);
        //     return null;
        // }
        return null; // set to null for know, could change to show icons if we wanted
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
