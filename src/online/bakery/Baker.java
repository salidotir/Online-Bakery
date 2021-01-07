package online.bakery;

import javafx.util.Pair;
import online.bakery.Post.Post;
import online.bakery.decorators.Decorator;
import online.bakery.decorators.DecoratorToBuild;
import online.bakery.sweets.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Baker extends Account implements Confectioner{
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
        Sweets sweets;
        Scanner sc = new Scanner(System.in);
        System.out.println("please select Number of Sweets: ");

        TypeOfSweets[] sweetTypes = TypeOfSweets.values();
        Decorator[] decorators = Decorator.values();
        int j = 1;
        for (TypeOfSweets st : sweetTypes
        ) {
            System.out.print(j + " " + st.toString() + "\t");
            j += 1;
        }
        int WhichSweet = sc.nextInt();
        ArrayList<DecoratorToBuild> mydecorators = new ArrayList<DecoratorToBuild>();

        while (true) {
            int i = 1;
            System.out.println("please select one of the decorator:");
            for (Decorator d : decorators
            ) {
                System.out.print(i + " " + d.toString() + "\t");
                i += 1;
            }
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
        return sweets;
    }

    public boolean addOrderSweet() {
        Sweets sweet = CreateSweets();
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


    public List<ConfectionerStatus> acceptOrder(Order order,List<SweetType> st){
        List<ConfectionerStatus> out = new ArrayList<ConfectionerStatus>();
        System.out.println("Customer");
        System.out.println(order.getCustomerProfile());
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

        return out;

    }





//    public List<Sweets> getPost() {
//        return post;
//    }


    public List<Sweets> getOrderSweets() {
        return Admin.getInstance().getOrderSweets(this);
    }

    public List<Order> getOrderList() {
        return Admin.getInstance().getOrderList(this);
    }



    public boolean addDiscount(){


        int max = 1000;
        Scanner scan = new Scanner(System.in);
        System.out.println("Baker  --> add Discount ___________________________________________________________________");
        System.out.println("choose name :");
        String name = scan.nextLine();

        System.out.println("choose percent :");
        int percent = scan.nextInt();

        System.out.println("choose start date (1399/10/1) :");
        String starts  = scan.nextLine();
        LocalDate startL = LocalDate.parse(starts, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Date start = new Date(startL.getYear()- 1900,startL.getMonthValue() - 1, startL.getDayOfMonth());


        System.out.println("choose end date (1399/10/10) :");
        String ends  = scan.nextLine();
        LocalDate endL = LocalDate.parse(starts, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Date end = new Date(endL.getYear()- 1900,endL.getMonthValue() - 1, endL.getDayOfMonth());


        if(percent<= 100 && percent > 0){
            Discount d = new Discount(name, percent, start, end, this.getID(), max);
            return Admin.getInstance().addDiscount(d);
        }else
            return false;
    }
    
    public List<Discount> getDiscountList() {
        return Admin.getInstance().getDiscount(this.getID());
    }


    public String getProfile() {
        double score = Admin.getInstance().getScoreBaker(this);
        String bakeryP;
        if(score == 0.0){
            bakeryP = name +"\n" + description + "\n" + super.getProfile() + "\n" ;
        }
        else{
            bakeryP = name +"\n" + description + "\n" + super.getProfile() + "Score : " + score + "/5 + \n" ;
        }

        return bakeryP;
    }

    public boolean addNote(Note note, String extraText) {
        if (this.getID() == note.getNoteSellerId()) {
            note.setNoteSellerText(extraText);
        }
        return true;
    }

    public  BigDecimal getProfit(Date start , Date end){
        List <Order> orders = Admin.getInstance().getOrderDate(this,start,end,OrderStatus.DELIVERED);
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
        return  profit;

    }

    public Post createPost(){
        List<String> images = new ArrayList<String>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter caption post");
        String caption = scan.nextLine();
        while (true){
            System.out.println("Enter image");
            String s = scan.nextLine();
            images.add(s);
            System.out.println("break? y/n");
            char y = scan.next().charAt(0);
            if (y == 'y') break;
        }
        return Post.createPost(this,caption,images);
    }

}