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

    public double getLoanAmount() { return loanAmount; }

    public double getPaidAmount() { return paidAmount; }

    public Date getDueDate() { return dueDate; }
}
