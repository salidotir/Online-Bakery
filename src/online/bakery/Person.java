package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

public class Person implements Confectioner{

    private int id ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private String firstName;
    private String lastName;
    private String number;
    private int score;



    public Person( String firstName, String lastName,String number) {
        atomicInteger.incrementAndGet();
        this.id=atomicInteger.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
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
