package online.bakery;

import java.math.BigDecimal;

public abstract class CondimentDecorator extends Sweets {

    public abstract String getDescription();

    public BigDecimal getTOTAL_Grams() {
        return TOTAL_Grams;
    }

}
