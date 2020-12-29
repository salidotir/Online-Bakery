package online.bakery;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melika
 */
public class Customer extends Account{
    private final int CustomerID;
    private final Wallet wallet;
    private List<Integer> OrdersID = new ArrayList<Integer>();
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
    
    public void setOrderID(int OrderID) {
        this.OrdersID.add(OrderID);
    }

    public List<Integer> getOrdersID() {
        return OrdersID;
    }
    
    public String getProfile() {
        String personP = super.getFirstname() +" " + super.getLastname() + "\n" + "Number : " + super.getContactNo() + "\n" + "Address : " + super.getAddress() + "\n";
        return personP;
    }

    public Order createNewSweet(int SweetsId, int StaffId){
        List<Integer> SweetList = new ArrayList<Integer>();
        SweetList.add(SweetsId);
        Order order = new Order(this.CustomerID, SweetList, StaffId);
        this.OrdersID.add(order.getOrderId());
        return order;
    }

}
