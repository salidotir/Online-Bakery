package online.bakery;
import java.util.List;

/**
 *
 * @author melika
 */
public class Order {
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    
    private final String customerId;
    private final String orderId;
    private final List<String> SweetsId;

    
    private final String StaffId;
    private String paymentId;

    
    
    public Order(String customerId, String orderId, List<String> SweetsId, String StaffId) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.SweetsId = SweetsId;
        this.StaffId = StaffId;
    }
    
    public List<String> getSweetsId() {
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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
}
