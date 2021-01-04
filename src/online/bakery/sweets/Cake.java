package online.bakery.sweets;

import online.bakery.decorators.DecoratorToBuild;
import online.bakery.decorators.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cake extends Sweets {

    public Cake(CakeBuilder builder) {

        this.type= TypeOfSweets.CAKE;
        this.description="Cake: ";
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

    public static class CakeBuilder {
        protected String description;
        protected BigDecimal TOTAL_Grams = new BigDecimal(0);
        protected BigDecimal TOTAL_COST = new BigDecimal(0);


        public CakeBuilder(ArrayList<DecoratorToBuild> decorators) {
            Sweets cake = new Cake(this);
            for (DecoratorToBuild decorator : decorators) {

                switch (decorator.decorator_name) {
                    case CHOCOLATE:
                        break;
                    case BAKING_POWDER:
                        cake = new BakingPowderCondimentDecorator(cake, decorator.germs, decorator.cost);
                        break;
                    case SUGAR:
                        cake = new SugarCondimentDecorator(cake, decorator.germs, decorator.cost);
                        break;

                    case FLOUR:

                        cake = new FlourCondimentDecorator(cake, decorator.germs, decorator.cost);
                        break;
                    case VANILLA:
                        cake = new VanillaCondimentDecorator(cake, decorator.germs, decorator.cost);
                        break;
                    case WALNUT:
                        cake = new WalnutCondimentDecorator(cake, decorator.germs, decorator.cost);

                        break;
                    case STRAWBERRY:
                        cake = new StrawberryCondimentDecorator(cake, decorator.germs, decorator.cost);

                        break;
                    case MILK:
                        cake = new MilkCondimentDecorator(cake, decorator.germs, decorator.cost);

                        break;
                }
            }

            //Sweets sweets = new SugarCondimentDecorator.SugarCondimentDecoratorBuilder(new FlourCondimentDecorator.FlourCondimentDecoratorBuilder(new BackingPowderCondimentDecorator.BakingPowderCondimentDecoratorBuilder(, new BigDecimal(10)).build(), new BigDecimal(100)).build(), new BigDecimal(20)).build();
            System.out.println("ID: " + cake.SweetId + "\n" + cake.getDescription() + "\ntotal cost:  $" + cake.getTOTAL_COST() + " total grams: " + cake.getTOTAL_Grams());
            TOTAL_Grams = cake.getTOTAL_Grams();
            TOTAL_COST = cake.getTOTAL_COST();
            description = cake.getDescription();
        }

        public Cake build() {
            atomicInteger.decrementAndGet();

            return new Cake(this);

        }


    }
}
