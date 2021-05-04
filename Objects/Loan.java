package Objects;

import Util.DataManager;

import java.util.Date;

public class Loan {
    double loanAmount;
    double paidAmount;
    Date dueDate;
    String userID;

    public Loan(double loanAmount, double paidAmount, Date dueDate, String userID) {
        this.loanAmount = loanAmount;
        this.paidAmount = paidAmount;
        this.dueDate = dueDate;
        this.userID = userID;
    }

    public double getLoanAmount() { return loanAmount; }

    public double getPaidAmount() { return paidAmount; }

    public Date getDueDate() { return dueDate; }

    public String getUserID() { return userID; }

    public void makePayment(double paymentAmount) {
        paidAmount += paymentAmount;
        DataManager.updateLoan(this);
    }
}
