package online.bakery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

/**
 *
 * @author melika
 */
public class Order {
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    
    private final Customer customer;
    private final int orderId;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private Map<Sweets,Integer> map = new HashMap<Sweets,Integer>(); ;  
    
    private final Confectioner Staff;
    private int paymentId;
    
    public Order(Customer customer, List<Sweets> Sweets, Confectioner Staff) {
        this.customer = customer;
        atomicInteger.incrementAndGet();
        this.orderId = atomicInteger.incrementAndGet();
        for(Sweets s: Sweets){
            map.put(s, null);
        }
        this.Staff = Staff;
    }
    
    public boolean addtoOrder(Sweets Sweet){
        if (map.get(Sweet) != null){
            map.put(Sweet, null);
            return true;
        }else
            return false;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Sweets> getSweets() {
        List<Sweets> sweets = new ArrayList<Sweets>();
        for (Sweets i : map.keySet()) {
            sweets.add(i);
        }
        return sweets;
    }

    public Map<Sweets, Integer> getMap() {
        return map;
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
    
    public boolean addScore(Sweets sweet, int score){
        boolean result = map.replace(sweet, null , score);

        if(result){
            switch (score){
              case 0:
                  sweet.addScore(Rate.ZERO, customer);
                  break;
              case 1:
                  sweet.addScore(Rate.ONE, customer);
                  break;
              case 2:
                  sweet.addScore(Rate.TWO, customer);
                  break;
              case 3:
                  sweet.addScore(Rate.THREE, customer);
                  break;
              case 4:
                  sweet.addScore(Rate.FOUR, customer);
                  break;
              case 5:
                  sweet.addScore(Rate.FIVE, customer);
                  break;
              case 6:
                  sweet.addScore(Rate.SIX, customer);
                  break;
              case 7:
                  sweet.addScore(Rate.SEVEN, customer);
                  break;
              case 8:
                  sweet.addScore(Rate.EIGHT, customer);
                  break;
              case 9:
                  sweet.addScore(Rate.NINE, customer);
                  break;
              case 10:
                  sweet.addScore(Rate.TEN, customer);
                  break;
            }
            Staff.setScore(score);
            return true;
        }
        else
            return false;
    }
   
}
