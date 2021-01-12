package online.bakery;

import online.bakery.Post.Comment;
import online.bakery.Post.Post;
import online.bakery.birthdayItems.BirthdayItems;
import online.bakery.birthdayItems.Candle;
import online.bakery.decorators.Decorator;
import online.bakery.decorators.DecoratorToBuild;
import online.bakery.decorators.Design;
import online.bakery.decorators.DesignDecoration;
import online.bakery.sweets.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class main {
    public static void main(String[] args) {
        // test4();
//        test_multi_tiered();
//        test_GiftCard();
//        test1();
        Test test =new Test();
//        testComplaint();
        //testPost();
        //test3();
//        test2();
    }

    public static void testComplaint() { 
        // create a customer
        Customer c = new Customer("sara","4434");
        c.setFirstname("Sara");
        c.setLastname("Limooee");
        c.setContactNo("09170213754");
        c.setAddress("Shiraz, Iran");

        // create a complaint
        Complaint.createComplaint(c, new Date(), "bad cake!!", "The cake was not delicious.");
        Complaint.createComplaint(c, new Date(), "my complaint", "Your employee shouted a me!");
        
        List<Complaint> complaints = Admin.getInstance().viewAllComplaints();
        for(Complaint com:complaints) {
            System.out.println(com);
        }
        
        System.out.println("___________________________");
        
        // set the first complaint respond true
        Admin.getInstance().setComplantRespondTrue(complaints.get(0));
        for(Complaint com:complaints) {
            System.out.println(com);
        }
        
        System.out.println("___________________________");
        
        complaints = Admin.getInstance().viewNotRespondComplaints();
        for(Complaint com:complaints) {
            System.out.println(com);
        }
        
        System.out.println("___________________________");
        
        complaints = Admin.getInstance().getUserComplaints(c.ID);
        for(Complaint com:complaints) {
            System.out.println(com);
        }
    }
    
    private static void testPost() {
        ArrayList conf = new ArrayList<Confectioner>();
        Bakery b1 = new Bakery("شب شیرینی","username","pass","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
        System.out.println(b1.getProfile());
        //Discount d1 = new Discoun("تخفیف یلدایی" , 20 , new Date(1399,9,20),new Date(1399,10,1),b1.getID());


        // test Discount
        /*b1.addDiscount();

        List<Discount> ddd = b1.getDiscountList();
        for(Discount d : ddd){
            System.out.println(d.getName());
        }*/

        // test post

       /* if(b1.createPost()){
            System.out.println("Post create" + "\n ________________________________");
        }
        if(b1.createPost()){
            System.out.println("Post create" + "\n ________________________________");
        }*/



        Customer c = new Customer("fm","1234");
        c.setFirstname("فاطمه");
        c.setLastname("مسعودی");
        c.setContactNo("۰۹۱۷۲۲۳۲۸۳۵");
        c.setAddress("شیراز شهرک شهید باهنر");



        Post p = null;
        if(b1.getPosts().size() > 0 ){
            p = b1.getPosts().get(0);
            p.addLike(c);
            p.addComment(c);
        }

        for (Post p1 : b1.getPosts()){
            System.out.println("\n Post create by: " + p1.getAuthorId() + " __________");
            System.out.println("Image(s):");
            for(String s: p1.getImages()){
                System.out.println(s);
            }
            System.out.println("Like : " + p1.getLikeId().size());
            for(Comment comment:p1.getComments()){
                System.out.println(comment.getAuthorId() + " : " + comment.getText());
            }

        }




    }
    public static void test_GiftCard() {
        Admin.getInstance().setQuestions(new ArrayList<String>());
        Customer c1 = new Customer("Sara","4444");
        c1.setFirstname("sara");
        c1.setLastname("limoe");
        System.out.println(c1.getLastLogin());
        Customer c2 = new Customer("neg","4444");
        
        //checking toggle Activeness
//        System.out.println(c1.getActiveness());
//        Admin.getInstance().toggleActiveness(c1);
//        System.out.println(c1.getActiveness());


        c1.BuyGiftCardTo(c2);
        //System.out.println(c1.forgotPassword().getValue());
//        c1.changePassword();
        
        c1.LogOut();
        //System.out.println(c1.forgotPassword());
        c1.Login("Sara", "4444", Role.CUSTOMER);
        System.out.println(c1.getLastLogin());

    }
    public static void test_multi_tiered() {
        ArrayList<DecoratorToBuild> decorators = new ArrayList<>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR, new BigDecimal(100), new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR, new BigDecimal(300), new BigDecimal(40)));
        decorators.add(new DecoratorToBuild(Decorator.BAKING_POWDER, new BigDecimal(200), new BigDecimal(500)));
        Sweets cake1 = new Cake.CakeBuilder(decorators).build();
        System.out.println(cake1.getDescription() + "_______________");
        decorators.add(new DecoratorToBuild(Decorator.VANILLA, new BigDecimal(200), new BigDecimal(500)));
        Sweets cake2 = new Cake.CakeBuilder(decorators).build();
        ArrayList<Design> cakeWithDesign = new ArrayList<>();
        cakeWithDesign.add(new Design(new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLACK)), new ArrayList<>(Arrays.asList(DesignDecoration.CHOCOLATE, DesignDecoration.COCONUT_POWDER)), cake1));
        cakeWithDesign.add(new Design(new ArrayList<>(Collections.singletonList(Color.RED)), new ArrayList<>(Arrays.asList(DesignDecoration.CREAM, DesignDecoration.COCONUT_POWDER)), cake2));
        Sweets s = new MultiTieredCake(2, cakeWithDesign);
        System.out.println(s.cost());
        System.out.println(s);
    }

    // test vehicles and delivery system
    private static void test4() {
        // create a list of employees
        Employee e1 = new Employee("salidotir", "4444", "Sara", "Limooee");
        Employee e2 = new Employee("hello", "1234", "firstname1", "lastname1");
        Employee e3 = new Employee("world", "5678", "firstname2", "lastname2");
        
        // craete a list of vehicles
        Vehicle v1 = new Vehicle(VehicleType.MOTOR, "12M345");
        Vehicle v2 = new Vehicle(VehicleType.CAR, "89D3673");
        Vehicle v3 = new Vehicle(VehicleType.TRUCK, "34A300");
        
        // test delivery system
        DeliverySystem deliverySystem = DeliverySystem.getDeliverySystem();
        deliverySystem.assignEmployeesToOrder();
        System.out.println(deliverySystem.toStringGetOrderEmployeeMap());
        
        // sara is set to an order delivery employee
//        e1.deliverOrder(o1);
        System.out.println(deliverySystem.toStringGetOrderEmployeeMap());
    }
    
   
    private static void test3(){
        
        LocalDate todayy = LocalDate.now();
        LocalDate tomorrow = todayy.plusDays( 3650 ) ;
        
        Admin.getInstance().createDiscount("تخفیف اولین سفارش در اپ ما" , 20  
                , new Date(todayy.getYear()- 1900,todayy.getMonthValue() - 1, todayy.getDayOfMonth())
                , new Date(tomorrow.getYear()- 1900,tomorrow.getMonthValue() -1,tomorrow.getDayOfMonth()), 20000);        
        
        ArrayList<Sweets> ss = new ArrayList<Sweets>();
        ArrayList<DecoratorToBuild> decorators = new ArrayList<DecoratorToBuild>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR, new BigDecimal(100), new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR, new BigDecimal(300), new BigDecimal(40)));
        decorators.add(new DecoratorToBuild(Decorator.BAKING_POWDER, new BigDecimal(200), new BigDecimal(500)));
        Sweets s1 = new Cake.CakeBuilder(decorators).build();
        decorators.add(new DecoratorToBuild(Decorator.STRAWBERRY, new BigDecimal(200), new BigDecimal(500)));
        Sweets s2 = new Cookie.CookieBuilder(decorators).build();
        System.out.println(s1.getTOTAL_COST());
        ss.add(s1);
        ss.add(s2);
        
        ArrayList conf = new ArrayList<Confectioner>();
        Bakery b1 = new Bakery("شب شیرینی","usermane","pass","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
//        System.out.printf(b1.getProfile());

//        LocalDate baker_todey = LocalDate.now();
//        LocalDate baker_tomorrow = todayy.plusDays( 10 ) ;
//        b1.addDiscount("تخفیف یلدایی" , 20
//                , new Date(baker_todey.getYear()- 1900,baker_todey.getMonthValue() - 1, baker_todey.getDayOfMonth())
//                , new Date(baker_tomorrow.getYear()- 1900,baker_tomorrow.getMonthValue() -1,baker_tomorrow.getDayOfMonth()) 
//                ,200);
        
        Employee e1 = new Employee("salidotir", "4444", "Sara", "Limooee");
        Employee e2 = new Employee("hello", "1234", "firstname1", "lastname1");
        Employee e3 = new Employee("world", "5678", "firstname2", "lastname2");
        
        // craete a list of vehicles
        Vehicle v1 = new Vehicle(VehicleType.MOTOR, "12M345");
        Vehicle v2 = new Vehicle(VehicleType.CAR, "89D3673");
        Vehicle v3 = new Vehicle(VehicleType.TRUCK, "34A300");
        
        Customer c = new Customer("melika","1234");
        Customer c2 = new Customer("neg","4444");
        
        c2.BuyGiftCardTo(c);
        
        List<Sweets> sList = new ArrayList<Sweets>();
        List<SweetType> stList = new ArrayList<SweetType>();
        sList.add(s2);
        stList.add(SweetType.CREATE_CUSTOMER);

        sList.add(s1);
        stList.add(SweetType.READY);
                
        List<BirthdayItems> listitem = new ArrayList<BirthdayItems>();
        Candle candle = new Candle("happy",  new BigDecimal(1000), new BigDecimal(700), "3", "red");
        Order o1 = c.createNewSweet(sList, listitem);
        o1.ItemaddtoOrder(candle);
        boolean result = true;
        while(result){
            result = o1.finalizedOrder();
            if(!result)
                break;
            AbstractMap.SimpleEntry res = o1.chooseBakery(b1, stList);
            result = (boolean)res.getKey();
            if(!result)
                break;
            
            List<ConfectionerStatus> cs1 =(List<ConfectionerStatus>) res.getValue();
            ConfectionerStatus s = o1.getConfirmBaker(cs1);
            if (s == ConfectionerStatus.ACCEPT){
                c.setAddress("ملاصدرا دانشکده کامپیوتر");
                
                res = o1.setDelivery();
                result = (boolean)res.getKey();
                System.out.println(res.getValue());
                if(!result)                    
                    break;
                result = o1.payOrder("paying order o1");
                if(!result)                    
                    break;
                result = o1.setBakerStatus(stList);
                if(!result)
                    break;
                result = o1.callDelivery();
                if(!result)
                    break;
                
//                System.out.println(Admin.getInstance().getOrders().size());
                
                DeliverySystem deliverySystem = DeliverySystem.getDeliverySystem();
                deliverySystem.assignEmployeesToOrder();
                System.out.println(deliverySystem.toStringGetOrderEmployeeMap());
                
                e1.deliverOrder(o1);
//                e1.ruineOrder(o1);
                
                System.out.println(e1.getProfile());
                
//                System.out.println(deliverySystem.toStringGetOrderEmployeeMap());
//                
//                for(Order order: Admin.getInstance().getOrders()){
//                    System.out.println(order.getOrderInformation());
//                    System.out.println(order.getDelivery().getDeliveryInformation());
//                }
                System.out.println("------------------------------------------------------");
                System.out.println(o1.getOrderInformation());
                System.out.println(o1.getDelivery().getDeliveryInformation());
                
                if(o1.getOrderStatus() == OrderStatus.DELIVERED){
                    for(Sweets sweet: o1.getSweets()){
                        o1.addScore(sweet, Rate.FOUR);
                    }
                    
                }
//                System.out.println(o1.getOrderInformation());
            }
                
        }
        
    }
    
   
    // test2 -> test note & payment & delivery
    /*private static void test2() {
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
        Bakery b1 = new Bakery("شب شیرینی","usermane","pass","علی","شریعتی","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
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
            Order o1 = c.createNewSweet(sList, listitem);
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
//        DeliveryInformation c1_delivery1 = DeliveryInformation.createNewDelivery(c.getOrdersID().get(0), e1.getID(), "معالی آباد، کوچه ی سوم", new Date(2021, 1, 15, 16, 30));
//        System.out.println(c1_delivery1.getDeliveryInformation());
        
        // create a note for the order
        Note order1_note = new Note(c.getOrdersID().get(0), b1.getID(), c.getID(), e1.getID());
        // employee add a text to note
        e1.addNote(order1_note, "تحویل داده خواهد شد توسط اینجانب");        
        System.out.println(order1_note.getNoteInformation());
        
        // pay the order
        PaymentType paymentType = Payment.howToPay();
//        Payment payment = new Payment(c.getOrdersID().get(0), c.getID(), new Date(), new BigDecimal(400), "خرید کیک از شب شیرینی", paymentType);

        Payment walletChargePay = Payment.chargeWallet(c, "شارژ کیف پول");
        System.out.println(walletChargePay.getPaymentInformation());
        
        System.out.println(c.getWallet().getWalletInformation());
        //System.out.println(payment.getPaymentInformation());

//        if (payment.pay(payment, c) == true) {
//            System.out.println(payment.getPaymentInformation());
//            
//            // deliver the order
//            e1.deliverOrder(c1_delivery1);
//            System.out.println(c1_delivery1.getDeliveryInformation());
//        }

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

        Bakery b1 = new Bakery("شب شیرینی","username","pass","علی","شریعتی","لحظات زندگی خود را با کمک ما شیرین کنید" , "07131111111" , "تاچارا");
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
            Order o1 = c.createNewSweet(sList, listitem);
            b1.addOrder(o1);
            
            System.out.println(b1.getScore());
            
//            System.out.println("hey");
//            System.out.println(o1.getSweets());
//            System.out.println(o1.getMap());
            for(Sweets s: o1.getSweets()){
                o1.addScore(s, Rate.FIVE);
            }
            System.out.println(o1.addScore(s2, Rate.FOUR));
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
    }*/

}
