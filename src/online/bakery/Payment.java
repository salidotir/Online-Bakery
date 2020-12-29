/*
 * Create a new payment object.
 */
package online.bakery;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Payment {
    int paymentId ;
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    private final String paymentCustomerId;
    private final Date paymentDate;
    private final int paymentAmount;          // payment amount is calculated in 'Rials' -> int
    private String paymentDiscreption;        // any discreption for payment
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    
    // constructor for creating payment object the first time for ordeing
    public Payment(String paymentCustomerId, Date paymentDate, int paymentAmount, String paymentDesciption, PaymentStatus paymentStatus, PaymentType paymentType) {
        this.paymentCustomerId = paymentCustomerId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentDiscreption = "";
        this.paymentId = atomicInteger.incrementAndGet();
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
    }

    /**
     * @return the paymentId
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * @return the paymentCustomerId
     */
    public String getPaymentCustomerId() {
        return paymentCustomerId;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @return the paymentAmount
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @return the paymentDiscreption
     */
    public String getPaymentDiscreption() {
        return paymentDiscreption;
    }

    /**
     * @param paymentDiscreption the paymentDiscreption to set
     */
    public void setPaymentDiscreption(String paymentDiscreption) {
        this.paymentDiscreption = paymentDiscreption;
    }

    /**
     * @return the paymentStatus
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the paymentType
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    

}
