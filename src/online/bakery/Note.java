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
    private int noteSellerId;
    private final int noteCustomerId;
    private int noteEmployeeId;
    
    private String noteSellerText;          // any note seller wants to add
    private String noteCustomerText;        // any note customer wants to add
    private String noteEmployeeText;        // any note delivery employee wants to add
    private String noteAdminText;           // any note admin wants to add
    
    /**
     * 
     * @param orderId
     * @param customerId
     * set these 2 parameters in the constructor, if customer or employee added a note, set their id fields.
     */
    public Note(int orderId, int customerId) {
        this.noteId = atomicInteger.incrementAndGet();
        
        this.orderId = orderId;
//        this.noteSellerId = sellerId;               //seller is not set at first
        this.noteCustomerId = customerId;
        //this.noteEmployeeId = employeeId;         // employee is not set at first
        
        this.noteSellerText = "";
        this.noteCustomerText = "";
        this.noteEmployeeText = "";
        this.noteAdminText = "";
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
    public boolean setNoteSellerText(String noteSellerText) {
        this.noteSellerText = noteSellerText;
        return true;
    }

    public String getNoteAdminText() {
        return noteAdminText;
    }

    public void setNoteAdminText(String noteAdminText) {
        this.noteAdminText = noteAdminText;
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
    public boolean setNoteCustomerText(String noteCustomerText) {
        this.noteCustomerText = noteCustomerText;
        return true;
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
    public boolean setNoteEmployeeText(String noteEmployeeText) {
        this.noteEmployeeText = noteEmployeeText;
        return true;
    }
    
    public boolean setNoteEmployeeId(int id) {
        this.noteEmployeeId = id;
        return true;
    }

    public void setNoteSellerId(int noteSellerId) {
        this.noteSellerId = noteSellerId;
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
                "admin text: " + this.noteAdminText + "\n" +
                "____________________________\n";
        return s;
    }
}
