/*
 * Delivery employees information.
 */
package online.bakery;

/**
 *
 * @author salidotir
 */
public class Employee {
    private String id;      // e.g. for employee the id will be 'e_idnumber'
    private String firstName;
    private String lastName;
    private int score;
    
    public Employee(String Id, String FirstName, String LastName) {
        this.id = Id;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.score = 0;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * @param diff the amount to be added to score of employee
     */
    public void increaseScore(int diff) {
        this.score += diff;
    }
    
    /**
     * @param diff the amount to reduce from score of employee
     */
    public void decreaseScore(int diff) {
        this.score -= diff;
    }
}
