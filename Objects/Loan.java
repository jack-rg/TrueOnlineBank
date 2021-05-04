package Objects;

import Types.Status;
import Util.DataManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class Loan {
    public static final double INTEREST_RATE = 0.03;

    double loanAmount;
    double paidAmount;
    LocalDateTime loanDate;
    String userID;
    Status status;

    public Loan(double loanAmount, double paidAmount, LocalDateTime loanDate, String userID, Status status) {
        this.loanAmount = loanAmount;
        this.paidAmount = paidAmount;
        this.loanDate = loanDate;
        this.userID = userID;
        this.status = status;
    }

    public double getLoanAmount() { return loanAmount; }

    public double getPaidAmount() { return paidAmount; }

    public LocalDateTime getLoanDate() { return loanDate; }

    public String getUserID() { return userID; }

    public void makePayment(double paymentAmount) {
        paidAmount += paymentAmount;

        if (paidAmount >= loanAmount) {
            status = Status.INACTIVE;
        }

        DataManager.updateLoan(this);
    }

    public double getLoanBalance() {
        LocalDateTime today = LocalDateTime.now();
        Duration duration = Duration.between(loanDate, today);
        long days = duration.toDays();

        double interestToDate = (loanAmount * INTEREST_RATE) * days;

        return loanAmount + interestToDate - paidAmount;
    }

    public Status getStatus() {
        return status;
    }
}
