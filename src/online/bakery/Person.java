package online.bakery;

import javafx.util.Pair;
import online.bakery.decorators.Decorator;
import online.bakery.decorators.DecoratorToBuild;
import online.bakery.sweets.Cake;
import online.bakery.sweets.Rate;
import online.bakery.sweets.Sweets;

import java.math.BigDecimal;
import java.util.*;

public class Person extends Account implements Confectioner{

    private final int  id ;
    private String name;
    private String description;




    public Person(String name, String username, String password,String firstName, String lastName, String description,String number) {
        super();
        this.id = super.ID;
        super.SignUp(username, password, Role.BAKER);
        super.setFirstname(firstName);
        super.setLastname(lastName);
        this.name = name;
        this.description = description;
        super.setContactNo(number);
        Admin.getInstance().createPerson(this); 
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





    public boolean addOrderSweet(){
        ArrayList<Sweets> ss = new ArrayList<Sweets>();
        ArrayList<DecoratorToBuild> decorators = new ArrayList<DecoratorToBuild>();
        decorators.add(new DecoratorToBuild(Decorator.FLOUR, new BigDecimal(100), new BigDecimal(400)));
        decorators.add(new DecoratorToBuild(Decorator.SUGAR, new BigDecimal(300), new BigDecimal(40)));
        decorators.add(new DecoratorToBuild(Decorator.BAKING_POWDER, new BigDecimal(200), new BigDecimal(500)));
        Sweets sweet = new Cake.CakeBuilder(decorators).build();
        return Admin.getInstance().addOrderSweet(this,sweet);
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



    public boolean addDiscount(Discount discount){
        return Admin.getInstance().addDiscount(this,discount);}

    public List<Discount> getDiscountList() {
        return Admin.getInstance().getDiscount(this);
    }

    public String getProfile() {
        String bakeryP = name +"\n" + description + "\n" + "Number : " + super.getContactNo() + "\n" + "Address : " + super.getAddress() + "\n" + "Score : " +Admin.getInstance().getScoreBaker(this) + "/5 + \n" ;
        return bakeryP;
    }

    public boolean addNote(Note note, String extraText) {
        if (this.id == note.getNoteSellerId()) {
            note.setNoteSellerText(extraText);
        }
        return true;
    }

}