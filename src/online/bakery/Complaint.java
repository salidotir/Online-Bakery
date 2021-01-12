/*
 * Class for users (employee, baker & bakery, customer) to write their complaints to admin.
 */
package online.bakery;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Complaint {
    private int complaintId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    private Account complainant;        // the one who write a complaint
    private Date createdDate;           // date the complaint was created
    private Date respondDate;           // date the complaint is respond
    private String title;
    private String text;
    private boolean respond;            // if the complaint is responced or not
    
    public Complaint(Account complainant, Date createdDate, String title, String text) {
        this.complaintId = atomicInteger.incrementAndGet();
        this.complainant = complainant;
        this.createdDate = createdDate;
        this.title = title;
        this.text = text;
        this.respond = false;
    }
    
    public static Complaint createComplaint(Account complainant, Date createdDate, String title, String text) {
        // notify admin of a new complaint & it adds it to dbms
        Complaint c = new Complaint(complainant, createdDate, title, text);
        boolean res = Admin.getInstance().newComplaint(c);     
        return c;
    }

    /**
     * @return the complainant
     */
    public Account getComplainant() {
        return complainant;
    }

    /**
     * @param complainant the complainant to set
     */
    public void setComplainant(Account complainant) {
        this.complainant = complainant;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the respond
     */
    public boolean isResponced() {
        return respond;
    }

    /**
     * set respond to true
     */
    public void setRespondTrue() {
        this.respond = true;
    }

    /**
     * @return the respondDate
     */
    public Date getRespondDate() {
        return respondDate;
    }

    /**
     * @param respondDate the respondDate to set
     */
    public void setRespondDate(Date respondDate) {
        this.respondDate = respondDate;
    }
    
    /**
     * @return the complaintId
     */
    public int getComplaintId() {
        return complaintId;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "** Complaint **\n" + 
                "id: " + this.complaintId + "\n" +
                "complainant: " + complainant.getProfile()+ "\n" + 
                "title: "  + title + "\n" +
                "text: " + text + "\n" +
                "created date: " + createdDate.toString() + "\n";
        if(respondDate == null) {
            s += "respond date: 'not responded yet.'\n";
        } else
            s += "respond date: " + respondDate.toString() + "\n";
        
        s += "is responded: " + respond + "\n" + "***************" + "\n";
                
        return s;
    }

}
