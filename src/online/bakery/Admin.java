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
    ArrayList<Customer> customers = new ArrayList<Customer>();
    ArrayList<Bakery> bakeries = new ArrayList<Bakery>();
    ArrayList<Person> persons = new ArrayList<Person>();
    
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
    
    String createCustomer(Customer customer){
        boolean result = customers.add(customer);
        return (result == true ? "Active" : "Inactive");
    }
    
    List<Customer> viewCustomers(){
        return customers;
    }
    
    boolean deleteCustomer(Customer customer){
        customer.setActiveness("Inactive");
        return customers.remove(customer);
    }
    
    void createBakery (Bakery baker){
        bakeries.add(baker);
    }
    
    List<Bakery> viewBakery(){
        return bakeries;
    }
    
    boolean deleteBakery (Bakery baker){
        return bakeries.remove(baker);
    }
    
    void createPerson (Person person){
        persons.add(person);
    }
    
    List<Person> viewPerson(){
        return persons;
    }
    
    boolean deletePerson(Person person){
        return persons.remove(person);
    }
    
}

