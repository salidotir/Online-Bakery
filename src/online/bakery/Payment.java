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
    private String trackingCode; 
    private final Date date;
    private final BigDecimal paymentAmount;          // payment amount is calculated in BigDecimal
    private String discreption;                      // any discreption for payment
    private PaymentStatus paymentStatus;
    private PaymentType paymentType;
    
    // constructor for creating payment object the first time for ordeing
    public Payment(Date paymentDate, BigDecimal paymentAmount, String paymentDesciption, PaymentType paymentType) {
        this.date = paymentDate;
        this.paymentAmount = paymentAmount;
        this.discreption = paymentDesciption;
        
        this.paymentId = atomicInteger.incrementAndGet();
        
        this.paymentStatus = PaymentStatus.IN_PROGRESS;
        this.paymentType = paymentType;
        
        
        this.trackingCode = TrackingCode.randomDecimalString(24);
    }
    
    // private constructor for chargeWallet 
    private Payment(BigDecimal amount, Date date, String discreption) {
        this.paymentId = atomicInteger.incrementAndGet();
        this.date = date;
        this.paymentAmount = amount;
        this.discreption = discreption;
        this.paymentStatus = PaymentStatus.IN_PROGRESS;
        this.paymentType = PaymentType.PAY_ONLINE;
        
        this.trackingCode = TrackingCode.randomDecimalString(24);
    }

    /**
     * @return the paymentId
     */
    public int getPaymentId() {
        return paymentId;
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

    public String getPaymentInformation() {
        String s;
        s = "**Payment information**\n" +
                "payment id: " + this.paymentId + "\n" +
                "amount: " + this.paymentAmount + "\n" +
                "date: " + this.date.toString() + "\n" +
                "discreption: " + this.discreption + "\n" +
                "payment type: " + this.paymentType.toString() + "\n" +
                "payment status: " + this.paymentStatus.toString() + "\n" + 
                "____________________________\n";
        return s;
    }
    
    public static Payment chargeWallet(Account account, String discreption) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How much do you want to charge your wallet: ");
        BigDecimal num = scan.nextBigDecimal();
        //scan.close();
        
        Payment pay = new Payment(num, new Date(), discreption);
        if(Payment.payOnline(pay) == true) {
            account.getWallet().addAmount(num);
            return pay;
        }
        return null;
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
    
    public boolean pay(Payment pay, Account account) {
        if(pay.getPaymentType() == PaymentType.FROM_WALLET) {
            return this.payFromWallet(pay, account);
        }
        else if(pay.getPaymentType() == PaymentType.PAY_ONLINE) {
            return this.payOnline(pay);
        }
        pay.setPaymentStatus(PaymentStatus.UNSUCCESSFUL);
        return false;
    }
    
    private static boolean payFromWallet(Payment pay, Account account) {
        int res = account.getWallet().getAmount().compareTo(pay.paymentAmount);
        // res == 0 -> both are equal
        // res == 1 -> wallet > pay amount
        // res == -1 -> pay amount > wallet
        if(res == 0 || res == 1) {
            account.getWallet().subtractAmount(pay.paymentAmount);
            pay.setPaymentStatus(PaymentStatus.SUCCESSFUL);
            return true;
        }
        else {
            pay.setPaymentStatus(PaymentStatus.UNSUCCESSFUL);
            System.out.println("There is not enough money in the wallet.\n");
            return false;
        }
    }
    
    private static boolean payOnline(Payment pay) {
        pay.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        System.out.println("Paid for the order online.\n");
        return true;
    }

    /**
     * @return the trackingCode
     */
    public String getTrackingCode() {
        return trackingCode;
    }

    /**
     * @param trackingCode the trackingCode to set
     */
    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
    
}
