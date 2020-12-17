package online.bakery;

public class Cake extends Sweets {
    public Cake(){
        this.description="Cake";
    }
    @Override
    public Double cost() {
        return 0.0;
    }
}
