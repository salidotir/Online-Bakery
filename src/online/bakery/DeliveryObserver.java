/*
 * DeliveryObserver implements observer interface to contain a list of pbservers and implement methods for addition and removal of subscribers from the list.
 */
package online.bakery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salidotir
 */
public class DeliveryObserver implements Subject{

    private List<Observer> subscribers = new ArrayList<>();
    
    @Override
    public void addSubscriber(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeSubscriber(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(Observer observer: subscribers) {
            // notify observer whenever order status changed.
            observer.update("Order status in Delivery inforation related to the order the observer is observing changed!");
        }
    }
    
}
