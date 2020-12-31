/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author melika
 */
abstract public class Account {
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    protected int ID;
    String Address = null;
    String ContactNo;

    Date dateCreated;
    
    //for bakery name of manger 
    String Firstname;
    String Lastname; 
    String username;
    String password;
    
    Role role;
    
    public Account() {
        atomicInteger.incrementAndGet();
        this.ID = atomicInteger.incrementAndGet();
        this.dateCreated = new Date();
    }

    public int getID() {
        return ID;
    }

    public Date getDateCreated() {
        return dateCreated;
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
    
    public boolean SignUp(String username, String password, Role role){
        if( Login.SignUp(username, password, role)){
            this.username = username;
            this.password = password;
            return true;
        }else
            return false;
    }
    
    public boolean Login(String username, String password, Role role){
        return Login.ValidateLogin(username, password, role);
    }
    
    public void LogOut(){
        Login.Logout();
    }
    
    public Date getLastLogin(){
        return Login.lastLoginTime;
    }
    
    public AbstractMap.SimpleEntry forgotPassword(){
        if (!Login.isLogedIn){
            System.out.println("Please answer these security questions.\n");
            boolean result = true;
            List<String> actuallAnswers = Admin.getInstance().getAnswer(this.username);
            for(String q: Admin.getInstance().getQuestions()){
                Scanner scan = new Scanner(System.in);
                System.out.printf(q);
                String ans = scan.nextLine();
                String actullAnswer = actuallAnswers.get(Admin.getInstance().getQuestions().indexOf(q));
                if(!actullAnswer.equals(ans)){
                  result = false;
                  break;
                }
            }
            if(result){
                Scanner scan = new Scanner(System.in);
                System.out.printf("Enter new password: ");
                String pass = scan.next();
                boolean changed = Admin.getInstance().changePassword(this.username, pass);
                if(changed)
                    return new AbstractMap.SimpleEntry(true, "Password successfully changed");
                else
                    return new AbstractMap.SimpleEntry(false, "No password is set");
            }else{
                boolean permit = Admin.getInstance().AskPermission(this.username);
                if(permit){
                    Scanner scan = new Scanner(System.in);
                    System.out.printf("Enter new password: ");
                    String pass = scan.next();
                    boolean changed = Admin.getInstance().changePassword(this.username, pass);
                    if(changed)
                        return new AbstractMap.SimpleEntry(true, "Password successfully changed");
                    else
                        return new AbstractMap.SimpleEntry(false, "No password is set");
                }else
                    return new AbstractMap.SimpleEntry(false, "You have no permission to change password without answering security questions");
            }
        }else
            return new AbstractMap.SimpleEntry(false, "You are loged in so use change password.");
    }
    
    public AbstractMap.SimpleEntry changePassword(){
        if (Login.isLogedIn){
            Scanner scan = new Scanner(System.in);
            System.out.printf("Enter new password: ");
            String pass = scan.next();
            boolean changed = Admin.getInstance().changePassword(this.username, pass);
            if(changed)
                return new AbstractMap.SimpleEntry(true, "Password successfully changed");
            else
                return new AbstractMap.SimpleEntry(false, "No password is set");
        }else
            return new AbstractMap.SimpleEntry(false, "You are not Loged in.");
    }
}
