/*
 * Store delivery information.
 * To create a delivery information object, call createNewDelivery() function
 */
package online.bakery;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class DeliveryInformation {
    
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private int deliveryId;

    private BigDecimal transferPrice;
    private String deliveryAddress;
    private Date deliveryTime;                      // date & time the customer wants to recieve order.
                                                    // deliveruTime is not final because customer can change the delivery time later if accepted by the seller.
    private Date actualDeliveryTime;                // actual date & time the order is delivered.
    

    private DeliveryInformation(String deliveryAddress, Date deliveryTime, BigDecimal transferPrice) {
        
        this.deliveryId = atomicInteger.incrementAndGet();
        
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.actualDeliveryTime = null;                     // filled after order is delivered.
        this.transferPrice = transferPrice;
    }

    public static DeliveryInformation createNewDelivery(String deliveryAddress, Date deliveryTime) {                
        return new DeliveryInformation(deliveryAddress, deliveryTime, guessTransferPrice(deliveryAddress));
    }

    // guess the price of transfer based on address
    private static BigDecimal guessTransferPrice(String deliveryAddress) {
        // currently it returns a random number between 50-100
        int r = (int)(Math.random() * (100 - 50)) + 50;
        return BigDecimal.valueOf(r);
    }

    /**
     * @return the deliveryAddress
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @param deliveryAddress the deliveryAddress to set
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * @return the deliveryTime
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @param deliveryTime the deliveryTime to set
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * @return the actualDeliveryTime
     */
    public Date getActualDeliveryTime() {
        return actualDeliveryTime;
    }

    /**
     * @param actualDeliveryTime the actualDeliveryTime to set
     */
    public void setActualDeliveryTime(Date actualDeliveryTime) {
        this.actualDeliveryTime = actualDeliveryTime;
    }
    
    public String getDeliveryInformation() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s;
        s = "**Delivery information**\n" + 
                "delivery id: " + this.getDeliveryId() + "\n" +
                "delivery address: " + this.deliveryAddress + "\n" +
                "delivery time: " + formatter.format(this.deliveryTime) + "\n";
        if(this.actualDeliveryTime == null) {
            s += "actual delivery time: 'not delivered yet'" + "\n";
        }
        else {
            s += "actual delivery time: " + formatter.format(this.actualDeliveryTime) + "\n";
        }
        s += "____________________________\n";
        return s;
    }

    /**
     * @return the deliveryId
     */
    public int getDeliveryId() {
        return deliveryId;
    }

    /**
     * @param deliveryId the deliveryId to set
     */
    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }
    
    /**
     * @return the transferPrice
     */
    public BigDecimal getTransferPrice() {
        return transferPrice;
    }
}
