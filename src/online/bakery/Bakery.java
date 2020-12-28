package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;
import java.math.BigDecimal;
import java.util.*;

public class Bakery implements Confectioner {

    private int id ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private String name;
    private String description;
    private String number;
    private String address;
    private int score;
    private int numScore;
    private List<Integer> post = new ArrayList<Integer>(); // post id
    private List<Integer> sweetId = new ArrayList<Integer>(); //list of sweets is ready to buy
    private List<Integer> menu = new ArrayList<Integer>(); //List of sweets id to order
    private List<Integer> orderList = new ArrayList<Integer>(); // list order id
    private List<Integer> itemId=new ArrayList<Integer>();




    public Bakery(String name, String description, String number, String address) {
        atomicInteger.incrementAndGet();
        this.id=atomicInteger.incrementAndGet();
        this.name = name;
        this.description = description;
        this.number = number;
        this.address = address;
        this.score = 0;
        this.numScore = 0;
    }




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }


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

    public void addMenu(int id){this.menu.add(id);}

    public void addItem(int id) {this.itemId.add(id);}

    @Override
    public String getProfile() {
        String s = name +"\n" + description + "\n" + "Number : " + number + "\n" + "Address : " + address + "\n" + "Score : " +score + "(" + numScore + ")" ;
        return s;
    }
}
