package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;



public class SnowSpray implements BirthdayItems {

    int itemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    String description;
    BigDecimal cost ;

    public SnowSpray(String description,BigDecimal cost){
        this.description = description;
        this.cost = cost;
        atomicInteger.incrementAndGet();
        this.itemId=atomicInteger.incrementAndGet();

    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description; }
    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }


}
