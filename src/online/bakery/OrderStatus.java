/*
 * Shows order status.
 */
package online.bakery;

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
    DELIVERED               // it is delivered to customer  
}
