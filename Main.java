import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\nWELCOME TO YOUR GAMES!!");
        Scanner scanInput = new Scanner(System.in);
        char choice;
        choice = menu(scanInput);
        
        while (choice != 'Q'){
            //test for choice type and call appropriate Game
            scanInput.nextLine();
            choice = menu(scanInput);
            choice = 'Q';
        }

        scanInput.close();

    }

    public static char menu(Scanner input){
        char choice = 'Z';
        String inputString;

        //menu loop
        //   print menu
        //   get response & convert to upper case
 
        return choice;
    }
}

