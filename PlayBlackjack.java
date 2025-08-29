import java.util.*;
public class PlayBlackjack {
    public static void playBlackjack(Scanner scanner) {
        System.out.println("\nWELCOME TO BLACKJACK!!\n");
        List<Integer> deck = new ArrayList<>();

        // Initialize deck
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        boolean keepPlaying = true;
        while (keepPlaying) {
            Collections.shuffle(deck);
            int deckIndex = 0;
            List<Integer> playerHand = new ArrayList<>();
            List<Integer> dealerHand = new ArrayList<>();

            // Deal initial two cards each
            // Player turn

            boolean playerBust = false;

            // Dealer turn

            // Determine winner
        }

        System.out.println("Thanks for playing!");
    }

    // Get Blackjack value of a card (1–13 -> 1–11, all face cards are worth 10)
    public static int getCardValue(int cardNumber) {
        int faceValue = (cardNumber - 1) % 13 + 1;
        if (faceValue >= 10) return 10;
        return faceValue;
    }

    // Get the string of a hand for display
    public static String getHandString(List<Integer> hand) {
        List<String> cards = new ArrayList<>();
        for (int card : hand) {
            cards.add(cardToString(card));
        }
        return String.join(", ", cards);
    }

    // Convert a card number to suit and face (e.g., "Ace of Hearts")
    public static String cardToString(int cardNumber) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] faces = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        int faceIndex = (cardNumber - 1) % 13;
        int suitIndex = (cardNumber - 1) / 13;

        return faces[faceIndex] + " of " + suits[suitIndex];
    }

    // Compute Blackjack hand value with Ace handling (1 or 11)
    public static int getHandValue(List<Integer> hand) {
        return 0;
    }
}