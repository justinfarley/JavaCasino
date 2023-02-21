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
        try{
            playSlots();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

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
         * ask user to bet, input validate etc,
         * after successful bet, give the user 2 random cards
         * and assign 3 random cards to the "middle of the table"
         * display the cards to the user as well as the cards in the middle
         * ask the user if they would like to raise or not
         * if yes, ask amount etc like beginning and flip one more card, then ask for
         * raise again
         * if no. flip next 2 cards and calculate win or loss
         * idk about rewards....maybe like the higher up on the poker chart you get the
         * higher the winnings
         * maybe like this:
         * nothing/high card: 0x
         * 1-pair: 1.5x (50% gain)
         * 2-pair: 2x
         * 3 of a kind: 3x
         * straight: 3.5x
         * flush: 3.5x
         * full house: 5x
         * four of a kind: 10x
         * straight flush: 25x
         * royal flush: 10000x aint no way this shit gonna happen
         * 
         * obviously will adjust once we test
         */

    }

    /**
     * game loop for slots being played in the console
     * 
     */
    void playSlots() throws InterruptedException{
        /**
         * ask for bet
         * after bet is confirmed
         * randomly choose from an array of chars: {'*', '@', '#','&', '$', '7'}
         * IN ORDER OF LIKELY-NESS ex '*' is most common '7' is most rare maybe hashmap for this
         * then after randomly picking 3 symbols
         * loop through the 3 and system.out.print(symbolI + " ");
         * wait like 2.5 seconds in between each print
         * 
         * rewards:
         *  2 *: 1x
         *  3 *: 1.5x
         *  2 @: 2x
         *  3 @: 3x
         *  1 #: 0.5x;
         *  2 #: 2.5x
         *  3 #: 5x
         *  1 &: 0.75x
         *  2 &: 3x
         *  3 &: 10x
         *  1 $: 1x
         *  2 $: 5x
         *  3 $: 25x
         *  1 7: 0x
         *  2 7: 0x
         *  3 7: 10000x
         *  
         *  will change when we test
         */
    }
}
