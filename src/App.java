
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to BLackJAck!");

        //Scanner for player input
        Scanner myScanner = new Scanner(System.in);

        // Generate a deck
        String[] deck = new String[]{"H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
            "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "RJ", "RQ", "RK",
            "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK",
            "K1", "K2", "K3", "K4", "K5", "K6", "K7", "K8", "K9", "K10", "KJ", "KQ", "KK"};

        

        // pick 2 random cards to player hand
        Random randomGen = new Random();


        int player1 = playerActivation(myScanner,deck,randomGen);
        int dealer = dealerActivation(myScanner,deck,randomGen);

        System.out.println("player has value: " + player1);
        System.out.println("dealer has value: " + dealer);


    }

    private static int playerActivation(Scanner myScanner, String[] deck, Random randomGen) {
        // Create a player hadn ( if we hae 12 cards we auto loose)
        String[] playerHand = new String[12];
        
        int index = randomGen.nextInt(52);
        //playerHand[0] = deck[index];
        playerHand[0] = "H1";
        index = randomGen.nextInt(52);
        playerHand[1] = deck[index];
        int handIndex = 2;

        //Print player hand (only non null values)
        for (int i = 0; i < playerHand.length; i++) {
            if (playerHand[i] != null) {
                System.out.println(playerHand[i]);
            }
        }

        int playerValue = findPValue(playerHand, deck);
        System.out.println("PLayer value is " + playerValue);

        while (playerValue < 21) {
            System.out.println("do you want another card? (Y/N)");
            String answer = myScanner.nextLine();

            if (answer.equals("Y")) {
                //draw a random card
                index = randomGen.nextInt(52);
                playerHand[handIndex] = deck[index];
                handIndex++;

                //calculate player value
                playerValue = findPValue(playerHand, deck);
                System.out.println("PLayer value is " + playerValue);
            }else{
                break;
            }
        }
        return playerValue;
    }

    //DEALER!
    private static int dealerActivation(Scanner myScanner, String[] deck, Random randomGen) {
        // Create a player hadn ( if we hae 12 cards we auto loose)
        String[] playerHand = new String[12];
        
        int index = randomGen.nextInt(52);
        //playerHand[0] = deck[index];
        playerHand[0] = "H1";
        index = randomGen.nextInt(52);
        playerHand[1] = deck[index];
        int handIndex = 2;

        //Print player hand (only non null values)
        for (int i = 0; i < playerHand.length; i++) {
            if (playerHand[i] != null) {
                System.out.println(playerHand[i]);
            }
        }

        int playerValue = findPValue(playerHand, deck);
        System.out.println("PLayer value is " + playerValue);

        while (playerValue < 17) {
                //draw a random card
                index = randomGen.nextInt(52);
                playerHand[handIndex] = deck[index];
                handIndex++;

                //calculate player value
                playerValue = findPValue(playerHand, deck);
                System.out.println("PLayer value is " + playerValue);
        }
        return playerValue;
    }

    public static int findPValue(String[] hand, String[] deck) {
        int sum = 0; //This is where all cards values will be added
        boolean aceInHand = false;

        for (int i = 0; i < hand.length; i++) {
            String currentCard = hand[i];
            if (currentCard != null) {
                int index = findIndex(currentCard, deck);
                int cardValue = index % 13 + 1; // +1 beacuse H8 has index 7 and therefore value 7
                //chekc if image card, j,q,k
                if (cardValue > 10) {
                    cardValue = 10;
                } else if (cardValue == 1) {//We have an ace
                    cardValue = 0;
                    aceInHand = true;
                }
                System.out.println(currentCard + " has value " + cardValue);

                sum = sum + cardValue;
            }
        }

        if (aceInHand) {
            if (sum <= 10) {
                sum += 11;  // same as: sum= sum + 11;
            } else {
                sum += 1;
            }
        }

        return sum;
    }

    private static int findIndex(String currentCard, String[] deck) {
        for (int j = 0; j < deck.length; j++) {
            if (currentCard.equals(deck[j])) { // currentCard == deck[j]
                System.out.println(currentCard + " has index " + j);
                return j;
            }
        }
        return -1; // SHould never happen, but needs to be here
    }

    public static int findValue(String strValue) {

        return 0; // OBS! needs changing
    }
}

/*
If finding values using substring start with
 * 
 * 
 //Split sting into substrings for each card
        for( int i= 0; i < playerHand.length; i++){
            String value= playerHand[i].substring(1);
          
            int number= findValue(value);

        }
 * 
 */
