package online.bakery;


import java.math.BigDecimal;

public class FlourCondimentDecorator extends CondimentDecorator {
    Sweets sweets;
    BigDecimal COST;


    public FlourCondimentDecorator(Sweets sweets, BigDecimal grams, BigDecimal cost) {
        this.sweets = sweets;
        this.COST = cost;
        setGrams(grams);
        description = sweets.description + " + " + getGrams() + " Flour";
    }

    @Override
    public String getDescription() {
        this.description = sweets.description + " + " + getGrams() + " Flour";
        return sweets.description + " + " + getGrams() + " Flour";
    }

    @Override
    public BigDecimal cost() {
        return sweets.cost().add(COST.multiply(getGrams()));
    }
}
