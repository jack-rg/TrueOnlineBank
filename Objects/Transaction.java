package Objects;

import Types.TransactionType;

public class Transaction {
    private TransactionType type;
    private String name;
    private String date;
    private float amount;

    public Transaction(String name, float amount, TransactionType type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(String name, String date, float amount, TransactionType type) {
        this(name, amount, type);
        this.date = date;
    }

    public String getName() { return name; }

    public float getAmount() { return amount; }

    public String getDate() { return date; }

    public TransactionType getType() { return type; }
}
