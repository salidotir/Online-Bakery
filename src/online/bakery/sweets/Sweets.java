package online.bakery.sweets;


import online.bakery.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Sweets {
    protected double Score = 0.0;
    protected int NumCustomerForScore = 0;
    protected int SweetId;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public String description = "Unknown Sweets";
    public BigDecimal TOTAL_Grams = new BigDecimal(0);
    public BigDecimal TOTAL_COST = new BigDecimal(0);
    protected BigDecimal fee;
    protected ArrayList<String> Images = new ArrayList<>();

    public abstract BigDecimal cost();

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
        return "Sweets{" +
                ", SweetId=" + SweetId +
                ", description='" + description + '\'' +
                ", TOTAL_Grams=" + TOTAL_Grams +
                ", TOTAL_COST=" + TOTAL_COST +
                "Score=" + Score +
                ", NumCustomerForScore=" + NumCustomerForScore +
                '}';
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
}
