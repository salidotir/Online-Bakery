package online.bakery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import online.bakery.sweets.Sweets;

/**
 *
 * @author melika
 */
public class Order {
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    
    private final int customerId;
    private final int orderId;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private final List<Sweets> Sweets ;

    
    private final int StaffId;
    private int paymentId;
    
    public Order(int customerId, List<Sweets> Sweets, int StaffId) {
        this.customerId = customerId;
        atomicInteger.incrementAndGet();
        this.orderId = atomicInteger.incrementAndGet();
        this.Sweets = Sweets;
        this.StaffId = StaffId;
    }
    
    public boolean addtoOrder(Sweets Sweet){
        return this.Sweets.add(Sweet);
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Sweets> getSweets() {
        return Sweets;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
}
