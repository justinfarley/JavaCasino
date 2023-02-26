/**
 * Attributes:
 * balance: the current balance of dollars in the specified bank account
 * salary: the DAILY salary of the bank account
 * Methods:
 * Constructor: set initial balance and salary
 * withdraw: add a balance to the wallet and remove from the bank
 * interestPerDay: calculates and adds the interest after sleeping each day
 * getters and setters for balance and salary:
 */
public class Bank {
    private double balance;
    private double salary;

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
    void withdraw(double amount, Wallet wallet, Bank bank) {
        wallet.addBalance(amount);
        this.balance -= amount;
        System.out.println("Successful withdraw! You now have:\nBank: $" + bank.getBalance() +"\nWallet: " + wallet.getBalance());
    }

    void deposit(double amount, Wallet wallet, Bank bank) {
        if (wallet.getBalance() >= amount) {
            balance += amount;
            wallet.addBalance(-amount);
            System.out.println("Successful deposit! You now have:\nBank: $" + bank.getBalance() +"\nWallet: " + wallet.getBalance());
        }
    }

    /**
     * used for loading saved data
     * @param balance balance to set to
     */
    void setBalance(double b){
        balance = b;
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
