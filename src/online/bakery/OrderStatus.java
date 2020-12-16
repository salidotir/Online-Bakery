/*
 * Shows order status.
 */
package online.bakery;

/**
 *
 * @author salidotir
 */
public enum OrderStatus {
    PENDING,            // can be canceled
    IN_PROGRESS,        // it is becoming ready
    DONE,               // it is ready but not delivered
    DELIVERED,          // it is delivered to customer
    CANCELED            // customer can cancel the order in PENDING mode
}