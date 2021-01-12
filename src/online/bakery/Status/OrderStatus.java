/*
 * Shows order status.
 */
package online.bakery.Status;

/**
 *
 * @author salidotir
 */
public enum OrderStatus {
    ORDERING_BY_CUSTOMER,   // customer can edit order
    FINALIZED_BY_CUSTOMER,  // no more edit in the order is allowed and add to site waiting for suggestions from bakers
    CANCELED_BY_CUSTOMER,   // customer can cancel the order in FINALIZED mode
    PENDING_CHOSEN_BAKER,   // from the list choose one baker and send confirmation to him/her
    CANCELED_BY_BAKER,      // baker can cancel an order and does not accept it
    ACCEPTED,               // order accepted by baker
    SET_DELIVERY,           // assign an emploee to the order
    PAYED,                  // pay cost of order
    IN_PROGRESS,            // it is becoming ready
    DONE,                   // it is ready but not delivered
    RUINED_BY_EMPLOYEE,     // the order was ruind by employee
    WAITING,                // it must wait for the delivery
    ON_THE_WAY,             // Order is now on the way to customer
    CANCELED_BY_DELIVERY,   // there is shipping available to deliver the order
    DELIVERED               // it is delivered to customer  
}
