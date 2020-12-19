package online.bakery;

import java.math.BigDecimal;

public class BakingPowderCondimentDecorator extends CondimentDecorator {
    private final Sweets sweets;
    BigDecimal COST ;

    public BakingPowderCondimentDecorator(Sweets sweets, BigDecimal grams, BigDecimal Cost) {
        this.sweets = sweets;
        this.COST=Cost;
        setGrams(grams);
        description=sweets.description + " + " + getGrams() + " BackingPowder";

    }

    @Override
    public String getDescription() {

        this.description=sweets.description + " + " + getGrams() + " BackingPowder";
        return sweets.description + " + " + getGrams() + " BackingPowder";
    }

    @Override
    public BigDecimal cost() {
        return sweets.cost().add(COST.multiply(getGrams()));
    }
}
