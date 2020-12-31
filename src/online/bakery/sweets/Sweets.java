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

    public abstract BigDecimal cost();

    public int getSweetId() {
        return SweetId;
    }

    public double getScore() {
        return Score;
    }

    public void addScore(Rate rate) {
        double myscore = this.NumCustomerForScore * this.Score;
        this.NumCustomerForScore += 1;
        double score = 0;
        switch (rate) {
            case ZERO:
                score = 0;
                break;
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
            case SIX:
                score = 6;
                break;
            case SEVEN:
                score = 7;
                break;
            case EIGHT:
                score = 8;
                break;
            case NINE:
                score = 9;
                break;
            case TEN:
                score = 10;
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
