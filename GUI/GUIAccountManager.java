package GUI;

import Objects.Account;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIAccountManager {
    JPanel panel;
    JButton accountNameBtn;


    public GUIAccountManager(Account account) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        accountNameBtn = new JButton(account.toString());
        panel.add(accountNameBtn);


    }

    public JPanel getPanel() {
        return panel;
    }
    
    public JButton getAccountNameBtn() {
    	return accountNameBtn;
    }
    
}