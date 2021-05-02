package Util;

import Types.CurrencyType;

public class CurrencyConverter {
    public static float execute(CurrencyType convertFrom, CurrencyType convertTo, float amount) {
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

    private static float EURTo(CurrencyType convertTo, float amount) {
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

    private static float INRTo(CurrencyType convertTo, float amount) {
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

    private static float GBPTo(CurrencyType convertTo, float amount) {
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

    private static float USDTo(CurrencyType convertTo, float amount) {
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
