package online.bakery;

import online.bakery.Confectioner.Confectioner;
import online.bakery.Discount.Discount;
import online.bakery.Status.ConfectionerStatus;
import online.bakery.Type.Role;
import online.bakery.Type.SweetType;
import online.bakery.Post.Post;
import online.bakery.Status.OrderStatus;
import online.bakery.Type.TypeOfSweets;
import online.bakery.decorators.Decorator;
import online.bakery.decorators.DecoratorToBuild;
import online.bakery.sweets.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static online.bakery.Test.BACKGROUNDS;
import static online.bakery.Test.FOREGROUNDS;

public class Baker extends Account implements Confectioner {
    private String name;
    private String description;



    public Baker(String name, String username, String password, String description, String number, String address) {
        super();
        super.SignUp(username, password, Role.BAKER);
        super.setAddress(address);
        this.name = name;
        this.description = description;
        super.setContactNo(number);
        Admin.getInstance().createBaker(this);
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return Admin.getInstance().editBaker(this);
    }

    public boolean setDescription(String description) {
        this.description = description;
        return Admin.getInstance().editBaker(this);
    }

    public String getDescription() { return description; }

    public double getScore() {
        return Admin.getInstance().getScoreBaker(this);
    }

    public boolean setScore(Rate score, Sweets sweet) {
        return Admin.getInstance().setScore(this,score,sweet.getSweetId());
    }



    //public boolean addPost(Sweets sweet){this.post.add(sweet);}





