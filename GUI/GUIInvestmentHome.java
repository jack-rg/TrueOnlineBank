package GUI;

import Objects.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GUIInvestmentHome extends JPanel {
    JTabbedPane tabbedPane;

    public GUIInvestmentHome(Person person) {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        GUIStockPositions accountsOverview = new GUIStockPositions();
        tabbedPane.addTab("Trade", accountsOverview.getPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        // TODO: 2021/5/3 newAccount -> TradePanel
        GUIStockPositions stockPositions = new GUIStockPositions();
        tabbedPane.addTab("Position View", stockPositions.getPanel());

        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        GUIStockPositions deposit = new GUIStockPositions();
        tabbedPane.addTab("Bill History", deposit.getPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        add(tabbedPane);
    }

    public JTabbedPane getTabbedPane(){
        return this.tabbedPane;
    }

//    public Component getPanel() {
//        return this.panel;
//    }


//    ArrayList<String> stringList = FileReaderUtil.readUserPosFileByLine(person.getUserName());
//        System.out.println(stringList.size());
//    String[] stringArr = new String[stringList.size()];
//    int index = 0;
//        for(String s : stringList){
//        stringArr[index++] = s;
//    }

}
