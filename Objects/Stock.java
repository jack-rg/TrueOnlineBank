package Objects;

public class Stock {
    private String ticker;
    private double value;

    public Stock(String ticker, double value) {
        this.ticker = ticker;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double newValue) {
        value = newValue;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String newTicker) {
        ticker = newTicker;
    }
}