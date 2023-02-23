import java.util.*;

public class Casino {
    /**
     * input for user to decide which game they want to play
     */
    public void inputGame(Bank bank, Wallet wallet) throws InterruptedException {
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
            playPoker(bank, wallet);
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
            cards[i + 13] = i + "oS";
        }
        cards[24] = "JoS";
        cards[25] = "QoS";
        cards[26] = "KoS";

        for (int i = 1; i <= 10; i++) { // 27-39 is diamons
            cards[i + 26] = i + "oD";
        }
        cards[37] = "JoD";
        cards[38] = "QoD";
        cards[39] = "KoD";

        for (int i = 1; i <= 10; i++) { // 39-52 is diamonds
            cards[i + 39] = i + "oC";
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
         * if user gets 21, display a special message celebreating their blackjack
         */
        Random rand = new Random();
        int card1, card2 = cardsInit(rand(1,53));
        Player one = new player(card1, card2);
        Player two = new player(card1, card2);
        System.out.println("Your cards are " + card1 + " "  + card2); 
        System.out.println("The computer cards are " + card1 + " "  + card2); 

        int sum = 0; 
        if(one.card1 + one.card2 == 21)
            System.out.println("Congratulations, you have blackjack");
        else if(one.card1 + one.card2 == 21 && two.card1 + two.card2 == 21);
            System.out.println("You have blackjack, but so does player2 ");
        if(two.cards1 + two.cards2 <= 17)
            hits(two);

    }
        void hits() { 
            // adds card to the user or player deck
            int cardNext = cardsInit(rand(1,53));
            
        }


