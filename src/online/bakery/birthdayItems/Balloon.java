package online.bakery.birthdayItems;

import online.bakery.birthdayItems.BirthdayItems;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Balloon implements BirthdayItems {

    private final int itemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    String name;
    String material;
    String color;
    BigDecimal cost ;
    BigDecimal purchasePrice;

    public Balloon(String name,BigDecimal cost,BigDecimal purchasePrice,String material,String color){
        this.itemId=atomicInteger.incrementAndGet();
        this.name = name;
        this.cost = cost;
        this.purchasePrice = purchasePrice;
        this.material = material;
        this.color = color;
    }

    public int getItemId() {
        return itemId;
    }

    public String getDescription(){
        String s = name + "\nmaterial : " + material + "\n color : "+color + "\n" + cost + " Tooman" ;
        return s;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name; }

    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public BigDecimal getPurchasePrice() {return  purchasePrice; }
    public void setPurchasePrice(BigDecimal price) { this.purchasePrice = price; }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {this.color = color; }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {this.material = material; }
}
