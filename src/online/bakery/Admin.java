/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author melika
 */
public class Admin extends Account{
    private String username, password;
    
    //Lazy Initialization with Double Lock
    private static Admin INSTANCE = null;
    private Admin() {
        super();
        super.role = Role.ADMIN;
        this.username = "admin";
        this.password = "admin123";
        DBMS.addNewAdmin(this, username, password);        
        Login.SignUp(username, password, Role.ADMIN); // for seting lastLoginDate and isLogedIn
    }
    
    public static Admin getInstance() {
        if (INSTANCE == null) {
            synchronized (Admin.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Admin();                  
                }
            }
        }
        return INSTANCE;
    }
    void setQuestions(List<String> questions){
        String q1= "how old are you? ";
        questions.add(q1);
        DBMS.getDBMS(this).setQuestions(questions);
    }
    
    boolean setAnswers(String username, List<String> answers){
        return DBMS.getDBMS(this).setAnswer(username, answers);
    }
    
    List<String> getQuestions(){
        return DBMS.getDBMS(this).getQuestions();
    }
    
    List<String> getAnswer(String username){
        return DBMS.getDBMS(this).getAnswers(username);
    }
    
    boolean changePassword(String username, String password){
        if( DBMS.getDBMS(this).hasEntry(username, password)){
            String result = DBMS.getDBMS(this).addEntry(username, password);
            if (result != null){ // say there was password before this event 
                return true;
            }else
                return false;
        }else
            return false;
    }
    
    boolean checkPasword(String username, String password){
        return DBMS.getDBMS(this).checkPasword(username , password);
    }
    
    boolean AskPermission(String username){
        // check if this username has option to change password without answering security Q&A
        return true;
    }
    
    boolean hasEntry(String username, String password){
        boolean res = DBMS.getDBMS(this).hasEntry(username, password);
        return res;
    }
    
    String addEntry(String username, String password){
        return DBMS.getDBMS(this).addEntry(username, password);
    }
    
    boolean hasSalt(String username){
        return DBMS.getDBMS(this).hasSalt(username);
    } 
    
    boolean createCustomer(Customer customer){
        return DBMS.getDBMS(this).addCustomer(customer);
    }
    
    List<Customer> viewCustomers(){
        return DBMS.getDBMS(this).getListOfCustomers();
    }
    
    boolean deleteCustomer(Customer customer){
        customer.setActiveness("Inactive");
        return DBMS.getDBMS(this).removeCustomer(customer);
    }
    
    boolean createBakery (Bakery baker){
        return DBMS.getDBMS(this).addBakery(baker);
    }
    
    List<Bakery> viewBakery(){
        return DBMS.getDBMS(this).getListOfBakeries();
    }
    
    boolean deleteBakery (Bakery baker){
        return DBMS.getDBMS(this).removeBakery(baker);
    }
    
    boolean createPerson (Person person){
        return DBMS.getDBMS(this).addBaker(person);
    }
    
    List<Person> viewPerson(){
        return DBMS.getDBMS(this).getListOfBakers();
    }
    
    boolean deletePerson(Person person){
        return DBMS.getDBMS(this).removeBaker(person);
    }
    
    boolean createEmploee (Employee employee){
        return DBMS.getDBMS(this).addEmployee(employee);
    }
    
    List<Employee> viewEmployee(){
        return DBMS.getDBMS(this).getListOfEmployees();
    }
    
    boolean deleteEmployee(Employee employee){
        return DBMS.getDBMS(this).removeEmployee(employee);
    }
    
    Employee getFirstFreeEmployee() {
        return DBMS.getDBMS(this).getFirstFreeEmployee();
    }
    
    List<Order> getOrders() {
        return DBMS.getDBMS(this).getListOfOrders();
    }
    
    boolean addOrder(Order order) {
        return DBMS.getDBMS(this).addOrder(order);
    }
    
    boolean addVehicle(Vehicle vehicle) {
        return DBMS.getDBMS(this).addVehicle(vehicle);
    }
    
    Vehicle getFirstFreeVehicle() {
        return DBMS.getDBMS(this).getFirstFreeVehicle();
    }
    
    List<Vehicle> getVehicles() {
        return DBMS.getDBMS(this).getVehicles();
    }
    
    boolean addItemToOrderEmployeeMap(Pair key, Pair value) {
        return DBMS.getDBMS(this).addItemToOrderEmployeeMap(key, value);
    }
    
    public Map<Pair<Order, Integer>, Pair<List<Employee>, Vehicle>> getOrderEmployeeMap() {
        return DBMS.getDBMS(this).getOrderEmployeeMap();
    }
    
    public boolean deliverOrder(Order order) {
        return DBMS.getDBMS(this).deliverOrder(order);
    }
}

