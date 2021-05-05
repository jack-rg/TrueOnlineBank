package Objects;

import Types.TransactionType;

/**
 * Transaction represents a transaction that the user could have within an account
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class Transaction {
    private final TransactionType type;
    private final String name;
    private final double amount;
    private String date;

    public Transaction(String name, double amount, TransactionType type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(String name, String date, double amount, TransactionType type) {
        this(name, amount, type);
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
