package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Candle implements BirthdayItems {

    private final int itemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    String description;
    String number;
    String color;
    BigDecimal cost ;

    public Candle(String description,BigDecimal cost,String number,String color){
        this.itemId=atomicInteger.incrementAndGet();
        this.description = description;
        this.cost = cost;
        this.number = number;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description; }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {this.color = color; }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {this.number = number; }


}
