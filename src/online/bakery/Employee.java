/*
 * Delivery employees information.
 */
package online.bakery;

import java.util.Date;
import online.bakery.sweets.Rate;

/**
 *
 * @author salidotir
 */
public class Employee extends Account{
    int employeeId ;
    private boolean isBusy;
    private Rate score;
    private int numOfPeopleWhoScored = 0;
    
    public Employee(String FirstName, String LastName) {
        super();
        super.setFirstname(FirstName);
        super.setLastname(LastName);
        this.score = Rate.ZERO;
        this.employeeId = super.ID;
        this.isBusy = false;
    }

    public String getProfile() {
        return "First name: " + super.getFirstname() + " | Last name: " + super.getLastname() + " | Id: " + employeeId + " | Score: " + score;
    }

    /**
     * @return the score
     */
    public Rate getScore() {
        return score;
    }

    /**
     * @param newScore the score to set
     */
    public void giveScore(Rate newScore) {
        //this.score = (this.score*this.numOfPeopleWhoScored + newScore) / (this.numOfPeopleWhoScored + 1);
        this.numOfPeopleWhoScored += 1;
    }
    
     /**
     * @return the id
     */
    public int getNumOfPeopleWhoScored() {
        return this.numOfPeopleWhoScored;
    }
    
    public boolean deliverOrder(DeliveryInformation deliveryInformation) {
        // call setEmployeeIsBusyFalse() of DBMS from Admin
        deliveryInformation.setActualDeliveryTime(new Date());
        return true;
    }
    
    public boolean addNote(Note note, String extraText) {
        if (this.employeeId == note.getNoteEmployeeId()) {
            note.setNoteEmployeeText(extraText);
        }
        return true;
    }

    /**
     * @return the isBusy
     */
    public boolean isIsBusy() {
        return isBusy;
    }

    /**
     * @param isBusy the isBusy to set
     */
    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
}
