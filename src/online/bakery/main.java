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
        test1();

//        test2();
    }
    
    // test2 -> test note & payment & delivery
    private static void test2() {
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
        Bakery b1 = new Bakery("شب شیرینی","علی","شریعتی","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
        b1.setScore(3);
        System.out.printf(b1.getProfile());

        Discount d1 = new Discount("تخفیف یلدایی" , 20 , new Date(1399,9,20),new Date(1399,10,1),b1.getID());
        b1.addDiscount(d1);

        b1.addMenu(s1);
        
        //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~

        // create customer-1 with wallet

        Customer c = new Customer("fm","1234");
        c.setFirstname("فاطمه");
        c.setLastname("مسعودی");
        c.setContactNo("۰۹۱۷۲۲۳۲۸۳۵");
        c.setAddress("شیراز شهرک شهید باهنر");

        // customer-1 order sweet 2 , 1
        List<Sweets> sList = new ArrayList<Sweets>();
        List<SweetType> stList = new ArrayList<SweetType>();
        sList.add(s2);
        stList.add(SweetType.CREATE_CUSTOMER);

        sList.add(s1);
        stList.add(SweetType.READY);

        ConfectionerStatus cs1 = b1.sweetToOrder(sList,stList,c);
        System.out.println(cs1.toString());
        if(cs1 == ConfectionerStatus.ACCEPT){
            List<BirthdayItems> listitem = new ArrayList<BirthdayItems>();
            Order o1 = c.createNewSweet(sList,b1, listitem);
            b1.addOrder(o1);

        }
        
        
        // print customer1 order-ids
        List<Order> orderIdC = c.getOrders();
        for (Order i : orderIdC){
            System.out.println(i+"");
        }
        
        //~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~
                
        // create employee
        Employee e1 = new Employee("سارا", "لیمویی");        
        
        // request for a delivery for customer-1 order
        DeliveryInformation c1_delivery1 = DeliveryInformation.createNewDelivery(c.getOrdersID().get(0), e1.getID(), "معالی آباد، کوچه ی سوم", new Date(2021, 1, 15, 16, 30));
        System.out.println(c1_delivery1.getDeliveryInformation());
        
        // create a note for the order
        Note order1_note = new Note(c.getOrdersID().get(0), b1.getID(), c.getID(), e1.getID());
        // employee add a text to note
        e1.addNote(order1_note, "تحویل داده خواهد شد توسط اینجانب");        
        System.out.println(order1_note.getNoteInformation());
        
        // pay the order
        PaymentType paymentType = Payment.howToPay();
        Payment payment = new Payment(c.getOrdersID().get(0), c.getID(), new Date(), new BigDecimal(400), "خرید کیک از شب شیرینی", paymentType);

        Payment walletChargePay = Payment.chargeWallet(c, "شارژ کیف پول");
        System.out.println(walletChargePay.getPaymentInformation());
        
        System.out.println(c.getWallet().getWalletInformation());
        //System.out.println(payment.getPaymentInformation());

        if (payment.pay(payment, c) == true) {
            System.out.println(payment.getPaymentInformation());
            
            // deliver the order
            e1.deliverOrder(c1_delivery1);
            System.out.println(c1_delivery1.getDeliveryInformation());
        }

        //System.out.println(payment.getPaymentInformation());
        System.out.println(c.getWallet().getWalletInformation());
        
        
    }
    
    
    // test1 -> test sweets and bakery and confectionary
    private static void test1() {
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

        Bakery b1 = new Bakery("شب شیرینی","علی","شریعتی","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
        b1.setScore(3);
        System.out.printf(b1.getProfile());

        Discount d1 = new Discount("تخفیف یلدایی" , 20 , new Date(1399,9,20),new Date(1399,10,1),b1.getID());
        b1.addDiscount(d1);

        b1.addReadySweet(s1);

        Customer c = new Customer("fm","1234");
        c.setFirstname("فاطمه");
        c.setLastname("مسعودی");
        c.setContactNo("۰۹۱۷۲۲۳۲۸۳۵");
        c.setAddress("شیراز شهرک شهید باهنر");
        
        List<Sweets> sList = new ArrayList<Sweets>();
        List<SweetType> stList = new ArrayList<SweetType>();
        sList.add(s2);
        stList.add(SweetType.CREATE_CUSTOMER);

        sList.add(s1);
        stList.add(SweetType.READY);

        ConfectionerStatus cs1 = b1.sweetToOrder(sList,stList,c);
        System.out.println(cs1.toString());
        if(cs1 == ConfectionerStatus.ACCEPT){
            List<BirthdayItems> listitem = new ArrayList<BirthdayItems>();
            Order o1 = c.createNewSweet(sList,b1, listitem);
            b1.addOrder(o1);
            
            System.out.println(b1.getScore());
            
//            System.out.println("hey");
//            System.out.println(o1.getSweets());
//            System.out.println(o1.getMap());
            for(Sweets s: o1.getSweets()){
                o1.addScore(s, 2);
            }
            System.out.println(o1.addScore(s2, 1));
            System.out.println(b1.getScore());
            
            System.out.println("cost");
            System.out.println(o1.getCost());
            Candle candle = new Candle("happy",  new BigDecimal(1000), "123", "red");
            o1.ItemaddtoOrder(candle);
            System.out.println("newcost");
            System.out.println(o1.getCost());
//            System.out.println("now");
//            System.out.println(o1.getMap());
        }

        List<Order> orderIdC = c.getOrders();
        for (Order i : orderIdC){
            System.out.println(i.getOrderId());
            System.out.println(i.getSweets());
        }
    }
}
