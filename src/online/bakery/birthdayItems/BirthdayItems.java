package online.bakery.birthdayItems;

import java.math.BigDecimal;

public interface BirthdayItems {

    String getDescription();

    int getItemId();


    String getName();
    void setName(String Name);

    BigDecimal getCost() ;
    void setCost(BigDecimal cost);

     BigDecimal getPurchasePrice();
     void  setPurchasePrice(BigDecimal price);

}