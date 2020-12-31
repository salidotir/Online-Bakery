/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melika
 */
public class Admin extends Account{
    private final int AdminID;
    private String username, password;
    
    //Lazy Initialization with Double Lock
    private static Admin INSTANCE = null;
    private Admin() {
        super();
        this.AdminID = super.ID;
        this.username = "admin";
        this.password = "admin123";
        super.SignUp("admin", "admin123");
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
    
    boolean createCustomer(Customer customer){
        return DBMS.getDBMS(this.username, this.password).addCustomer(customer);
    }
    
    List<Customer> viewCustomers(){
        return DBMS.getDBMS(this.username, this.password).getListOfCustomers();
    }
    
    boolean deleteCustomer(Customer customer){
        customer.setActiveness("Inactive");
        return DBMS.getDBMS(this.username, this.password).removeCustomer(customer);
    }
    
    boolean createBakery (Bakery baker){
        return DBMS.getDBMS(this.username, this.password).addBakery(baker);
    }
    
    List<Bakery> viewBakery(){
        return DBMS.getDBMS(this.username, this.password).getListOfBakeries();
    }
    
    boolean deleteBakery (Bakery baker){
        return DBMS.getDBMS(this.username, this.password).removeBakery(baker);
    }
    
    boolean createPerson (Person person){
        return DBMS.getDBMS(this.username, this.password).addBaker(person);
    }
    
    List<Person> viewPerson(){
        return DBMS.getDBMS(this.username, this.password).getListOfBakers();
    }
    
    boolean deletePerson(Person person){
        return DBMS.getDBMS(this.username, this.password).removeBaker(person);
    }
    
}

