/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author melika
 */
abstract public class Account {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    protected int ID;
    private String Address = null;
    private String ContactNo;

    private final Date dateCreated;
    
    //for bakery name of manger 
    private String Firstname;
    private String Lastname; 
    private String username;
    private String password;
    
    private String activeness;
    private boolean isLogedIn;
    private Date lastLoginTime;
    private final Wallet wallet;
    private List<Payment> payments;
    Role role;
    
    public Account() {
        this.ID = atomicInteger.incrementAndGet();
        this.dateCreated = new Date();
        this.wallet = new Wallet();
        this.payments = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }
    
    public String getActiveness() {
        return activeness;
    }

    public void setActiveness(String activeness) {
        this.activeness = activeness;
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
       
    public Wallet getWallet() {
        return wallet;
    }
    
    public boolean addPayment(Payment payment){
        this.payments.add(payment);
        return true;
    }

    public List<Payment> getPayments() {
        return payments;
    }
    
    public String getProfile() {
        String personP = "Name : " + this.Firstname + " " + this.Lastname + "\n" 
                + "Number : " + this.ContactNo + "\n" + "Address : " + this.Address + "\n"
                + "Activeness : " + this.activeness + "\n";
        return personP;
    }
    
    public boolean SignUp(String username, String password, Role role){
        if(!isLogedIn && Login.SignUp(username, password, role)){
            this.username = username;
            this.password = password;
            this.role = role;
            this.isLogedIn = true;
            this.lastLoginTime = new Date();
            return true;
        }else
            return false;
    }
    
    public boolean Login(String username, String password, Role role){
        if(!isLogedIn && Login.ValidateLogin(username, password, role)){
            isLogedIn = true;
            lastLoginTime = new Date();
            return true;
        }else
            return false;
    }
    
    public boolean LogOut(){
        if(isLogedIn){
            isLogedIn = false;
            return true;
        }else
            return false;
    }
    
    public Date getLastLogin(){
        return lastLoginTime;
    }
    
    public AbstractMap.SimpleEntry forgotPassword(){
        if (!isLogedIn){
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
                    return new AbstractMap.SimpleEntry(false, "You have no permission to change password without answering correctly to security questions");
            }
        }else
            return new AbstractMap.SimpleEntry(false, "You are loged in so use change password.");
    }
    
    public AbstractMap.SimpleEntry changePassword(){
        if (isLogedIn){
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
