package online.bakery;

import java.math.BigDecimal;

public class BackingPowderCondimentDecorator extends CondimentDecorator {
    private Sweets sweets;
    private BigDecimal COST;
    private BigDecimal grams;
    public static BigDecimal DEFAULT_COST = new BigDecimal(100);


    public BackingPowderCondimentDecorator(Sweets sweets, BigDecimal grams, BigDecimal cost) {
        this.sweets = sweets;
        this.grams = grams;
        this.COST = cost;


        TOTAL_Grams = sweets.TOTAL_Grams.add(grams);
        TOTAL_COST = cost();
        SweetId = atomicInteger.decrementAndGet();
        description = sweets.description + " + " + this.grams + " BackingPowder ";
    }


    @Override
    public String getDescription() {

        this.description = sweets.description + " + " + this.grams + " BackingPowder";
        return sweets.description + " + " + this.grams + " BackingPowder";
    }

    @Override

    public BigDecimal cost() {
        return sweets.TOTAL_COST.add(COST.multiply(this.grams));
    }
}
