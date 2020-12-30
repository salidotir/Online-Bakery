/*
 * DBMS using singletone pattern
 */
package online.bakery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salidtoir
 */
public class DBMS {
    
    private static DBMS dbms = new DBMS();
    
    private Map<String,String> usernamePasswordTable = new HashMap<String,String>(); 
    
    private List<Bakery> bakeries;
    private List<Person> bakers;
    private List<Customer> customers;
    private List<Employee> employees;
    
    private List<Order> orders;
    private List<Note> notes;
    private List<Payment> payments;
    private List<DeliveryInformation> deliveryInformations;
    
    private DBMS() {
        this.customers = new ArrayList<Customer>();
        this.employees = new ArrayList<Employee>();
        this.bakeries = new ArrayList<Bakery>();
        this.bakers = new ArrayList<Person>();  
        this.orders = new ArrayList<Order>();
    }
    
    public static DBMS getDBMS() {
        return DBMS.dbms;
    }
    
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    // functions to get orders and payments of a specific customer

    public static List<Order> getCustomerOrders(Customer customer) {
        List<Order> result = new ArrayList<Order>();
        for(Order order:DBMS.getDBMS().orders) {
            if(order.getCustomerId() == customer.getID()) {
                result.add(order);
            }
        }
        return result;
    }
    
