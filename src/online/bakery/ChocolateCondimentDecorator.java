package online.bakery;


import java.math.BigDecimal;

public class ChocolateCondimentDecorator extends CondimentDecorator {
    private Sweets sweets;
    private BigDecimal COST;
    private BigDecimal grams;


    public ChocolateCondimentDecorator(Sweets sweets, BigDecimal grams, BigDecimal cost) {
        this.sweets = sweets;
        this.grams = grams;
        this.COST = cost;
        this.SweetId = sweets.SweetId;


        TOTAL_Grams = sweets.TOTAL_Grams.add(grams);
        TOTAL_COST = cost();
        description = sweets.description + " + " + this.grams + " Chocolate ";
    }


    @Override
    public String getDescription() {

        this.description = sweets.description + " + " + this.grams + " Chocolate";
        return sweets.description + " + " + this.grams + " Chocolate";
    }

    @Override

    public BigDecimal cost() {
        return sweets.TOTAL_COST.add(COST.multiply(this.grams));
    }
}
