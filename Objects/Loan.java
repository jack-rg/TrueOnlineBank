package Objects;

import Types.Status;
import Util.DataManager;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Loan represents a loan that the user could have.
 *
 * @author rachelpeng
 * @author jackgiunta
 * @author yuanwei
 * @since May 4, 2021
 */
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

    public Status getStatus() { return status; }

    /**
     * Allows the user to make a payment on the loan.
     *
     * @param paymentAmount the amount of the payment.
     */
    public void makePayment(double paymentAmount) {
        paidAmount += paymentAmount;

        if (paidAmount >= loanAmount) {
            status = Status.INACTIVE;
        }

        DataManager.updateLoan(this);
    }

    /**
     * Determines the loan balance.
     *
     * @return the loan balance
     */
    public double getLoanBalance() {
        LocalDateTime today = LocalDateTime.now();
        Duration duration = Duration.between(loanDate, today);
        long days = duration.toDays();

        double interestToDate = (loanAmount * INTEREST_RATE) * days;

        return loanAmount + interestToDate - paidAmount;
    }
}
