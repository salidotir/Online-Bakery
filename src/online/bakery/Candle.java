package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Candle implements BirthdayItems {

    int ItemId ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    String description;
    BigDecimal TOTAL_COST = new BigDecimal(0);

    public Candle(){
        this.description = "Candle: ";
        atomicInteger.incrementAndGet();
        this.ItemId=atomicInteger.incrementAndGet();

    }

    public BigDecimal cost() {
        return new BigDecimal(0);
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getTOTAL_COST() {
        return TOTAL_COST;
    }


}
