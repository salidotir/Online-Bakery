/*
 * Create a new payment object.
 */
package online.bakery;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Payment {
    int paymentId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private final int customerId;
    private final int orderId;
    private final Date date;
    private final BigDecimal paymentAmount;          // payment amount is calculated in BigDecimal
    private String discreption;                      // any discreption for payment
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    
    // constructor for creating payment object the first time for ordeing
    public Payment(int paymentOrderId, int paymentCustomerId, Date paymentDate, BigDecimal paymentAmount, String paymentDesciption, PaymentType paymentType) {
        this.orderId = paymentOrderId;
        this.customerId = paymentCustomerId;
        this.date = paymentDate;
        this.paymentAmount = paymentAmount;
        this.discreption = paymentDesciption;
        
        this.paymentId = atomicInteger.incrementAndGet();
        
        this.paymentStatus = PaymentStatus.IN_PROGRESS;
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
    
    public static PaymentType howToPay() {
        int index = 1;
        for (PaymentType PT: PaymentType.values())
        {
            System.out.println(index + " : "+ PT);
            index += 1;
        }
        Scanner scan = new Scanner(System.in);
        System.out.printf("Please Enter how you want to pay for the order: ");
        int num = scan.nextInt();
        //scan.close();
        return PaymentType.values()[num-1];
    }
    
    public boolean pay(Payment pay, Customer customer) {
        if(pay.getPaymentType() == PaymentType.FROM_WALLET) {
            return this.payFromWallet(pay, customer);
        }
        else if(pay.getPaymentType() == PaymentType.PAY_ONLINE) {
            return this.payOnline(pay);
        }
        pay.setPaymentStatus(PaymentStatus.UNSUCCESSFUL);
        return false;
    }
    
    private boolean payFromWallet(Payment pay, Customer customer) {
        int res = customer.getWallet().getAmount().compareTo(pay.paymentAmount);
        // res == 0 -> both are equal
        // res == 1 -> wallet > pay amount
        // res == -1 -> pay amount > wallet
        if(res == 0 || res == 1) {
            customer.getWallet().subtractAmount(pay.paymentAmount);
            pay.setPaymentStatus(PaymentStatus.SUCCESSFUL);
            return true;
        }
        else {
            pay.setPaymentStatus(PaymentStatus.UNSUCCESSFUL);
            System.out.println("There is not enough money in the wallet.\n");
            return false;
        }
    }
    
    private boolean payOnline(Payment pay) {
        pay.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        System.out.println("Paid for the order online.\n");
        return true;
    }
    
}
