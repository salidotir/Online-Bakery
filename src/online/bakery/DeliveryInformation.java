/*
 * Store delivery information.
 */
package online.bakery;

import java.util.Date;

/**
 *
 * @author salidotir
 */
public class DeliveryInformation {
    private OrderStatus orderStatus;
    
    private final String deliveryId;
    private final String orderId;
    private String employeeId;
    
    private String deliveryAddress;
    private Date deliveryTime;                      // date & time the customer wants to recieve order.
                                                    // deliveruTime is not final because customer can change the delivery time later if accepted by the seller.
    private Date actualDeliveryTime;                // actual date & time the order is delivered.
    
    // constructor for making the delivery information at first time ordering
    public DeliveryInformation(String deliveryId, String orderId, Date deliveryTime) {
        this.orderStatus = OrderStatus.PENDING;     // orderStatus the first time creating the delivery information is PENDING.
        
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.deliveryTime = deliveryTime;
        
        this.employeeId = null;                     // maybe no need to deliver the order.
        this.deliveryAddress = null;                // maybe no need to deliver the order.
        this.actualDeliveryTime = null;             // filled after order is delivered.
    }
    
    // constructor for making the delivery information after reading from database
    public DeliveryInformation(OrderStatus orderStatus, String deliveryId, String orderId, String employeeId, String deliveryAddress, Date deliveryTime, Date actualDeliveryTime) {
        this.orderStatus = orderStatus;
        
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.deliveryTime = deliveryTime;
        
        this.employeeId = employeeId;
        this.deliveryAddress = deliveryAddress;
        this.actualDeliveryTime = actualDeliveryTime;
    }

    /**
     * @return the orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
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
}
