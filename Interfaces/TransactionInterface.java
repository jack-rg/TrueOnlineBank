package Interfaces;

import Types.CurrencyType;

public interface TransactionInterface {
    public boolean withdrawal(float funds, String name);

    public void deposit(float funds, String name, CurrencyType cType);
}
