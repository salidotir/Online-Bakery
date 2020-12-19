package online.bakery;

import java.math.BigDecimal;

public class Cake extends Sweets {
    public Cake(){
        this.description="Cake";
    }
    @Override
    public BigDecimal cost() {
        return new BigDecimal(0);
    }
}
