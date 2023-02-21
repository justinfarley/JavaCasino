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
    void bankDeposit(double val, Bank bank){
        bank.addBalance(val);  // add the amount to the bank
        balance -= val;  // remove the amount from hand (from the wallet)

    }
    void display(double amount) { 
        System.out.print(amount);

    }
}
