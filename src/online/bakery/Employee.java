/*
 * Delivery employees information.
 */
package online.bakery;

import java.util.Date;

/**
 *
 * @author salidotir
 */
public class Employee extends Account{
    int employeeId ;
    private int score;
    private int numOfPeopleWhoScored = 0;
    
    public Employee(String FirstName, String LastName) {
        super();
        super.setFirstname(FirstName);
        super.setLastname(LastName);
        this.score = 0;
        this.employeeId = super.ID;
    }

    public String getProfile() {
        return "First name: " + super.getFirstname() + " | Last name: " + super.getLastname() + " | Id: " + employeeId + " | Score: " + score;
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
    
    public boolean deliverOrder(DeliveryInformation deliveryInformation) {
        deliveryInformation.setActualDeliveryTime(new Date());
        return true;
    }
    
    public boolean addNote(Note note, String extraText) {
        if (this.employeeId == note.getNoteEmployeeId()) {
            note.setNoteEmployeeText(extraText);
        }
        return true;
    }
}