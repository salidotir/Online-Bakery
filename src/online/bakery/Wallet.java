package online.bakery;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Wallet {
    private BigDecimal Amount;
    private final int walletId ;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    
    public Wallet() {
        this.Amount = new BigDecimal(0);
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
    
    public BigDecimal getAmount() {
        return Amount;
    }

    public void subtractAmount(BigDecimal Amount) {
        this.Amount = this.Amount.subtract(Amount);
    }
    
    public void addAmount(BigDecimal Amount) {
        this.Amount = this.Amount.add(Amount);
    }   
}
