/*
 * Observer needs to know when there's an update from their subject.
 * Their behavior based on this update will differ between classes.
 */
package online.bakery;

/**
 *
 * @author salidotir
 */
public interface Observer {
    public void update(String message);
}
