package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author melika
 */
public class Wallet {
    private int Amount;
    int walletId ;
    static AtomicInteger atomicInteger = new AtomicInteger(2);
    
    public Wallet() {
        this.Amount = 0;
        this.walletId=atomicInteger.incrementAndGet();
    }
        
    public int getWalletId() {
        return this.walletId;
    }
    
    public int getAmount() {
        return Amount;
    }

    public void subtractAmount(int Amount) {
        this.Amount -= Amount;
    }
    
    public void addAmount(int Amount) {
        this.Amount += Amount;
    }   
}
