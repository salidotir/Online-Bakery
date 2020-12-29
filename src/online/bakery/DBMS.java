/*
 * DBMS using singletone pattern
 */
package online.bakery;

import java.util.ArrayList;
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
