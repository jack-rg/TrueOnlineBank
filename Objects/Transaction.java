package Objects;

import Types.TransactionType;

public class Transaction {
    private TransactionType type;
    private String name;
    private String date;
    private double amount;

    public Transaction(String name, double amount, TransactionType type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(String name, String date, double amount, TransactionType type) {
        this(name, amount, type);
        this.date = date;
    }

    public String getName() { return name; }

    public double getAmount() { return amount; }

    public String getDate() { return date; }

    public TransactionType getType() { return type; }
}
