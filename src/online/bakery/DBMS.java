/*
 * DBMS using singletone pattern
 */
package online.bakery;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;
import online.bakery.sweets.Rate;
import online.bakery.sweets.TypeOfSweets;
import online.bakery.sweets.Sweets;

/**
 * @author salidtoir
 */
public class DBMS {

    private static DBMS dbms = new DBMS();

    /**
     * Map of usernames to their password hashes.
     */
    private Map<String, String> usernamePasswordTable;

    /**
     * Map of usernames to their salts.
     */
    private Map<String, Integer> userSaltMap;

    private List<Bakery> bakeries;
    private List<Person> bakers;
    private List<Customer> customers;

    private List<Admin> admins;
    private static int addOnceAdmin = 1;

    private List<Order> orders;
    private Map<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> orderEmployeeMap;
    private List<Vehicle> vehicles;
    private List<Employee> employees;

    private List<String> securityQuestions;
    private Map<String, List<String>> usernameAnswersTable;

    private Map<Integer, List<Pair<Sweets, Integer>>> bakerReadySweetMap;
    private Map<Integer, List<Sweets>> bakerOrderSweetMap;
    private Map<Integer, List<Pair<BirthdayItems, Integer>>> bakerBirthdayItemsMap;
    private List<Discount> discounts;

    private DBMS() {
        this.customers = new ArrayList<Customer>();
        this.employees = new ArrayList<Employee>();
        this.bakeries = new ArrayList<Bakery>();
        this.bakers = new ArrayList<Person>();
        this.orders = new ArrayList<Order>();
        this.usernamePasswordTable = new HashMap<String, String>();
        this.userSaltMap = new HashMap<String, Integer>();
        this.securityQuestions = new ArrayList<String>();
        this.usernameAnswersTable = new HashMap<String, List<String>>();
        this.admins = new ArrayList<Admin>();
        this.vehicles = new ArrayList<Vehicle>();
        this.orderEmployeeMap = new HashMap<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>>();

        this.bakerReadySweetMap = new HashMap<Integer, List<Pair<Sweets, Integer>>>();
        this.bakerOrderSweetMap = new HashMap<Integer, List<Sweets>>();
        this.bakerBirthdayItemsMap = new HashMap<Integer, List<Pair<BirthdayItems, Integer>>>();
        this.discounts = new ArrayList<Discount>();
    }

    // function to give access to dbms only for admin
    public static DBMS getDBMS(Account account) {
        if (account.role == Role.ADMIN) {
            return DBMS.dbms;
        }
        return null;
    }

    // function to give access to dbms for functions in the same class DBMS
    private static DBMS getDBMS() {
        return DBMS.dbms;
    }


    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 

    /**
     * Returns a random number between 0 and 1000.
     */
    public int getRandomSalt() {
        return (int) (Math.random() * 1000);
    }

    /**
     * In real life this would probably read from a config file,
     * so you could check your code into a repo without the config file.
     */
    public String getPepper() {
        return "this is a very long random string";
    }

