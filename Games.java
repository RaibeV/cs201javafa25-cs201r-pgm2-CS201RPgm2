import java.util.Random;
import java.util.Scanner;

public class Games {
   
    public static void lotteryGame(Scanner input) {
        System.out.println("\nWELCOME TO THE LOTTERY!!\n");

        // Create random number generator and scanner
        Random rand = new Random();

        // Generate a random two-digit lottery number (10 to 99)
        //         && set the 2 digits of lottery number 
        int lotteryNum = rand.nextInt(90) + 10;

        // Prompt the user to enter a two-digit number
        System.out.print("Enter your lottery pick (10-99): ");
        Scanner scanner = new Scanner(System.in);
        int user = scanner.nextInt();

        // Ensure it's a valid two-digit number
        int userGuess = user;
        while (userGuess < 10 || userGuess > 99){
            System.out.println("Invalid input, please enter a valid lottery number (10-99): ");
            int newGuess = scanner.nextInt();
            userGuess = newGuess;
        }

        // Display lottery number
        System.out.println("\nThe lottery number is: " + lotteryNum);

        // Check for types of matches & print results
        // exact match
                int lotto1 = lotteryNum/10;
                int lotto2 = lotteryNum%10;
                int user1 = user/10;
                int user2 = user%10;

                if (lotteryNum == user){
                    System.out.println("You won big! $10,000!!");
                }
        // Check for all digits match (in different order)
                else if (user1 == lotto2 && user2 == lotto1){
                    System.out.println("You won $3,000!!");
                }
        // Check for one digit match
                else if (user1 == lotto1 || user1 == lotto2 || user2 == lotto1 || user2 == lotto2){
                    System.out.println("You won $1,000!!");
                }
        // Lose if no match
                else {
                    System.out.println("Sorry, you lost");
                }
                
                // After the result, require Y to play again or M to return to menu
                String resp;
                while (true) {
                    System.out.print("\nPlay again? (Y to play again, M to return to menu): ");
                    resp = scanner.next().trim().toUpperCase();
                    scanner.nextLine();
                    if (resp.equals("Y")) {
                        // restart the game if Y
                        lotteryGame(input);
                        return;
                    } else if (resp.equals("M")) {
                        return; // back to main menu if M
                    } else { 
                        System.out.println("Invalid choice. Please enter 'Y' to play again or 'M' to return to the menu.");
                    }
                }
        }

    public static void playCraps(Scanner input) {
        System.out.println("\nWELCOME TO CRAPS!!\n");
        Random rand = new Random();

        double netWorth = 50.00;
        boolean keepPlaying = true;

        while (keepPlaying) {
            if (netWorth <= 0.0) {
                System.out.println("You have no money left. Returning to the main menu.");
                return;
            }

            System.out.printf("\nYour current net worth: $%.2f\n", netWorth);

            // Prompt for bet amount
            double bet = 0.0;
            while (true) {
                System.out.print("Enter your bet amount: ");
                String line = input.nextLine().trim();
                try {
                    bet = Double.parseDouble(line);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a numeric bet amount.");
                    continue;
                }
                if (bet <= 0) {
                    System.out.println("Bet must be greater than 0.");
                } else if (bet > netWorth) {
                    System.out.println("Bet cannot exceed your net worth.");
                } else {
                    break;
                }
            }

            System.out.println("Press Enter to roll the dice (come-out roll)...");
            input.nextLine();

            int roll = rollDice(rand);
            System.out.println("You rolled: " + roll);

            boolean playerWon = false;

            // Cross check roll with game rules
            if (roll == 7 || roll == 11) {
                System.out.println("Natural! You win the Pass Line.");
                playerWon = true;
            } else if (roll == 2 || roll == 3 || roll == 12) {
                System.out.println("Craps! You lose the Pass Line.");
                playerWon = false;
            } else {
                int point = roll;
                System.out.println("Point is set to " + point + ". Keep rolling until you hit the point (win) or roll a 7 (lose).\n");

                boolean resolved = false; // Rolls dice until win/lose
                while (!resolved) {
                    System.out.println("Press Enter to roll the dice...");
                    input.nextLine();
                    roll = rollDice(rand);
                    System.out.println("You rolled: " + roll);
                    if (roll == point) {
                        System.out.println("You hit the point! Pass Line wins.");
                        playerWon = true;
                        resolved = true;
                    } else if (roll == 7) {
                        System.out.println("Seven-out! You lose the Pass Line.");
                        playerWon = false;
                        resolved = true;
                    }
                }
            }

            // Update net worth
            if (playerWon) {
                netWorth += bet;
                System.out.printf("You won $%.2f! New net worth: $%.2f\n", bet, netWorth);
            } else {
                netWorth -= bet;
                System.out.printf("You lost $%.2f. New net worth: $%.2f\n", bet, netWorth);
            }

            if (netWorth <= 0.0) {
                System.out.println("You have $0.00 and must quit betting. Returning to main menu.");
                return;
            }

            // Play again prompt
            String resp;
            while (true) {
                System.out.print("\nPlay again? (Y to play again, M to return to menu): ");
                resp = input.nextLine().trim().toUpperCase();
                if (resp.equals("Y")) {
                    break;
                } else if (resp.equals("M")) {
                    keepPlaying = false;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'Y' to play again or 'M' to return to the menu.");
                }
            }
        }

        System.out.println("Thanks for playing Craps!");

    }

