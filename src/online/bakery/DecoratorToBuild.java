package online.bakery;

import java.math.BigDecimal;

public class DecoratorToBuild {
    Decorator decorator_name;
    BigDecimal germs;
    BigDecimal cost;
    DecoratorToBuild(Decorator my_decorator,BigDecimal germs,BigDecimal cost){
        this.decorator_name=my_decorator;
        this.cost=cost;
        this.germs=germs;
    }
}
