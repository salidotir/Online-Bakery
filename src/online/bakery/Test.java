package online.bakery;

import com.sun.istack.internal.NotNull;
import javafx.util.Pair;
import online.bakery.Confectioner.Confectioner;
import online.bakery.Status.ConfectionerStatus;
import online.bakery.Type.Role;
import online.bakery.Type.SweetType;
import online.bakery.Post.Post;
import online.bakery.Status.OrderStatus;
import online.bakery.Type.VehicleType;
import online.bakery.birthdayItems.BirthdayItems;
import online.bakery.birthdayItems.Candle;
import online.bakery.decorators.Decorator;
import online.bakery.decorators.DecoratorToBuild;
import online.bakery.sweets.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Test {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

    public static final String[] FOREGROUNDS = {
            ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW,
            ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE,
            ANSI_BRIGHT_BLACK, ANSI_BRIGHT_RED, ANSI_BRIGHT_GREEN, ANSI_BRIGHT_YELLOW,
            ANSI_BRIGHT_BLUE, ANSI_BRIGHT_PURPLE, ANSI_BRIGHT_CYAN, ANSI_BRIGHT_WHITE
    };

    public static final String ANSI_BG_BLACK = "\u001B[40m";
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    public static final String ANSI_BRIGHT_BG_BLACK = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE = "\u001B[107m";

    public static final String[] BACKGROUNDS = {
            ANSI_BG_BLACK, ANSI_BG_RED, ANSI_BG_GREEN, ANSI_BG_YELLOW,
            ANSI_BG_BLUE, ANSI_BG_PURPLE, ANSI_BG_CYAN, ANSI_BG_WHITE,
            ANSI_BRIGHT_BG_BLACK, ANSI_BRIGHT_BG_RED, ANSI_BRIGHT_BG_GREEN, ANSI_BRIGHT_BG_YELLOW,
            ANSI_BRIGHT_BG_BLUE, ANSI_BRIGHT_BG_PURPLE, ANSI_BRIGHT_BG_CYAN, ANSI_BRIGHT_BG_WHITE};

    ArrayList<Confectioner> conf = new ArrayList<>();

    public Test() {
        List<String> questions = new ArrayList<>();
        questions.add("What is your grandfader first name?");
        Admin.getInstance().setQuestions(questions);
        
        LocalDate todayy = LocalDate.now();
        LocalDate tomorrow = todayy.plusDays(3650);

        Admin.getInstance().createDiscount("تخفیف اولین سفارش در اپ ما", 20
                , new Date(todayy.getYear() - 1900, todayy.getMonthValue() - 1, todayy.getDayOfMonth())
                , new Date(tomorrow.getYear() - 1900, tomorrow.getMonthValue() - 1, tomorrow.getDayOfMonth()), 20000);

        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 3 Delivery Employees");
        this.AddEmployees();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 3 Delivery Vehicles");
        this.AddVehicles();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 4 Confectioners");
        this.AddConfectioner();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Show All Bakeries");
        this.ShowAllBakeries();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Show All Bakers");
        this.ShowAllBakers();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Search By Name for Bakeries:(substring='baker2')");
        this.SearchByNameBakeries();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Search By Name for Bakers:(substring='reza')");
        this.SearchByNameBakers();
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 4 Customers");
        this.AddCustomers();
        //////System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Senario Forgot password");
        //////this.ForgetPass();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add 3 Emploee");
        //this.AddCustomers();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Buy a Gift Card (negin to sara)");
        //this.BuyGiftCard();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Order Multi Tiered Cake By Melika to Bakery2");
        //this.CreateMultiTieredCake();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Make Sweets by Confectioners (By Baker1)");
        //this.MakeSweetByBaker();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add discount by Confectioners (By Bakery2)");
        //this.AddDiscountByBakery();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add BirthDay Item to Bakery (By Bakery2)");
        //this.AddBirthDayItemBakery();
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add Post By Bakery (By Bakery2)");
        //Post prevPost = this.AddCreatePostByBakery();

        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add Comment (Prev post /Melika)");
        //this.AddComment(prevPost);
        //System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Add Like (Prev post /Melika)");
        //this.AddLike(prevPost);
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "ScenarioLogin");
        Customer cu = (Customer) this.ScenarioLogin();
        this.ScenarioOrder(cu);

        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "Test for creating a complaint.");
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[2] + "In testComplaint() function in 'main.java'");
        main.testComplaint();
        
    }

    void AddEmployees() {
        Employee e1 = new Employee("salidotir", "4444", "Sara", "Limooee");
        Employee e2 = new Employee("hello", "1234", "firstname1", "lastname1");
        Employee e3 = new Employee("world", "5678", "firstname2", "lastname2");
    }
    
    void AddVehicles() {
        // create a list of vehicles
        Vehicle v1 = new Vehicle(VehicleType.MOTOR, "12M345");
        System.out.println("Added new vehicle");
        Vehicle v2 = new Vehicle(VehicleType.CAR, "89D3673");
        System.out.println("Added new vehicle");
        Vehicle v3 = new Vehicle(VehicleType.TRUCK, "34A300");
        System.out.println("Added new vehicle");
    }
    
    void AddConfectioner() {
        Bakery bakery1 = new Bakery("Bakery1", "usermane1", "pass1", "Have a Good Time", "07131111110", "Eram");
        bakery1.setFirstname("Mitra");
        bakery1.setLastname("Hejazi");

        Baker baker1 = new Baker("Baker1", "usermane2", "pass2", "Have a Good Time", "07131111112", "Khalili");
        baker1.setFirstname("Ali");
        baker1.setLastname("Abedi");

        Bakery bakery2 = new Bakery("Bakery2", "usermane3", "pass3", "Have a Good Time", "07131111113", "Farhang Shahr");
        bakery2.setFirstname("sara");
        bakery2.setLastname("Ahmadi");
        bakery2.addDiscount();

        Baker baker2 = new Baker("Baker2", "usermane4", "pass4", "Have a Good Time", "07131111114", "Tachara");
        baker2.setFirstname("reza");
        baker2.setLastname("Salmani");

        //
        ArrayList<DecoratorToBuild> decorators = new ArrayList<>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR, new BigDecimal(100), new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR, new BigDecimal(300), new BigDecimal(40)));
        decorators.add(new DecoratorToBuild(Decorator.BAKING_POWDER, new BigDecimal(200), new BigDecimal(500)));
        Sweets cake1 = new Cake.CakeBuilder(decorators).build();
        cake1.setName("Chocolate Cake");
        cake1.setFee(new BigDecimal(100000));
        decorators.add(new DecoratorToBuild(Decorator.STRAWBERRY, new BigDecimal(200), new BigDecimal(500)));
        Sweets cake2 = new Cake.CakeBuilder(decorators).build();
        cake2.setName("Vanilla Cake");
        cake2.setFee(new BigDecimal(50000));
        decorators.add(new DecoratorToBuild(Decorator.WALNUT, new BigDecimal(400), new BigDecimal(500)));
        Sweets cake3 = new Cake.CakeBuilder(decorators).build();
        cake3.setName("Walnut Cake");
        cake3.setFee(new BigDecimal(5000));
        Sweets cookie = new Cookie.CookieBuilder(decorators).build();
        cookie.setName("Chocolate Cookie");
        Sweets tart = new Tart.TartBuilder(decorators).build();
        tart.setName("Raspberry Tart");

        baker2.addOrderSweet(cake1);
        baker2.addOrderSweet(cake2);

        baker1.addOrderSweet(cookie);

        bakery2.addOrderSweet(cake3);
        bakery2.addOrderSweet(cookie);
        bakery2.addReadySweet(tart, 12);

        bakery1.addOrderSweet(tart);
        bakery1.addReadySweet(cake1, 1);
        bakery1.addReadySweet(cookie, 10);
        BirthdayItems b = bakery2.CreateBirthdayItem();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of item : ");
        int num = scan.nextInt();
        scan.nextLine();
        bakery2.addBirthdayItem(b,num);


        conf.add(baker1);
        conf.add(baker2);
        conf.add(bakery1);
        conf.add(bakery2);

        System.out.println(ANSI_RESET);
    }

    List<Bakery> ShowAllBakeries() {
        List<Bakery> All_bakeries = Admin.getInstance().ShowAllBakery();
        int i = 1;
        for (Bakery bakery : All_bakeries
        ) {
            System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + i + ") " + bakery.getProfile());
            System.out.println(ANSI_RESET);
            i+=1;

        }
        return All_bakeries;
    }

    List<Baker> ShowAllBakers() {
        List<Baker> All_bakers = Admin.getInstance().ShowAllBakers();
        int i = 1;
        for (Baker bakery : All_bakers
        ) {
            System.out.println(BACKGROUNDS[3] + FOREGROUNDS[15] + i + ") " + bakery.getProfile());
            System.out.println(ANSI_RESET);
            i+=1;

        }
        return All_bakers;
    }

    private void SearchByNameBakeries() {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("baker2");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            for (Bakery bakery : bakeries
            ) {
                System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + bakery.getProfile());
                System.out.println(ANSI_RESET);

            }
        }
    }

    private void SearchByNameBakers() {
        List<Baker> bakers = Admin.getInstance().searchBakerByName("reza");
        if (bakers.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            for (Baker bakery : bakers
            ) {
                System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + bakery.getProfile());
                System.out.println(ANSI_RESET);

            }
        }
    }

    void AddCustomers() {
        Customer c = new Customer("fm", "1234");
        c.setFirstname("Fateme");
        c.setLastname("Masoudi");
        c.setContactNo("0917863533");
        c.setAddress("Bahonar");
        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c.getProfile());
        System.out.println(ANSI_RESET);
        c.LogOut();


        Customer c2 = new Customer("melika", "1234");
        c2.setFirstname("Melika");
        c2.setLastname("zare");
        c2.setContactNo("0917863532");
        c2.setAddress("Mellat");
        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c2.getProfile());
        System.out.println(ANSI_RESET);
        c2.LogOut();


        Customer c3 = new Customer("neg", "4444");
        c3.setFirstname("Negin");
        c3.setLastname("Khalifat");
        c3.setContactNo("0914863532");
        c3.setAddress("Bagh shah");
        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c3.getProfile());
        System.out.println(ANSI_RESET);
        c3.LogOut();


        Customer c4 = new Customer("sara", "4434");
        c4.setFirstname("Sara");
        c4.setLastname("LimooE");
        c4.setContactNo("0914843533");
        c4.setAddress("MaliAbad");
        System.out.println(BACKGROUNDS[12] + FOREGROUNDS[15] + c4.getProfile());
        System.out.println(ANSI_RESET);
        c4.LogOut();
        
    }

    private void BuyGiftCard() {
        Customer sara = Admin.getInstance().searchCustomerByContactNo("0914843533");
        Customer negin = Admin.getInstance().searchCustomerByContactNo("0914863532");
        negin.BuyGiftCardTo(sara);
    }

    private void CreateMultiTieredCake() {
        Customer Melika = Admin.getInstance().searchCustomerByContactNo("0917863532");
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("Bakery2");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        }
        Sweets multitieredCake = Melika.MakeMultitieredCake(bakeries.get(0));
        System.out.println(multitieredCake.getDescription());
    }

    private void MakeSweetByBaker() {
        List<Baker> bakers = Admin.getInstance().searchBakerByName("reza");
        if (bakers.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            Sweets sweets = bakers.get(0).CreateSweets();
            System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + sweets.toString());
            System.out.println(ANSI_RESET);

        }
    }

    private void MakeSweetByBakery() {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("Bakery1");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            Sweets sweets = bakeries.get(0).CreateSweets();
            System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + sweets.toString());
            System.out.println(ANSI_RESET);

        }
    }

    private void AddDiscountByBakery() {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("Bakery2");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            bakeries.get(0).addDiscount();
        }
    }

    private void AddBirthDayItemBakery() {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("Bakery2");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
        } else {
            BirthdayItems birthdayItems = bakeries.get(0).CreateBirthdayItem();
            System.out.println("Please Enter number of this item:");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            bakeries.get(0).addBirthdayItem(birthdayItems, num);
        }
    }

    private Post AddCreatePostByBakery() {
        List<Bakery> bakeries = Admin.getInstance().searchBakeryByName("Bakery2");
        if (bakeries.size() == 0) {

            System.out.println(BACKGROUNDS[1] + FOREGROUNDS[15] + "Not Found");
            System.out.println(ANSI_RESET);
            return null;
        } else {
            Post post = bakeries.get(0).createPost();

            System.out.println(post.toString());
            return post;

        }
    }

    private void AddComment(@NotNull Post post) {
        Customer Melika = Admin.getInstance().searchCustomerByContactNo("0917863532");
        post.addComment(Melika);
        System.out.println(post.toString());
    }

    private void AddLike(Post post) {
        Customer Melika = Admin.getInstance().searchCustomerByContactNo("0917863532");
        post.addLike(Melika);
        System.out.println(BACKGROUNDS[2] + FOREGROUNDS[15] + post.toString());
    }

    private void ScenarioOrder(@NotNull Customer customer){
        System.out.println(BACKGROUNDS[15] + FOREGROUNDS[10] + "_________________________ you login _________________________");
        Scanner scan = new Scanner(System.in);
        System.out.println("1)Baker 2)Bakery");
        int whichRole = 1;
        whichRole = scan.nextInt();
        scan.nextLine();
        if(whichRole == 1) {
            List<Baker> bakers = ShowAllBakers();
            if(bakers.size() == 0){
                System.out.println("Baker Not Found");
                ScenarioOrder(customer);
                return;
            }
            else {
                System.out.println("Enter number of Baker:");
                int whichBaker = 1;
                whichBaker = scan.nextInt();
                scan.nextLine();
                List<Sweets> sweetsOrder = new ArrayList<Sweets>();
                List<SweetType> sweetTypes = new ArrayList<SweetType>();

                while (true) {
                    System.out.println("1) Order Sweets    2)Make Sweets");
                    int whichTypeSweet = 1;
                    whichTypeSweet = scan.nextInt();
                    scan.nextLine();
                    switch (whichTypeSweet) {
                        case 1:
                            if(bakers.get(whichBaker - 1).getOrderSweets().size() == 0) {
                                System.out.println("Order Sweets not available");
                                continue;
                            }
                            System.out.println("Order Sweets for baker " + bakers.get(whichBaker - 1).getName());
                            int i = 1;
                            for (Sweets s : bakers.get(whichBaker - 1).getOrderSweets()) {
                                System.out.println(i +") name sweet : "+ s.getSweet());
                                System.out.println();
                                i += 1;
                            }
                            System.out.println("Enter number of Sweet:");
                            int whichSweet = 1;
                            whichSweet = scan.nextInt();
                            scan.nextLine();
                            sweetsOrder.add(bakers.get(whichBaker - 1).getOrderSweets().get(whichSweet - 1));
                            sweetTypes.add(SweetType.CREATE_CONFECTIONER);
                            break;
                        case 2:
                            Sweets multiTieredCake = customer.MakeMultitieredCake(bakers.get(whichBaker - 1));
                            sweetsOrder.add(multiTieredCake);
                            sweetTypes.add(SweetType.CREATE_CUSTOMER);
                            break;
                    }

                    System.out.println("break ? y/n");
                    char y = scan.next().charAt(0);
                    if (y == 'y') break;
                }
                List<BirthdayItems> item = new ArrayList<BirthdayItems>();

                System.out.println(BACKGROUNDS[15] + FOREGROUNDS[5] + "           Shopping list             ");
                int i = 1;
                for (Sweets s : sweetsOrder){
                    System.out.println(i +") name item : "+ s.getSweet() + "\n");
                }
                System.out.println("Buy ? y/n");
                char y = scan.next().charAt(0);
                if (y == 'n') {
                    ScenarioOrder(customer);
                    return;
                }

                Order o1 = customer.createNewSweet(sweetsOrder, item);
                System.out.println(o1.getOrderInformation());



                boolean result = true;
                while (result) {
                    result = o1.finalizedOrder();
                    if (!result)
                        break;
                    AbstractMap.SimpleEntry res = o1.chooseBaker(bakers.get(whichBaker - 1), sweetTypes);
                    result = (boolean) res.getKey();
                    if (!result)
                        break;

                    List<ConfectionerStatus> cs1 = (List<ConfectionerStatus>) res.getValue();
                    ConfectionerStatus s = o1.getConfirmBaker(cs1);
                    if (s == ConfectionerStatus.ACCEPT) {
                        customer.setAddress("ملاصدرا دانشکده کامپیوتر");

                        res = o1.setDelivery();
                        result = (boolean) res.getKey();
                        System.out.println(res.getValue());
                        if (!result)
                            break;
                        result = o1.payOrder("paying order o1");
                        if (!result)
                            break;
                        result = o1.setBakerStatus(sweetTypes);
                        if (!result)
                            break;
                        result = o1.callDelivery();
                        if (!result)
                            break;


                        DeliverySystem deliverySystem = DeliverySystem.getDeliverySystem();
                        deliverySystem.assignEmployeesToOrder();
                        System.out.println(deliverySystem.toStringGetOrderEmployeeMap());
                        //Employee e1 = new Employee("salidotir12", "4444", "Sara", "Limooee");
                        //e1.deliverOrder(o1);
                        //System.out.println(e1.getProfile());
                        
                        // employee login to say if it has deivered th eorder or not
                        Employee e1 = (Employee)this.ScenarioLogin();
                        System.out.println("What did tou do with the order? ");
                        System.out.println("1. Delivered        2. Ruined!");
                        Scanner sc = new Scanner(System.in);
                        int employeeChoice = sc.nextInt();
                        if (employeeChoice != 1 && employeeChoice != 2) {
                            employeeChoice = sc.nextInt();
                        }
                        if (employeeChoice == 1) {
                            e1.deliverOrder(o1);
                        }
                        else if (employeeChoice == 2) {
                            e1.ruineOrder(o1);
                        }
                        System.out.println("------------------------------------------------------");
                        System.out.println(o1.getOrderInformation());
                        System.out.println(o1.getDelivery().getDeliveryInformation());

                        if (o1.getOrderStatus() == OrderStatus.DELIVERED) {
                            for (Sweets sweet : o1.getSweets()) {
                                o1.addScore(sweet, Rate.FOUR);
                            }

                        }
                    }


                }
            }
        }
        else {
            List<Bakery> bakeries = ShowAllBakeries();
            if (bakeries.size() == 0) {
                System.out.println("Bakery Not Found");
                ScenarioOrder(customer);
                return;
            } else {
                System.out.println("Enter number of Bakery:");
                int whichBakery = 1;
                whichBakery = scan.nextInt();
                scan.nextLine();
                List<Sweets> sweetsOrder = new ArrayList<Sweets>();
                List<SweetType> sweetTypes = new ArrayList<SweetType>();
                List<BirthdayItems> item = new ArrayList<BirthdayItems>();
                while (true) {
                    System.out.println("1) Order Sweets    2) Make Sweets   3) Ready Sweets   4)Birthday Items");
                    int whichTypeSweet = 1;
                    whichTypeSweet = scan.nextInt();
                    scan.nextLine();
                    switch (whichTypeSweet) {
                        case 1:
                            if(bakeries.get(whichBakery - 1).getOrderSweets().size() == 0) {
                                System.out.println("Order Sweets not available");
                                continue;
                            }
                            System.out.println("Order Sweets for bakery " + bakeries.get(whichBakery - 1).getName());
                            int i = 1;
                            for (Sweets s : bakeries.get(whichBakery - 1).getOrderSweets()) {
                                System.out.println(i +") name sweet : "+ s.getSweet());
                                System.out.println();
                                i += 1;
                            }
                            System.out.println("Enter number of Sweet:");
                            int whichSweet = 1;
                            whichSweet = scan.nextInt();
                            scan.nextLine();
                            sweetsOrder.add(bakeries.get(whichBakery - 1).getOrderSweets().get(whichSweet - 1));
                            sweetTypes.add(SweetType.CREATE_CONFECTIONER);
                            break;
                        case 2:
                            Sweets multiTieredCake = customer.MakeMultitieredCake(bakeries.get(whichBakery - 1));
                            sweetsOrder.add(multiTieredCake);
                            sweetTypes.add(SweetType.CREATE_CUSTOMER);
                            break;

                        case 3:
                            if(bakeries.get(whichBakery - 1).getReadySweets().size() == 0) {
                                System.out.println("Ready Sweets not available");
                                continue;
                            }
                            System.out.println("Ready Sweets for bakery " + bakeries.get(whichBakery - 1).getName());
                            i = 1;
                            for (Pair<Sweets, Integer> s : bakeries.get(whichBakery - 1).getReadySweets()) {
                                System.out.println(i +") name sweet : "+ s.getKey().getSweet() + "\t" + "Number: " + s.getValue());
                                System.out.println();
                                i += 1;
                            }
                            System.out.println("Enter number of Sweet:");
                            whichSweet = 1;
                            whichSweet = scan.nextInt();
                            scan.nextLine();
                            sweetsOrder.add(bakeries.get(whichBakery - 1).getReadySweets().get(whichSweet - 1).getKey());
                            sweetTypes.add(SweetType.READY);
                            break;

                        case 4:
                            if(bakeries.get(whichBakery - 1).getBirthdayItem().size() == 0) {
                                System.out.println("Birthday Items not available");
                                continue;
                            }
                            System.out.println("Birthday Items for bakery " + bakeries.get(whichBakery - 1).getName());
                            i = 1;
                            for (Pair<BirthdayItems, Integer> b : bakeries.get(whichBakery - 1).getBirthdayItem()) {
                                System.out.println(i + ") name item : " + b.getKey().getDescription() + "\t" +"Number: " + b.getValue());
                                System.out.println();
                                i += 1;
                            }
                            System.out.println("Enter number of Birthday Item:");
                            int whichItem = 1;
                            whichItem = scan.nextInt();
                            scan.nextLine();
                            item.add(bakeries.get(whichBakery - 1).getBirthdayItem().get(whichItem - 1).getKey());
                            break;
                    }

                    System.out.println("break ? y/n");
                    char y = scan.next().charAt(0);
                    if (y == 'y') break;
                }
                System.out.println(BACKGROUNDS[15] + FOREGROUNDS[5] + "           Shopping list             ");
                int i = 1;
                for (Sweets s : sweetsOrder){
                    System.out.println(i +") name item : "+ s.getSweet() + "\n");
                }
                i = 1;
                for (BirthdayItems b : item) {
                    System.out.println(i + ") name item : " + b.getDescription());
                    i += 1;
                }
                System.out.println("Buy ? y/n");
                char y = scan.next().charAt(0);
                if (y == 'n') {
                    ScenarioOrder(customer);
                    return;
                }

                Order o1 = customer.createNewSweet(sweetsOrder, item);

                System.out.println(o1.getOrderInformation());

                boolean result = true;
                while (result) {
                    result = o1.finalizedOrder();
                    if (!result)
                        break;
                    AbstractMap.SimpleEntry res = o1.chooseBakery(bakeries.get(whichBakery - 1), sweetTypes);
                    result = (boolean) res.getKey();
                    if (!result)
                        break;

                    List<ConfectionerStatus> cs1 = (List<ConfectionerStatus>) res.getValue();
                    ConfectionerStatus s = o1.getConfirmBaker(cs1);
                    if (s == ConfectionerStatus.ACCEPT) {
                        customer.setAddress("ملاصدرا دانشکده کامپیوتر");

                        res = o1.setDelivery();
                        result = (boolean) res.getKey();
                        System.out.println(res.getValue());
                        if (!result)
                            break;
                        result = o1.payOrder("paying order o1");
                        if (!result)
                            break;
                        result = o1.setBakerStatus(sweetTypes);
                        if (!result)
                            break;
                        result = o1.callDelivery();
                        if (!result)
                            break;


                        DeliverySystem deliverySystem = DeliverySystem.getDeliverySystem();
                        deliverySystem.assignEmployeesToOrder();
                        System.out.println(deliverySystem.toStringGetOrderEmployeeMap());
                        //Employee e1 = new Employee("salidotir12", "4444", "Sara", "Limooee");
                        //e1.deliverOrder(o1);
                        //System.out.println(e1.getProfile());
                        
                        // employee login to say if it has deivered th eorder or not
                        Employee e1 = (Employee)this.ScenarioLogin();
                        System.out.println("What did tou do with the order? ");
                        System.out.println("1. Delivered        2. Ruined!");
                        Scanner sc = new Scanner(System.in);
                        int employeeChoice = sc.nextInt();
                        if (employeeChoice != 1 && employeeChoice != 2) {
                            employeeChoice = sc.nextInt();
                        }
                        if (employeeChoice == 1) {
                            e1.deliverOrder(o1);
                        }
                        else if (employeeChoice == 2) {
                            e1.ruineOrder(o1);
                        }
                        
                        System.out.println("------------------------------------------------------");
                        System.out.println(o1.getOrderInformation());
                        System.out.println(o1.getDelivery().getDeliveryInformation());

                        if (o1.getOrderStatus() == OrderStatus.DELIVERED) {
                            for (Sweets sweet : o1.getSweets()) {
                                o1.addScore(sweet, Rate.FOUR);
                            }

                        }
                    }


                }

            }
        }



    }

    private Account ScenarioLogin(){
        //System.out.println("1)Login 2)Sign up");
        Scanner scan = new Scanner(System.in);
        Role[] roles = Role.values();

        System.out.printf("Username : ");
        String username = scan.next();
        System.out.printf("password : ");
        String pass = scan.next();



        int j = 1;
        for(Role r:roles){
            System.out.print(j+") "+r + "\t");
            j+=1;
        }
        boolean t = true;
        int whichRole = 1;
        while (t) {
            whichRole = scan.nextInt();
            scan.nextLine();
            if(whichRole <= roles.length && whichRole > 0){
                t = false;
            }
            else{
                System.out.println("Wrong Num ... try again");
            }
        }

        switch (roles[whichRole - 1]){
            case CUSTOMER:
                System.out.println("Login Customer_________");
                AbstractMap.SimpleEntry result = Account.Login(username, pass, Role.CUSTOMER);
                if( result.getValue() != null){
                    Customer c1= (Customer)result.getValue();
                    System.out.println(c1.getProfile());
                    //ScenarioOrder(c1);
                    return c1;
                }
                else {
                    System.out.println("You are not Customer");
                    return ScenarioLogin();
                }
            case BAKER:
                System.out.println("Login Baker_________");
                AbstractMap.SimpleEntry result1 = Account.Login(username, pass, Role.BAKER);
                if( result1.getValue() != null){
                    Baker b1= (Baker) result1.getValue();
                    System.out.println(b1.getProfile());
                    return b1;
                }
                else {
                    System.out.println("You are not Baker");
                    return ScenarioLogin();
                }

            case BAKERY:
                System.out.println("Login Bakery_________");
                AbstractMap.SimpleEntry result2 = Account.Login(username, pass, Role.BAKERY);
                if( result2.getValue() != null){
                    Bakery b1= (Bakery) result2.getValue();
                    System.out.println(b1.getProfile());
                    return b1;
                }
                else {
                    System.out.println("You are not Bakery");
                    return ScenarioLogin();
                }

            case EMPLOYEE:
                System.out.println("Login Employee_________");
                AbstractMap.SimpleEntry result3 = Account.Login(username, pass, Role.EMPLOYEE);
                if( result3.getValue() != null){
                    Employee e1= (Employee)result3.getValue();
                    System.out.println(e1.getProfile());
                    return e1;
                }
                else {
                    System.out.println("You are not Employee");
                    return ScenarioLogin();
                }

            case ADMIN:
                System.out.println("Login Admin_________");
                AbstractMap.SimpleEntry result4 = Account.Login(username, pass, Role.ADMIN);
                if( result4.getValue() != null){
                    Admin a1= (Admin) result4.getValue();
                    System.out.println(a1.getProfile());
                    return a1;
                }
                else {
                    System.out.println("You are not Admin");
                    return ScenarioLogin();
                }

        }




        //Customer c1 = Admin.getInstance().LoginAccount(username,pass,Role.CUSTOMER);

        return null;

        //Customer c2;
        //c2.Login("Sara", "4444", Role.CUSTOMER);
        //System.out.println();

    }

    private void Scenario1() {

        
        ArrayList<Sweets> ss = new ArrayList<Sweets>();
        ArrayList<DecoratorToBuild> decorators = new ArrayList<DecoratorToBuild>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR, new BigDecimal(100), new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR, new BigDecimal(300), new BigDecimal(40)));
        decorators.add(new DecoratorToBuild(Decorator.BAKING_POWDER, new BigDecimal(200), new BigDecimal(500)));
        Sweets s1 = new Cake.CakeBuilder(decorators).build();
        decorators.add(new DecoratorToBuild(Decorator.STRAWBERRY, new BigDecimal(200), new BigDecimal(500)));
        Sweets s2 = new Cookie.CookieBuilder(decorators).build();
        ss.add(s1);
        ss.add(s2);

        ArrayList<Confectioner> conf = new ArrayList<>();
        Bakery b1 = new Bakery("شب شیرینی", "usermane", "pass", "لحظات زندگی خود را با کمک ما شیرین کنید", "07131111111", "تاچارا");

        Employee e1 = new Employee("salidotir", "4444", "Sara", "Limooee");
        Employee e2 = new Employee("hello", "1234", "firstname1", "lastname1");
        Employee e3 = new Employee("world", "5678", "firstname2", "lastname2");

        // craete a list of vehicles
        Vehicle v1 = new Vehicle(VehicleType.MOTOR, "12M345");
        Vehicle v2 = new Vehicle(VehicleType.CAR, "89D3673");
        Vehicle v3 = new Vehicle(VehicleType.TRUCK, "34A300");


        Customer c2 = Admin.getInstance().searchCustomerByContactNo("0914843533");
        Customer c = Admin.getInstance().searchCustomerByContactNo("0914863532");
        c2.BuyGiftCardTo(c);

        List<Sweets> sList = new ArrayList<>();
        List<SweetType> stList = new ArrayList<>();
        sList.add(s2);
        stList.add(SweetType.CREATE_CUSTOMER);

        sList.add(s1);
        stList.add(SweetType.READY);

        List<BirthdayItems> listitem = new ArrayList<>();
        Candle candle = new Candle("happy", new BigDecimal(1000), new BigDecimal(700), "3", "red");
        Order o1 = c.createNewSweet(sList, listitem);
        o1.ItemaddtoOrder(candle);
        boolean result = true;
        while (result) {
            result = o1.finalizedOrder();
            if (!result)
                break;
            AbstractMap.SimpleEntry res = o1.chooseBakery(b1, stList);
            result = (boolean) res.getKey();
            if (!result)
                break;

            List<ConfectionerStatus> cs1 = (List<ConfectionerStatus>) res.getValue();
            ConfectionerStatus s = o1.getConfirmBaker(cs1);
            if (s == ConfectionerStatus.ACCEPT) {
                c.setAddress("ملاصدرا دانشکده کامپیوتر");

                res = o1.setDelivery();
                result = (boolean) res.getKey();
                System.out.println(res.getValue());
                if (!result)
                    break;
                result = o1.payOrder("paying order o1");
                if (!result)
                    break;
                result = o1.setBakerStatus(stList);
                if (!result)
                    break;
                result = o1.callDelivery();
                if (!result)
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

                if (o1.getOrderStatus() == OrderStatus.DELIVERED) {
                    for (Sweets sweet : o1.getSweets()) {
                        o1.addScore(sweet, Rate.FOUR);
                    }

                }
//                System.out.println(o1.getOrderInformation());
            }

        }

    }
    private void ForgetPass(){
        Admin.getInstance().setQuestions(new ArrayList<String>());
        Customer c1 = new Customer("ali","4444");
        c1.setFirstname("ali");
        c1.setLastname("limoe");
        System.out.println(c1.getLastLogin());

        //checking toggle Activeness
//        System.out.println(c1.getActiveness());
//        Admin.getInstance().toggleActiveness(c1);
//        System.out.println(c1.getActiveness());


        System.out.println(c1.getLastLogin());
        
        c1.LogOut();
        AbstractMap.SimpleEntry result = Account.Login("sara", "4434", Role.CUSTOMER);
        if( result.getValue() != null){
            Customer cust = (Customer)result.getValue();
            System.out.println(cust.getProfile());  
            
            cust.LogOut();
        }
        
        AbstractMap.SimpleEntry resu = Account.forgotPassword(Role.CUSTOMER, "sara");
        if((boolean) resu.getKey())
            System.out.println(resu.getValue());
        else
            System.out.println(resu.getValue());
        
    }

}
