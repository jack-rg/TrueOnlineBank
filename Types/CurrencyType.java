package Types;

public enum CurrencyType {
    USD(1F), // US Dollar
    EUR(0.82780F), // Euro
    INR(74.6229F), // Rupee
    GDP(0.71954F); // Pound

    private final float value;

    CurrencyType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}