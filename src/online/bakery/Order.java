
/**
 *
 * @author melika
 */
public class Order {
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    
    private final String customerId;
    private final String orderId;
    private final String SweetsId;
    private final String StaffId;
    private String paymentId;

    
    
    public Order(String customerId, String orderId, String SweetsId, String StaffId) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.SweetsId = SweetsId;
        this.StaffId = StaffId;
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
