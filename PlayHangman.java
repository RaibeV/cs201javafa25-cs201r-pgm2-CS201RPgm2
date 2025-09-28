//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayHangman {

    public static void playHangman(Scanner input) {
        System.out.println("\nWELCOME TO HANGMAN!!\n");
        Random random = new Random();
        ArrayList<String> wordList = new ArrayList<>();

        // load words into the ArrayList
        try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim().toLowerCase();
                if (!word.isEmpty()) {
                    wordList.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: words.txt file not found.");
            return;
        }

        if (wordList.isEmpty()) {
            System.out.println("The words.txt file is empty.");
            return;
        }            

        boolean keepPlaying = true;

        System.out.println("Welcome to Hangman!");

        while (keepPlaying) {
            // pick a random word
            String secret = wordList.get(random.nextInt(wordList.size()));
            // mask: underscores for letters
            char[] mask = new char[secret.length()];
            for (int i = 0; i < secret.length(); i++) {
                char ch = secret.charAt(i);
                if (Character.isLetter(ch)) mask[i] = '_';
                else mask[i] = ch; // keep non-letters as-is
            }

            java.util.Set<Character> guessed = new java.util.HashSet<>();
            int remainingAttempts = 6;
            boolean won = false;

            System.out.println("\nNew word selected. Start guessing!");

            while (remainingAttempts > 0 && !won) {
                // display mask
                System.out.print("Word: ");
                for (char c : mask) System.out.print(c + " ");
                System.out.println();

                System.out.println("Guessed letters: " + guessed);
                System.out.println("Remaining wrong attempts: " + remainingAttempts + "\n");
                System.out.print("Enter a letter or guess the full word: ");
                String line = input.nextLine().trim().toLowerCase();
                if (line.isEmpty()) {
                    System.out.println("Please enter a letter or a word.");
                    continue;
                }

                if (line.length() == 1) {
                    char ch = line.charAt(0);
                    if (!Character.isLetter(ch)) {
                        System.out.println("Please enter a valid letter.");
                        continue;
                    }
                    if (guessed.contains(ch)) {
                        System.out.println("You've already guessed '" + ch + "'.");
                        continue;
                    }
                    guessed.add(ch);

                    boolean found = false;
                    for (int i = 0; i < secret.length(); i++) {
                        if (secret.charAt(i) == ch) {
                            mask[i] = ch;
                            found = true;
                        }
                    }
                    if (found) {
                        System.out.println("Good guess!");
                    } else {
                        remainingAttempts--;
                        System.out.println("Wrong guess.");
                    }

                } else {
                    // full-word guess
                    if (line.equals(secret)) {
                        won = true;
                        // reveal
                        for (int i = 0; i < secret.length(); i++) mask[i] = secret.charAt(i);
                        break;
                    } else {
                        remainingAttempts--;
                        System.out.println("Wrong guess.");
                    }
                }

                // check win
                won = true;
                for (char c : mask) if (c == '_') { won = false; break; }
            }

            if (won) {
                System.out.println("\nCongratulations! You guessed the word: " + secret);
            } else {
                System.out.println("\nOut of attempts. The word was: " + secret);
            }

            // Prompt to play again or return to menu
            while (true) {
                System.out.print("\nPlay again? (Y to play again, M to return to menu): ");
                String resp = input.nextLine().trim().toUpperCase();
                if (resp.equals("Y")) {
                    break; // outer while repeats
                } else if (resp.equals("M")) {
                    keepPlaying = false;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 'Y' or 'M'.");
                }
            }

        }

        System.out.println("Thanks for playing Hangman!");
    }
}