/*
 * Store delivery information.
 */
package online.bakery;

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
    private int orderId;
    private int employeeId;
    
    private String deliveryAddress;
    private Date deliveryTime;                      // date & time the customer wants to recieve order.
                                                    // deliveruTime is not final because customer can change the delivery time later if accepted by the seller.
    private Date actualDeliveryTime;                // actual date & time the order is delivered.
    
    // constructor for making the delivery information at first time ordering
    public DeliveryInformation(int orderId, int employeeId, String deliveryAddress, Date deliveryTime) {
        
        this.deliveryId = atomicInteger.incrementAndGet();
        this.orderId = orderId;
        this.deliveryTime = deliveryTime;
        
        this.employeeId = employeeId;                       // maybe no need to deliver the order.
        this.deliveryAddress = deliveryAddress;             // maybe no need to deliver the order.
        this.actualDeliveryTime = null;                     // filled after order is delivered.
    }
    

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
                "delivery id: " + this.deliveryId + "\n" +
                "order id: " + this.orderId + "\n" +
                "employee id: " + this.employeeId + "\n" +
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
        
    public static DeliveryInformation createNewDelivery(int orderID, int employeeId, String deliveryAddress, Date deliveryTime) {
        return new DeliveryInformation(orderID, employeeId, deliveryAddress, deliveryTime);
    }
}
