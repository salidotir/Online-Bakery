/*
 * Notes to be added to an order by:
 * 1. seller
 * 2. customer
 * 3. delivery employee
 */
package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Note {    
    int noteId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    private final int orderId;
    private final int noteSellerId;
    private final int noteCustomerId;
    private final int noteEmployeeId;          // maybe no need to employee for delivering the order
    
    private String noteSellerText;          // any note seller wants to add
    private String noteCustomerText;        // any note customer wants to add
    private String noteEmployeeText;        // any note delivery employee wants to add
    
    /**
     * 
     * @param orderId
     * @param sellerId
     * @param customerId
     * set these 2 parameters in the constructor, if customer or employee added a note, set their id fields.
     */
    public Note(int orderId, int sellerId, int customerId, int employeeId) {
        this.noteId = atomicInteger.incrementAndGet();
        
        this.orderId = orderId;
        this.noteSellerId = sellerId;
        this.noteCustomerId = customerId;
        this.noteEmployeeId = employeeId;
        
        this.noteSellerText = "";
        this.noteCustomerText = "";
        this.noteEmployeeText = "";
    }

    /**
     * @return the id
     */
    public int getId() {
        return noteId;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }
    
    /**
     * @return the noteSellerId
     */
    public int getNoteSellerId() {
        return noteSellerId;
    }

    /**
     * @return the noteCustomerId
     */
    public int getNoteCustomerId() {
        return noteCustomerId;
    }

    /**
     * @return the noteEmployeeId
     */
    public int getNoteEmployeeId() {
        return noteEmployeeId;
    }    
    
    /**
     * @return the noteSellerText
     */
    public String getNoteSellerText() {
        return noteSellerText;
    }

    /**
     * @param noteSellerText the noteSellerText to set
     */
    public void setNoteSellerText(String noteSellerText) {
        this.noteSellerText = noteSellerText;
    }

    /**
     * @return the noteCustomerText
     */
    public String getNoteCustomerText() {
        return noteCustomerText;
    }

    /**
     * @param noteCustomerText the noteCustomerText to set
     */
    public void setNoteCustomerText(String noteCustomerText) {
        this.noteCustomerText = noteCustomerText;
    }

    /**
     * @return the noteEmployeeText
     */
    public String getNoteEmployeeText() {
        return noteEmployeeText;
    }

    /**
     * @param noteEmployeeText the noteEmployeeText to set
     */
    public void setNoteEmployeeText(String noteEmployeeText) {
        this.noteEmployeeText = noteEmployeeText;
    }
    
    public String getNoteInformation() {
        String s;
        s = "**Note nformation**\n" +
                "note id: " + this.noteId + "\n" +
                "seller id: " + this.noteSellerId + "\n" +
                "customer id: " + this.noteCustomerId + "\n" +
                "employee id: " + this.noteEmployeeId + "\n" +
                "seller text: " + this.noteSellerText + "\n" +
                "customer text: " + this.noteCustomerText + "\n" +
                "employee text: " + this.noteEmployeeText + "\n" +
                "____________________________\n";
        return s;
    }
}
