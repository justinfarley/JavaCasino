public class Bank {
    double balance;

    Bank(double bal) {
        balance = bal;a
    }

    void withdraw(double amount, Wallet wallet) {
        wallet.addBalance(amount);
        this.balance -= amount;
    }

    void interest() {
        double interestVal = balance * 0.06;
        balance += interestVal;
        System.out.println("Your balance has increased by: " + interestVal);
    }
}