    /**
     * game loop for poker being played in the console
     */
    void playPoker(Bank bank, Wallet wallet) throws InterruptedException {
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
        Scanner scan = new Scanner(System.in);
        Random r = new Random();
        String[] cards = cardsInit();
        // if card is picked already, take it out of the pool
        int rand = r.nextInt(1, cards.length - 1);
        int temp = rand;
        int card1Num = rand;
        rand = r.nextInt(1, cards.length - 1);
        while (rand == temp) {
            rand = r.nextInt(1, cards.length - 1);
        }
        int card2Num = rand;
        String card1 = cards[card1Num];
        String card2 = cards[card2Num];
        System.out.println("______ " + card1 + "   " + card2);
        ArrayList<String> pot = new ArrayList<String>();
        rand = r.nextInt(1, cards.length - 1);
        pot.add(cards[rand]);
        temp = rand;
        while (rand == temp) {
            rand = r.nextInt(1, cards.length - 1);
        }
        pot.add(cards[rand]);
        int temp2 = rand;
        while (rand == temp || rand == temp2) {
            rand = r.nextInt(1, cards.length - 1);
        }
        pot.add(cards[rand]);
        int temp3 = rand;
        while (rand == temp || rand == temp2 || rand == temp3) {
            rand = r.nextInt(1, cards.length - 1);
        }
        pot.add(cards[rand]);
        int temp4 = rand;
        while (rand == temp || rand == temp2 || rand == temp3 || rand == temp4) {
            rand = r.nextInt(1, cards.length - 1);
        }
        pot.add(cards[rand]);

        // START GAME

        System.out.println("Welcome to Poker!");
        Thread.sleep(1000);

        System.out.print("Enter the amount of money you would like to bet: ");
        double betAmount = scan.nextDouble();
        
        while(betAmount > wallet.getBalance()){
            System.out.println("OOPS! You don't have that much money in your wallet!");
            Thread.sleep(1000);
            if(betAmount <= bank.getBalance()){
                System.out.println("Would you like to use money from your bank instead? (y/n)");
                char ans = scan.next().charAt(0);
                if(ans == 'y'){
                    bank.addBalance(-betAmount);
                }
            }
            Thread.sleep(1000);
            System.out.println("Please enter a lower amount that you can afford: ");
            betAmount = scan.nextDouble();
        }
        System.out.println("You have successfully bet $" + betAmount + "!");
        Thread.sleep(1000);




        System.out.print("Your cards are ");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.print(". ");
        }
        Thread.sleep(1000);
        System.out.print("\n----------------------------------------");
        System.out.println("\n" + cardName(card1) + " AND " + cardName(card2));
        System.out.print("----------------------------------------");
        Thread.sleep(1000);
        System.out.println();
        System.out.print("The first 3 cards in the middle are ");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.print(". ");
        }
        System.out.println();
        System.out.print("\n----------------------------------------\n");

        //display first 3 cards only
        for (int i = 0; i < 2; i++) {
            System.out.print(cardName(pot.get(i)) + ", ");
            Thread.sleep(1000);
        }
        System.out.println(cardName(pot.get(2)));
        System.out.print("----------------------------------------\n");
        Thread.sleep(1000);

        System.out.println("Would you like to raise? (y/n)");
        char ans = scan.next().charAt(0);
        if(ans == 'y'){
            System.out.print("How much do you want to raise by? ");
            double raiseAmount = scan.nextDouble();
            while(raiseAmount > wallet.getBalance()){
                System.out.println("OOPS! You don't have that much money in your wallet!");
                Thread.sleep(1000);
                if(raiseAmount <= bank.getBalance()){
                    System.out.println("Would you like to use money from your bank instead? (y/n)");
                    ans = scan.next().charAt(0);
                    if(ans == 'y'){
                        bank.addBalance(-raiseAmount);
                    }
                }
                Thread.sleep(1000);
                System.out.println("Please enter a lower amount that you can afford: ");
                raiseAmount = scan.nextDouble();
            }
            System.out.println("You have successfully raised by another $" + raiseAmount);
        }



    }// end of poker method

    /**
     * game loop for slots being played in the console
     * 
     */
    void playSlots() throws InterruptedException {
        /**
         * ask for bet
         * after bet is confirmed
         * randomly choose from an array of chars: {'*', '@', '#','&', '$', '7'}
         * IN ORDER OF LIKELY-NESS ex '*' is most common '7' is most rare maybe hashmap
         * for this
         * then after randomly picking 3 symbols
         * loop through the 3 and system.out.print(symbolI + " ");
         * wait like 2.5 seconds in between each print
         * 
         * rewards:
         * 2 *: 1x
         * 3 *: 1.5x
         * 2 @: 2x
         * 3 @: 3x
         * 1 #: 0.5x;
         * 2 #: 2.5x
         * 3 #: 5x
         * 1 &: 0.75x
         * 2 &: 3x
         * 3 &: 10x
         * 1 $: 1x
         * 2 $: 5x
         * 3 $: 25x
         * 1 7: 0x
         * 2 7: 0x
         * 3 7: 10000x
         * 
         * will change when we test
         */
    }

    /**
     * 
     * determines the suit of a certain card
     * 
     * @param card the card input to be read: must be in format "KoD" or "10oH"
     * @return the suit of the card parameter in the form "Spade", "Diamond", etc.
     */
    String getCardSuit(String card) {
        String suit = card.substring(card.length() - 1).toLowerCase();
        if (suit.equals("d")) {
            return "Diamonds";
        } else if (suit.equals("h")) {
            return "Hearts";
        } else if (suit.equals("c")) {
            return "Clubs";
        } else if (suit.equals("s")) {
            return "Spades";
        } else {
            return null;
        }
    }
    /**
     * contains() function for arrays (since its not built in)
     * @param arr the array to search
     * @param search the item to search for
     * @return true or false depending on if the item was found or not
     */
    boolean contains(int[] arr, int search) {
        for (int i : arr) {
            if (i == search) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param card the card to get the name of
     * @return full string of the cards name, ex "10 of Hearts", or "Ace of Clubs", or "King of Spades"
     */
    String cardName(String card) {
        // input is "KoS"
        String cardName = "";
        String cardNum = card.substring(0, 1);
        String cardSuit = card.substring(2);
        switch (cardNum) {
            case "1":
                // account for 10
                if (card.substring(0, 2).equals("10")) {
                    cardName += "10";
                } else {
                    cardName += "Ace";
                }
                break;
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                cardName += cardNum;
                break;
            case "J":
                cardName += "Jack";
                break;
            case "Q":
                cardName += "Queen";
                break;
            case "K":
                cardName += "King";
                break;
        }
        cardName += " of ";
        cardName += getCardSuit(card);
        return cardName;

    }
}
