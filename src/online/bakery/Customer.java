package online.bakery;
import java.util.ArrayList;
import java.util.List;
import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

/**
 *
 * @author melika
 */
public class Customer extends Account{
    private final int CustomerID;
    private final Wallet wallet;
    private List<Order> Orders = new ArrayList<Order>();   
    private List<Sweets> designs = new ArrayList<Sweets>();
    private String activeness;
    
    public Customer(String username, String password){
        super();
        this.CustomerID = super.ID;
        Wallet w = new Wallet();
        this.wallet = w;
        super.SignUp(username, password);
        Admin.getInstance().createCustomer(this); 
    }

    public String getActiveness() {
        return activeness;
    }

    public void setActiveness(String activeness) {
        this.activeness = activeness;
    }
 
    public Wallet getWallet() {
        return wallet;
    }
    
    public void setOrder(Order Order) {
        this.Orders.add(Order);
    }

    public List<Order> getOrders() {
        return Orders;
    }
    
    public List<Integer> getOrdersID(){
        List<Integer> ids = new ArrayList<Integer>();
        for (Order order : this.Orders) {
            ids.add(order.getOrderId());
        }
        return ids;
    }
    
    public String getProfile() {
        String personP = super.getFirstname() +" " + super.getLastname() + "\n" + "Number : " + super.getContactNo() + "\n" + "Address : " + super.getAddress() + "\n";
        return personP;
    }

    public Order createNewSweet(List<Sweets> sweetlist, Confectioner Staff, List<BirthdayItems> items){
        Order order = new Order(this, sweetlist, Staff, items);
        this.Orders.add(order);
        return order;
    }
    
    public boolean SweetaddtoOrder(Sweets sweet, Order order){
        if (this.Orders.contains(order)){
            return this.Orders.get(this.Orders.lastIndexOf(order)).SweetaddtoOrder(sweet);
        }else
            return false;
    }
    
    public void addtoDesigns(Sweets sweet){
        designs.add(sweet);
    }
}
