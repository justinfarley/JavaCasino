public class Wallet {
    private double balance;

    Wallet(double initBal) {
        balance = initBal;
    }

    double getBalance() {
        return balance;
    }

    void addBalance(double amount) {
        balance += amount;
    }
}
