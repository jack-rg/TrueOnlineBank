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

    public GUIStockPositions(Person person) {
        System.out.println(person.getSecurityAccount());
        ArrayList<Position> positions = person.getSecurityAccount().getPositions();

        panel = new JPanel();
        panel.setLayout(new GridLayout(positions.size(), 3));

        for (Position p : positions) {
            JLabel stock = new JLabel(p.getStock().getSymbol());
            panel.add(stock);

            JLabel quantity = new JLabel(p.getQuantity() + "");
            panel.add(quantity);

            JLabel totalCost = new JLabel(p.getTotalCost() + "");
            panel.add(totalCost);
        }
    }

    public JPanel getPanel(){
        return panel;
    }
}
