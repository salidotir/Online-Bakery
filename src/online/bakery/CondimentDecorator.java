package online.bakery;

public abstract class CondimentDecorator extends Sweets {

    public void setGrams(double grams) {
        this.Grams = grams;
    }

    public abstract String getDescription();

    public double getGrams() {
        return Grams;
    }

}
