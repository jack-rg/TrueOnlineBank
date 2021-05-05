package Util;

import Types.CurrencyType;

/**
 * CurrencyConverter is a helper class to convert money.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public class CurrencyConverter {
    /**
     * Converts the amount from one currencyType to another.
     *
     * @param convertFrom type to convert from
     * @param convertTo   type to convert to
     * @param amount      amount to convert
     * @return correctly converted amount
     */
    public static double execute(CurrencyType convertFrom, CurrencyType convertTo, double amount) {
        switch (convertFrom) {
            case EUR:
                return EURTo(convertTo, amount);
            case INR:
                return INRTo(convertTo, amount);
            case GBP:
                return GBPTo(convertTo, amount);
            default:
                return USDTo(convertTo, amount);
        }
    }

    private static double EURTo(CurrencyType convertTo, double amount) {
        switch (convertTo) {
            case EUR:
                return amount;
            case INR:
                return amount / CurrencyType.EUR.getValue() * CurrencyType.INR.getValue();
            case GBP:
                return amount / CurrencyType.EUR.getValue() * CurrencyType.GBP.getValue();
            default:
                return amount / CurrencyType.EUR.getValue();
        }
    }

    private static double INRTo(CurrencyType convertTo, double amount) {
        switch (convertTo) {
            case EUR:
                return amount / CurrencyType.INR.getValue() * CurrencyType.EUR.getValue();
            case INR:
                return amount;
            case GBP:
                return amount / CurrencyType.INR.getValue() * CurrencyType.GBP.getValue();
            default:
                return amount / CurrencyType.INR.getValue();
        }
    }

    private static double GBPTo(CurrencyType convertTo, double amount) {
        switch (convertTo) {
            case EUR:
                return amount / CurrencyType.GBP.getValue() * CurrencyType.EUR.getValue();
            case INR:
                return amount / CurrencyType.GBP.getValue() * CurrencyType.INR.getValue();
            case GBP:
                return amount;
            default:
                return amount / CurrencyType.GBP.getValue();
        }
    }

    private static double USDTo(CurrencyType convertTo, double amount) {
        switch (convertTo) {
            case EUR:
                return amount * CurrencyType.EUR.getValue();
            case INR:
                return amount * CurrencyType.INR.getValue();
            case GBP:
                return amount * CurrencyType.GBP.getValue();
            default:
                return amount;
        }
    }
}