    public Sweets CreateSweets() {

        System.out.println(BACKGROUNDS[1] + FOREGROUNDS[0]+"___________________________Baker: " + this.name + "  terminal______________________________");
        System.out.println("                           Create Sweet                          ");

        Sweets sweets;
        Scanner sc = new Scanner(System.in);

        TypeOfSweets[] sweetTypes = TypeOfSweets.values();
        Decorator[] decorators = Decorator.values();
        int j = 1;

        System.out.println("Please select Number of Sweets: ");
        for (TypeOfSweets st : sweetTypes
        ) {
            System.out.print(BACKGROUNDS[2] + FOREGROUNDS[15] +j + " " + st.toString() + "\t");
            j += 1;
        }
        System.out.println('\n');

        int WhichSweet = sc.nextInt();
        ArrayList<DecoratorToBuild> mydecorators = new ArrayList<DecoratorToBuild>();

        while (true) {
            int i = 1;
            System.out.println("Please select one of the decorator:");
            for (Decorator d : decorators
            ) {
                System.out.print(i + " " + d.toString() + "\t");
                i += 1;
            }

            System.out.println('\n');
            int Which_decorator = sc.nextInt();
            System.out.println("How much in grams");
            int grams = sc.nextInt();
            System.out.println("Cost");
            int cost = sc.nextInt();
            System.out.println("break? y/n");
            mydecorators.add(new DecoratorToBuild(decorators[Which_decorator - 1], new BigDecimal(grams), new BigDecimal(cost)));

            //String y = sc.nextLine();
            //if (y.equals("n")) break;
            char y = sc.next().charAt(0);
            if (y == 'y') break;

        }
        sc.nextLine();

        switch (sweetTypes[WhichSweet-1]) {

            case CAKE:

                sweets = new Cake.CakeBuilder(mydecorators).build();
                System.out.println("choose name: ");
                String y = sc.nextLine();
                sweets.setName(y);
                System.out.println("Fee: ");
                int fee = sc.nextInt();
                sc.nextLine();
                sweets.setFee(new BigDecimal(fee));
                System.out.println("Image : ");
                String image = sc.nextLine();
                sweets.addImages(image);

                break;
            case TART:
                sweets = new Tart.TartBuilder(mydecorators).build();
                System.out.println("choose name: ");
                String name = sc.nextLine();
                sweets.setName(name);
                System.out.println("Fee: ");
                fee = sc.nextInt();
                sc.nextLine();
                sweets.setFee(new BigDecimal(fee));
                System.out.println("Image : ");
                image = sc.nextLine();
                sweets.addImages(image);
                break;
            case COOKIE:
                sweets = new Cookie.CookieBuilder(mydecorators).build();
                System.out.println("choose name: ");
                y = sc.nextLine();
                sweets.setName(y);
                System.out.println("Fee: ");
                fee = sc.nextInt();
                sc.nextLine();
                sweets.setFee(new BigDecimal(fee));
                System.out.println("Image : ");
                image = sc.nextLine();
                sweets.addImages(image);
                break;
            case UNKNOWN:

                sweets = new Unknown.UnknownBuilder(mydecorators).build();
                System.out.println("choose name: ");
                y = sc.nextLine();
                sweets.setName(y);
                System.out.println("Fee: ");
                fee = sc.nextInt();
                sc.nextLine();
                sweets.setFee(new BigDecimal(fee));
                System.out.println("Image : ");
                image = sc.nextLine();
                sweets.addImages(image);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sweetTypes[j - 1]);
        }
        System.out.println("\u001B[0m");
        return sweets;
    }

    public boolean addOrderSweet(Sweets sweet) {
        //Sweets sweet = CreateSweets();
        return Admin.getInstance().addOrderSweet(this, sweet);
    }

    public boolean deleteOrderSweet(Sweets sweet){
        return Admin.getInstance().deleteOrderSweet(this,sweet);
    }




    public boolean addOrder(Order order,List<SweetType> s){
        boolean b = Admin.getInstance().addOrder(order);
        for(int i=0; i<s.size();i++){
            if(s.get(i) == SweetType.CREATE_CUSTOMER){
                Admin.getInstance().addOrderSweet(this,order.getSweets().get(i));
            }
        }

        return b;

    }


    public List<ConfectionerStatus> acceptOrder(Order order, List<SweetType> st){

        System.out.println(BACKGROUNDS[1] + FOREGROUNDS[0]+"___________________________Baker: " + this.name + "  terminal______________________________");
        System.out.println("                           accept Order                           ");

        List<ConfectionerStatus> out = new ArrayList<ConfectionerStatus>();
        System.out.println("Customer : "+order.getCustomerProfile());
        System.out.println("Sweet");
        List<Sweets> s = order.getSweets();
        for (int i = 0;i < s.size();i++) {

            System.out.println(st.get(i));
            System.out.println(s.get(i).description);
            System.out.println(s.get(i).getTOTAL_Grams());
            System.out.println(s.get(i).getTOTAL_COST());

            int indexCS = 1;
            for (ConfectionerStatus CS : ConfectionerStatus.values()) {
                System.out.println(indexCS + " : " + CS);
                indexCS += 1;
            }
            Scanner scan = new Scanner(System.in);
            System.out.printf("Please Enter your Status for this Order : ");
            int num = scan.nextInt();
            out.add(ConfectionerStatus.values()[num - 1]);
        }
        System.out.println("\u001B[0m");
        return out;

    }



    public List<Sweets> getOrderSweets() {
        return Admin.getInstance().getOrderSweets(this);
    }

    public List<Order> getOrderList() {
        return Admin.getInstance().getOrderList(this);
    }


    public boolean addDiscount() {
        System.out.println(BACKGROUNDS[1] + FOREGROUNDS[0]+"___________________________Bakery: " + this.name + "  terminal______________________________");
        System.out.println("                           add Discount                           ");

        int max = 1000;
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose the name of discount :");
        String name = scan.nextLine();

        System.out.println("Choose percent (1-100) : ");
        int percent = scan.nextInt();
        scan.nextLine();
        System.out.println("Choose start date (2020/01/01) : ");
        String starts = scan.nextLine();
        LocalDate startL;
        try {
            startL = LocalDate.parse(starts, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("Unexpected Date : " + starts);
        }
        Date start = new Date(startL.getYear()- 1900,startL.getMonthValue() - 1, startL.getDayOfMonth());


        System.out.println("choose end date (2021/01/01) : ");
        String ends  = scan.nextLine();
        LocalDate endL;
        try {
            endL = LocalDate.parse(ends, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unexpected Date : " + ends);
        }
        Date end = new Date(endL.getYear() - 1900, endL.getMonthValue() - 1, endL.getDayOfMonth());
        System.out.println("\u001B[0m");
        if (percent <= 100 && percent > 0) {
            Discount d = new Discount(name, percent, start, end, this.getID(), max);
            return Admin.getInstance().addDiscount(d);

        } else
            return false;
    }
    
    public List<Discount> getDiscountList() {
        return Admin.getInstance().getDiscount(this.getID());
    }


    public String getProfile() {
        double score = Admin.getInstance().getScoreBaker(this);
        String bakeryP;
        if(score == 0.0){
            bakeryP = name +"\n" + description + "\n" + super.getProfile()  ;
        }
        else{
            bakeryP = name +"\n" + description + "\n" + super.getProfile() + "Score : " + score + "/5 + \n" ;
        }

        return bakeryP;
    }

    public  BigDecimal getProfit(Date start , Date end){
        List <Order> orders = Admin.getInstance().getOrderDate(this,start,end, OrderStatus.DELIVERED);
        BigDecimal profit = new BigDecimal("0");
        for (Order o : orders){
            System.out.println(o.getOrderId());

            List<Discount> discount = o.getDiscount();
            Discount d = null;
            for (Discount dd : discount){
                if(dd.getCreatorID() == this.ID){
                    d = dd;
                }

            }
            int percent = 0;
            if(d != null){
                System.out.println("Discount made by you : " + d.getPercent()+ " % ");
                percent = d.getPercent();
            }

            BigDecimal ps = new BigDecimal("0");
            for(Sweets s : o.getSweets()){
                System.out.println(s.getName());
                System.out.println(s.getDescription());
                System.out.println(s.getTOTAL_COST());
                System.out.println(s.get_OderCost());
                ps = ps.add(s.getFee().multiply(new BigDecimal((100 - percent)/100)));
            }
            System.out.println("Profit for Sweet(s) : " + ps);
            //profit.add(pb);
            profit = profit.add(ps);
        }
        System.out.println("\u001B[0m");
        return  profit;

    }

    public Post createPost(){
        System.out.println(BACKGROUNDS[1] + FOREGROUNDS[0]+"___________________________Baker: " + this.name + "  terminal______________________________");
        System.out.println("                           Create Post                          ");

        List<String> images = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter caption post");
        String caption = scan.nextLine();
        System.out.println("Enter number of image : ");
        int num = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < num; i++){
            System.out.println("Enter image");
            String s = scan.nextLine();
            images.add(s);

        }
        System.out.println("\u001B[0m");
        return Post.createPost(this,caption,images);
    }

    public List<Post> getPosts(){
        return Admin.getInstance().getPosts(this.ID);
    }

}