package Util;

import Objects.*;
import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;
import Types.TransactionType;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataManager {
    public static void writeUser(User user) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
            String accountFormatter = "%s | %s | %s | %s \n";
            out.printf(accountFormatter, dtf.format(LocalDateTime.now()), user.getUserName(), user.getPassword(),
                    user.getUserID());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAccount(Account account) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
            String accountFormatter = "%s | %s | %s | %s | %f | %s | %s \n";
            out.printf(accountFormatter, dtf.format(LocalDateTime.now()), account.getUserID(), account.getAccountType(),
                    account.getAccountID(), account.getValue(), account.getStatus(), account.getCurrencyType());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTransaction(Transaction transaction, String userID, String accountID) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/transactionLog.txt";

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
            String transactionFormatter = "%s | %s | %s | %s | %f | %s\n";
            out.printf(transactionFormatter, dtf.format(LocalDateTime.now()), userID, accountID,
                    transaction.getName(), transaction.getAmount(), transaction.getType());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAccounts(User user) {
        String userID = user.getUserID();
        ArrayList<Account> accounts = new ArrayList<Account>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + userID + " |")) {
                    String[] account = line.split(" \\| ");
                    String accountType = account[3];
                    String accountID = account[4];
                    float value = Float.valueOf(account[5]);
                    String status = account[6];
                    String currencyType = account[7];

                    AccountState accountState;
                    if (status.equals("ACTIVE")) {
                        accountState = AccountState.ACTIVE;
                    } else {
                        accountState = AccountState.INACTIVE;
                    }

                    CurrencyType cType;
                    switch (currencyType) {
                        case "EUR":
                            cType = CurrencyType.EUR;
                            break;
                        case "INR":
                            cType = CurrencyType.INR;
                            break;
                        case "GDP":
                            cType = CurrencyType.GBP;
                            break;
                        default:
                            cType = CurrencyType.USD;
                            break;
                    }

                    if (accountType.equals("Checking")) {
                        accounts.add(new Checking(AccountType.CHECKING, accountID, userID, cType, accountState, value));
                    } else {
                        accounts.add(new Saving(AccountType.SAVING, accountID, userID, cType, accountState, value));
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        user.setAccounts(accounts);
    }

    public static void loadTransactions(Account account) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/transactionLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + account.getUserID() + " |") && line.contains("| " + account.getAccountID() + " |")) {
                    String[] transaction = line.split(" \\| ");
                    String name = transaction[4];
                    String type = transaction[6];
                    String date = transaction[0];
                    float amount = Float.valueOf(transaction[5]);

                    if (type.equals("WITHDRAWAL")) {
                        transactions.add(new Transaction(name, date, amount, TransactionType.WITHDRAWAL));
                    } else {
                        transactions.add(new Transaction(name, date, amount, TransactionType.DEPOSIT));
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        account.setTransactions(transactions);
    }

    public static User userExists(String username, String password, boolean register) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + username + " |") && line.contains("| " + password + " |")) {
                    if (!register) {
                        String[] user = line.split(" \\| ");
                        String userID = user[4];
                        return new User(username, password, userID);
                    } else {
                        return null;
                    }
                } else {
                    traceFile.add(line);
                }
            }

            if (register) {
                User user = new User(username, password, "U" + (traceFile.size() + 1));
                DataManager.writeUser(user);

                return user;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static void updatePerson(Person person) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            String userID = person.getUserID();

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + userID)) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
                    traceFile.add(dtf.format(LocalDateTime.now()) + " | " + person.getUserName() + " | " + person.getPassword() + " | " + userID);
                } else {
                    traceFile.add(line);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(file);

            for (String output : traceFile) {
                fileOut.write((output + "\n").toString().getBytes());
            }

            fileOut.flush();
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void updateAccount(Account account) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            String accountID = account.getAccountID();
            System.out.println(account.getValue());

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + accountID + " | ")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
                    traceFile.add(dtf.format(LocalDateTime.now()) + " | " + account.getUserID() + " | " + account.getAccountType()
                    + " | " + account.getAccountID() + " | " + account.getValue() + " | " +  account.getStatus()
                            + " | " + account.getCurrencyType());
                } else {
                    traceFile.add(line);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(file);

            for (String output : traceFile) {
                fileOut.write((output + "\n").toString().getBytes());
            }

            fileOut.flush();
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void deactivateAccount(Account account) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + account.getUserID() + " |") && line.contains("| " + account.getAccountID() + " |")) {
                    String[] accountInfo = line.split(" \\| ");
                    accountInfo[accountInfo.length - 2] = "INACTIVE";
                    String replaced = String.join(" | ", accountInfo);
                    traceFile.add(replaced);
                } else {
                    traceFile.add(line);
                }
            }
            br.close();

            FileOutputStream fileOut = new FileOutputStream(file);

            for (String output : traceFile) {
                fileOut.write((output + "\n").toString().getBytes());
            }

            fileOut.flush();
            fileOut.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}