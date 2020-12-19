package online.bakery;


import java.math.BigDecimal;

public abstract class Sweets {
    protected String description = "Unknown Sweets";
    protected BigDecimal Grams = new BigDecimal(0);

    //todo id,score,producerId,designerId
    public abstract BigDecimal cost();

    public String getDescription() {
        return description;
    }
}
