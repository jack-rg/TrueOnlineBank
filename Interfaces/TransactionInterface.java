package Interfaces;

import Types.CurrencyType;

public interface TransactionInterface {
    boolean withdraw(double funds, String name, boolean chargeFee);

    void deposit(double funds, String name, CurrencyType cType, boolean chargeFee);
}
