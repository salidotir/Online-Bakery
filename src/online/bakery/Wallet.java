package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Wallet {
    private int Amount;
    private final int walletId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    public Wallet() {
        this.Amount = 0;
        this.walletId=atomicInteger.incrementAndGet();
    }
        
    public String getWalletInformation() {
        String s;
        s = "**Wallet infrmation**\n" + 
                "wallet id: " + walletId + "\n" +
                "amount: " + Amount + "\n" + 
                "____________________________\n";
        return s;
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
