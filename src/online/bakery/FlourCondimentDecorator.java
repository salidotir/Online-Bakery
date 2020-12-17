package online.bakery;


public class FlourCondimentDecorator extends CondimentDecorator {
    Sweets sweets;
    double COST ;


    public FlourCondimentDecorator(Sweets sweets,double grams,double cost) {
        this.sweets = sweets;
        this.COST=cost;
        setGrams(grams);

        description=sweets.description + " + " + getGrams() + " Flour";
    }

    @Override
    public String getDescription() {
        this.description=sweets.description + " + " + getGrams() + " Flour";
        return sweets.description + " + " + getGrams() + " Flour";
    }

    @Override
    public Double cost() {
        return sweets.cost() + getGrams() * COST;
    }
}
