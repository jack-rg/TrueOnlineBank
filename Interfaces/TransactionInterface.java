package Interfaces;

import Types.CurrencyType;

/**
 * TransactionInterface ensures that objects implementing this interface has the transaction functionality.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public interface TransactionInterface {
    boolean withdraw(double funds, String name, boolean chargeFee);

    void deposit(double funds, String name, CurrencyType cType, boolean chargeFee);
}
