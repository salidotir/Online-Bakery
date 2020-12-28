package online.bakery.sweets;


import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Sweets {
    public int SweetId;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public String description = "Unknown Sweets";
    public BigDecimal TOTAL_Grams = new BigDecimal(0);
    public BigDecimal TOTAL_COST = new BigDecimal(0);

    public abstract BigDecimal cost();

    public String getDescription() {
        return description;
    }

    public BigDecimal getTOTAL_Grams() {
        return TOTAL_Grams;
    }

    public BigDecimal getTOTAL_COST() {
        return TOTAL_COST;
    }
}
