package Types;

public enum CurrencyExchangeRate {
    EUR(0.82780F), // Euro
    INR(74.6229F), // Rupee
    GDP(0.71954F); // Pound

    private final float value;

    CurrencyExchangeRate(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}