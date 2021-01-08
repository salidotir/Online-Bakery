package online.bakery.sweets;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Sweets {

    protected double Score = 0.0;
    protected String name="";
    protected int NumCustomerForScore = 0;
    protected int SweetId;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public String description = "Unknown Sweets";
    public BigDecimal TOTAL_Grams = new BigDecimal(0);
    public BigDecimal TOTAL_COST = new BigDecimal(0);
    protected BigDecimal fee;
    protected ArrayList<String> Images = new ArrayList<>();
    protected TypeOfSweets type;


    public TypeOfSweets getType() {
        return type;
    }

    public abstract BigDecimal cost();

    public int getNumCustomerForScore() {
        return NumCustomerForScore;
    }

    public int getSweetId() {
        return SweetId;
    }

    public double getScore() {
        return Score;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public ArrayList<String> getImages() {
        return Images;
    }

    public void addImages(String image) {
        Images.add(image);
    }

    @Override
    public String toString() {
        return type.toString() + "{" +
                "Name=" + name +
                ", SweetId=" + SweetId +
                ", description='" + description + '\'' +
                ", TOTAL_Grams=" + getTOTAL_Grams().toString() +
                ", Order Cost=" + get_OderCost() +
                ", Score=" + Score +
                ", NumCustomerForScore=" + NumCustomerForScore +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addImage(String  image) {
        Images.add( image);
    }

    public String getName() {
        return name;
    }

    public void addScore(Rate rate) {
        double myscore = this.NumCustomerForScore * this.Score;
        this.NumCustomerForScore += 1;
        double score = 0;
        switch (rate) {
            case ONE:
                score = 1;
                break;
            case TWO:
                score = 2;
                break;
            case THREE:
                score = 3;
                break;
            case FOUR:
                score = 4;
                break;
            case FIVE:
                score = 5;
                break;
        }
        this.Score = (myscore + score) / this.NumCustomerForScore;


    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getTOTAL_Grams() {
        return TOTAL_Grams;
    }

    public BigDecimal getTOTAL_COST() {
        return TOTAL_COST;
    }
    public BigDecimal get_OderCost(){
        return TOTAL_COST.add(fee);
    }
}
