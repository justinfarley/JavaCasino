
/**
 * 
 * 
 * 
 * 
 * 
 */
public class Wallet {
    private double balance;

    Wallet(double initBal) {
        balance = initBal;
    }

    double getBalance() {
        return balance;
    }
    /**
     * adds amount inputted to the wallet balance
     * @param amount amount to add to the wallet
     */
    void addBalance(double amount) {
        balance += amount;
    }
    /**
     * deposits a certain amount into the bank and removes from wallet balance
     * @param val amount to deposit
     * @param bank the bank to deposit into
     */
    void bankDeposit(double val, Bank bank){
        bank.addBalance(val);  // add the amount to the bank
        balance -= val;  // remove the amount from hand (from the wallet)

    }
    /**
     * used to set balance when loading data
     * @param b amount to set to
     */
    void setBalance(double b){
        balance = b;
    }
}
