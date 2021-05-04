package Util;

import Objects.*;
import Types.AccountState;
import Types.AccountType;
import Types.CurrencyType;
import Types.TransactionType;

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
                    account.getAccountID(), account.getBalance(), account.getStatus(), account.getCurrencyType());
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

    public static void writeLoan(Loan loan, Person person) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/loanLog.txt";

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            String transactionFormatter = "%s | %f | %f | %s\n";
            out.printf(transactionFormatter, person.getUserID(), loan.getLoanAmount(),
                    loan.getPaidAmount(), loan.getDueDate().toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writePositions(SecurityAccount securityAccount) {
        Map<String, StockPosition> positionMap = securityAccount.getStockName2position();
        if (positionMap == null || positionMap.size() == 0) {
            return;
        }
        String file = Paths.get("").toAbsolutePath() + "/Logs/positionLog.txt";
        // if already exists, simply delete one and create one.
        File deleteFile = new File(file);
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            String transactionFormatter = "%s | %s | %s | %s | %f | %s \n";
            for (String stockName : positionMap.keySet()) {
                StockPosition position = positionMap.get(stockName);
                out.printf(transactionFormatter,
                        position.getPositionStockName(),
                        position.getPositionStockSymbol(),
                        position.getMktValue(),
                        position.getTotalCost(),
                        position.getUnrealizedPL(),
                        position.getUnrealizedPLRate()
                );
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStockOrder(StockOrder stockOrder) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockOrderRecord.txt";
        // if already exists, simply delete one and create one.
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            String transactionFormatter = "%s | %s | %s | %s | %f \n";

//            public void displayOrder(){
//                System.out.println( "Symbol: " + stock.getSymbol() +
//                        " Name: " + stock.getName() +
//                        " Filled quantity: " + quantity +
//                        " orderPlacedtime: " + this.orderPlacedTime
//                );
//            }
            out.printf(transactionFormatter,
                    stockOrder.getOrderPlacedTime(),
                    stockOrder.getOrderStockName(),
                    stockOrder.getOrderStockSymbol(),
                    stockOrder.getQuantity(),
                    stockOrder.getOrderLastPrice()
            );

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Account loadAccount(String accountID) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + accountID + " | ")) {
                    String[] account = line.split(" \\| ");
                    String userID = account[2];
                    String accountType = account[3];
                    double value = Double.valueOf(account[5]);
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
                        return new Checking(AccountType.CHECKING, accountID, userID, cType, accountState, value);
                    } else {
                        return new Saving(AccountType.SAVING, accountID, userID, cType, accountState, value);
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static void loadAccounts(Person person) {
        String userID = person.getUserID();
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
                    double value = Double.valueOf(account[5]);
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

        person.setAccounts(accounts);
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

                        Loan loan = loadLoan(userID);
                        if (loan != null) {
                            return new User(username, password, userID, loan);
                        } else {
                            return new User(username, password, userID);
                        }

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

    private static Loan loadLoan(String userID) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/loanLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(userID + " | ")) {
                    String[] loan = line.split(" \\| ");
                    double loanAmount = Double.parseDouble(loan[1]);
                    double paidAmount = Double.parseDouble(loan[2]);
                    Date dueDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(loan[3]);
                    return new Loan(loanAmount, paidAmount, dueDate);
                }
            }
        } catch (IOException | ParseException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static boolean updatePerson(Person person, String newName, String newPassword) {
        String file;

        if (person instanceof Manager) {
            file = Paths.get("").toAbsolutePath() + "/Logs/managerLog.txt";
        } else {
            file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            String userID = person.getUserID();

            String name, password;
            name = (newName != null) ? newName : person.getUserName();
            password = (newPassword != null) ? newPassword : person.getPassword();

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + newName + " | ")) {
                    return false;
                } else if (line.contains("| " + userID)) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
                    traceFile.add(dtf.format(LocalDateTime.now()) + " | " + name + " | " + password + " | " + userID);
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

        return true;
    }


    public static Manager isManager(String username, String password) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/managerLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + username + " |") && line.contains("| " + password + " |")) {
                    String[] user = line.split(" \\| ");
                    String userID = user[4];
                    return new Manager(username, password, userID);
                } else {
                    traceFile.add(line);
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static void updateAccount(Account account) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<String>();
            String line;

            String accountID = account.getAccountID();
            System.out.println(account.getBalance());

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + accountID + " | ")) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd | HH:mm:ss");
                    traceFile.add(dtf.format(LocalDateTime.now()) + " | " + account.getUserID() + " | " + account.getAccountType()
                            + " | " + account.getAccountID() + " | " + account.getBalance() + " | " + account.getStatus()
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
