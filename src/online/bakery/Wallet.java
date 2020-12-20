
import java.util.List;

/**
 *
 * @author melika
 */
public class Wallet {
    private int Amount;
    private final String WalletID;
    private List<String> Transaction;
    
    public Wallet(String WalletID) {
        this.WalletID = WalletID;
    }
    
    
    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }   
    
}
