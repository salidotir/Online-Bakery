package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

public class Bakery implements Confectioner {

    private int id ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private String firstName;
    private String lastName;
    private String number;
    private String address;
    private int score;



    public Bakery( String firstName, String lastName,String number,String address) {
        atomicInteger.incrementAndGet();
        this.id=atomicInteger.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
        this.score = 0;
    }




    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
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
        this.score = score;
    }

    public void increaseScore(int a) { this.score += a; }

    public void decreaseScore(int a) {
        this.score -= a;
    }
}
