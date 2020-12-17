package online.bakery;

public class BakingPowderCondimentDecorator extends CondimentDecorator {
    private final Sweets sweets;
    double COST ;

    public BakingPowderCondimentDecorator(Sweets sweets,double grams,double Cost) {
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
    public Double cost() {
        return sweets.cost() + getGrams() * COST;
    }
}
