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
    private List<Integer> post = new ArrayList<Integer>(); // post id
    private List<Integer> sweetId = new ArrayList<Integer>(); //list of sweets is ready to buy
    private List<Integer> orderList = new ArrayList<Integer>(); // list order id
    private List<Integer> birthdayItemId = new ArrayList<Integer>();
    private List<Integer> discountList = new ArrayList<Integer>();



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

    public void addPost(int id){this.post.add(id);}

    public void addSweet(int id){this.sweetId.add(id);}

    public void addOrder(int id){this.orderList.add(id);}

    public void addBirthdayItem(int id) {this.birthdayItemId.add(id);}

    public ConfectionerStatus sweetToOrder(Sweets s){
        int i = 1;
        System.out.println(s.description);
        for (ConfectionerStatus CS: ConfectionerStatus.values())
        {
            System.out.println(i + " : "+ CS);
            i += 1;
        }
        Scanner scan = new Scanner(System.in);
        System.out.printf("Please Enter your Status for this Order : ");
        int num = scan.nextInt();
        scan.close();

        return ConfectionerStatus.values()[num-1];
    }

    public List<Integer> getPost() {
        return post;
    }

    public List<Integer> getSweetId() {
        return sweetId;
    }

    public List<Integer> getOrderList() {
        return orderList;
    }

    public List<Integer> getBirthdayItemId() {
        return birthdayItemId;
    }

    public void addDiscount(int id){this.discountList.add(id);}

    public List<Integer> getDiscountList() {
        return discountList;
    }

    public String getProfile() {
        String personP = super.getFirstname() +" " + super.getLastname() +"\n" + description + "\n" + "Number : " + super.getContactNo() + "\n" + "\n" + "Score : " + score + "/5 "  + "(" + numScore + ") \n";
        return personP;
    }
}
