package online.bakery;
import java.util.List;

/**
 *
 * @author melika
 */
public class Wallet {
    private int Amount;
    private final String WalletID;
    private List<String> Transaction; //should decide when to set later
    
    public Wallet(String WalletID) {
        this.WalletID = WalletID;
    }
        
    public int getAmount() {
        return Amount;
    }

    public void addAmount(int Amount) {
        this.Amount += Amount;
    }   
    
    
}