    public static List<Payment> getCustomerPayments(Customer customer) {
        List<Payment> result = new ArrayList<Payment>();
        for(Payment payment:DBMS.getDBMS().payments) {
            if(payment.getCustomerId() == customer.getID()) {
                result.add(payment);
            }
        }
        return result;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    // functions to edit informations in bakeries, bakers, customers & employee
    
    // edit bakery information -> gets all fileds again and set them all again
    public static boolean editBakery(int id, String name, String discreption, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for(int i= 0; i < DBMS.getDBMS().bakeries.size(); i++) {
            if(DBMS.getDBMS().bakeries.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            return false;
        }
        
        // change fields
        DBMS.getDBMS().bakeries.get(index).setName(name);
        DBMS.getDBMS().bakeries.get(index).setDescription(discreption);
        DBMS.getDBMS().bakeries.get(index).setFirstname(firstName);
        DBMS.getDBMS().bakeries.get(index).setLastname(lastName);
        DBMS.getDBMS().bakeries.get(index).setAddress(address);
        DBMS.getDBMS().bakeries.get(index).setContactNo(contactNo);
        return true;
    }
    
    // edit baker information
    public static boolean editBaker(int id, String discreption, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for(int i= 0; i < DBMS.getDBMS().bakers.size(); i++) {
            if(DBMS.getDBMS().bakers.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            return false;
        }
        
        // change fields
        DBMS.getDBMS().bakers.get(index).setDescription(discreption);
        DBMS.getDBMS().bakers.get(index).setFirstname(firstName);
        DBMS.getDBMS().bakers.get(index).setLastname(lastName);
        DBMS.getDBMS().bakers.get(index).setAddress(address);
        DBMS.getDBMS().bakers.get(index).setContactNo(contactNo);
        return true;
    }
    
    // edit customer information
    public static boolean editCustomer(int id, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for(int i= 0; i < DBMS.getDBMS().customers.size(); i++) {
            if(DBMS.getDBMS().customers.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            return false;
        }
        
        // change fields
        DBMS.getDBMS().customers.get(index).setFirstname(firstName);
        DBMS.getDBMS().customers.get(index).setLastname(lastName);
        DBMS.getDBMS().customers.get(index).setAddress(address);
        DBMS.getDBMS().customers.get(index).setContactNo(contactNo);
        return true;
    }
    
    // edit employee information
    public static boolean editEmployee(int id, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for(int i= 0; i < DBMS.getDBMS().employees.size(); i++) {
            if(DBMS.getDBMS().employees.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            return false;
        }
        
        // change fields
        DBMS.getDBMS().employees.get(index).setFirstname(firstName);
        DBMS.getDBMS().employees.get(index).setLastname(lastName);
        DBMS.getDBMS().employees.get(index).setAddress(address);
        DBMS.getDBMS().employees.get(index).setContactNo(contactNo);
        return true;
    }
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    // functions to change some fields later in lists
    
    // set order status for a specific order in order
    public static boolean setOrderStatus(Order order, OrderStatus orderStatus) {
        int index = DBMS.getDBMS().orders.indexOf(order);
        DBMS.getDBMS().orders.get(index).setOrderStatus(orderStatus);
        return true;
    }
    
    // set actual delivery time for a specific order in delivery information list
    public static boolean setActualDeliveryTime(Order order, Date actualDeliveryTime) {
        for(DeliveryInformation deliveryInformation : DBMS.getDBMS().deliveryInformations) {
            if(order.getOrderId() == deliveryInformation.getOrderId()) {
                deliveryInformation.setDeliveryTime(actualDeliveryTime);
                return true;
            }
        }
        return false;
    }
    
    // set payment status for a specific order in payments list
    public static boolean setPaymentStatus(Order order, PaymentStatus paymentStatus) {
        for(Payment payment : DBMS.getDBMS().payments) {
            if(order.getOrderId() == payment.getOrderId()) {
                payment.setPaymentStatus(paymentStatus);
                return true;
            }
        }
        return false;
    }
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    
    // search for notes of a specific order
    public Note getOrderNote(Order order) {
        for(Note note : DBMS.getDBMS().notes) {
            if(order.getOrderId() == note.getOrderId()) {
                return note;
            }
        }
        return null;
    }
    
    // search for delivery information of a specific order
    public DeliveryInformation getOrderDeliverInformation(Order order) {
        for(DeliveryInformation deliveryInformation : DBMS.getDBMS().deliveryInformations) {
            if(order.getOrderId() == deliveryInformation.getOrderId()) {
                return deliveryInformation;
            }
        }
        return null;
    }
    
    // search for payment information of a specific order
    public Payment getOrderPaymentInformation(Order order) {
        for(Payment payment : DBMS.getDBMS().payments) {
            if(order.getOrderId() == payment.getOrderId()) {
                return payment;
            }
        }
        return null;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    
    // search a bakery in its name
    public static List<Bakery> searchBakeryByName(String subString) {
        List<Bakery> result = new ArrayList<Bakery>();
        for(Bakery bakery:DBMS.getDBMS().bakeries) {
            if(bakery.getName().toLowerCase().contains(subString.toLowerCase())) {
                result.add(bakery);
            }
        }
        return result;
    }
    
    // search a baker in its firstname & lastname
    public static List<Person> searchBakerByName(String subString) {
        List<Person> result = new ArrayList<Person>();
        for(Person baker:DBMS.getDBMS().bakers) {
            if(baker.getFirstname().toLowerCase().contains(subString.toLowerCase()) || baker.getLastname().toLowerCase().contains(subString.toLowerCase())) {
                result.add(baker);
            }
        }
        return result;
    }
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    
    public static boolean signUp(String username, String password){
        if (DBMS.getDBMS().usernamePasswordTable.containsKey(username))
            return false;
        else{
            DBMS.getDBMS().usernamePasswordTable.put(username, password);
            return true;
        }
    }
    
    public static boolean login(String username, String password){
        String pass = DBMS.getDBMS().usernamePasswordTable.get(username);
        if (pass != null){
            boolean result = pass.equals(password);
            return result;
        }else{
            return false;
        }
    }
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    
    public static List<Bakery> getListOfBakeries() {
        return DBMS.getDBMS().bakeries;
    }
    
    public static boolean addBakery(Bakery bakery) {
        DBMS.getDBMS().bakeries.add(bakery);
        return true;
    }
    
    public static boolean removeBakery(Bakery bakery) {
        DBMS.getDBMS().bakeries.remove(bakery);
        return true;
    }
    
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    
    public static List<Person> getListOfBakers() {
        return DBMS.getDBMS().bakers;
    }
    
    public static boolean addBaker(Person baker) {
        DBMS.getDBMS().bakers.add(baker);
        return true;
    }
    
    public static boolean removeBaker(Person baker) {
        DBMS.getDBMS().bakers.remove(baker);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    // we do not delete any customer, just make it inactive.
    public static List<Customer> getListOfCustomers() {
        return DBMS.getDBMS().customers;
    }
    
    public static boolean addCustome(Customer customer) {
        if (DBMS.getDBMS().customers.contains(customer) == true) {
            int index = DBMS.getDBMS().customers.indexOf(customer);
            DBMS.getDBMS().customers.get(index).setActiveness("Active");
            return true;
        }
        DBMS.getDBMS().customers.add(customer);
        return true;
    }
    
    public static boolean removeCustomer(Customer customer) {
        int index = DBMS.getDBMS().customers.indexOf(customer);
        DBMS.getDBMS().customers.get(index).setActiveness("Inactive");
        return true;
    }    
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    
    public static List<Employee> getListOfEmployees() {
        return DBMS.getDBMS().employees;
    }
    
    public static boolean addBaker(Employee employee) {
        DBMS.getDBMS().employees.add(employee);
        return true;
    }
    
    public static boolean removeBaker(Employee employee) {
        DBMS.getDBMS().employees.remove(employee);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    
    public static List<Order> getListOfOrders() {
        return DBMS.getDBMS().orders;
    }
    
    public static boolean addOrder(Order order) {
        DBMS.getDBMS().orders.add(order);
        return true;
    }
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    
    public static List<Note> getListOfNotes() {
        return DBMS.getDBMS().notes;
    }
    
    public static boolean addNote(Note note) {
        DBMS.getDBMS().notes.add(note);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    
    public static List<Payment> getListOfPayments() {
        return DBMS.getDBMS().payments;
    }
    
    public static boolean addPayment(Payment payment) {
        DBMS.getDBMS().payments.add(payment);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    
    public static List<DeliveryInformation> getListOfDeliveryInformations() {
        return DBMS.getDBMS().deliveryInformations;
    }
    
    public static boolean addDeliveryInformation(DeliveryInformation deliveryInformation) {
        DBMS.getDBMS().deliveryInformations.add(deliveryInformation);
        return true;
    }
 
}
