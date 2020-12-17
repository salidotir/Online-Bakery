package online.bakery;

public class SugarCondimentDecorator extends CondimentDecorator {
    private final Sweets sweets;
    double COST ;

    public SugarCondimentDecorator(Sweets sweets,double grams,double cost) {
        this.sweets = sweets;
        this.COST=cost;
        setGrams(grams);
        description=sweets.description + " + " + getGrams() + " Sugar";

    }
    @Override
    public String getDescription() {

        this.description=sweets.description + " + " + getGrams() + " Sugar";
        return sweets.description + " + " + getGrams() + " Sugar";
    }
    @Override
    public Double cost() {
        return sweets.cost() + getGrams() * COST;
    }
}