    public String getSimpleHash(String saltedAndPepperedPassword) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(saltedAndPepperedPassword.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    // functions for Security Questions

    public void setQuestions(List<String> questions) {
        DBMS.getDBMS().securityQuestions = questions;
    }

    public boolean setAnswer(String username, List<String> answers) {
        if (!DBMS.getDBMS().usernameAnswersTable.containsKey(username)) {
            DBMS.getDBMS().usernameAnswersTable.put(username, answers);
            return true;
        } else
            return false;
    }

    public List<String> getQuestions() {
        return DBMS.getDBMS().securityQuestions;
    }

    public List<String> getAnswers(String username) {
        return DBMS.getDBMS().usernameAnswersTable.get(username);
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    // functions to get orders and payments of a specific customer

    public List<Order> getCustomerOrders(Customer customer) {
        List<Order> result = new ArrayList<Order>();
        for (Order order : DBMS.getDBMS().orders) {
            if (order.getCustomerId() == customer.getID()) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Payment> getCustomerPayments(Customer customer) {
        List<Payment> result = new ArrayList<Payment>();
        for (Order order : DBMS.getDBMS().orders) {
            if (order.getCustomerId() == customer.getID()) {
                result.add(order.getPayment());
            }
        }
        return result;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 

    // function to find a free employee to assign a delivery to.
    public Employee getFirstFreeEmployee() {
        for (Employee employee : DBMS.getDBMS().employees) {
            if (employee.isIsBusy() == false) {
                DBMS.getDBMS().setEmployeeIsBusyTrue(employee);
                return employee;
            }
        }
        // could not find any free employee
        return null;
    }

    // function to call when an employee is assigned to deliver an order
    public boolean setEmployeeIsBusyTrue(Employee employee) {
        int index = DBMS.getDBMS().employees.indexOf(employee);
        DBMS.getDBMS().employees.get(index).setIsBusy(true);
        return true;
    }

    // function to call when an employee delivers an order -> when deliverOrder in employee is called
    public boolean setEmployeeIsBusyFalse(Employee employee) {
        int index = DBMS.getDBMS().employees.indexOf(employee);
        DBMS.getDBMS().employees.get(index).setIsBusy(false);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 
    // functions to edit informations in bakeries, bakers, customers & employee

    // edit bakery information -> gets all fileds again and set them all again
    public boolean editBakery(int id, String name, String description, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for (int i = 0; i < DBMS.getDBMS().bakeries.size(); i++) {
            if (DBMS.getDBMS().bakeries.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }

        // change fields
        DBMS.getDBMS().bakeries.get(index).setName(name);
        DBMS.getDBMS().bakeries.get(index).setDescription(description);
        DBMS.getDBMS().bakeries.get(index).setFirstname(firstName);
        DBMS.getDBMS().bakeries.get(index).setLastname(lastName);
        DBMS.getDBMS().bakeries.get(index).setAddress(address);
        DBMS.getDBMS().bakeries.get(index).setContactNo(contactNo);
        return true;
    }

    // edit baker information
    public boolean editBaker(int id, String name, String description, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for (int i = 0; i < DBMS.getDBMS().bakers.size(); i++) {
            if (DBMS.getDBMS().bakers.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }

        // change fields
        DBMS.getDBMS().bakers.get(index).setName(name);
        DBMS.getDBMS().bakers.get(index).setDescription(description);
        DBMS.getDBMS().bakers.get(index).setFirstname(firstName);
        DBMS.getDBMS().bakers.get(index).setLastname(lastName);
        DBMS.getDBMS().bakers.get(index).setAddress(address);
        DBMS.getDBMS().bakers.get(index).setContactNo(contactNo);
        return true;
    }

    // edit customer information
    public boolean editCustomer(int id, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for (int i = 0; i < DBMS.getDBMS().customers.size(); i++) {
            if (DBMS.getDBMS().customers.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
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
    public boolean editEmployee(int id, String firstName, String lastName, String address, String contactNo) {
        int index = -1;
        for (int i = 0; i < DBMS.getDBMS().employees.size(); i++) {
            if (DBMS.getDBMS().employees.get(i).getID() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
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

    // set order status delivered in list
    public boolean setOrderStatusDelivered(Order order) {
        int index = DBMS.getDBMS().orders.indexOf(order);
        DBMS.getDBMS().orders.get(index).finishOrder();
        return true;
    }

    // set actual delivery time for a specific order in delivery information list
    public boolean setActualDeliveryTime(Order order, Date actualDeliveryTime) {
        int index = DBMS.getDBMS().orders.indexOf(order);
        DBMS.getDBMS().orders.get(index).getDelivery().setActualDeliveryTime(actualDeliveryTime);
        return true;
    }

    // set payment status for a specific order in payments list
    public boolean setPaymentStatus(Order order, PaymentStatus paymentStatus) {
        for (Order ord : DBMS.getDBMS().orders) {
            if (ord.getOrderId() == order.getOrderId()) {
                ord.getPayment().setPaymentStatus(paymentStatus);
                return true;
            }
        }
        return false;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 

    // search for delivery information of a specific order
    public DeliveryInformation getOrderDeliverInformation(Order order) {
        return order.getDelivery();
    }

    // search for payment information of a specific order
    public Payment getOrderPaymentInformation(Order order) {
        return order.getPayment();
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 

    // search a bakery in its name
    public List<Bakery> searchBakeryByName(String subString) {
        List<Bakery> result = new ArrayList<Bakery>();
        for (Bakery bakery : DBMS.getDBMS().bakeries) {
            if (bakery.getName().toLowerCase().contains(subString.toLowerCase())) {
                result.add(bakery);
            }
        }
        return result;
    }

    // search a baker in its firstname & lastname
    public List<Person> searchBakerByName(String subString) {
        List<Person> result = new ArrayList<Person>();
        for (Person baker : DBMS.getDBMS().bakers) {
            if (baker.getFirstname().toLowerCase().contains(subString.toLowerCase()) || baker.getLastname().toLowerCase().contains(subString.toLowerCase())) {
                result.add(baker);
            }
        }
        return result;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 

    public boolean checkPasword(String username, String password) {
        int salt = DBMS.getDBMS().userSaltMap.get(username);
        String saltedAndPepperedPassword = password + salt + getPepper();
        String passwordHash = getSimpleHash(saltedAndPepperedPassword);
        String storedPasswordHash = DBMS.getDBMS().usernamePasswordTable.get(username);
        return passwordHash == storedPasswordHash;
    }

    public boolean hasEntry(String username, String password) {
        return DBMS.getDBMS().usernamePasswordTable.containsKey(username);
    }

    public boolean hasSalt(String username) {
        return DBMS.getDBMS().userSaltMap.containsKey(username);
    }

    public String addEntry(String username, String password) {
        int salt = getRandomSalt();
        String saltedAndPepperedPassword = password + salt + getPepper();
        String passwordHash = getSimpleHash(saltedAndPepperedPassword);
        DBMS.getDBMS().userSaltMap.put(username, salt);
        return DBMS.getDBMS().usernamePasswordTable.put(username, passwordHash);
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~ 

    public List<Bakery> getListOfBakeries() {
        return DBMS.getDBMS().bakeries;
    }

    public boolean addBakery(Bakery bakery) {
        DBMS.getDBMS().bakeries.add(bakery);
        return true;
    }

    public boolean removeBakery(Bakery bakery) {
        DBMS.getDBMS().bakeries.remove(bakery);
        return true;
    }


    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    

    public List<Person> getListOfBakers() {
        return DBMS.getDBMS().bakers;
    }

    public boolean addBaker(Person baker) {
        DBMS.getDBMS().bakers.add(baker);
        return true;
    }

    public boolean removeBaker(Person baker) {
        DBMS.getDBMS().bakers.remove(baker);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    
    // we do not delete any customer, just make it inactive.
    public List<Customer> getListOfCustomers() {
        return DBMS.getDBMS().customers;
    }

    public boolean addCustomer(Customer customer) {
        if (DBMS.getDBMS().customers.contains(customer) == true) {
            int index = DBMS.getDBMS().customers.indexOf(customer);
            DBMS.getDBMS().customers.get(index).setActiveness("Active");
            return true;
        }
        DBMS.getDBMS().customers.add(customer);
        return true;
    }

    public boolean removeCustomer(Customer customer) {
        int index = DBMS.getDBMS().customers.indexOf(customer);
        DBMS.getDBMS().customers.get(index).setActiveness("Inactive");
        DBMS.getDBMS().customers.remove(index);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    

    public List<Employee> getListOfEmployees() {
        return DBMS.getDBMS().employees;
    }

    public boolean addEmployee(Employee employee) {
        DBMS.getDBMS().employees.add(employee);
        return true;
    }

    public boolean removeEmployee(Employee employee) {
        DBMS.getDBMS().employees.remove(employee);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    

    public List<Order> getListOfOrders() {
        return DBMS.getDBMS().orders;
    }

    public boolean addOrder(Order order) {
        DBMS.getDBMS().orders.add(order);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    

    public List<Payment> getListOfPayments() {
        List<Payment> res = new ArrayList<>();
        for (Order order : DBMS.getDBMS().orders) {
            res.add(order.getPayment());
        }

        return res;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~    

    public List<DeliveryInformation> getListOfDeliveryInformations() {
        List<DeliveryInformation> res = new ArrayList<>();
        for (Order order : DBMS.getDBMS().orders) {
            res.add(order.getDelivery());
        }

        return res;
    }

    public List<Admin> getAdmins() {
        return DBMS.getDBMS().admins;
    }

    public static boolean addNewAdmin(Admin admin, String username, String password) {
        if (addOnceAdmin == 1) {
            addOnceAdmin = 0;
            DBMS.getDBMS().admins.add(admin);
            DBMS.getDBMS().addEntry(username, password);
            List<String> answers = new ArrayList<String>();
            for (String q : DBMS.getDBMS().getQuestions()) {
                Scanner scan = new Scanner(System.in);
                System.out.printf(q);
                String ans = scan.nextLine();
                answers.add(ans);
            }
            DBMS.getDBMS().setAnswer(username, answers);
            return true;
        } else
            return false;
    }


    // Delivery System

    public List<Vehicle> getVehicles() {
        return DBMS.getDBMS().vehicles;
    }

    public boolean addVehicle(Vehicle vehicle) {
        DBMS.getDBMS().vehicles.add(vehicle);
        return true;
    }

    public Vehicle getFirstFreeVehicle() {
        for (Vehicle vehicle : DBMS.getDBMS().vehicles) {
            if (vehicle.isIsBusy() == false) {
                DBMS.getDBMS().setVehicleIsBusyTrue(vehicle);
                return vehicle;
            }
        }
        // there is no free vehicle right now.
        return null;
    }

    // function to call when an employee is assigned to deliver an order
    public boolean setVehicleIsBusyTrue(Vehicle vehicle) {
        int index = DBMS.getDBMS().vehicles.indexOf(vehicle);
        DBMS.getDBMS().vehicles.get(index).setIsBusy(true);
        return true;
    }

    // function to call when an employee delivers an order -> when deliverOrder in employee is called
    public boolean setVehicleIsBusyFalse(Vehicle vehicle) {
        int index = DBMS.getDBMS().vehicles.indexOf(vehicle);
        DBMS.getDBMS().vehicles.get(index).setIsBusy(false);
        return true;
    }


    public Map<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> getOrderEmployeeMap() {
        return DBMS.getDBMS().orderEmployeeMap;
    }

    // key -> <Order, Integer>
    // value -> <List<Employee>, Vehicle>
    public boolean addItemToOrderEmployeeMap(Pair key, Pair value) {
        DBMS.getDBMS().orderEmployeeMap.put(key, value);
        return true;
    }


    // order is delivered:
    // 1. Order status -> delivered
    // 2. Employee isBusy -> false
    // 3. Vehicle isBusy -> false
    public boolean deliverOrder(Order order) {
        //System.out.println(DBMS.getDBMS().getListOfOrders().get(0).getOrderStatus());
        for (Map.Entry<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> entry : orderEmployeeMap.entrySet()) {
            if (entry.getKey().getKey().getOrderId() == order.getOrderId()) {
                // set order status delivered
                entry.getKey().getKey().finishOrder();
                Order o = entry.getKey().getKey();
                DBMS.getDBMS().setOrderStatusDelivered(o);

                // set employees isBusy false
                int size = entry.getValue().getKey().size();
                for (int i = 0; i < size; i++) {
                    entry.getValue().getKey().get(i).setIsBusy(false);
                    Employee e = entry.getValue().getKey().get(i);
                    DBMS.getDBMS().setEmployeeIsBusyFalse(e);
                }

                // set vehicle isBusy -> false
                entry.getValue().getValue().setIsBusy(false);
                Vehicle v = entry.getValue().getValue();
                DBMS.getDBMS().setVehicleIsBusyFalse(v);

                //System.out.println(DBMS.getDBMS().getListOfOrders().get(0).getOrderStatus());

                return true;
            }
        }
        return false;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~

    public List<Discount> getDiscounts() {
        return DBMS.getDBMS().discounts;
    }

    public List<Discount> getDiscountsBaker(int bakerId) {
        List<Discount> list = new ArrayList<Discount>();
        for (Discount discount : DBMS.getDBMS().discounts) {
            if (discount.getConfectionerId() == bakerId) {
                list.add(discount);
            }
        }
        return list;
    }

    public boolean addDiscount(Discount discount) {
        DBMS.getDBMS().discounts.add(discount);
        return true;
    }


    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~

    public List<Pair<BirthdayItems, Integer>> getBirthdayItems(int bakerId) {
        List<Pair<BirthdayItems, Integer>> b = DBMS.getDBMS().bakerBirthdayItemsMap.get(bakerId);
        return b;
    }

    public boolean addBirthdayItem(BirthdayItems birthdayItem, int num, int bakerId) {
        List<Pair<BirthdayItems, Integer>> list = DBMS.getDBMS().bakerBirthdayItemsMap.get(bakerId);
        Pair<BirthdayItems, Integer> p = new Pair<BirthdayItems, Integer>(birthdayItem, num);
        list.add(p);
        DBMS.getDBMS().bakerBirthdayItemsMap.put(bakerId, list);
        return true;
    }

    public boolean deleteBirthdayItem(BirthdayItems birthdayItem, int bakerId) {
        List<Pair<BirthdayItems, Integer>> list = DBMS.getDBMS().bakerBirthdayItemsMap.get(bakerId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() == birthdayItem) {
                list.remove(i);
                break;
            }
        }
        DBMS.getDBMS().bakerBirthdayItemsMap.put(bakerId, list);
        return true;
    }

    public boolean decreaseBirthdayItemNumber(BirthdayItems birthdayItem, int num, int bakerId) {
        List<Pair<BirthdayItems, Integer>> list = DBMS.getDBMS().bakerBirthdayItemsMap.get(bakerId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() == birthdayItem) {
                int n = list.get(i).getValue() - num;
                if (n < 0)
                    n = 0;
                Pair<BirthdayItems, Integer> pair = new Pair<>(birthdayItem, n);
                list.set(i, pair);
                break;
            }
        }
        DBMS.getDBMS().bakerBirthdayItemsMap.put(bakerId, list);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~

    public List<Sweets> getOrderSweets(int bakerId) {
        return DBMS.getDBMS().bakerOrderSweetMap.get(bakerId);
    }

    public boolean addOrderSweet(Sweets sweet, int bakerId) {
        List<Sweets> list = DBMS.getDBMS().bakerOrderSweetMap.get(bakerId);
        list.add(sweet);
        DBMS.getDBMS().bakerOrderSweetMap.put(bakerId, list);
        return true;
    }

    public boolean deleteOrderSweet(Sweets sweet, int bakerId) {
        List<Sweets> list = DBMS.getDBMS().bakerOrderSweetMap.get(bakerId);
        list.remove(sweet);
        DBMS.getDBMS().bakerOrderSweetMap.put(bakerId, list);
        return true;
    }

    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~

    public List<Pair<Sweets, Integer>> getReadySweets(int bakerId) {
        return DBMS.getDBMS().bakerReadySweetMap.get(bakerId);
    }


    public boolean addReadySweet(Sweets sweet, int num, int bakerId) {
        List<Pair<Sweets, Integer>> list = DBMS.getDBMS().bakerReadySweetMap.get(bakerId);
        Pair<Sweets, Integer> p = new Pair<Sweets, Integer>(sweet, num);
        list.add(p);
        DBMS.getDBMS().bakerReadySweetMap.put(bakerId, list);
        return true;
    }

    public boolean deleteReadySweet(Sweets sweet, int bakerId) {
        List<Pair<Sweets, Integer>> list = DBMS.getDBMS().bakerReadySweetMap.get(bakerId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() == sweet) {
                list.remove(i);
                break;
            }
        }
        DBMS.getDBMS().bakerReadySweetMap.put(bakerId, list);
        return true;
    }


    public boolean decreaseReadySweetNumber(Sweets sweet, int num, int bakerId) {
        List<Pair<Sweets, Integer>> list = DBMS.getDBMS().bakerReadySweetMap.get(bakerId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() == sweet) {
                int n = list.get(i).getValue() - num;
                if (n < 0)
                    n = 0;
                Pair<Sweets, Integer> pair = new Pair<>(sweet, n);
                list.set(i, pair);
                break;
            }
        }
        DBMS.getDBMS().bakerReadySweetMap.put(bakerId, list);
        return true;
    }
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~


    public boolean setScoreSweets(Rate score, int sweetId, int bakerId) {
        List<Pair<Sweets, Integer>> listR = DBMS.getDBMS().bakerReadySweetMap.get(bakerId);
        boolean b = false;
        for (int i = 0; i < listR.size(); i++) {
            if (listR.get(i).getKey().getSweetId() == sweetId) {
                listR.get(i).getKey().addScore(score);
                b = true;
                break;
            }
        }
        if (b == false) {
            List<Sweets> listO = DBMS.getDBMS().bakerOrderSweetMap.get(bakerId);
            for (int i = 0; i < listO.size(); i++) {
                if (listO.get(i).getSweetId() == sweetId) {
                    listO.get(i).addScore(score);
                    b = true;
                    break;
                }
            }
            if (b == true) {
                DBMS.getDBMS().bakerOrderSweetMap.put(bakerId, listO);
                return true;
            } else {
                return false;
            }
        } else {
            DBMS.getDBMS().bakerReadySweetMap.put(bakerId, listR);
            return true;
        }

    }

    public double getScoreBaker(int bakerId) {
        List<Pair<Sweets, Integer>> listR = DBMS.getDBMS().bakerReadySweetMap.get(bakerId);
        double sumScore = 0;
        int numSweet = 0;
        for (int i = 0; i < listR.size(); i++) {
            if (listR.get(i).getKey().getNumCustomerForScore() != 0) {
                sumScore += listR.get(i).getKey().getScore();
                numSweet += 1;
            }
        }
        List<Sweets> listO = DBMS.getDBMS().bakerOrderSweetMap.get(bakerId);
        for (int i = 0; i < listO.size(); i++) {
            if (listO.get(i).getSweetId() != 0) {
                sumScore += listR.get(i).getKey().getScore();
                numSweet += 1;
            }
        }
        double score = sumScore / numSweet;
        return score;

    }


    public List<Order> getListOfOrdersBaker(int bakerId) {
        List<Order> orders = new ArrayList<Order>();
        for (Order order : DBMS.getDBMS().orders) {
            if (order.getStaffId() == bakerId) {
                orders.add(order);
            }
        }
        return orders;
    }

    //////negin
    public ArrayList<String> GetAllPossibleTypeOfSweets(Confectioner confectioner) {
        List<Pair<Sweets, Integer>> listSweets = DBMS.getDBMS().bakerReadySweetMap.get(confectioner.getID());
        ArrayList<String> possibleSweets = new ArrayList<>();
        for (Pair<Sweets, Integer> sweets :
                listSweets) {
            if (sweets.getValue() > 0) {
                possibleSweets.add(sweets.getKey().getName());
            }

        }
        return possibleSweets;
    }

    public Sweets GetSweetsFromConfectioner(Confectioner confectioner, String name) {
        List<Pair<Sweets, Integer>> listSweets = DBMS.getDBMS().bakerReadySweetMap.get(confectioner.getID());
        for (Pair<Sweets, Integer> sweets :
                listSweets) {
            if (sweets.getKey().getName().equals(name)) {
                return sweets.getKey();
            }

        }
        return null;
    }

    public ArrayList<String> GetAllPossibleTypeOfCake(Confectioner confectioner) {
        List<Sweets> listSweets = DBMS.getDBMS().bakerOrderSweetMap.get(confectioner.getID());
        ArrayList<String> possibleSweets = new ArrayList<>();
        for (Sweets sweets :
                listSweets) {
            if (sweets.getType() == TypeOfSweets.CAKE) {
                possibleSweets.add(sweets.getName());
            }

        }
        return possibleSweets;
    }

    public Boolean SaveGiftCartForCustomer(Customer customer, GiftCard giftCard) {
        for (int i = 0; i < DBMS.getDBMS().customers.size(); i++) {
            if (DBMS.getDBMS().customers.get(i) == customer) {
                DBMS.getDBMS().customers.get(i).addGiftCard(giftCard);
                DBMS.getDBMS().customers.get(i).ShowReceivedGiftCard(giftCard);
                break;
            }
        }


        return true;
    }

    public ArrayList<GiftCard> GetAllGiftCards(Customer customer) {
        for (int i = 0; i < DBMS.getDBMS().customers.size(); i++) {
            if (DBMS.getDBMS().customers.get(i) == customer) {
                return DBMS.getDBMS().customers.get(i).getGiftCards();

            }
        }
        return null;
    }


    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~


    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~


    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~


}