/*
 * Class with singletone pattern to control delivery of orders
 * and assigne orders to employees.
 */
package online.bakery;

import java.util.AbstractMap;
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
    // map order <-> list of employees -> stores in database
    //private Map<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> orderEmployeeMap;
    private List<Order> orderQueue;                                                          // list of orders to be assigned employees
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
    // if customers has to wait to reciev order, it returns false
    public boolean assignEmployeesToOrder() {
        boolean test = true;
        List<Order> shippedOrders = new ArrayList<>();
        
        for (Order ord : DeliverySystem.getDeliverySystem().orderQueue) {
            List<Employee> lst = new ArrayList<>();
            Employee e = Admin.getInstance().getFirstFreeEmployee();
            if (e != null) {
                lst.add(e);
                Vehicle v = Admin.getInstance().getFirstFreeVehicle();
                if (v != null){
                    // notify employee to ship the order
                    e.recievOrder(ord);
                    // set isbusy of employees & vehicle true
                    // add new order-delivery item to database
                    Admin.getInstance().addItemToOrderEmployeeMap(new Pair(ord.getOrderId(), new Integer(0)), new Pair(lst, v));
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
                shippedOrders.add(ord);
            }
            // notify order if it can be delivered now or not.
            ord.isAvailableToShip(test);
        }
        
        // remove shippedOrders from Order Queue list
        for (Order o:shippedOrders) {
            deliverySystem.orderQueue.remove(o);
        }
        
        return test;
    }
    

    // all orders are sent to a queue and then are assigned an employee & vehicle to be delivered.
    public boolean addOrderToOrderQueue(Order order) {
        this.orderQueue.add(order);
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
