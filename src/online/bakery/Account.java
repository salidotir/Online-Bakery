/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author melika
 */
abstract public class Account {
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    protected int ID;
    String Address;
    String ContactNo;

//    private Login login;
    Date dateCreated;
    
    //for bakery name of manger 
    String Firstname;
    String Lastname;    
    
    public Account() {
        atomicInteger.incrementAndGet();
        this.ID = atomicInteger.incrementAndGet();
        this.dateCreated = new Date();
    }

    public int getID() {
        return ID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Fristname) {
        this.Firstname = Fristname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }
    
    public boolean SignUp(String username, String password){
        return Login.SignUp(username, password);
    }
    
    public boolean Login(String username, String password){
        return Login.ValidateLogin(username, password);
    }
    
}
