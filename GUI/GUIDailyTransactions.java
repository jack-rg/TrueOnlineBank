package GUI;

import Objects.Transaction;
import Util.DataManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUIDailyTransactions creates the GUI that shows daily transactions to the manager.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class GUIDailyTransactions {

    JPanel panel;
    GUIHome home;

    public GUIDailyTransactions(GUIHome home) {
        this.home = home;

        panel = new JPanel();
        update();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void update() {
        panel.removeAll();

        ArrayList<Transaction> transactions = DataManager.loadTodaysTransactions();
        panel.setLayout(new GridLayout(transactions.size(), 1));
        for (Transaction t : transactions) {
            GUITransaction trans = new GUITransaction(t);
            panel.add(trans.getPanel());
        }
    }
}