package online.bakery;

import java.math.BigDecimal;

public abstract class CondimentDecorator extends Sweets {

    public void setGrams(BigDecimal grams) {
        this.Grams = grams;
    }

    public abstract String getDescription();

    public BigDecimal getGrams() {
        return Grams;
    }

}
