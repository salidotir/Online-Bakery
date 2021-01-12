/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.math.BigDecimal;
import java.util.*;

import javafx.util.Pair;
import online.bakery.Confectioner.Confectioner;
import online.bakery.Discount.Discount;
import online.bakery.GiftCard.GiftCard;
import online.bakery.Post.Comment;
import online.bakery.Post.Post;
import online.bakery.Status.OrderStatus;
import online.bakery.birthdayItems.BirthdayItems;
import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

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

    public boolean newComplaint(Complaint complaint) {
        // save new complaint in dbms
        return DBMS.getDBMS(this).addNewComplaint(complaint);
    }
    
    public boolean setComplantRespondTrue(Complaint complaint) {
        return DBMS.getDBMS(this).responceComplaint(complaint);
    }
    
    public List<Complaint> viewNotRespondComplaints() {
        return DBMS.getDBMS(this).getNotRespondComplaints();
    }
    
    public List<Complaint> viewAllComplaints() {
        return DBMS.getDBMS(this).getAllComplaints();        
    }
    
    public List<Complaint> getUserComplaints(int userId) {
        return DBMS.getDBMS(this).getUserComplaints(userId);
    }    
    
    public boolean createDiscount(String name , int percent,Date start , Date end, int max) {
        if(percent<= 100 && percent > 0){
            Discount d = new Discount(name, percent, start, end, this.getID(), max);
            return Admin.getInstance().addDiscount(d);
        }else
            return false;
    }
    
    public boolean paytoAccount(BigDecimal cost, Account account, String description){
        PaymentType paymentType = Payment.howToPay();
        Payment payment = new Payment(new Date(), cost, description, paymentType);
        this.addPayment(payment);
        return payment.pay(payment, this, account);
    }
    
    void setQuestions(List<String> questions){
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
    
    public AbstractMap.SimpleEntry findAccountWithRoleAndUsername(Role role, String username){
        return DBMS.getDBMS(this).findAccountWithRoleAndUsername(role, username);
    }
    
    public boolean getLogedInAccount(Role role, String username){
        return DBMS.getDBMS(this).getLogedInAccount(role, username);
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
        return false;
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
        if (DBMS.getDBMS(this).addCustomer(customer)){
            customer.setActiveness("Active");
            return true;
        }else
            return false;
    }
    
    boolean toggleActiveness(Account account){
        AbstractMap.SimpleEntry result = DBMS.getDBMS(this).toggleActiveness(account);
        if ((boolean) result.getKey()){
            account.setActiveness((String)result.getValue());
            return true;
        }else
            return false;
    }
    
    public List<Payment> getPayment(Account account){
        return DBMS.getDBMS(this).getPayment(account);
    }
    
    List<Customer> viewCustomers(){
        return DBMS.getDBMS(this).getListOfCustomers();
    }
    
    boolean deleteCustomer(Customer customer){
        customer.setActiveness("Inactive");
        return DBMS.getDBMS(this).removeCustomer(customer);
    }
    
    boolean createBakery (Bakery baker){
        if (DBMS.getDBMS(this).addBakery(baker)){
            baker.setActiveness("Active");
            return true;
        }else
            return false;
    }
    
    List<Bakery> viewBakery(){
        return DBMS.getDBMS(this).getListOfBakeries();
    }
    
    boolean deleteBakery (Bakery baker){
        baker.setActiveness("Inactive");
        return DBMS.getDBMS(this).removeBakery(baker);
    }
    
    boolean createBaker (Baker baker){
        if (DBMS.getDBMS(this).addBaker(baker)){
            baker.setActiveness("Active");
            return true;
        }else
            return false;
    }
    
    List<Baker> viewBaker(){
        return DBMS.getDBMS(this).getListOfBakers();
    }
    
    boolean deleteBaker(Baker baker){
        baker.setActiveness("Inactive");
        return DBMS.getDBMS(this).removeBaker(baker);
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
    
    boolean updateOrder(Order order){
        return DBMS.getDBMS(this).updateOrder(order);
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
    
    public Map<Pair<Integer, Integer>, Pair<List<Employee>, Vehicle>> getOrderEmployeeMap() {
        return DBMS.getDBMS(this).getOrderEmployeeMap();
    }
    
    public AbstractMap.SimpleEntry getOrderByID(int orderId){
        return DBMS.getDBMS(this).getOrderByID(orderId);
    }
    
    public boolean deliverOrder(Order order, Date actuallDelivery) {
        return DBMS.getDBMS(this).deliverOrder(order, actuallDelivery);
    }

    public boolean ruinOrder(Order order) {
        return DBMS.getDBMS(this).ruinOrder(order);
    }
    
    public boolean editBaker(Baker baker){
        return DBMS.getDBMS(this).editBaker(baker.getID(), baker.getName(), baker.getDescription(), baker.getFirstname(), baker.getLastname(), baker.getAddress(), baker.getContactNo());
    }
    public boolean editBakery(Bakery bakery){
        return DBMS.getDBMS(this).editBakery(bakery.getID(),bakery.getName(),bakery.getDescription(),bakery.getFirstname(),bakery.getLastname(),bakery.getAddress(),bakery.getContactNo());
    }

    public boolean setScore(Confectioner confectioner, Rate score, int sweetId) {
        return DBMS.getDBMS(this).setScoreSweets(score,sweetId,confectioner.getID());
    }

    public double getScoreBaker(Confectioner confectioner) {
        return DBMS.getDBMS(this).getScoreBaker(confectioner.getID());
    }

    public boolean addOrderSweet(Confectioner confectioner, Sweets sweet) {
        return DBMS.getDBMS(this).addOrderSweet(sweet,confectioner.getID());
    }

    public boolean addReadySweet(Bakery bakery, Sweets sweet, int number) {
        return DBMS.getDBMS(this).addReadySweet(sweet,number,bakery.getID());
    }

    public boolean addBirthdayItem(Bakery bakery, BirthdayItems item, int number) {
        return DBMS.getDBMS(this).addBirthdayItem(item,number,bakery.getID());
    }

    public boolean deleteReadySweet(Bakery bakery, Sweets sweet, int number) {
        return DBMS.getDBMS(this).deleteReadySweet(sweet,bakery.getID());
    }

    public boolean deleteOrderSweet(Confectioner confectioner, Sweets sweet) {
        return DBMS.getDBMS(this).deleteOrderSweet(sweet,confectioner.getID());
    }

    public boolean deleteBirthdayItem(Bakery bakery, BirthdayItems birthdayItem) {
        return DBMS.getDBMS(this).deleteBirthdayItem(birthdayItem,bakery.getID());
    }

    public boolean decreaseReadySweetNumber(Bakery bakery, Sweets sweets, int num) {
        return DBMS.getDBMS(this).decreaseReadySweetNumber(sweets,num,bakery.getID());
    }

    public boolean decreaseBirthdayItemNumber(Bakery bakery, BirthdayItems birthdayItems, int num) {
        return DBMS.getDBMS(this).decreaseBirthdayItemNumber(birthdayItems,num,bakery.getID());
    }

    public List<Pair<Sweets, Integer>> getReadySweets(Bakery bakery) {
        return DBMS.getDBMS(this).getReadySweets(bakery.getID());
    }

    public List<Sweets> getOrderSweets(Confectioner confectioner) {
        return DBMS.getDBMS(this).getOrderSweets(confectioner.getID());
    }

    public List<Order> getOrderList(Confectioner confectioner) {
        return DBMS.getDBMS(this).getListOfOrdersBaker(confectioner.getID());
    }

    public List<Pair<BirthdayItems, Integer>> getBirthdayItem(Bakery bakery) {
        return DBMS.getDBMS(this).getBirthdayItems(bakery.getID());
    }

    public List<Discount> getDiscount(int creatorID) {
        return DBMS.getDBMS(this).getDiscountsBaker(creatorID);
    }
    
    public boolean firstOrder(int customerID){
        return DBMS.getDBMS(this).firstOrder(customerID);
    }

    public List<Discount> getActiveDiscount(int creatorID){
        return DBMS.getDBMS(this).getActiveDiscount(creatorID);
    }

    public boolean addDiscount(Discount discount) {
        return DBMS.getDBMS(this).addDiscount(discount);
    }
    ////////////////////////////////////////////////////////////////////Negin
    public ArrayList<String> GetAllPossibleTypeOfSweets(Confectioner confectioner){

        return DBMS.getDBMS(this).GetAllPossibleTypeOfSweets(confectioner);
    }
    public Sweets  GetSweetsFromConfectioner(Confectioner confectioner, String name){
        return DBMS.getDBMS(this).GetSweetsFromConfectioner(confectioner,name);

    }
    public ArrayList<String >GetAllPossibleTypeOfCake(Confectioner confectioner){
        return DBMS.getDBMS(this).GetAllPossibleTypeOfCake(confectioner);

    }
    public Boolean SaveGiftCartForCustomer(Customer customer, GiftCard giftCard){
        return DBMS.getDBMS(this).SaveGiftCartForCustomer(customer,giftCard);


    }
    public List<GiftCard>GetAllGiftCards(Customer customer){
        return DBMS.getDBMS(this).GetAllGiftCards(customer);

    }

    public List<Order> getOrderDate(Confectioner confectioner, Date start, Date end, OrderStatus orderStatus) {
        return DBMS.getDBMS(this).getOrderDate(confectioner.getID(),start,end,orderStatus);
    }

    public boolean addPost(Post post) {
        return DBMS.getDBMS(this).addPost(post);
    }

    public boolean addComment(Comment Comment) {
        return DBMS.getDBMS(this).addComment(Comment);
    }

    public boolean editPost(Post post) {
        return DBMS.getDBMS(this).editPost(post.getId(),post.getCaption(),post.getLikeId());
    }

    public Post createPost(){
        List<String> images = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter caption post");
        String caption = scan.nextLine();
        System.out.println("Enter number of image : ");
        int num = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < num; i++){
            System.out.println("Enter image");
            String s = scan.nextLine();
            images.add(s);

        }
        return Post.createPost(this,caption,images);
    }

    public List<Post> getPosts(int id) {
        return DBMS.getDBMS(this).getPosts(id);
    }

    public List<Comment> getComments(int id) {
        return DBMS.getDBMS(this).getComments(id);
    }
    public List<Bakery> ShowAllBakery(){return DBMS.getDBMS(this).ShowAllBakery();}
    public List<Baker> ShowAllBakers(){return DBMS.getDBMS(this).ShowAllBakers();}
    public List<Baker> searchBakerByName(String subString) {return DBMS.getDBMS(this).searchBakerByName(subString);}
    public List<Bakery> searchBakeryByName(String subString) {return DBMS.getDBMS(this).searchBakeryByName(subString);}
    public Customer searchCustomerByContactNo(String num) {return DBMS.getDBMS(this).searchCustomerByContactNo(num);}

}

