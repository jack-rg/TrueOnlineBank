package GUI;

import Objects.Person;
import Util.FileReaderUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GUIInvestmentHome extends JPanel {
    JTabbedPane tabbedPane;

    public GUIInvestmentHome(Person person) throws IOException {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        GUIAccountsOverview accountsOverview = new GUIAccountsOverview(person);
        tabbedPane.addTab("Trade", accountsOverview.getPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        // TODO: 2021/5/3 newAccount -> TradePanel
        //GUINewAccount newAccount = new GUINewAccount(person, accountsOverview, tabbedPane);
        GUIStockPositions stockPositions = new GUIStockPositions();
        tabbedPane.addTab("Position View", stockPositions.getPanel());

        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        GUIDeposit deposit = new GUIDeposit(person, accountsOverview);
        deposit.getSubmitBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
            }
        });

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
