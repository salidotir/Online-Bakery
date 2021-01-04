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

    public Discount(String name , int percent,Date start , Date end,int confectionerId) {
        atomicInteger.incrementAndGet();
        this.name = name;
        this.percent = percent;
        this.start = start;
        this.end = end;
        this.confectionerId = confectionerId;
    }

    public int getID(){return this.id;}

    public void setName(String name) {
        this.name = name;
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
}
