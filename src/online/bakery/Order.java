package online.bakery;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import online.bakery.Discount.Discount;
import online.bakery.Note.Note;
import online.bakery.Status.ConfectionerStatus;
import online.bakery.Type.SweetType;
import online.bakery.Status.OrderStatus;
import online.bakery.Type.PaymentType;
import online.bakery.birthdayItems.BirthdayItems;
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
    private Bakery bakery;
    private Baker baker;
    private int chooseTypeBaker;    // 1 for Bakery; 0 for Baker
    private Payment payment;
    private Date expectedDeliveryTime;
    private DeliveryInformation delivery = null;
    private List<Discount> listDiscounts = new ArrayList<>();
    private Cost costOrder;
    private Note note;
    
    public Order(Customer customer, List<Sweets> Sweets, List<BirthdayItems> items, Date expectedDeliveryTime) {
        this.customer = customer;
        this.orderId = atomicInteger.incrementAndGet();
        Sweets.forEach((s) -> {
            sweet_score.put(s, null);
        });
        this.items = items;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.orderStatus = OrderStatus.ORDERING_BY_CUSTOMER;
        this.note = new Note(this.orderId, this.customer.getID());
        Admin.getInstance().addOrder(this);
    }
    
    public boolean addNote(Account account, String extraText){
        switch(account.role){
            case CUSTOMER:
                if (account.getID() == note.getNoteCustomerId()) {
                    note.setNoteCustomerText(extraText);
                }
                return true;
            case BAKERY:
            case BAKER:
                if (account.getID() == note.getNoteSellerId()) {
                    note.setNoteSellerText(extraText);
                }
                return true;
            case ADMIN:
                note.setNoteAdminText(extraText);
                return true;
            case EMPLOYEE:
                note.setNoteEmployeeText(extraText);
                return true;
        }
        return false;
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
    
    public AbstractMap.SimpleEntry chooseBaker(Baker staff, List<SweetType> s){
        if(orderStatus == OrderStatus.FINALIZED_BY_CUSTOMER){
            this.baker = staff;
            this.bakery = null;
            this.chooseTypeBaker = 0;
            List<ConfectionerStatus> cs = staff.acceptOrder(this, s);
            orderStatus = OrderStatus.PENDING_CHOSEN_BAKER;
            note.setNoteSellerId(staff.getID());
            return new AbstractMap.SimpleEntry(true, cs);
        }else
            return new AbstractMap.SimpleEntry(false, null);
    }
    
    public AbstractMap.SimpleEntry chooseBakery(Bakery staff, List<SweetType> s){
        if(orderStatus == OrderStatus.FINALIZED_BY_CUSTOMER){
            this.bakery = staff;
            this.baker = null;
            this.chooseTypeBaker = 1;
            List<ConfectionerStatus> cs = staff.acceptOrder(this, s);
            orderStatus = OrderStatus.PENDING_CHOSEN_BAKER;
            note.setNoteSellerId(staff.getID());
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
            PaymentType paymentType = Payment.howToPay();
            this.payment = new Payment(new Date(), cost, description, paymentType);
            customer.addPayment(payment);
            boolean result = payment.pay(payment, this.customer, Admin.getInstance());
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
            if(chooseTypeBaker == 1)
                this.bakery.addOrder(this, s);
            else if(chooseTypeBaker == 0)
                this.baker.addOrder(this, s);
            return true;
        }else
            return false;
    }
    
    public boolean callDelivery(){
        if(orderStatus == OrderStatus.IN_PROGRESS){
            AbstractMap.SimpleEntry adminDiscount = costOrder.getAdminCostDiscount();
            AbstractMap.SimpleEntry adminGiftBaker = costOrder.getAdminCostGiftCardBaker();
            AbstractMap.SimpleEntry CustomerPayBaker = costOrder.getCostSweetWithGiftCard();
            
            if(!(boolean)adminDiscount.getKey()
                    || !(boolean)adminGiftBaker.getKey()
                    || !(boolean)CustomerPayBaker.getKey())
                return false;
            
            BigDecimal totalAdmin2Baker = (BigDecimal)adminDiscount.getValue();
            totalAdmin2Baker = totalAdmin2Baker.add((BigDecimal)adminGiftBaker.getValue());
            totalAdmin2Baker = totalAdmin2Baker.add((BigDecimal)CustomerPayBaker.getValue());
            
            System.out.println("___________________________Admin terminal______________________________");
            System.out.println("_____Baker_____");
            System.out.println("Please Pay discount diffrence cost");
            System.out.print("Diffrence Cost: ");
            System.out.println((BigDecimal)adminDiscount.getValue());
            System.out.println("Please Pay the GiftCard for Baker");
            System.out.print("Amount of GiftCard used: ");
            System.out.println((BigDecimal)adminGiftBaker.getValue());
            System.out.println("Please Pay cost which customer payed to you.");
            System.out.print("Amount of customer payed: ");
            System.out.println((BigDecimal)CustomerPayBaker.getValue());
            
            boolean result = false;
            while(!result){
                System.out.print("Total Amount you must pay: ");
                System.out.println(totalAdmin2Baker);
                if(chooseTypeBaker == 1)
                    result = Admin.getInstance().paytoAccount(totalAdmin2Baker, this.bakery, "pay order to bakery");
                else if(chooseTypeBaker == 0)
                    result = Admin.getInstance().paytoAccount(totalAdmin2Baker, this.baker, "pay order to baker");
                if(!result)
                    System.out.println("Payment Status was unsuccessful. Please try again.");
            }
            System.out.println("___________________________Finish terminal_____________________________");


            orderStatus = OrderStatus.DONE;
            DeliverySystem.getDeliverySystem().addOrderToOrderQueue(this);
            return true;
        }else
            return false;
    }
    
    public boolean finishOrder(Date actuallDelivery, Employee employee){
        System.out.println("finish");
        if(orderStatus == OrderStatus.ON_THE_WAY){
            orderStatus = OrderStatus.DELIVERED;
            delivery.setActualDeliveryTime(actuallDelivery);
            
            
            AbstractMap.SimpleEntry adminGiftEmploee = costOrder.getAdminCostGiftCardEmploee();
            AbstractMap.SimpleEntry CustomerPayEmploee = costOrder.getCostDeliveryWithGiftCard();
            
            if(!(boolean)adminGiftEmploee.getKey()
                    || !(boolean)CustomerPayEmploee.getKey())
                return false;
            
            BigDecimal totalAdmin2Employee = (BigDecimal)adminGiftEmploee.getValue();
            totalAdmin2Employee = totalAdmin2Employee.add((BigDecimal)CustomerPayEmploee.getValue());
            
            System.out.println("___________________________Admin terminal______________________________");
            System.out.println("_____Employee_____");
            System.out.println("Please Pay the GiftCard for Employee");
            System.out.print("Amount of GiftCard used: ");
            System.out.println((BigDecimal)adminGiftEmploee.getValue());
            System.out.println("Please Pay cost which customer payed to you.");
            System.out.print("Amount of customer payed: ");
            System.out.println((BigDecimal)CustomerPayEmploee.getValue());
            
            boolean result = false;
            while(!result){
                System.out.print("Total Amount you must pay: ");
                System.out.println(totalAdmin2Employee);
                result = Admin.getInstance().paytoAccount(totalAdmin2Employee, employee, "pay order to bakery");
                if(!result)
                    System.out.println("Payment Status was unsuccessful. Please try again.");
            }
            System.out.println("___________________________Finish terminal_____________________________");

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
                if(chooseTypeBaker == 1)
                    bakery.setScore(score,sweet);
                else if(chooseTypeBaker == 0)
                    baker.setScore(score, sweet);
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
            sweet += i.get_OderCost().toString();
            sweet += "\n";
        }
        for (BirthdayItems b: items){
            item += b.getDescription();
            item += " : ";
            item += b.getCost().toString();
            item += "\n";
        }
        s = "Detail Costs: "+
            "    Cost of Sweets: \n"+ sweet +
            "    Cost of items: \n" + item +
            "    Cost of delivery: \n" + delivery;
        return s;
    }
    
    // todo: change public 
    public boolean addDiscount(Discount discount){
        this.listDiscounts.add(discount);
        return true;
    }
    
    private BigDecimal getCost(){
        BigDecimal sweet_cost = new BigDecimal(0);
        for (Sweets i : sweet_score.keySet()) {
            sweet_cost = sweet_cost.add(i.get_OderCost());
        }
        BigDecimal item_cost = new BigDecimal(0);
        for (BirthdayItems b: items){
            item_cost = item_cost.add(b.getCost());
        }
        BigDecimal delivery_cost = new BigDecimal(0);
        if(delivery != null){
            delivery_cost = delivery_cost.add(delivery.getTransferPrice());
        }
        costOrder = new Cost(this, sweet_cost, item_cost, delivery_cost);
        return costOrder.getCost();
    }
    
    public String getDetailCosts(){
        if (this.costOrder != null)
            return this.costOrder.getDetailCosts();
        else
            return "Not ready yet.";
    }
    
    public String getOrderInformation(){
        String s;
        s = "____________Order information:______________\n" +
                "order id: " + this.orderId + "\n" +
                "order status: "+ this.orderStatus + "\n";
        if(orderStatus.ordinal() >= 7)
            s += getDetailCosts() + "\n";
        else
            s += "cost: Not ready yet\n";
        s += "expected delivery time: "+ this.expectedDeliveryTime + "\n";
        if(orderStatus == OrderStatus.DELIVERED)
            s += "actuall delivery time: "+ this.delivery.getActualDeliveryTime() + "\n";
        s += "****************\n"+
                note.getNoteInformation();
        
        if(orderStatus == OrderStatus.ACCEPTED){
            if(chooseTypeBaker == 1)
                s += "coefectioner profile: " + this.bakery.getProfile() + "\n";
            else if(chooseTypeBaker == 0)
                s += "coefectioner profile: " + this.baker.getProfile() + "\n";
        }
        
        s +=    "****************\n"+
                "customer profile: \n" + this.customer.getProfile() +
                "****************\n";
        if(orderStatus == OrderStatus.PAYED)
            s += "payment status: " + this.payment.getPaymentStatus() + "\n" +
                "____________________________\n";
        return s;
    }

    public String getCustomerProfile() {
        return customer.getProfile();
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getBakerId() {
        if(chooseTypeBaker == 1 && bakery != null)
            return bakery.getID();
        else if(chooseTypeBaker == 0 && baker != null)
            return baker.getID();
        return -1;
    }
    
    //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.
    
    // this function is called on the order by deliverySystem to notify the order that is going to be delivered now.
    // or the order must wait.
    public boolean isAvailableToShip(boolean isAvailable) {
        if(orderStatus == OrderStatus.DONE || orderStatus == OrderStatus.WAITING){
            // order can be shipped now
            if (isAvailable == true) {
                orderStatus = OrderStatus.ON_THE_WAY;
                System.out.println("Order with id " + this.orderId + " is going to be shipped right now.");
            }
            // order must wait for shipping
            else if(isAvailable == false) {
                orderStatus = OrderStatus.WAITING;
                System.out.println("Order with id " + this.orderId + " must wait for shipping.");            
            }
            return isAvailable;
        }else
            return false;
    }
    
    // if there is no shipping available for the order
    public boolean cancelByDelivery(){
        if( orderStatus == OrderStatus.DONE){
            orderStatus = OrderStatus.CANCELED_BY_DELIVERY;
            return true;
        }else
            return false;
    }    
    
    boolean ruinByEmployee() {
        if( orderStatus == OrderStatus.ON_THE_WAY){
            orderStatus = OrderStatus.RUINED_BY_EMPLOYEE;
            return true;
        }
        else {
            return false;
        }
    }

    public List<Discount> getDiscount() {
        return  this.listDiscounts;
    }
}
