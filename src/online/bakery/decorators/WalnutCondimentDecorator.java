package online.bakery.decorators;

import online.bakery.sweets.Sweets;

import java.math.BigDecimal;

public class WalnutCondimentDecorator extends CondimentDecorator {
    private Sweets sweets;
    private BigDecimal COST;
    private BigDecimal grams;

    public WalnutCondimentDecorator(Sweets sweets, BigDecimal grams, BigDecimal cost) {
        this.sweets = sweets;
        this.grams = grams;

        this.COST = cost;
        TOTAL_Grams = sweets.TOTAL_Grams.add(grams);
        TOTAL_COST = cost();

        this.SweetId = sweets.SweetId;
        description = sweets.description + " + " + this.grams + "Walnut ";
    }


    @Override
    public String getDescription() {

        this.description = sweets.description + " + " + this.grams + " Walnut";
        return sweets.description + " + " + this.grams + " Walnut";
    }

    @Override
    public BigDecimal cost() {
        return sweets.TOTAL_COST.add(COST.multiply(this.grams));
    }
}
