package Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public abstract class Person {
    private String userName;
    private String password;
    private String userID;
    private ArrayList<Account> accounts;
    private Portfolio portfolio;

    public Person(String userName, String password, String userID) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        accounts = new ArrayList<Account>();
        portfolio = new Portfolio();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String newName) {
        userName = newName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPass) {
        password = newPass;
    }

    public boolean authenPassword(String checkPass) {
        return password.equals(checkPass);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String newID) {
        userID = newID;
    }

    public ArrayList<Account> getRawAccounts() {
        return accounts;
    }

    public void addNewAccount(Account newAccount) {
        newAccount.addToAccountLog();
        accounts.add(newAccount);
    }

    public Account getAccountByID(String getID) {
        for (Account a : accounts) {
            if (a.getAccountID().equals(getID)) {
                return a;
            }
        }
        return null;
    }

    public double getTotalAssets() {
        double sum = 0;
        for (Account a : accounts) {
            sum += a.getValue();
        }
        return sum;
    }

    public void loadAccounts() {
        accounts = new ArrayList<Account>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + userID + " |") && line.contains("| " + "ACTIVE")) {
                    String[] account = line.split(" \\| ");
                    String accountType = account[3];
                    String accountID = account[4];
                    String userID = account[2];
                    float value = Float.valueOf(account[5]);

                    if (accountType.equals("Checking")) {
                        accounts.add(new Checking("Checking", accountID, userID, value));
                    } else {
                        accounts.add(new Saving("Saving", accountID, userID, value));
                    }
                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}