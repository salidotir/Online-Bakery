package online.bakery;


import java.math.BigDecimal;

public abstract class Sweets {
    protected String description = "Unknown Sweets";
    protected BigDecimal TOTAL_Grams = new BigDecimal(0);
    protected BigDecimal TOTAL_COST = new BigDecimal(0);


    //todo id,score,producerId,designerId
    public abstract BigDecimal cost();

    public String getDescription() {
        return description;
    }
    public BigDecimal getTOTAL_Grams(){
        return TOTAL_Grams;
    }
    public BigDecimal getTOTAL_COST(){
        return TOTAL_COST;
    }
}
