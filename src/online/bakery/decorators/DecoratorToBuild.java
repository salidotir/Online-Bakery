package online.bakery.decorators;

import java.math.BigDecimal;

public class DecoratorToBuild {
    public Decorator decorator_name;
    public BigDecimal germs;
    public BigDecimal cost;
    public DecoratorToBuild(Decorator my_decorator, BigDecimal germs, BigDecimal cost){
        this.decorator_name=my_decorator;
        this.cost=cost;
        this.germs=germs;
    }
}
