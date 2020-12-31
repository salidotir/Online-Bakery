package online.bakery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    
    private final Customer customer;
    private final int orderId;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private Map<Sweets,Integer> sweet_score = new HashMap<Sweets,Integer>(); ;  
    private List<BirthdayItems> items;
    private final Confectioner Staff;
    private Payment payment;
    
    public Order(Customer customer, List<Sweets> Sweets, Confectioner Staff, List<BirthdayItems> items) {
        this.customer = customer;
        atomicInteger.incrementAndGet();
        this.orderId = atomicInteger.incrementAndGet();
        for(Sweets s: Sweets){
            sweet_score.put(s, null);
        }
        this.Staff = Staff;
        this.items = items;
        this.orderStatus = OrderStatus.ORDERING_BY_CUSTOMER;
    }
    
    public boolean SweetaddtoOrder(Sweets Sweet){
        if (orderStatus == OrderStatus.ORDERING_BY_CUSTOMER && sweet_score.get(Sweet) != null ){
            sweet_score.put(Sweet, null);
            return true;
        }else
            return false;
    }
    
    public boolean removeSweet(Sweets sweet){
        if (orderStatus == OrderStatus.ORDERING_BY_CUSTOMER && sweet_score.get(sweet) != null){
            sweet_score.remove(sweet);
            return true;
        }else
            return false;
    }

    public boolean ItemaddtoOrder(BirthdayItems item){
        if (orderStatus == OrderStatus.ORDERING_BY_CUSTOMER && !items.contains(item)){
            return items.add(item);            
        }else
            return false;
    }
    
    public boolean removeItem(BirthdayItems item){
        return (orderStatus == OrderStatus.ORDERING_BY_CUSTOMER ? items.remove(item): false);
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public int getCustomerId(){
        return customer.getID();
    }

    public List<Sweets> getSweets() {
        List<Sweets> sweets = new ArrayList<Sweets>();
        for (Sweets i : sweet_score.keySet()) {
            sweets.add(i);
        }
        return sweets;
    }

    public Map<Sweets, Integer> getListScores() {
        return sweet_score;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public boolean addScore(Sweets sweet, int score){
        if (orderStatus == OrderStatus.DELIVERED){
            boolean result = sweet_score.replace(sweet, null , score);

            if(result){
                switch (score){
                  case 0:
    //                  sweet.addScore(Rate.ZERO, customer);
                      break;
                  case 1:
    //                  sweet.addScore(Rate.ONE, customer);
                      break;
                  case 2:
    //                  sweet.addScore(Rate.TWO, customer);
                      break;
                  case 3:
    //                  sweet.addScore(Rate.THREE, customer);
                      break;
                  case 4:
    //                  sweet.addScore(Rate.FOUR, customer);
                      break;
                  case 5:
    //                  sweet.addScore(Rate.FIVE, customer);
                      break;
                  case 6:
    //                  sweet.addScore(Rate.SIX, customer);
                      break;
                  case 7:
    //                  sweet.addScore(Rate.SEVEN, customer);
                      break;
                  case 8:
    //                  sweet.addScore(Rate.EIGHT, customer);
                      break;
                  case 9:
    //                  sweet.addScore(Rate.NINE, customer);
                      break;
                  case 10:
    //                  sweet.addScore(Rate.TEN, customer);
                      break;
                }
    //            Staff.setScore(dounle,double,sweet);
                return true;
            }
            else
                return false;
        }else
            return false;
    }
    //pay create payment 
    
    // set request delivey 
    //address profile
    //maintain emploee
    
    
    public String getDetailCosts(){
        String s;
        String sweet = null , item = null , delivery = null;
        delivery = "100";
        for (Sweets i : sweet_score.keySet()) {
            sweet += i.getDescription();
            sweet += " : ";
            sweet += i.getTOTAL_COST().toString();
            sweet += "\n";
        }
        for (BirthdayItems b: items){
            item += b.getDescription();
            item += " : ";
            item += b.getCost().toString();
            item += "\n";
        }
        s = "Detail Costs: "+
                "Cost of Sweets: \n"+ sweet +
                "Cost of items: \n" + item +
                "Cost of delivery: \n" + delivery;
        return s;
    }
    
    public BigDecimal getCost(){
        BigDecimal cost = new BigDecimal(0);
        for (Sweets i : sweet_score.keySet()) {
            cost = cost.add(i.getTOTAL_COST());
        }
        for (BirthdayItems b: items){
            cost = cost.add(b.getCost());
        }
        //cost deliry add kon
        return cost;
    }
    
    public String getOrderInformation(){
        String s;
        s = "Order nformation:\n" +
                "order id: " + this.orderId + "\n" +
                "order status: "+ this.orderStatus + "\n" +
                "coefectioner profile: " + this.Staff.getProfile() + "\n" +
                "****************\n"+
                "customer id: " + this.customer.getID() + "\n" +
                "payment status: " + this.payment.getPaymentStatus() + "\n" +
                "____________________________\n";
        return s;
    }
}
