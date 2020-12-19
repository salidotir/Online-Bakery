package online.bakery;

import java.math.BigDecimal;

public class SugarCondimentDecorator extends CondimentDecorator {
    private Sweets sweets;
    private BigDecimal COST;
    private BigDecimal grams;
    public static BigDecimal DEFAULT_COST = new BigDecimal(100);

    public static class SugarCondimentDecoratorBuilder {

        private Sweets sweets;
        private BigDecimal COST = DEFAULT_COST;
        private BigDecimal grams;


        public SugarCondimentDecoratorBuilder setCOST(BigDecimal cost) {
            this.COST = cost;
            return this;
        }

        public SugarCondimentDecoratorBuilder(Sweets sweets, BigDecimal grams) {
            this.sweets = sweets;
            this.grams = grams;

        }

        public SugarCondimentDecorator build() {
            return new SugarCondimentDecorator(this);
        }
    }

    private SugarCondimentDecorator(SugarCondimentDecoratorBuilder builder) {
        this.sweets = builder.sweets;
        this.grams = builder.grams;
        this.COST = builder.COST;
        TOTAL_Grams = builder.sweets.TOTAL_Grams.add(builder.grams);
        TOTAL_COST = cost();
        SweetId = atomicInteger.decrementAndGet();
        description = builder.sweets.description + " + " + this.grams + "Sugar ";
    }


    @Override
    public String getDescription() {

        this.description = sweets.description + " + " + this.grams + " Sugar";
        return sweets.description + " + " + this.grams + " Sugar";
    }

    @Override
    public BigDecimal cost() {
        return sweets.TOTAL_COST.add(COST.multiply(this.grams));
    }
}
