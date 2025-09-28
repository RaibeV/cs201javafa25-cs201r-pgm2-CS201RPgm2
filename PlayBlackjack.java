import java.util.*;
public class PlayBlackjack {
    public static void playBlackjack(Scanner scanner) {
        System.out.println("\nWELCOME TO BLACKJACK!!");
        boolean keepPlaying = true;
        while (keepPlaying) {
            // create and shuffle a fresh deck each round to avoid running out of cards
            List<Integer> deck = new ArrayList<>();
            for (int i = 1; i <= 52; i++) deck.add(i);
            Collections.shuffle(deck);

            List<Integer> playerHand = new ArrayList<>();
            List<Integer> dealerHand = new ArrayList<>();

            // Deal initial two cards each
            // Player turn
            
            for (int i = 0; i < 2; i++) {
                playerHand.add(deck.remove(0));
                dealerHand.add(deck.remove(0));
            }
            boolean playerBust = false;
            boolean playerStanding = false;

            while (!playerBust && !playerStanding) {
                System.out.println("\nYour hand: " + getHandString(playerHand) + " (Value: " + getHandValue(playerHand) + ")");
                System.out.print("Would you like to Hit or Stand? (H/S):");
                String choice = scanner.nextLine().trim().toUpperCase();
                // Allow quitting Blackjack entirely at any prompt
                if (choice.equals("Q")) {
                    System.out.println("Exiting Blackjack and returning to menu...");
                    return; // return to main menu
                }
                if (choice.equals("H")) {
                    if (deck.isEmpty()) {
                        // fresh deck in practice shouldn't be empty here, but guard just in case
                        for (int i = 1; i <= 52; i++) deck.add(i);
                        Collections.shuffle(deck);
                    }
                    playerHand.add(deck.remove(0));
                    if (getHandValue(playerHand) > 21) {
                        playerBust = true;
                        System.out.println("\nYou busted with a hand value of " + getHandValue(playerHand) + "!");
                    }
                } else if (choice.equals("S")) {
                    playerStanding = true;
                } else {
                    System.out.println("Invalid choice. Please enter H or S.");
                }
            }

            // Dealer turn & determine winner
            int playerValue = getHandValue(playerHand);
            if (playerBust) {
                // Player already busted
                System.out.println("\nDealer's hand: " + getHandString(dealerHand) + " (Value: " + getHandValue(dealerHand) + ")");
                System.out.println("Dealer wins, you busted.");
            } else {
                // Dealer draws until 17 or higher
                while (getHandValue(dealerHand) < 17) {
                    if (deck.isEmpty()) {
                        for (int i = 1; i <= 52; i++) deck.add(i);
                        Collections.shuffle(deck);
                    }
                    dealerHand.add(deck.remove(0));
                }

                int dealerValue = getHandValue(dealerHand);
                System.out.println("\nDealer's hand: " + getHandString(dealerHand) + " (Value: " + dealerValue + ")");

                if (dealerValue > 21) {
                    System.out.println("Dealer busted, you win!");
                } else if (dealerValue > playerValue) {
                    System.out.println("\nDealer wins with " + dealerValue + " against your " + playerValue + ".");
                } else if (dealerValue < playerValue) {
                    System.out.println("\nYou win with " + playerValue + " against dealer's " + dealerValue + "!");
                } else {
                    System.out.println("\nTie, both you and the dealer have " + playerValue + ".");
                }
            }

            // After the round, ask whether to play another hand (Y) or return to menu (M)
            String again;
            while (true) {
                System.out.print("\nPlay another hand? (Y to play again, M to return to menu): ");
                again = scanner.nextLine().trim().toUpperCase();
                if (again.equals("Y")) {
                    // start another round
                    break;
                } else if (again.equals("M")) {
                    keepPlaying = false;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'Y' to play again or 'M' to return to the menu.");
                }
            }

        }

        System.out.println("Thanks for playing!");
    }

    // Get Blackjack value of a card
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
        int sum = 0;
        int aces = 0;

        for (int card : hand) {
            int face = (card - 1) % 13 + 1; // 1..13
            if (face == 1) {
                aces++;
                sum += 1; // count ace as 1 for now
            } else if (face >= 10) {
                sum += 10;
            } else {
                sum += face;
            }
        }

        // Upgrade aces from 1 to 11 (add extra 10) while it doesn't bust
        while (aces > 0 && sum + 10 <= 21) {
            sum += 10;
            aces--;
        }

        return sum;
    }
}