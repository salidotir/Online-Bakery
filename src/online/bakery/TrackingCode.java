/*
 * Class to create 24-digit random tracking codes.
 * mainly used for tracking code in payment.
 */
package online.bakery;

/**
 *
 * @author salidotir
 */
public abstract class TrackingCode {
    static char digits[] = {'0','1','2','3','4','5','6','7','8','9'};

    public static char randomDecimalDigit() {
        return digits[(int)Math.floor(Math.random() * 10)];
    }

    public static String randomDecimalString(int ndigits) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<ndigits; i++) {
            result.append(randomDecimalDigit());
        }
        return result.toString();
    }
}
