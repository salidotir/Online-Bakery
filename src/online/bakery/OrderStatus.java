/*
 * Shows order status.
 */
package online.bakery;

/**
 *
 * @author salidotir
 */
public enum OrderStatus {
    PENDING,                // can be canceled
    IN_PROGRESS,            // it is becoming ready
    DONE,                   // it is ready but not delivered
    DELIVERED,              // it is delivered to customer
    CANCELED_BY_CUSTOMER,   // customer can cancel the order in PENDING mode
    CANCELED_BY_BAKER       // baker can cancel an order and does not accept it
}
