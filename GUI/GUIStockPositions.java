package GUI;

import Objects.Account;
import Objects.Person;
import Objects.Position;
import Objects.Security;
import Util.ComboBoxGenerator;

import javax.swing.*;
import java.awt.*;
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
    JSplitPane sp, bottomSP;

    JComboBox<String> bCB;

    HashMap<String, Account> accMap;
    ArrayList<Position> positions;

    Person person;
    GUIInvestmentHome home;

    public GUIStockPositions(Person person, GUIInvestmentHome home) {
        this.person = person;
        this.home = home;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.3);

        JPanel aPanel = new JPanel();
        aPanel.setLayout(null);

        JLabel accountLabel = new JLabel("Please pick an account: ");
        accountLabel.setBounds(30, 50, 200, 25);
        aPanel.add(accountLabel);

        bCB = new JComboBox<>();
        bCB.setBounds(200, 50, 300, 25);
        aPanel.add(bCB);
        sp.add(aPanel);

        updateAccounts();
        bCB.setSelectedIndex(0);

        bCB.addActionListener(e -> {
            update();
        });

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel(){
        return panel;
    }

    public void update() {
        positions = ((Security) accMap.get(bCB.getSelectedItem())).getPositions();

        if (bottomSP != null) {
            sp.remove(bottomSP);
        }

        bottomSP = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        topPanel = new JPanel();
        topPanel.setBounds(25, 25, 400, 500);

        JPanel titlePanel = new JPanel(new GridLayout(1, 4));
        titlePanel.add(new JLabel("Stock"));
        titlePanel.add(new JLabel("Quantity"));
        titlePanel.add(new JLabel("Total Cost"));
        titlePanel.add(new JLabel("Unrealized Profit / Loss"));
        bottomSP.add(titlePanel);

        positionsPanel = new JPanel(new GridLayout(positions.size(), 1));
        for (Position p : positions) {
            if (p.getQuantity() > 0) {
                positionsPanel.add((new GUIPosition(p)).getPanel());
            }
        }
        bottomSP.add(positionsPanel);

        sp.add(bottomSP);
    }

    public void updateAccounts() {
        bCB.removeAllItems();
        accMap = ComboBoxGenerator.getAccMap(person, true);
        for (String a : ComboBoxGenerator.getAccKeys(accMap)) {
            bCB.addItem(a);
        }

        update();
    }
}
