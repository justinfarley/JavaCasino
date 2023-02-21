import java.util.*;

public class Casino {
    /**
     * input for user to decide which game they want to play
     */
    public void inputGame() {
        Scanner kb = new Scanner(System.in);

        System.out.print("Which game would you like to play: ");
        System.out.println("\n1. Blackjack\n2. Poker\n3. Slots");

        int num = kb.nextInt();
        while (!(num == 1 || num == 2 || num == 3)) {
            System.out.print("Error: enter valid input: ");
            num = kb.nextInt();
        }
        if (num == 1)
            cardsInit();
        else if (num == 2)
            playPoker();
        else if (num == 3)
            playSlots();

    }

    /**
     * no params
     * puts all card values into an array
     * 
     * @return a string array of the cards initialized
     */
    String[] cardsInit() {
        String[] cards = new String[53];
        cards[0] = "0";
        for (int i = 1; i <= 10; i++) { // 1-13 is hearts
            cards[i] = i + "oH";
        }
        cards[11] = "JoH";
        cards[12] = "QoH";
        cards[13] = "KoH";
        for (int i = 1; i <= 10; i++) { // 14-26 is spades
            cards[i + 10] = i + "oS";
        }
        cards[24] = "JoS";
        cards[25] = "QoS";
        cards[26] = "KoS";

        for (int i = 1; i <= 10; i++) { // 27-39 is diamons
            cards[i + 20] = i + "oD";
        }
        cards[37] = "JoD";
        cards[38] = "QoD";
        cards[39] = "KoD";

        for (int i = 1; i <= 10; i++) { // 39-52 is diamonds
            cards[i + 30] = i + "oC";
        }
        cards[50] = "JoC";
        cards[51] = "QoC";
        cards[52] = "KoC";
        return cards;
    }

    /**
     * game loop for blackjack being played in the console
     */
    void playBlackJack() {
        String[] cards = cardsInit();
        /**
         * randomly generate two set of cards(one for the user and one for the cpu) and
         * make sure they are not the same
         * display users cards
         * ask the user if they want to hit or stay
         * if user decides to hit and total of all current cards is over 21 they bust,
         * if the sum EQUALS 21 they win, otherwise if the sum of cpu's is greater than
         * 1 they lose
         * if both are over 21, get money back
         * if cpu goes over and you dont you win 2x money
         * dealer repeatedly hits until bust or over equal 17
         * if uesr gets 21, display a special message celebreating their blackjack
         */
    }

    /**
     * game loop for poker being played in the console
     */
    void playPoker() {
        String[] cards = cardsInit();
        /**
         * 
         */
    }

    /**
     * game loop for slots being played in the console
     * 
     */
    void playSlots() {
        /**
         * 
         */
    }
}
