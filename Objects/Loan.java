package Objects;

import java.util.Date;

public class Loan {
    double loanAmount;
    double paidAmount;
    Date dueDate;

    public Loan(double loanAmount, double paidAmount, Date dueDate) {
        this.loanAmount = loanAmount;
        this.paidAmount = paidAmount;
        this.dueDate = dueDate;
    }
}
