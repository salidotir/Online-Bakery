/*
 * Delivery is a subject of observation.
 * Customers and sellers are observing it.
 * Subject interface provides methods to add and remove subscribers/observers and notify them of a change of order status in delivery.
 */
package online.bakery;

/**
 *
 * @author salidotir
 */
public interface Subject {
    public void addSubscriber(Observer observer);
    public void removeSubscriber(Observer observer);
    public void notifySubscribers();
}
