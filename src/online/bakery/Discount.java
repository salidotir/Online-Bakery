package online.bakery;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Discount {
    private int id ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private String name;
    private int percent;
    private Date start;
    private Date end;
    private int confectionerId;
    private int maxUsed;
    private int numberUsed;      // how many customers used this discount

    public Discount(String name , int percent,Date start , Date end,int confectionerId, int max) {
        atomicInteger.incrementAndGet();
        this.name = name;
        this.percent = percent;
        this.start = start;
        this.end = end;
        this.confectionerId = confectionerId;
        this.maxUsed = max;
        this.numberUsed = 0;
        Admin.getInstance().addDiscount(this);
    }
    
    public String getDiscountInformation(){
        String s = "_______Discount information:_______"+
                "Name: "+ this.name + "\n"+
                "Percent Off: "+ this.percent + "\n"+
                "Start Date: "+ this.start + "\n"+
                "End Date: "+ this.end + "\n"+
                (this.maxUsed - this.numberUsed) +" number is left.";
        return s;
    }

    public int getID(){return this.id;}

    public void setName(String name) {
        this.name = name;
    }

    public boolean useDiscount(){
        if (numberUsed + 1 == maxUsed){
            return false;
        }else{
            numberUsed += 1;
            return true;
        }
    }
        
    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getConfectionerId() {
        return confectionerId;
    }

    public int getPercent() {
        return percent;
    }
    
}
