package online.bakery;

import java.math.BigDecimal;

public class Cake extends Sweets {
    public Cake(CakeBuilder builder) {
        this.description = "Cake: ";
        atomicInteger.incrementAndGet();
        this.SweetId=atomicInteger.incrementAndGet();
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(0);
    }

    public static class CakeBuilder {

        public CakeBuilder() {
            Sweets sweets =new SugarCondimentDecorator.SugarCondimentDecoratorBuilder(new FlourCondimentDecorator.FlourCondimentDecoratorBuilder(new BakingPowderCondimentDecorator.BakingPowderCondimentDecoratorBuilder(new Cake(this), new BigDecimal(10)).build(),new BigDecimal(100)).build(),new BigDecimal(20)).build();
            System.out.println("ID: "+sweets.SweetId+"\n" +sweets.getDescription() + "\ntotal cost:  $"+ sweets.getTOTAL_COST()+ " total grams: "+sweets.getTOTAL_Grams());


        }
        public Cake build(){
            return new Cake(this);
        }


    }
}
