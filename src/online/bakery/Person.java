package online.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Person implements Confectioner{

    private int id ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private String firstName;
    private String lastName;
    private String description;
    private String number;
    private int score;
    private int numScore;
    private List<Integer> post = new ArrayList<Integer>(); // post id
    private List<Integer> sweetId = new ArrayList<Integer>(); //list of sweets is ready to buy
    private List<Integer> orderList = new ArrayList<Integer>(); // list order id
    private List<Integer> itemId=new ArrayList<Integer>();



    public Person( String firstName, String lastName,String description,String number) {
        atomicInteger.incrementAndGet();
        this.id=atomicInteger.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.number = number;
        this.score = 0;
        this.numScore = 0;
    }




    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public void addItem(int id) {this.itemId.add(id);}

    @Override
    public String getProfile() {
        String s = firstName +" " + lastName +"\n" + description + "\n" + "Number : " + number + "\n" + "\n" + "Score : " + score + "(" + numScore + ")";
        return s;
    }
}
