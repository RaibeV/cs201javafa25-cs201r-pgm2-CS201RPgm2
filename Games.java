import java.util.Random;
import java.util.Scanner;

public class Games {
   
    public static void lotteryGame(Scanner input) {
        System.out.println("\nWELCOME TO THE LOTTERY!!\n");

        // Create random number generator and scanner
        Random rand = new Random();

        // Step 1: Generate a random two-digit lottery number (10 to 99)
        //         && set the 2 digits of lottery number 

        // Step 2: Prompt the user to enter a two-digit number
 

        // Step 3:Ensure it's a valid two-digit number
 
        // Step 4: get the 2 digits of userGuess

        // Step 5: Display lottery number
 

        // Step 6: Check for types of matches & print results
        // exact match
 
        // Step 7: Check for all digits match (in different order)
 
        // Step 8: Check for one digit match
 
        // Step 9: No match
 

    }

    public static void playCraps(Scanner input) {
        System.out.println("\nWELCOME TO CRAPS!!\n");
        double netWorth = 50;
          
        System.out.println("Thanks for playing! You ended with $" + netWorth);

    }

    // Helper method to roll two six-sided dice
    private static int rollDice(Random rand) {
        int die1 = 0;
        int die2 = 0;
        return die1 + die2;
    }

    public static void playScraps(Scanner input) {
        
        System.out.println("\nWELCOME TO SCRAPS!!\n");

        Random random = new Random();
        double worth = 50.00;

        

        System.out.println("Thanks for playing! You ended with $" + worth);
    }
    

}
 

