package online.bakery.birthdayItems;

import online.bakery.birthdayItems.BirthdayItems;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;



public class SnowSpray implements BirthdayItems {

    private final int itemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    String name;
    BigDecimal cost ;
    BigDecimal purchasePrice;

    public SnowSpray(String name,BigDecimal cost,BigDecimal purchasePrice){
        this.itemId=atomicInteger.incrementAndGet();
        this.name = name;
        this.cost = cost;
        this.purchasePrice = purchasePrice;
    }

    public String getDescription(){
        String s = name + "\n" + cost + " Tooman" ;
        return s;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public BigDecimal getPurchasePrice() {return  purchasePrice; }
    public void setPurchasePrice(BigDecimal price) { this.purchasePrice = price; }


}