    // Rolls 2 6 sided dice
    private static int rollDice(Random rand) {
        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        return die1 + die2;
    }

    public static void playScraps(Scanner input) {
        System.out.println("\nWELCOME TO SCRAPS!!\n");

        Random rand = new Random();
        double netWorth = 50.00;

        boolean keepPlaying = true;
        while (keepPlaying) {
            if (netWorth <= 0.0) {
                System.out.println("You have no money left. Returning to the main menu."); // Puts user in menu after losing all money
                return;
            }

            System.out.printf("\nYour current net worth: $%.2f\n", netWorth);

            // Prompt for bet amount
            double bet = 0.0;
            while (true) {
                System.out.print("Enter your bet amount: ");
                String line = input.nextLine().trim();
                try {
                    bet = Double.parseDouble(line);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a numeric bet amount.");
                    continue;
                }
                if (bet <= 0) {
                    System.out.println("Bet must be greater than 0.");
                } else if (bet > netWorth) {
                    System.out.println("Bet cannot exceed your net worth.");
                } else {
                    break;
                }
            }

            // User keeps rolling until win/lose
            System.out.println("Press Enter to roll three eight-sided dice...");
            input.nextLine();

            // roll 3 8 sided dice
            int d1 = rand.nextInt(8) + 1;
            int d2 = rand.nextInt(8) + 1;
            int d3 = rand.nextInt(8) + 1;
            int total = d1 + d2 + d3;
            System.out.printf("You rolled: %d, %d, %d (Total: %d)\n", d1, d2, d3, total);

            boolean playerWon = false;

            // Immediate win if any die is 8
            if (d1 == 8 || d2 == 8 || d3 == 8) {
                System.out.println("One of the dice is an 8 you win!");
                playerWon = true;
            }
            // Immediate win if total is 9, 10, or 14
            else if (total == 9 || total == 10 || total == 14) {
                System.out.println("Total is " + total + " you win!");
                playerWon = true;
            }
            // Immediate loss if any die is 1
            else if (d1 == 1 || d2 == 1 || d3 == 1) {
                System.out.println("One of the dice is a 1 you lose.");
                playerWon = false;
            }
            // Immediate loss if total is 8,20,23,24
            else if (total == 8 || total == 20 || total == 23 || total == 24) {
                System.out.println("Total is " + total + " you lose.");
                playerWon = false;
            }
            // Otherwise set point
            else {
                int point = total;
                System.out.println("Point is set to " + point + ". Keep rolling until you get the point (win) or roll a single 8 or a total of 15 (lose).");

                boolean resolved = false;
                while (!resolved) {
                    System.out.println("Press Enter to roll three eight-sided dice...");
                    input.nextLine();
                    d1 = rand.nextInt(8) + 1;
                    d2 = rand.nextInt(8) + 1;
                    d3 = rand.nextInt(8) + 1;
                    total = d1 + d2 + d3;
                    System.out.printf("You rolled: %d, %d, %d (Total: %d)\n", d1, d2, d3, total);

                    if (d1 == 8 || d2 == 8 || d3 == 8) { // User loses if any die is 8
                        System.out.println("Rolled an 8 you lose this round.");
                        playerWon = false;
                        resolved = true;
                    } else if (total == 15) { // User loses if total is 15
                        System.out.println("Total 15 you lose this round.");
                        playerWon = false;
                        resolved = true;
                    } else if (total == point) { // User wins if they roll the point
                        System.out.println("You rolled the point! You win this round.");
                        playerWon = true;
                        resolved = true;
                    }
                }
            }

            // Update net worth
            if (playerWon) {
                netWorth += bet;
                System.out.printf("You won $%.2f! New net worth: $%.2f\n", bet, netWorth);
            } else {
                netWorth -= bet;
                System.out.printf("You lost $%.2f. New net worth: $%.2f\n", bet, netWorth);
            }

            if (netWorth <= 0.0) {
                System.out.println("You have $0.00 and must quit betting. Returning to main menu.");
                return;
            }

            // Replay prompt: Y to play again, M to return to menu
            String resp;
            while (true) {
                System.out.print("\nPlay again? (Y to play again, M to return to menu): ");
                resp = input.nextLine().trim().toUpperCase();
                if (resp.equals("Y")) {
                    break;
                } else if (resp.equals("M")) {
                    keepPlaying = false;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'Y' to play again or 'M' to return to the menu.");
                }
            }
        }

        System.out.println("Thanks for playing Scraps!");
    }
    
