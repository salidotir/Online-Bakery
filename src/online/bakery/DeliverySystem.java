/*
 * Class with singletone pattern to control delivery of orders
 * and assigne orders to employees.
 */
package online.bakery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author salidotir
 */
public class DeliverySystem {
    private static DeliverySystem deliverySystem = null;
    
    // map -> order, order priority, list of employees, vehicle
    // default -> order priorority of all orders is set to 0.
    private Map<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> orderEmployeeMap;       // map order <-> list of employees
    private List<Order> orderQueue;                                                          // list of orders to be assigned employees
    private List<Vehicle> vehicles;
    
    private DeliverySystem() {
        this.orderQueue = Admin.getInstance().getOrders();
        this.vehicles = Admin.getInstance().getVehicles();
        this.orderEmployeeMap = Admin.getInstance().getOrderEmployeeMap();
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
    
    // ** steps to do for assigning employees a job **
    // get a list of free employees

    // assign employees to order
    // check if the delivery addresses of orders, are in the same e.g street, venue, etc. or not.
    // if they are close enough && sum of totalGram of orders <= 5KG -> assign the orders to a same employee to be delivered
    
    // assign a vehicle
    // if sum of totalGram of orders/order < 5KG && distance < 10KM -> vehicle : MOTOR
    // if sum of totalGram of orders/order < 10KG                   -> vehicle : CAR
    // if sum of totalGram of orders/order > 10KG                   -> vehicle : TRUCK
    
    // ** for now, just assign each order, the first free employee **
    public boolean assignEmployeesToOrder() {
        for (Order ord : DeliverySystem.getDeliverySystem().orderQueue) {
            List<Employee> lst = new ArrayList<>();
            Employee e = Admin.getInstance().getFirstFreeEmployee();
            lst.add(e);
            Vehicle v = Admin.getInstance().getFirstFreeVehicle();
            // set isbusy of employees & vehicle true
            // add new order-delivery item to database
            Admin.getInstance().addItemToOrderEmployeeMap(new Pair(ord, new Integer(0)), new Pair(lst, v));
            // update DeliverySystem map
            DeliverySystem.getDeliverySystem().orderEmployeeMap = Admin.getInstance().getOrderEmployeeMap();
        }
        
        return true;
    }
    
    // all orders are sent to a queue and then are assigned an employee & vehicle to be delivered.
    public boolean addOrderToOrderQueue(Order order) {
        this.orderQueue.add(order);
        return true;
    }

    /**
     * @return the orderEmployeeMap
     */
    public Map<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> getOrderEmployeeMap() {
        return orderEmployeeMap;
    }

    /**
     * @param OrderEmployeeMap the orderEmployeeMap to set
     */
    public boolean setOrderEmployeeMap(Map<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> OrderEmployeeMap) {
        orderEmployeeMap = OrderEmployeeMap;
        return true;
    }

    /**
     * @return the orderQueue
     */
    public List<Order> getOrderQueue() {
        return orderQueue;
    }

    /**
     * @param aOrderQueue the orderQueue to set
     */
    public boolean setOrderQueue(List<Order> aOrderQueue) {
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
        String res = "______ Delivery System Informations ______";
        for (Map.Entry<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> entry : orderEmployeeMap.entrySet()) {
            res += "\n         ____________________________ \n";
            res += entry.getKey().getKey().getOrderInformation();
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
