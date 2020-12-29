package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;



public class SnowSpray implements BirthdayItems {

    private final int itemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    String description;
    BigDecimal cost ;

    public SnowSpray(String description,BigDecimal cost){
        this.itemId=atomicInteger.incrementAndGet();
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description; }
    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }


}
