package online.bakery;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private Map<Sweets,Rate > sweet_score = new HashMap<Sweets,Rate>(); ;  
    private List<BirthdayItems> items;
    private Confectioner Staff;
    private Payment payment;
    private Date expectedDeliveryTime;
    private DeliveryInformation delivery = null;
    private BigDecimal actuallCost;
    private BigDecimal costWithDisocunt;
    private Discount discount = null;
    private Discount discountFirstOrder = null;
    
    public Order(Customer customer, List<Sweets> Sweets, List<BirthdayItems> items, Date expectedDeliveryTime) {
        this.customer = customer;
        this.orderId = atomicInteger.incrementAndGet();
        for(Sweets s: Sweets){
            sweet_score.put(s, null);
        }
        this.items = items;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.orderStatus = OrderStatus.ORDERING_BY_CUSTOMER;
        Admin.getInstance().addOrder(this);
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
    
    public boolean finalizedOrder(){
        if( orderStatus == OrderStatus.ORDERING_BY_CUSTOMER){
            orderStatus = OrderStatus.FINALIZED_BY_CUSTOMER;
            return true;
        }else
            return false;
    }
    
    public boolean cancelByCustomer(){
        if( orderStatus == OrderStatus.FINALIZED_BY_CUSTOMER){
            orderStatus = OrderStatus.CANCELED_BY_CUSTOMER;
            return true;
        }else
            return false;
    }
    
    public AbstractMap.SimpleEntry chooseBaker(Confectioner staff, List<SweetType> s){
        if(orderStatus == OrderStatus.FINALIZED_BY_CUSTOMER){
            this.Staff = staff;
            List<ConfectionerStatus> cs = staff.acceptOrder(this, s);
            orderStatus = OrderStatus.PENDING_CHOSEN_BAKER;
            return new AbstractMap.SimpleEntry(true, cs);
        }else
            return new AbstractMap.SimpleEntry(false, null);
    }
    
    public ConfectionerStatus getConfirmBaker(List<ConfectionerStatus> acceptness){
        ConfectionerStatus s = null;
        for(ConfectionerStatus i : acceptness){
            if (i == ConfectionerStatus.ACCEPT){
                s = ConfectionerStatus.ACCEPT;
            }else{
                s = i;
                break;
            }
        }
        if(orderStatus == OrderStatus.PENDING_CHOSEN_BAKER){
            if ( s == ConfectionerStatus.ACCEPT){
                orderStatus = OrderStatus.ACCEPTED;
                return s;
            }else{
                orderStatus = OrderStatus.CANCELED_BY_BAKER;
                return s;
            }
        }else
            return s;
    }
    
    public AbstractMap.SimpleEntry setDelivery(){
        if(orderStatus == OrderStatus.ACCEPTED ){
            if (customer.getAddress() == null)
                return new AbstractMap.SimpleEntry(false, "please Set address first");
            else{
                DeliveryInformation newdelivery = DeliveryInformation.createNewDelivery(customer.getAddress(), expectedDeliveryTime);
                if (newdelivery == null)
                    return new AbstractMap.SimpleEntry(false, "No emploee can find");
                else{
                    this.delivery = newdelivery;
                    orderStatus = OrderStatus.SET_DELIVERY;
                    return new AbstractMap.SimpleEntry(true, "Emploee set");
                }
            }
        }else
            return new AbstractMap.SimpleEntry(false, "your order is not accepted");
    }
    
    public boolean payOrder(String description){      
        if(orderStatus == OrderStatus.SET_DELIVERY){
            BigDecimal cost = this.getCost();
//            System.out.println(cost);
//            System.out.println(this.actuallCost);
//            System.out.println(this.costWithDisocunt);
            PaymentType paymentType = Payment.howToPay();
            this.payment = new Payment(new Date(), cost, description, paymentType);
            boolean result = payment.pay(payment, this.customer);
            if(result){
                orderStatus = OrderStatus.PAYED;
                return true;
            }else
                return false;
        }else
            return false;
    }
    
    public boolean setBakerStatus(List<SweetType> s){
        if(orderStatus == OrderStatus.PAYED){
            orderStatus = OrderStatus.IN_PROGRESS;
            this.Staff.addOrder(this, s);
            return true;
        }else
            return false;
    }
    
    public boolean callDelivery(){
        if(orderStatus == OrderStatus.IN_PROGRESS){
            orderStatus = OrderStatus.DONE;
            DeliverySystem.getDeliverySystem().addOrderToOrderQueue(this);
            return true;
        }else
            return false;
    }
    
    public boolean finishOrder(Date actuallDelivery){
        System.out.println("finish");
        if(orderStatus == OrderStatus.DONE){
            orderStatus = OrderStatus.DELIVERED;
            delivery.setActualDeliveryTime(actuallDelivery);
            return true;
        }else
            return false;
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
    
    public List<BirthdayItems> getItems(){
        return items;
    }

    public Map<Sweets, Rate> getListScores() {
        return sweet_score;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Payment getPayment() {
        return payment;
    }
    
    public DeliveryInformation getDelivery(){
        return delivery;
    }

    public Date getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public Date getActualDeliveryTime() {
        return delivery.getActualDeliveryTime();
    }
    
    public boolean addScore(Sweets sweet, Rate score){
        if (orderStatus == OrderStatus.DELIVERED){
            boolean result = sweet_score.replace(sweet, null , score);

            if(result){
                //double prevoius = sweet.getScore();
                //sweet.addScore(score);
                //double next = sweet.getScore();
                Staff.setScore(score,sweet);
                return true;
            }
            else
                return false;
        }else
            return false;
    }
    
    public String getDetailActuallCosts(){
        String s;
        String sweet = null , item = null , delivery = null;
        if (this.delivery != null){
            delivery = this.delivery.getTransferPrice().toString();
        }
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
    
    public String getDetailDiscountCosts(){
        String s;
        if( orderStatus == OrderStatus.PAYED){
            s = "Cost before discount: "+ this.actuallCost + "\n"+
                this.discount.getDiscountInformation()+"\n"+
                "******************\n" +
                "Cost with discount: " + this.costWithDisocunt;
            return s;
        }else
            return "You should first pay your order.";
    }
    
    private BigDecimal applyDiscount(BigDecimal cost){
        BigDecimal newcost = cost;
        List<Discount> listAdmin = Admin.getInstance().getActiveDiscount(Admin.getInstance().getID());
        if(listAdmin.size() > 0){
            System.out.println("____Our App discount___\nActuall Cost: ");
            System.out.println(this.actuallCost);
        }
        for(Discount discount: listAdmin){           
            if(discount.useDiscount()){
                System.out.println(discount.getDiscountInformation());
                System.out.println("***************\n");
                if(discount.getName().equals("تخفیف اولین سفارش در اپ ما") &&
                        Admin.getInstance().firstOrder(customer)){
                    newcost = newcost.multiply(new BigDecimal(100 - discount.getPercent()));
                    newcost = newcost.divide(new BigDecimal(100));
                    System.out.println("Cost after dicount: ");
                    System.out.println(newcost);
                }else{
                    newcost = newcost.multiply(new BigDecimal(100 - discount.getPercent()));
                    newcost = newcost.divide(new BigDecimal(100));
                    System.out.println("Cost after discount: ");
                    System.out.println(newcost);
                    System.out.println("\n");
                }
            }
        }
//            System.out.println("Choose one of Admin discounts from above: ");
//            Scanner sc = new Scanner(System.in);
//            int choose = sc.nextInt();
//            Discount discount = listAdmin.get(choose - 1);
        
        List<Discount> list = Admin.getInstance().getActiveDiscount(Staff.getID());
        if(list.size() > 0)
            System.out.println("Bakery discount");
        for(Discount discount: list){
            
            if(discount.useDiscount()){
                System.out.println(discount.getDiscountInformation());
                System.out.println("***************\n");
                newcost = newcost.multiply(new BigDecimal(100 - discount.getPercent()));
                newcost = newcost.divide(new BigDecimal(100));
                System.out.println("Cost after discount: ");
                System.out.println(newcost);
                System.out.println("\n");
            }
        }
//        System.out.println("Choose one of discounts from above: ");
//        Scanner sc = new Scanner(System.in);
//        int choose = sc.nextInt();
//        Discount discount = list.get(choose - 1);
        this.costWithDisocunt = newcost;
        return newcost;
    }

    public BigDecimal getCost(){
        BigDecimal cost = new BigDecimal(0);
        for (Sweets i : sweet_score.keySet()) {
            cost = cost.add(i.getTOTAL_COST());
        }
        for (BirthdayItems b: items){
            cost = cost.add(b.getCost());
        }
        if(delivery != null){
            cost = cost.add(delivery.getTransferPrice());
        }
        this.actuallCost = cost;
        return applyDiscount(cost);
    }
    
    public String getOrderInformation(){
        String s;
        s = "____________Order information:______________\n" +
                "order id: " + this.orderId + "\n" +
                "order status: "+ this.orderStatus + "\n";
        if(orderStatus == OrderStatus.PAYED || orderStatus == OrderStatus.IN_PROGRESS || 
                orderStatus == OrderStatus.DONE || orderStatus == OrderStatus.DELIVERED)
            s += "cost: "+ this.getCost() + "\n";
        else
            s += "cost: not ready yet\n";
        s += "expected delivery time: "+ this.expectedDeliveryTime + "\n";
        if(orderStatus == OrderStatus.DELIVERED)
            s += "actuall delivery time: "+ this.delivery.getActualDeliveryTime() + "\n";
        s +=    "****************\n"+
                "coefectioner profile: " + this.Staff.getProfile() + "\n" +
                "****************\n"+
                "customer profile: \n" + this.customer.getProfile() +
                "****************\n"+
                "payment status: " + this.payment.getPaymentStatus() + "\n" +
                "____________________________\n";
        return s;
    }

    public String getCustomerProfile() {
        return customer.getProfile();
    }

    public int getStaffId() {
        return Staff.getID();
    }
}
