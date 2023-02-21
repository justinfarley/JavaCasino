public class Bank {
    private double balance;
    double salary;

    /**
     * constructor for the Bank class
     * 
     * @param bal inital balance of the created bank account
     * @param s   initial salary of created bank account
     */
    Bank(double bal, double s) {
        balance = bal;
        salary = s;
    }

    /**
     * 
     * @param amount the amount to withdraw from the bank
     * @param wallet the wallet to put the withdrawal into
     */
    void withdraw(double amount, Wallet wallet) {
        wallet.addBalance(amount);
        this.balance -= amount;
    }

    /**
     * Calculated interest each day is 0.002 times the current balance and it
     * immediately gets added back onto the balance
     */
    void interestPerDay() {
        double interestVal = balance * 0.002;
        balance += interestVal;
        System.out.println("Your balance has increased by: " + interestVal);

    }

    /**
     * 
     * @return the bank balance
     */
    double getBalance() {
        return balance;
    }

    /**
     * 
     * @param val the value to be added to the bank balance
     */
    void addBalance(double val) {
        balance += val;
    }

    /**
     * 
     * @param wallet the wallet to add the salary to
     */
    void addSalary(Wallet wallet) {
        // salary depends on job
        wallet.addBalance(salary);

    }

    void setSalary(double sal) {
        salary = sal;
    }

    double getSalary() {
        return salary;
    }
}
