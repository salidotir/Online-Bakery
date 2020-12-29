package online.bakery;

import online.bakery.decorators.Decorator;
import online.bakery.decorators.DecoratorToBuild;
import online.bakery.sweets.Cake;
import online.bakery.sweets.Cookie;
import online.bakery.sweets.Sweets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class main {
    public static void main(String[] args) {
        ArrayList<Sweets> ss = new ArrayList<Sweets>();
        ArrayList<DecoratorToBuild> decorators = new ArrayList<DecoratorToBuild>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR, new BigDecimal(100), new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR, new BigDecimal(300), new BigDecimal(40)));
        decorators.add(new DecoratorToBuild(Decorator.BACKGROUNDER, new BigDecimal(200), new BigDecimal(500)));
        Sweets s1 = new Cake.CakeBuilder(decorators).build();
        decorators.add(new DecoratorToBuild(Decorator.STRAWBERRY, new BigDecimal(200), new BigDecimal(500)));
        Sweets s2 = new Cookie.CookieBuilder(decorators).build();
        System.out.println(s1.getTOTAL_COST());
        ss.add(s1);
        ss.add(s2);
        //System.out.println(ss);

        ArrayList conf = new ArrayList<Confectioner>();
        Bakery b1 = new Bakery("شب شیرینی","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
        b1.setScore(3);
        System.out.printf(b1.getProfile());

        Discount d1 = new Discount("تخفیف یلدایی" , 20 , new Date(1399,9,20),new Date(1399,10,1),b1.getId());
        b1.addDiscount(d1.getId());

        b1.addMenu(s1.getSweetId());

        Customer c = new Customer();

        ConfectionerStatus cs1 = b1.sweetToOrder(s2);
        System.out.println(cs1.toString());
        if(cs1 == ConfectionerStatus.ACCEPT){
            c.createNewSweet(s2.getSweetId(),b1.getId());

        }
        List<Integer> orderIdC = c.getOrdersID();
        for (int i : orderIdC){
            System.out.println(i+"");
        }
    }
}
