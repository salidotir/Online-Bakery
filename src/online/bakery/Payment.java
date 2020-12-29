/*
 * Create a new payment object.
 */
package online.bakery;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Payment {
    int paymentId ;
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    private final int customerId;
    private final int orderId;
    private final Date date;
    private final BigDecimal paymentAmount;          // payment amount is calculated in BigDecimal
    private String discreption;                      // any discreption for payment
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    
    // constructor for creating payment object the first time for ordeing
    public Payment(int paymentOrderId, int paymentCustomerId, Date paymentDate, BigDecimal paymentAmount, String paymentDesciption, PaymentStatus paymentStatus, PaymentType paymentType) {
        this.orderId = paymentOrderId;
        this.customerId = paymentCustomerId;
        this.date = paymentDate;
        this.paymentAmount = paymentAmount;
        this.discreption = paymentDesciption;
        
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
    public int getCustomerId() {
        return this.customerId;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }
    
    /**
     * @return the paymentDate
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

    /**
     * @return the paymentDiscreption
     */
    public String getPaymentDiscreption() {
        return this.discreption;
    }

    /**
     * @param paymentDiscreption the paymentDiscreption to set
     */
    public void setPaymentDiscreption(String paymentDiscreption) {
        this.discreption = paymentDiscreption;
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
