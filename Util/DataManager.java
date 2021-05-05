package Util;

import Objects.*;
import Types.*;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * DataManager is a helper class to read and write to text files
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class DataManager {
    public static void writeUser(User user) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");
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
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");

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
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");
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
            String transactionFormatter = "%s | %f | %f | %s | %s\n";
            out.printf(transactionFormatter, person.getUserID(), loan.getLoanAmount(),
                    loan.getPaidAmount(), loan.getLoanDate().toString(), loan.getStatus());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStockOrder(StockOrder stockOrder) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockOrderLog.txt";

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            String transactionFormatter = "%s | %s | %s | %f | %s | %s \n";

            out.printf(transactionFormatter,
                    stockOrder.getOrderDate(),
                    stockOrder.getStock().getSymbol(),
                    stockOrder.getQuantity(),
                    stockOrder.getPricePerStock(),
                    stockOrder.getOrderType(),
                    stockOrder.getAccountId()
            );

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<StockOrder> loadStockOrders(String accountID) {
        ArrayList<StockOrder> orders = new ArrayList<>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockOrderLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + accountID)) {
                    String[] order = line.split(" \\| ");
                    LocalDateTime orderDate = LocalDateTime.parse(order[0]);
                    Stock stock = loadStock(order[1]);
                    int quantity = Integer.parseInt(order[2]);
                    double price = Double.parseDouble(order[3]);
                    StockOrderType orderType;
                    if (order[4].equals("BUY")) {
                        orderType = StockOrderType.BUY;
                    } else {
                        orderType = StockOrderType.SELL;
                    }
                    accountID = order[5];
                    orders.add(new StockOrder(orderDate, stock, quantity, price, orderType, accountID));
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return orders;
    }

    public static ArrayList<Position> loadPositions(String accountID) {
        ArrayList<Position> positions = new ArrayList<>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/positionLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + accountID)) {
                    String[] position = line.split(" \\| ");
                    Stock stock = loadStock(position[0]);
                    double totalCost = Double.parseDouble(position[1]);
                    int quantity = Integer.parseInt(position[2]);

                    positions.add(new Position(stock, quantity, totalCost, accountID));
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return positions;
    }

    public static void writePosition(Position position) {
        boolean updated = false;

        String file = Paths.get("").toAbsolutePath() + "/Logs/positionLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + position.getAccountID()) && line.contains(position.getStock().getSymbol() + " | ")) {
                    traceFile.add(position.getStock().getSymbol() + " | " + position.getTotalCost() + " | "
                            + position.getQuantity() + " | " + position.getAccountID());
                    updated = true;
                } else {
                    traceFile.add(line);
                }
            }

            if (!updated) {
                traceFile.add(position.getStock().getSymbol() + " | " + position.getTotalCost() + " | "
                        + position.getQuantity() + " | " + position.getAccountID());
            }

            FileOutputStream fileOut = new FileOutputStream(file);

            for (String output : traceFile) {
                fileOut.write((output + "\n").getBytes());
            }

            fileOut.flush();
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    private static Stock loadStock(String symbol) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(" | " + symbol + " | ")) {
                    String[] account = line.split(" \\| ");
                    String stockName = account[0];
                    double currentValue = Double.parseDouble(account[2]);

                    return new Stock(stockName, symbol, currentValue);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
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
                    double value = Double.parseDouble(account[5]);
                    String status = account[6];
                    String currencyType = account[7];

                    Status accountStatus;
                    if (status.equals("ACTIVE")) {
                        accountStatus = Status.ACTIVE;
                    } else {
                        accountStatus = Status.INACTIVE;
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
                        return new Checking(AccountType.CHECKING, accountID, userID, cType, accountStatus, value);
                    } else {
                        return new Saving(AccountType.SAVING, accountID, userID, cType, accountStatus, value);
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Account> loadAccounts(String userID) {
        ArrayList<Account> accounts = new ArrayList<>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/accountLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + userID + " |")) {
                    String[] account = line.split(" \\| ");
                    String accountType = account[3];
                    String accountID = account[4];
                    double value = Double.parseDouble(account[5]);
                    String status = account[6];
                    String currencyType = account[7];

                    Status accountStatus;
                    if (status.equals("ACTIVE")) {
                        accountStatus = Status.ACTIVE;
                    } else {
                        accountStatus = Status.INACTIVE;
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
                        accounts.add(new Checking(AccountType.CHECKING, accountID, userID, cType, accountStatus, value));
                    } else if (accountType.equals("Savings")) {
                        accounts.add(new Saving(AccountType.SAVING, accountID, userID, cType, accountStatus, value));
                    } else {
                        accounts.add(new Security(AccountType.SECURITY, accountID, userID, cType, accountStatus, value));
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return accounts;
    }

    public static ArrayList<Transaction> loadTodaysTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/transactionLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] transaction = line.split(" \\| ");
                String date = transaction[0];
                String name = transaction[4];
                String type = transaction[6];
                double amount = Double.parseDouble(transaction[5]);
                if (java.time.LocalDate.now().toString().equals(date)) {
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
        return transactions;
    }


    public static void loadTransactions(Account account) {
        ArrayList<Transaction> transactions = new ArrayList<>();
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
                    double amount = Double.parseDouble(transaction[5]);

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


    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(" \\| ");
                String userName = userInfo[2];
                String password = userInfo[3];
                String userID = userInfo[4];

                users.add(new User(userName, password, userID));

            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return users;
    }


    public static User userExists(String username, String password, boolean register) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/userLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<>();
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

    public static Loan loadLoan(String userID) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/loanLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(userID + " | ") && line.contains(" | ACTIVE")) {
                    String[] loan = line.split(" \\| ");
                    double loanAmount = Double.parseDouble(loan[1]);
                    double paidAmount = Double.parseDouble(loan[2]);
                    LocalDateTime dueDate = LocalDateTime.parse(loan[3]);

                    Status loanStatus;
                    if (loan[4].equals("ACTIVE")) {
                        loanStatus = Status.ACTIVE;
                    } else {
                        loanStatus = Status.INACTIVE;
                    }

                    return new Loan(loanAmount, paidAmount, dueDate, userID, loanStatus);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateLoan(Loan loan) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/loanLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<>();
            String line;

            String userID = loan.getUserID();

            while ((line = br.readLine()) != null) {
                if (line.contains(userID + " | ")) {
                    traceFile.add(userID + " | " + loan.getLoanAmount() + " | " + loan.getPaidAmount()
                            + " | " + loan.getLoanDate() + " | " + loan.getStatus());
                } else {
                    traceFile.add(line);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(file);

            for (String output : traceFile) {
                fileOut.write((output + "\n").getBytes());
            }

            fileOut.flush();
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
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
            ArrayList<String> traceFile = new ArrayList<>();
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
                fileOut.write((output + "\n").getBytes());
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
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("| " + username + " |") && line.contains("| " + password + " |")) {
                    String[] user = line.split(" \\| ");
                    String userID = user[4];
                    return new Manager(username, password, userID);
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
            ArrayList<String> traceFile = new ArrayList<>();
            String line;

            String accountID = account.getAccountID();

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
                fileOut.write((output + "\n").getBytes());
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
            ArrayList<String> traceFile = new ArrayList<>();
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
                fileOut.write((output + "\n").getBytes());
            }

            fileOut.flush();
            fileOut.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public static void writeStock(Stock stock) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockLog.txt";
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            String stockFormatter = "%s | %s | %.2f%n";
            out.printf(stockFormatter, stock.getName(), stock.getSymbol(), stock.getPrice());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean existStock(Stock stock) {
        ArrayList<Stock> stocks = loadStocks();
        for (Stock s : stocks) {
            if (stock.getName().equals(s.getName()) || stock.getSymbol().equals(s.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Stock> loadStocks() {
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] stockInfo = line.split(" \\| ");
                String stockName = stockInfo[0];
                String stockTicker = stockInfo[1];
                double stockPrice = Double.parseDouble(stockInfo[2]);
                stocks.add(new Stock(stockName, stockTicker, stockPrice));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return stocks;

    }


    public static void updateStock(Stock stock) {
        String file = Paths.get("").toAbsolutePath() + "/Logs/stockLog.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<String> traceFile = new ArrayList<>();
            String line;

            String stockID = stock.getSymbol();

            while ((line = br.readLine()) != null) {
                if (line.contains(stockID + " | ")) {
                    traceFile.add(stock.getName() + " | " + stockID + " | " + stock.getPrice());
                } else {
                    traceFile.add(line);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(file);

            for (String output : traceFile) {
                fileOut.write((output + "\n").getBytes());
            }

            fileOut.flush();
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}