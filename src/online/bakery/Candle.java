package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Candle implements BirthdayItems {

    private final int itemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    String name;
    String number;
    String color;
    BigDecimal cost ;
    BigDecimal purchasePrice;

    public Candle(String name,BigDecimal cost,BigDecimal purchasePrice,String number,String color){
        this.itemId=atomicInteger.incrementAndGet();
        this.name = name;
        this.cost = cost;
        this.purchasePrice = purchasePrice;
        this.number = number;
        this.color = color;
    }

    public String getDescription(){
        String s = name + "  " + number + "\n color : "+color + "\n" + cost + " Tooman" ;
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

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {this.number = number; }


}
