package Interfaces;

import Types.CurrencyType;

public interface TransactionInterface {
    public boolean withdraw(double funds, String name);

    public void deposit(double funds, String name, CurrencyType cType);
}
