package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BirthdayItems {
    protected int ItemId;
    protected static AtomicInteger atomicInteger = new AtomicInteger(2);
    protected String description = "Unknown Items";
    protected BigDecimal TOTAL_COST = new BigDecimal(0);

    public abstract BigDecimal cost();

    public String getDescription() {
        return description;
    }

    public BigDecimal getTOTAL_COST() {
        return TOTAL_COST;
    }
}