package GUI;

import Objects.Person;
import Objects.Position;

import javax.swing.*;
import java.util.ArrayList;

public class GUIStockTrade extends JPanel {
    JPanel panel;
    JSplitPane sp;

    JButton buyBtn, sellBtn;
    JTextField stockNameText;
    JTextField stockSymbolText;
    JTextField requestAmountText;



    JComboBox<String> accountCB;

    public GUIStockTrade(Person person) {
        ArrayList<Position> positions = person.getSecurityAccount().getPositions();

        panel = new JPanel();
        JLabel userLabel = new JLabel("Enter The stock name: ");
        userLabel.setBounds(100, 200, 150, 25);
        panel.add(userLabel);

        JLabel symbolLabel = new JLabel("Enter The stock symbol: ");
        symbolLabel.setBounds(100, 230, 150, 25);
        panel.add(symbolLabel);

        JLabel amountLabel = new JLabel("Enter the amount: ");
        amountLabel.setBounds(100, 260, 150, 25);
        panel.add(amountLabel);

        stockNameText = new JTextField(20);
        stockNameText.setBounds(300, 200, 165, 25);
        panel.add(stockNameText);

        stockSymbolText = new JTextField(20);
        stockSymbolText.setBounds(300, 230, 165, 25);
        panel.add(stockSymbolText);

        requestAmountText = new JTextField(20);
        requestAmountText.setBounds(300, 260, 165, 25);
        panel.add(requestAmountText);

        panel.setLayout(null);
        buyBtn = new JButton("BUY");
        buyBtn.setBounds(180, 310, 80, 40);

        sellBtn = new JButton("SELL");
        sellBtn.setBounds(270,310,80, 40);



        sellBtn.setVisible(true);
        buyBtn.setVisible(true);

        panel.add(buyBtn);
        panel.add(sellBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}
