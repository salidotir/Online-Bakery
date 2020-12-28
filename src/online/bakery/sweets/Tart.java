
package online.bakery.sweets;

import online.bakery.decorators.DecoratorToBuild;
import online.bakery.decorators.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Tart extends Sweets {

    public Tart(TartBuilder builder) {

        this.description = "Tart: ";
        atomicInteger.incrementAndGet();
        this.SweetId = atomicInteger.get();
        this.TOTAL_COST = builder.TOTAL_COST;

    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(0);
    }

    public static class TartBuilder {
        protected String description;
        protected BigDecimal TOTAL_Grams = new BigDecimal(0);
        protected BigDecimal TOTAL_COST = new BigDecimal(0);


        public TartBuilder(ArrayList<DecoratorToBuild> decorators) {
            Sweets tart = new Tart(this);

            for (DecoratorToBuild decorator : decorators) {

                switch (decorator.decorator_name) {
                    case CHOCOLATE:
                        break;
                    case BACKGROUNDER:
                        tart = new BackingPowderCondimentDecorator(tart, decorator.germs, decorator.cost);
                        break;
                    case SUGAR:
                        tart = new SugarCondimentDecorator(tart, decorator.germs, decorator.cost);
                        break;

                    case FLOUR:

                        tart = new FlourCondimentDecorator(tart, decorator.germs, decorator.cost);
                        break;
                    case VANILLA:
                        tart = new VanillaCondimentDecorator(tart, decorator.germs, decorator.cost);
                        break;
                    case WALNUT:
                        tart = new WalnutCondimentDecorator(tart, decorator.germs, decorator.cost);

                        break;
                    case STRAWBERRY:
                        tart = new StrawberryCondimentDecorator(tart, decorator.germs, decorator.cost);

                        break;
                    case MILK:
                        tart = new MilkCondimentDecorator(tart, decorator.germs, decorator.cost);

                        break;
                }
            }

            //Sweets sweets = new SugarCondimentDecorator.SugarCondimentDecoratorBuilder(new FlourCondimentDecorator.FlourCondimentDecoratorBuilder(new BackingPowderCondimentDecorator.BakingPowderCondimentDecoratorBuilder(, new BigDecimal(10)).build(), new BigDecimal(100)).build(), new BigDecimal(20)).build();
            System.out.println("ID: " + tart.SweetId + "\n" + tart.getDescription() + "\ntotal cost:  $" + tart.getTOTAL_COST() + " total grams: " + tart.getTOTAL_Grams());
            TOTAL_Grams = tart.getTOTAL_Grams();
            TOTAL_COST = tart.getTOTAL_COST();
            description = tart.getDescription();
        }

        public Tart build() {
            atomicInteger.decrementAndGet();
            return new Tart(this);

        }


    }
}
