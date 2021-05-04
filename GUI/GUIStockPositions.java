package GUI;

import Objects.Person;
import Objects.Position;
import Types.TransactionType;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class GUIStockPositions extends JPanel{
    JPanel panel;
    JSplitPane sp;

    public GUIStockPositions(Person person) {
        ArrayList<Position> positions = person.getSecurityAccount().getPositions();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setResizeWeight(0.1);

        JPanel topPanel = new JPanel();
        topPanel.setBounds(25, 25, 400, 500);

        JPanel titlePanel = new JPanel(new GridLayout(1, 4));
        titlePanel.add(new JLabel("Stock"));
        titlePanel.add(new JLabel("Quantity"));
        titlePanel.add(new JLabel("Total Cost"));
        titlePanel.add(new JLabel("Unrealized Profit / Loss"));
        sp.add(titlePanel);

        JPanel positionsPanel = new JPanel(new GridLayout(positions.size(), 1));
        for (Position p : positions) {
            positionsPanel.add((new GUIPosition(p)).getPanel());
        }
        sp.add(positionsPanel);

        panel.add(sp, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public JPanel getPanel(){
        return panel;
    }
}
