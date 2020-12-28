package online.bakery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final List<Integer> SweetsId ;

    
    private final int StaffId;
    private int paymentId;

    
    
    public Order(int customerId, List<Integer> SweetsId, int StaffId) {
        this.customerId = customerId;
        atomicInteger.incrementAndGet();
        this.orderId=atomicInteger.incrementAndGet();
        this.SweetsId = SweetsId;
        this.StaffId = StaffId;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Integer> getSweetsId() {
        return SweetsId;
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
