package online.bakery;

public abstract class Sweets {
    protected String description = "Unknown Sweets";
    protected double Grams=0.0;
    //todo id,score,producerId,designerId
    public abstract Double cost();

    public String getDescription() {
        return description;
    }
}
