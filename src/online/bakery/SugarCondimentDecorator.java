package online.bakery;

import java.math.BigDecimal;

public class SugarCondimentDecorator extends CondimentDecorator {
    private final Sweets sweets;
    BigDecimal COST;

    public SugarCondimentDecorator(Sweets sweets, BigDecimal grams, BigDecimal cost) {
        this.sweets = sweets;
        this.COST = cost;
        setGrams(grams);
        description = sweets.description + " + " + getGrams() + " Sugar";

    }

    @Override
    public String getDescription() {

        this.description = sweets.description + " + " + getGrams() + " Sugar";
        return sweets.description + " + " + getGrams() + " Sugar";
    }

    @Override
    public BigDecimal cost() {
        return sweets.cost().add(COST.multiply(getGrams()));
    }
}
