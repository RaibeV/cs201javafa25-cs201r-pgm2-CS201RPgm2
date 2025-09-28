import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO YOUR GAMES!!");
        Scanner scanInput = new Scanner(System.in);
        char choice = menu(scanInput);

        while (choice != 'Q'){
            // dispatch to the selected game
            switch (choice) {
                case 'A' -> PlayHangman.playHangman(scanInput);
                case 'B' -> PlayBlackjack.playBlackjack(scanInput);
                case 'C' -> Games.lotteryGame(scanInput);
                case 'D' -> Games.playCraps(scanInput);
                case 'E' -> Games.playScraps(scanInput);
                case 'F' -> Games.playRPS(scanInput);
                case 'G' -> Games.playRPSSpock(scanInput);
                case 'Q' -> System.out.println("Thanks for playing!");
                default -> System.out.println("Invalid choice, please try again.");
            }

            // prompt again
            choice = menu(scanInput);
        }

        scanInput.close();

    }

    public static char menu(Scanner input){
        String inputString;

        // Menu prompt
        System.out.println("\nPlease choose a game to play:");
        System.out.println("A: Hangman");
        System.out.println("B: Blackjack");
        System.out.println("C: Lottery");
        System.out.println("D: Craps");
        System.out.println("E: Scraps");
        System.out.println("F: Rock, Paper, Scissors");
        System.out.println("G: Rock, Paper, Scissors Spock");
        System.out.println("Q: Quit");
        System.out.print("Enter your choice: ");

        // use the provided Scanner and return the first character of the input (uppercased)
        inputString = input.nextLine().trim().toUpperCase();
        if (inputString.isEmpty()) return 'Z';
        return inputString.charAt(0);
    }
}

