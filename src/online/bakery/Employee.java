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
    private float score;
    private int numOfPeopleWhoScored = 0;
    
    public Employee(String FirstName, String LastName) {
        super();
        super.setFirstname(FirstName);
        super.setLastname(LastName);
        this.score = 0;
        this.employeeId = super.ID;
        this.isBusy = false;
    }

    public String getProfile() {
        return "First name: " + super.getFirstname() + " | Last name: " + super.getLastname() + " | Id: " + employeeId + " | Score: " + score;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
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
    
    /**
     * @param rate the score to set
     */
    public float addScore(Rate rate) {
        float newScore = 0;
        if(null != rate) switch (rate) {
            case ZERO:
                newScore = 0;
                break;
            case ONE:
                newScore = 1;
                break;
            case TWO:
                newScore = 2;
                break;
            case THREE:
                newScore = 3;
                break;
            case FOUR:
                newScore = 4;
                break;
            case FIVE:
                newScore = 5;
                break;
            case SIX:
                newScore = 6;
                break;
            case SEVEN:
                newScore = 7;
                break;
            case EIGHT:
                newScore = 8;
                break;
            case NINE:
                newScore = 9;
                break;
            case TEN:
                newScore = 10;
                break;
            default:
                break;
        }
        
        this.score = (this.score*this.numOfPeopleWhoScored + newScore) / (this.numOfPeopleWhoScored + 1);
        this.numOfPeopleWhoScored += 1;
        return this.score;
    }
}
