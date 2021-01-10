/*
 * Class with singletone pattern to control delivery of orders
 * and assigne orders to employees.
 */
package online.bakery;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.util.Pair;

/**
 *
 * @author salidotir
 */
public class DeliverySystem {
    private static DeliverySystem deliverySystem = null;
    
    // map -> order, order priority, list of employees, vehicle
    // default -> order priorority of all orders is set to 0.
    // map order <-> list of employees -> stores in database
    //private Map<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> orderEmployeeMap;
    private List<Pair<Order, Integer>> orderQueue;                                                          // list of orders to be assigned employees
    private List<Vehicle> vehicles;
    
    private DeliverySystem() {
        this.orderQueue = new ArrayList<>();
        this.vehicles = Admin.getInstance().getVehicles();
//        this.orderEmployeeMap = Admin.getInstance().getOrderEmployeeMap();
    }
    
    public static DeliverySystem getDeliverySystem() {
        if (deliverySystem == null) {
            synchronized (DeliverySystem.class) {
                if (deliverySystem == null) {
                    deliverySystem = new DeliverySystem();                  
                }
            }
        }
        return deliverySystem;
    }
    
    // check if requested delivery time is after now
    boolean hasNotPassedDeliveryTime(Date requested) {
        Calendar calendar1= Calendar.getInstance();
        Calendar calendar2= Calendar.getInstance();
        
        Date date1 = new Date();
        calendar1.set(date1.getYear(), date1.getMonth(), date1.getDay());

        calendar2.set(requested.getYear(), requested.getMonth(), requested.getDay());
        
        return calendar1.before(calendar2) || calendar1.equals(calendar2);
    }
    
    // sort OrderQueue based on order priority
    List<Pair<Order, Integer>> sortOrderQueueBasedOnPriority() {
        orderQueue.sort(new Comparator<Pair<Order, Integer>>() {
            @Override public int compare(Pair<Order, Integer> p1, Pair<Order, Integer> p2) 
                { 
                    return p1.getValue() - p2.getValue(); 
                } 
        });
        return orderQueue;
    }
    
    // ** steps to do for assigning employees a job **
    // if there is less than a day for the order to be delivered, increase its priority
    
    // get a list of free employees

    // assign employees to order
    // check if the delivery addresses of orders, are in the same e.g street, venue, etc. or not.
    // if they are close enough && sum of totalGram of orders <= 5KG -> assign the orders to a same employee to be delivered
    
    // assign a vehicle
    // if sum of totalGram of orders/order < 5KG && distance < 10KM -> vehicle : MOTOR
    // if sum of totalGram of orders/order < 10KG                   -> vehicle : CAR
    // if sum of totalGram of orders/order > 10KG                   -> vehicle : TRUCK
    
    // ** for now, just assign each order, the first free employee **
    // if customers has to wait to reciev order, it returns false
    public boolean assignEmployeesToOrder() {
        boolean test = true;
        List<Pair<Order, Integer>> shippedOrders = new ArrayList<>();
        
        // first sort orderQueue
        orderQueue = sortOrderQueueBasedOnPriority();
        
        for (Pair<Order, Integer> p : DeliverySystem.getDeliverySystem().orderQueue) {
            List<Employee> lst = new ArrayList<>();
            Employee e = Admin.getInstance().getFirstFreeEmployee();
            if (e != null) {
                lst.add(e);
                Vehicle v = Admin.getInstance().getFirstFreeVehicle();
                if (v != null){
                    // check if it has not passed from the delivery time
                    if(hasNotPassedDeliveryTime(p.getKey().getExpectedDeliveryTime()) == true) {
                        // notify employee to ship the order
                        e.recievOrder(p.getKey());
                        // set isbusy of employees & vehicle true
                        // add new order-delivery item to database
                        Admin.getInstance().addItemToOrderEmployeeMap(new Pair(p.getKey().getOrderId(), p.getValue()), new Pair(lst, v));
                    }
                    else {
                        // it has passed the time of delivery
                        p.getKey().cancelByDelivery();
                        // just to remove it from order queue later
                        shippedOrders.add(p);
                        test = false;
                    }
                }
                else{
                    test = false;
                }
            }
            else{
                test = false;
            }
            
            // if order is goint to be delivered now, remove it from list of orders to be delivered.
            if (test == true) {
                shippedOrders.add(p);
            }
            // if order has to wait , we increase its priority to be sure it will be assigned an employee
            else if(test == false) {
                int index = orderQueue.indexOf(p);
                orderQueue.set(index, new Pair(p.getKey(), p.getValue()+1));
            }
            // notify order if it can be delivered now or not.
            p.getKey().isAvailableToShip(test);
        }
        
        // remove shippedOrders from Order Queue list
        for (Pair p:shippedOrders) {
            deliverySystem.orderQueue.remove(p);
        }
        return test;
    }
    

    // all orders are sent to a queue and then are assigned an employee & vehicle to be delivered.
    public boolean addOrderToOrderQueue(Order order) {
        this.orderQueue.add(new Pair(order, new Integer(0)));
        return true;
    }

    /**
     * @return the orderEmployeeMap got from dbms
     */
    public Map<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> getOrderEmployeeMap() {
        return Admin.getInstance().getOrderEmployeeMap();
    }

    /**
     * @param OrderEmployeeMap the orderEmployeeMap to set
     */
//    public boolean setOrderEmployeeMap(Map<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> OrderEmployeeMap) {
//        orderEmployeeMap = OrderEmployeeMap;
//        return true;
//    }

    /**
     * @return the orderQueue
     */
    public List<Pair<Order, Integer>> getOrderQueue() {
        return orderQueue;
    }

    /**
     * @param aOrderQueue the orderQueue to set
     */
    public boolean setOrderQueue(List<Pair<Order, Integer>> aOrderQueue) {
        orderQueue = aOrderQueue;
        return true;
    }

    /**
     * @return the vehicles
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @param aVehicles the vehicles to set
     */
    public boolean setVehicles(List<Vehicle> aVehicles) {
        vehicles = aVehicles;
        return true;
    }
    
    public String toStringGetOrderEmployeeMap() {
        
        Map<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> orderEmployeeMap = getOrderEmployeeMap();
        String res = "______ Delivery System Informations ______";
        for (Map.Entry<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> entry : orderEmployeeMap.entrySet()) {
            res += "\n         ____________________________ \n";
            AbstractMap.SimpleEntry result = Admin.getInstance().getOrderByID(entry.getKey().getKey());
            if ((boolean)result.getKey()){
                Order order = (Order)result.getValue();
                res += order.getOrderInformation();
            }else
                res += "No such Order with this OrderID exits";

            //res += entry.getKey().getKey().toString() + "\n";
            res += "\norder priority: " + entry.getKey().getValue().toString() + "\n";
            
            res += "\nEmployees: \n";
            int size = entry.getValue().getKey().size();
            for(int i = 0; i < size; i++) {
                res += entry.getValue().getKey().get(i).getProfile() + "\n";
            }
            
            res += "\nvehicle: \n" + entry.getValue().getValue().getVehicleInformation();
        }               
        res += "\n__________________________________________\n\n";
        return res;
    }
}
