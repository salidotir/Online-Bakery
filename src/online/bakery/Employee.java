/*
 * Delivery employees information.
 */
package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Employee {
    int employeeId ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    private String firstName;
    private String lastName;
    private int score;
    private int numOfPeopleWhoScored = 0;
    
    public Employee(String FirstName, String LastName) {
        this.firstName = FirstName;
        this.lastName = LastName;
        this.score = 0;
        this.employeeId=atomicInteger.incrementAndGet();
    }

    public String getProfile() {
        return "First name: " + firstName + " | Last name: " + lastName + " | Id: " + employeeId + " | Score: " + score;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param newScore the score to set
     */
    public void giveNewScore(int newScore) {
        this.score = (this.score*this.numOfPeopleWhoScored + newScore) / (this.numOfPeopleWhoScored + 1);
        this.numOfPeopleWhoScored += 1;
    }
    
     /**
     * @return the id
     */
    public int getNumOfPeopleWhoScored() {
        return this.numOfPeopleWhoScored;
    }
}
