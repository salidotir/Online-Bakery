package online.bakery;
import java.util.List;


/**
 *
 * @author melika
 */
public class Customer {
    private final String CustomerID;
    private final String WalletID;
    private List<String> OrdersID;

    public Customer(String CustomerID, String WalletID) {
        this.CustomerID = CustomerID;
        this.WalletID = WalletID;
    }
    
    public void setOrderID(String OrderID) {
        this.OrdersID.add(OrderID);
    }
    
    public void createNewSweet(String OrderId, String SweetsId, String StaffId){
        List<String> SweetList = null;
        SweetList.add(SweetsId);
        Order order = new Order(this.CustomerID, OrderId, SweetList, StaffId);
        this.OrdersID.add(OrderId);
    }
}
