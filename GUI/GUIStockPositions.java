package GUI;

import Objects.Account;
import Objects.Person;
import Objects.Position;
import Objects.Security;
import Util.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * GUIStockPositions creates the GUI that allows users to see all their investment positions.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIStockPositions extends JPanel{
    JPanel panel, topPanel, positionsPanel;
    JSplitPane sp;

    JComboBox<String> accountCB;

    HashMap<String, Account> accMap;
    ArrayList<Position> positions;

    public GUIStockPositions(Person person) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel aPanel = new JPanel();
        aPanel.setLayout(null);

        JLabel accountLabel = new JLabel("Please pick an account: ");
        accountLabel.setBounds(50, 80, 150, 25);
        aPanel.add(accountLabel);

        accMap = AccountManager.getAccMap(person, true);
        accountCB = new JComboBox<>(AccountManager.getAccKeys(accMap));
        accountCB.setBounds(180, 80, 300, 25);
        aPanel.add(accountCB);

        panel.add(aPanel, BorderLayout.NORTH);

        accountCB.addActionListener(e -> {
            positions = ((Security) accMap.get(accountCB.getSelectedItem())).getPositions();
            update();
        });

        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.1);

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel(){
        return panel;
    }

    private void update() {
        if (topPanel != null) {
            sp.remove(topPanel);
        }

        if (positionsPanel != null) {
            sp.remove(positionsPanel);
        }

        topPanel = new JPanel();
        topPanel.setBounds(25, 25, 400, 500);

        JPanel titlePanel = new JPanel(new GridLayout(1, 4));
        titlePanel.add(new JLabel("Stock"));
        titlePanel.add(new JLabel("Quantity"));
        titlePanel.add(new JLabel("Total Cost"));
        titlePanel.add(new JLabel("Unrealized Profit / Loss"));
        sp.add(titlePanel);

        positionsPanel = new JPanel(new GridLayout(positions.size(), 1));
        for (Position p : positions) {
            positionsPanel.add((new GUIPosition(p)).getPanel());
        }

        sp.add(positionsPanel);
    }
}