    public static void playRPS(Scanner input) {
    System.out.println("\nWELCOME TO ROCK, PAPER, SCISSORS!!\n");

    Random rand = new Random();
    boolean keepPlaying = true;

    while (keepPlaying) {
        System.out.print("Enter your choice (R = Rock, P = Paper, S = Scissors): "); // Prompt user
        String userChoice = input.nextLine().trim().toUpperCase();

        if (!(userChoice.equals("R") || userChoice.equals("P") || userChoice.equals("S"))) {
            System.out.println("Invalid choice! Please enter R, P, or S.");
            continue; // loop back for valid input
        }

        // Computer's choice
        int compNum = rand.nextInt(3); // 0=R, 1=P, 2=S
        String compChoice;
        if (compNum == 0) {
            compChoice = "R";
        } else if (compNum == 1) {
            compChoice = "P";
        } else {
            compChoice = "S";
        }

        // Print computer's choice
        if (compChoice.equals("R")) {
            System.out.println("Computer chose: Rock");
        } else if (compChoice.equals("P")) {
            System.out.println("Computer chose: Paper");
        } else {
            System.out.println("Computer chose: Scissors");
        }

        // Determine winner
        if (userChoice.equals(compChoice)) {
            System.out.println("It's a tie!");
        } else if (
            (userChoice.equals("R") && compChoice.equals("S")) ||
            (userChoice.equals("P") && compChoice.equals("R")) ||
            (userChoice.equals("S") && compChoice.equals("P"))
        ) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }

        // Replay prompt
        String resp;
        while (true) {
            System.out.print("\nPlay again? (Y to play again, M to return to menu): ");
            resp = input.nextLine().trim().toUpperCase();
            if (resp.equals("Y")) {
                break; // restart game
            } else if (resp.equals("M")) {
                keepPlaying = false; // exit loop
                break;
            } else {
                System.out.println("Invalid choice. Please enter Y or M.");
            }
        }
    }

    System.out.println("Thanks for playing Rock, Paper, Scissors!");
}

    public static void playRPSSpock(Scanner input) {
        System.out.println("\nWELCOME TO ROCK, PAPER, SCISSORS, SPOCK!!\n");

        Random rand = new Random();
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.print("Enter your choice (R = Rock, P = Paper, S = Scissors, SP = Spock): ");
            String userChoice = input.nextLine().trim().toUpperCase();

            // validate input
            if (!(userChoice.equals("R") || userChoice.equals("P") ||
                userChoice.equals("S") || userChoice.equals("SP"))) {
                System.out.println("Invalid choice! Please enter R, P, S, or SP.");
                continue;
            }

            // Computer choice
            int compNum = rand.nextInt(4); // 0=R, 1=P, 2=S, 3=SP
            String compChoice;
            if (compNum == 0) {
                compChoice = "R";
                System.out.println("Computer chose: Rock");
            } else if (compNum == 1) {
                compChoice = "P";
                System.out.println("Computer chose: Paper");
            } else if (compNum == 2) {
                compChoice = "S";
                System.out.println("Computer chose: Scissors");
            } else {
                compChoice = "SP";
                System.out.println("Computer chose: Spock");
            }

            // Determine winner
            if (userChoice.equals(compChoice)) {
                System.out.println("It's a tie!");
            } 
            else if (
                (userChoice.equals("S") && compChoice.equals("P")) || // Scissors cuts Paper
                (userChoice.equals("P") && compChoice.equals("R")) || // Paper covers Rock
                (userChoice.equals("R") && compChoice.equals("S")) || // Rock crushes Scissors
                (userChoice.equals("P") && compChoice.equals("SP")) || // Paper exposes Spock
                (userChoice.equals("SP") && compChoice.equals("R")) || // Spock pulverizes Rock
                (userChoice.equals("SP") && compChoice.equals("S"))    // Spock uses Scissors
            ) {
                System.out.println("You win!");
            } 
            else {
                System.out.println("You lose!");
            }

            // Replay prompt
            String resp;
            while (true) {
                System.out.print("\nPlay again? (Y to play again, M to return to menu): ");
                resp = input.nextLine().trim().toUpperCase();
                if (resp.equals("Y")) {
                    break; // restart game
                } else if (resp.equals("M")) {
                    keepPlaying = false; // exit loop
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter Y or M.");
                }
            }
        }

        System.out.println("Thanks for playing Rock, Paper, Scissors, Spock!");
}


}
 

