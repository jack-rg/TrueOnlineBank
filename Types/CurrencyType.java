package Types;

/**
 * CurrencyType enum enumerates the types of currencies and their values.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
public enum CurrencyType {
    USD(1F), // US Dollar
    EUR(0.82780F), // Euro
    INR(74.6229F), // Rupee
    GBP(0.71954F); // Pound

    private final float value;

    CurrencyType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}