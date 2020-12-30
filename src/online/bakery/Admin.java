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
    
    //Lazy Initialization with Double Lock
    private static Admin INSTANCE = null;
    private Admin() {
        super();
        this.AdminID = super.ID;
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
        return DBMS.addCustomer(customer);
    }
    
    List<Customer> viewCustomers(){
        return DBMS.getListOfCustomers();
    }
    
    boolean deleteCustomer(Customer customer){
        customer.setActiveness("Inactive");
        return DBMS.removeCustomer(customer);
    }
    
    boolean createBakery (Bakery baker){
        return DBMS.addBakery(baker);
    }
    
    List<Bakery> viewBakery(){
        return DBMS.getListOfBakeries();
    }
    
    boolean deleteBakery (Bakery baker){
        return DBMS.removeBakery(baker);
    }
    
    boolean createPerson (Person person){
        return DBMS.addBaker(person);
    }
    
    List<Person> viewPerson(){
        return DBMS.getListOfBakers();
    }
    
    boolean deletePerson(Person person){
        return DBMS.removeBaker(person);
    }
    
}

