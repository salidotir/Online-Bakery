package online.bakery;

import java.math.BigDecimal;

public interface BirthdayItems {

    String getDescription();


    String getName();
    void setName(String Name);

    BigDecimal getCost() ;
    void setCost(BigDecimal cost);

     BigDecimal getPurchasePrice();
     void  setPurchasePrice(BigDecimal price);

}