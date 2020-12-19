package online.bakery;


import java.math.BigDecimal;

public class FlourCondimentDecorator extends CondimentDecorator {
    private Sweets sweets;
    private BigDecimal COST;
    private BigDecimal grams;
    public static BigDecimal DEFAULT_COST = new BigDecimal(100);

    public static class FlourCondimentDecoratorBuilder {

        private Sweets sweets;
        private BigDecimal COST = DEFAULT_COST;
        private BigDecimal grams;


        public FlourCondimentDecoratorBuilder setCOST(BigDecimal cost) {
            this.COST = cost;
            return this;
        }

        public FlourCondimentDecoratorBuilder(Sweets sweets, BigDecimal grams) {
            this.sweets = sweets;
            this.grams = grams;

        }

        public FlourCondimentDecorator build() {
            return new FlourCondimentDecorator(this);
        }
    }

    private FlourCondimentDecorator(FlourCondimentDecoratorBuilder builder) {
        this.sweets = builder.sweets;
        this.grams = builder.grams;
        this.COST = builder.COST;
        TOTAL_Grams = builder.sweets.TOTAL_Grams.add(builder.grams);
        TOTAL_COST=cost();
        description = builder.sweets.description + " + " + this.grams + " Flour";
    }


    @Override
    public String getDescription() {

        this.description = sweets.description + " + " + this.grams + " Flour";
        return sweets.description + " + " + this.grams + " Flour";
    }

    @Override
    public BigDecimal cost() {
        return sweets.TOTAL_COST.add(COST.multiply(this.grams));
    }
}