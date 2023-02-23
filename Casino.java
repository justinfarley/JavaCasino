import java.util.*;

import javax.management.loading.MLet;

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
        String[] cards = cardsInit();
        Random rand = new Random();
        String card1, card2;
        card1 = cards[rand.nextInt(1, 53)];
        /*Player one = new Player(card1, card2);
        Player two = new Player(card1, card2);
        System.out.println("Your cards are " + card1 + " " + card2);
        System.out.println("The computer cards are " + card1 + " " + card2);
        int sum = 0;
        if (one.card1 + one.card2 == 21)
            System.out.println("Congratulations, you have blackjack");
        else if (one.card1 + one.card2 == 21 && two.card1 + two.card2 == 21)
            ;
        System.out.println("You have blackjack, but so does player2 ");
        if (two.cards1 + two.cards2 <= 17)
            hits(two);
*/
    }
/*
    void hits() {
        // adds card to the user or player deck
        int cardNext = cardsInit(rand(1, 53));
    }
    */
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
        // #region prompts
        System.out.println("Welcome to Poker!");
        Thread.sleep(1000);

        System.out.print("Enter the amount of money you would like to bet: ");
        double betAmount = scan.nextDouble();

        while (betAmount > wallet.getBalance()) {
            System.out.println("OOPS! You don't have that much money in your wallet!");
            Thread.sleep(1000);
            if (betAmount <= bank.getBalance()) {
                System.out.println("Would you like to use money from your bank instead? (y/n)");
                char ans = scan.next().charAt(0);
                if (ans == 'y') {
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
        System.out.println("\n" + getCardName(card1) + " AND " + getCardName(card2));
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

        // display first 3 cards only
        for (int i = 0; i < 2; i++) {
            System.out.print(getCardName(pot.get(i)) + ", ");
            Thread.sleep(1000);
        }
        System.out.println(getCardName(pot.get(2)));
        System.out.print("----------------------------------------\n");
        Thread.sleep(1000);

        System.out.println("Would you like to raise? (y/n)");
        char ans = scan.next().charAt(0);
        if (ans == 'y') {
            System.out.print("How much do you want to raise by? ");
            double raiseAmount = scan.nextDouble();
            while (raiseAmount > wallet.getBalance()) {
                System.out.println("OOPS! You don't have that much money in your wallet!");
                Thread.sleep(1000);
                if (raiseAmount <= bank.getBalance()) {
                    System.out.println("Would you like to use money from your bank instead? (y/n)");
                    ans = scan.next().charAt(0);
                    if (ans == 'y') {
                        bank.addBalance(-raiseAmount);
                    }
                }
                Thread.sleep(1000);
                System.out.println("Please enter a lower amount that you can afford: ");
                raiseAmount = scan.nextDouble();
            }
            System.out.println("You have successfully raised by another $" + raiseAmount);
            betAmount += raiseAmount;
            // show one more card, ask for raise again
            System.out.println("Okay! Here is the next card: ");
            for (int i = 0; i < 3; i++) {
                System.out.print(getCardName(pot.get(i)) + ", ");
                Thread.sleep(1000);
            }
            System.out.println(getCardName(pot.get(3)));
            Thread.sleep(1000);
            System.out.println("Would you like to raise? (y/n)");
            ans = scan.next().charAt(0);
            if (ans == 'y') {
                System.out.print("How much do you want to raise by? ");
                raiseAmount = scan.nextDouble();
                while (raiseAmount > wallet.getBalance()) {
                    System.out.println("OOPS! You don't have that much money in your wallet!");
                    Thread.sleep(1000);
                    if (raiseAmount <= bank.getBalance()) {
                        System.out.println("Would you like to use money from your bank instead? (y/n)");
                        ans = scan.next().charAt(0);
                        if (ans == 'y') {
                            bank.addBalance(-raiseAmount);
                        }
                    }
                    Thread.sleep(1000);
                    System.out.println("Please enter a lower amount that you can afford: ");
                    raiseAmount = scan.nextDouble();
                }
                System.out.println("You have successfully raised by another $" + raiseAmount);
                betAmount += raiseAmount;
                // show one more card, ask for raise again
                System.out.println("Okay! Here is the next card: ");
                for (int i = 0; i < 4; i++) {
                    System.out.print(getCardName(pot.get(i)) + ", ");
                    Thread.sleep(1000);
                }
                System.out.println(getCardName(pot.get(4)));
                Thread.sleep(1000);
            }

        } else {
            // show last 2 cards
            System.out.println("Okay! Here are all 5 cards: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(getCardName(pot.get(i)) + ", ");
                Thread.sleep(1000);
            }
            System.out.println(getCardName(pot.get(4)));
            Thread.sleep(1000);
        }
        // #endregion prompts
        String result = pokerCalculations(card1, card2, pot);
        switch (result.toLowerCase()) {
            case "onepair":
                System.out.println("You got a One Pair! Thats a 1.5x multiplier win! GG!");
                win(1.5, betAmount, wallet, bank);
                break;
            case "twopair":
                System.out.println("You got a Two Pair! Thats a 2x multiplier win! GG!");
                win(2, betAmount, wallet, bank);
                break;
            case "threeofakind":
                System.out.println("You got a Three of a Kind! Thats a 3x multiplier win! GG!");
                win(3, betAmount, wallet, bank);
                break;
            case "fourofakind":
                System.out.println("You got a FOUR OF A KIND!! Thats a WHOPPING 10x multiplier win! GG!");
                win(10, betAmount, wallet, bank);
                break;
            case "straight":
                System.out.println("You got a Straight! Thats a 3.5x multiplier win! GG!");
                win(3.5, betAmount, wallet, bank);
                break;
            case "flush":
                System.out.println("You got a Flush! Thats a 3.5x multiplier win! GG!");
                win(3.5, betAmount, wallet, bank);
                break;
            case "straightflush":
                System.out.println("You got a STRAIGHT FLUSH! WOW! Thats a HUGE 25x MULTIPLIER WIN! GG!");
                win(25, betAmount, wallet, bank);
                break;
            case "fullhouse":
                System.out.println("You got a FULL HOUSE! Thats a 5x multiplier win!");
                win(5, betAmount, wallet, bank);
                break;
            case "royalflush":
                System.out.println(
                        "WOW!!! YOU GOT A ROYAL FLUSH!!! GG!!!!!!!!!! THATS AN INSANE 10000x WIN! CONGRATULATIONS!");
                win(10000, betAmount, wallet, bank);
                break;
            default:
                System.out.println("You got nothing but a high card. Better luck next time");
                win(0, betAmount, wallet, bank);
                break;
        }
        // do calculations
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

    int getCardNum(String card) {
        if (card.substring(0, 2).equals("10")) {
            return 10;
        } else if (card.substring(0, 1).equals("J")) {
            return 11;
        } else if (card.substring(0, 1).equals("Q")) {
            return 12;
        } else if (card.substring(0, 1).equals("K")) {
            return 13;
        } else {
            return Integer.parseInt(card.substring(0, 1));
        }
    }

    /**
     * contains() function for arrays (since its not built in)
     * 
     * @param arr    the array to search
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
     * @return full string of the cards name, ex "10 of Hearts", or "Ace of Clubs",
     *         or "King of Spades"
     */
    String getCardName(String card) {
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

    /**
     * calculates what type of hand you have on the poker chart
     * 
     * @param pc1  the first card in the players hand
     * @param pc2  the seconds card in the players hand
     * @param pot1 the first card in the center
     * @param pot2 the second card in the center
     * @param pot3 the third card in the center
     * @param pot4 the fourth card in the center
     * @param pot5 the fifth card in the center
     * @return a string with the poker hand name. etc: OnePair, TwoPair,
     *         ThreeOfAKind, FourOfAKind, Straight, Flush, StraightFlush, FullHouse,
     *         RoyalFlush
     */
    String pokerCalculations(String pc1, String pc2, ArrayList<String> pot) {
        String result = "";
        int pc1Num = getCardNum(pc1);
        int pc2Num = getCardNum(pc2);
        HashMap<Integer, Integer> pairSet = new HashMap<Integer, Integer>();
        /*
         * 
         * 
         * 
         * 
         */

        // #region check_pairs
        pairSet.put(pc1Num, 0);
        pairSet.put(pc2Num, 0);
        for (int i = 0; i < 5; i++) {
            if (pc1Num == getCardNum(pot.get(i))) {
                if (pairSet.containsKey(pc1Num))
                    pairSet.put(pc1Num, pairSet.get(pc1Num) + 1); // increment
                else
                    pairSet.put(pc1Num, 1);
            }
            if (pc2Num == getCardNum(pot.get(i))) {
                if (pairSet.containsKey(pc2Num))
                    pairSet.put(pc2Num, pairSet.get(pc2Num) + 1); // increment
                else
                    pairSet.put(pc2Num, 1);
            }
        }
        switch (pairSet.get(pc1Num)) {
            case 1:
                result = "OnePair";
                break;
            case 2:
                result = "ThreeOfAKind";
                break;
            case 3:
                result = "FourOfAKind";
                break;
        }
        switch (pairSet.get(pc2Num)) {
            case 1:
                if (!result.equals("ThreeOfAKind") && !result.equals("FourOfAKind"))
                    result = "OnePair";
                break;
            case 2:
                if (!result.equals("FourOfAKind"))
                    result = "ThreeOfAKind";
                break;
            case 3:
                result = "FourOfAKind";
                break;
        }
        // #endregion check_pairs
        // #region check_straight
        int numsInRow = 1;
        HashMap<String, Integer> straightFlushCount = new HashMap<String, Integer>();

        straightFlushCount.put(getCardSuit(pc1), 1);
        straightFlushCount.put(getCardSuit(pc2), 1);
        for (int i = 0; i < 5; i++) {
            if (straightFlushCount.containsKey(getCardSuit(pot.get(i)))) {
                straightFlushCount.put(getCardSuit(pot.get(i)), straightFlushCount.get(getCardSuit(pot.get(i))) + 1);
            } else {
                straightFlushCount.put(getCardSuit(pot.get(i)), 1);
            }
        }

        if (pc1Num - numsInRow == pc2Num) {
            // before increment check if pc1Num PLUS 1 is on the board
            int temp = numsInRow;
            for (int i = 0; i < 5; i++) {
                if (pc1Num + numsInRow == getCardNum(pot.get(i))) {
                    numsInRow++;
                    break;
                }
            }
            if (temp == numsInRow) {
                numsInRow++;
            }
        }
        if (pc1Num + numsInRow == pc2Num) {
            int temp = numsInRow;
            for (int i = 0; i < 5; i++) {
                if (pc1Num - numsInRow == getCardNum(pot.get(i))) {
                    numsInRow++;
                    break;
                }
            }
            if (temp == numsInRow) {
                numsInRow++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (pc1Num - numsInRow == getCardNum(pot.get(i))) {
                numsInRow++;
            } else if (pc1Num - numsInRow == getCardNum(pot.get(i))) {
                numsInRow++;
            }
        }
        if (numsInRow >= 5) {
            result = "Straight";
        }
        // #endregion check_straight
        // #region check_flush

        HashMap<String, Integer> suitCounts = new HashMap<String, Integer>();
        suitCounts.put(getCardSuit(pc1), 1);
        if (!suitCounts.containsKey(getCardSuit(pc2))) {
            suitCounts.put(getCardSuit(pc2), 1);
        } else {
            suitCounts.put(getCardSuit(pc2), suitCounts.get(getCardSuit(pc2)) + 1);
        }
        for (int i = 0; i < 5; i++) {
            if (!suitCounts.containsKey(getCardSuit(pot.get(i)))) {
                suitCounts.put(getCardSuit(pot.get(i)), 1);
            } else {
                suitCounts.put(getCardSuit(pot.get(i)), suitCounts.get(getCardSuit(pot.get(i))) + 1);
            }
        }
        if ((suitCounts.containsKey("Hearts") && suitCounts.get("Hearts") >= 5)
                || (suitCounts.containsKey("Spades") && suitCounts.get("Spades") >= 5)
                || (suitCounts.containsKey("Diamonds") && suitCounts.get("Diamonds") >= 5)
                || (suitCounts.containsKey("Clubs") && suitCounts.get("Clubs") >= 5)) {
            result = "Flush";
        }

        // #endregion check_flush

        // #region check_fullHouse

        // #endregion check_fullHouse

        // #region check_straightFlush
        if (numsInRow >= 5) {
            if (straightFlushCount.get("Hearts") >= 5 || straightFlushCount.get("Diamonds") >= 5
                    || straightFlushCount.get("Spades") >= 5 || straightFlushCount.get("Clubs") >= 5) {
                result = "StraightFlush";
            }
        }
        // #endregion check_straightFlush
        //#region check_royalFlush
        HashMap<Integer, Boolean> isRoyalFlush = new HashMap<Integer, Boolean>();
        String suit = "";
        isRoyalFlush.put(1, false);
        isRoyalFlush.put(10, false);
        isRoyalFlush.put(11, false);
        isRoyalFlush.put(12, false);
        isRoyalFlush.put(13, false);
        
        if(pc1Num == 10 || pc1Num == 11 || pc1Num == 12 || pc1Num == 13 || pc1Num == 1){
            suit = getCardSuit(pc1);
            for(int i = 0; i < 5; i++){
                int num = getCardNum(pot.get(i));
                if(num == 10 || num == 11 || num == 12 || num == 13 || num == 1){
                    isRoyalFlush.put(num, true);
                }
            }
        }
        if(pc2Num == 10 || pc2Num == 11 || pc2Num == 12 || pc2Num == 13 || pc2Num == 1){
            suit = getCardName(pc2);
            for(int i = 0; i < 5; i++){
                int num = getCardNum(pot.get(i));
                if(num == 10 || num == 11 || num == 12 || num == 13 || num == 1){
                    isRoyalFlush.put(num, true);
                }
            }
        }
        boolean royalFlush = false;
        if(isRoyalFlush.get(1) && isRoyalFlush.get(10) && isRoyalFlush.get(11) && isRoyalFlush.get(12) && isRoyalFlush.get(13)){
            //check if all suits are the same
            royalFlush = true;
            for(int i = 0; i < 5; i++){
                if(!getCardSuit(pot.get(i)).equals(suit)){
                    royalFlush = false;
                }
            }
        }
        if(royalFlush){
            result = "RoyalFlush";
        }
        //#endregion check_royalFlush
        System.out.println((pairSet.get(pc1Num) + pairSet.get(pc2Num)) + " pairs");
        return result;
    }

    void win(double multiplier, double bet, Wallet wallet, Bank bank) {
        if (multiplier != 0) {
            double winnings = bet * multiplier;
            wallet.addBalance(winnings - bet);
        }
        else{
            wallet.addBalance(-bet);
        }
        System.out.println("You now have a wallet balance of: $" + wallet.getBalance() + " and a balance of $"
                + bank.getBalance() + " in your bank account.");
    }
}
