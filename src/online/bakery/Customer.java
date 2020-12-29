package online.bakery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author melika
 */
public class Customer {
    private final int CustomerID;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private final Wallet wallet;
    private List<Integer> OrdersID = new ArrayList<Integer>();

    public Customer() {
        atomicInteger.incrementAndGet();
        this.CustomerID=atomicInteger.incrementAndGet();
        Wallet w = new Wallet();
        this.wallet = w;
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

    public void createNewSweet(int SweetsId, int StaffId){
        List<Integer> SweetList = new ArrayList<Integer>();
        SweetList.add(SweetsId);
        Order order = new Order(this.CustomerID, SweetList, StaffId);
        this.OrdersID.add(order.getOrderId());
    }

}
