package online.bakery;

import java.math.BigDecimal;
import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        ArrayList ss=new ArrayList<Sweets>();
        ArrayList<DecoratorToBuild>decorators=new ArrayList<DecoratorToBuild>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR,new BigDecimal(100),new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR,new BigDecimal(300),new BigDecimal(40)));

        decorators.add(new DecoratorToBuild(Decorator.BACKGROUNDER,new BigDecimal(200),new BigDecimal(500)));
        Sweets s1=new Cake.CakeBuilder(decorators).build();
        Sweets s2=new Cake.CakeBuilder(decorators).build();
        System.out.println(s1.getTOTAL_COST());
        ss.add(s1);
        ss.add(s2);
        //System.out.println(ss);


    }
}
