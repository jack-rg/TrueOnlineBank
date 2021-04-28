package Interfaces;

public interface TransactionInterface {
    public boolean withdrawal(float amount, String transactionName);

    public void deposit(float amount, String transactionName);
}
