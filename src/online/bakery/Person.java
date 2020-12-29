package online.bakery;

import online.bakery.sweets.Sweets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person extends Account implements Confectioner{

    private final int  id ;
    private String description;
    private int score;
    private int numScore;
    private List<Sweets> post = new ArrayList<Sweets>(); // post id
    private List<Sweets> readySweet = new ArrayList<Sweets>(); //list of sweets is ready to buy
    private List<Order> orderList = new ArrayList<Order>(); // list order id
    private List<Discount> discountList = new ArrayList<Discount>();



    public Person( String firstName, String lastName,String description,String number) {
        super();
        this.id = super.ID;
        super.setFirstname(firstName);
        super.setLastname(lastName);
        this.description = description;
        super.setContactNo(number);
        this.score = 0;
        this.numScore = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        numScore += 1;
        this.score = (this.score + score) / numScore ;
    }

    public void increaseScore(int a) { this.score += a; }

    public void decreaseScore(int a) {
        this.score -= a;
    }

    public void addPost(Sweets sweet){this.post.add(sweet);}

    public void addReadySweet(Sweets sweet){this.readySweet.add(sweet);}

    public void addOrder(Order order){this.orderList.add(order);}


    public ConfectionerStatus sweetToOrder(List<Sweets> s,List<SweetType> st,Customer c){
        System.out.println("Customer");
        System.out.println(c.getProfile());

        for (int i = 0;i < s.size();i++) {
            System.out.println("Sweet");
            System.out.println(st.get(i));
            System.out.println(s.get(i).description);
            System.out.println(s.get(i).getTOTAL_Grams());
            System.out.println(s.get(i).getTOTAL_COST());
        }
        int indexCS = 1;
        for (ConfectionerStatus CS : ConfectionerStatus.values()) {
            System.out.println(indexCS + " : " + CS);
            indexCS += 1;
        }
        Scanner scan = new Scanner(System.in);
        System.out.printf("Please Enter your Status for this Order : ");
        int num = scan.nextInt();
        scan.close();

        return ConfectionerStatus.values()[num - 1];
    }

    public List<Sweets> getPost() {
        return post;
    }

    public List<Sweets> getReadySweet() { return readySweet; }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addDiscount(Discount discount){this.discountList.add(discount);}

    public List<Discount> getDiscountList() {
        return discountList;
    }


    public String getProfile() {
        String personP = super.getFirstname() +" " + super.getLastname() +"\n" + description + "\n" + "Number : " + super.getContactNo() + "\n" + "\n" + "Score : " + score + "/5 "  + "(" + numScore + ") \n";
        return personP;
    }

    public boolean addNote(Note note, String extraText) {
        if (this.id == note.getNoteSellerId()) {
            note.setNoteSellerText(extraText);
        }
        return true;
    }

}
