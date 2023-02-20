import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        double deposit;
        Casino mainCasino = new Casino();
        System.out.print("Enter amount for deposit: ");
        deposit = kb.nextDouble();
        do {
            if (deposit < 100 || deposit > 10000) {
                System.out.println("Please enter a valid deposit: (100-10000)");
                deposit = kb.nextDouble();
            }
        } while (deposit < 100 || deposit > 10000);
        Bank p1Bank = new Bank(deposit);

        kb.close();

    }
}
