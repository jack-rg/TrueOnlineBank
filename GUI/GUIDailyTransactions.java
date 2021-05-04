package GUI;

import Objects.Account;
import Objects.Person;
import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIDailyTransactions {

    JPanel panel;
    GUIHome home;

    public GUIDailyTransactions() {
        panel = new JPanel();
        reload();

    }

    public JPanel getPanel(GUIAccountsHome accountsHome) {
        return panel;
    }

    public void reload(){
        panel.removeAll();
        ArrayList<Transaction> transactions = DataManager.loadTodaysTransactions();
        panel.setLayout(new GridLayout(transactions.size(), 1));
        for (Transaction t : transactions) {
            GUITransaction trans = new GUITransaction(t);
            panel.add(trans.getPanel());
        }
    }








}
