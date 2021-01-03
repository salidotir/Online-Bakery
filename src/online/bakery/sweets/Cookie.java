
package online.bakery.sweets;

import online.bakery.decorators.DecoratorToBuild;
import online.bakery.decorators.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cookie extends Sweets {

    public Cookie(CookieBuilder builder) {

        this.description = "Cookie: ";
        atomicInteger.incrementAndGet();
        this.SweetId = atomicInteger.get();
        this.TOTAL_COST = builder.TOTAL_COST;

    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(0);
    }

    public static class CookieBuilder {
        protected String description;
        protected BigDecimal TOTAL_Grams = new BigDecimal(0);
        protected BigDecimal TOTAL_COST = new BigDecimal(0);


        public CookieBuilder(ArrayList<DecoratorToBuild> decorators) {
            Sweets cookie = new Cookie(this);

            for (DecoratorToBuild decorator : decorators) {

                switch (decorator.decorator_name) {
                    case CHOCOLATE:
                        break;
                    case BAKING_POWDER:
                        cookie = new BakingPowderCondimentDecorator(cookie, decorator.germs, decorator.cost);
                        break;
                    case SUGAR:
                        cookie = new SugarCondimentDecorator(cookie, decorator.germs, decorator.cost);
                        break;

                    case FLOUR:

                        cookie = new FlourCondimentDecorator(cookie, decorator.germs, decorator.cost);
                        break;
                    case VANILLA:
                        cookie = new VanillaCondimentDecorator(cookie, decorator.germs, decorator.cost);
                        break;
                    case WALNUT:
                        cookie = new WalnutCondimentDecorator(cookie, decorator.germs, decorator.cost);

                        break;
                    case STRAWBERRY:
                        cookie = new StrawberryCondimentDecorator(cookie, decorator.germs, decorator.cost);

                        break;
                    case MILK:
                        cookie = new MilkCondimentDecorator(cookie, decorator.germs, decorator.cost);

                        break;
                }
            }

            //Sweets sweets = new SugarCondimentDecorator.SugarCondimentDecoratorBuilder(new FlourCondimentDecorator.FlourCondimentDecoratorBuilder(new BackingPowderCondimentDecorator.BakingPowderCondimentDecoratorBuilder(, new BigDecimal(10)).build(), new BigDecimal(100)).build(), new BigDecimal(20)).build();
            System.out.println("ID: " + cookie.SweetId + "\n" + cookie.getDescription() + "\ntotal cost:  $" + cookie.getTOTAL_COST() + " total grams: " + cookie.getTOTAL_Grams());
            TOTAL_Grams = cookie.getTOTAL_Grams();
            TOTAL_COST = cookie.getTOTAL_COST();
            description = cookie.getDescription();
        }

        public Cookie build() {
            atomicInteger.decrementAndGet();

            return new Cookie(this);

        }


    }
}
