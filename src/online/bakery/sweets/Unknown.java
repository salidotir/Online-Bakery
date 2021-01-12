package online.bakery.sweets;

import online.bakery.Type.TypeOfSweets;
import online.bakery.decorators.*;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Unknown extends Sweets {

    public Unknown(UnknownBuilder builder) {

        this.type= TypeOfSweets.UNKNOWN;
        this.description="Unknown: ";
        if (builder.description!=null){
            this.description =builder.description;}
        atomicInteger.incrementAndGet();
        this.SweetId = atomicInteger.get();
        this.TOTAL_COST = builder.TOTAL_COST;
        this.TOTAL_Grams = builder.TOTAL_Grams;

    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(0);
    }

    public static class UnknownBuilder {
        protected String description;
        protected BigDecimal TOTAL_Grams = new BigDecimal(0);
        protected BigDecimal TOTAL_COST = new BigDecimal(0);


        public UnknownBuilder(ArrayList<DecoratorToBuild> decorators) {
            Sweets unknown = new Unknown(this);
            for (DecoratorToBuild decorator : decorators) {

                switch (decorator.decorator_name) {
                    case CHOCOLATE:
                        unknown = new ChocolateCondimentDecorator(unknown, decorator.germs, decorator.cost);

                        break;
                    case BAKING_POWDER:
                        unknown = new BakingPowderCondimentDecorator(unknown, decorator.germs, decorator.cost);
                        break;
                    case SUGAR:
                        unknown = new SugarCondimentDecorator(unknown, decorator.germs, decorator.cost);
                        break;

                    case FLOUR:

                        unknown = new FlourCondimentDecorator(unknown, decorator.germs, decorator.cost);
                        break;
                    case VANILLA:
                        unknown = new VanillaCondimentDecorator(unknown, decorator.germs, decorator.cost);
                        break;
                    case WALNUT:
                        unknown = new WalnutCondimentDecorator(unknown, decorator.germs, decorator.cost);

                        break;
                    case STRAWBERRY:
                        unknown = new StrawberryCondimentDecorator(unknown, decorator.germs, decorator.cost);

                        break;
                    case MILK:
                        unknown = new MilkCondimentDecorator(unknown, decorator.germs, decorator.cost);

                        break;
                }
            }

            //Sweets sweets = new SugarCondimentDecorator.SugarCondimentDecoratorBuilder(new FlourCondimentDecorator.FlourCondimentDecoratorBuilder(new BackingPowderCondimentDecorator.BakingPowderCondimentDecoratorBuilder(, new BigDecimal(10)).build(), new BigDecimal(100)).build(), new BigDecimal(20)).build();
            System.out.println("ID: " + unknown.SweetId + "\n" + unknown.getDescription() + "\ntotal cost:  $" + unknown.getTOTAL_COST() + " total grams: " + unknown.getTOTAL_Grams());
            TOTAL_Grams = unknown.getTOTAL_Grams();
            TOTAL_COST = unknown.getTOTAL_COST();
            description = unknown.getDescription();
        }

        public Unknown build() {
            atomicInteger.decrementAndGet();

            return new Unknown(this);

        }


    }
}
