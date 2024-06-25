import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int totalScore = 0;
        int roundsWon = 0;

        while (playAgain) {
            int attempts = 0;
            int maxAttempts = 10;
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100

            System.out.println("I have generated a number between 1 and 100. Can you guess it?");
            
            boolean correctGuess = false;

            while (attempts < maxAttempts && !correctGuess) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    correctGuess = true;
                    totalScore += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    roundsWon++;
                } else if (userGuess > randomNumber) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }
            }

            if (!correctGuess) {
                System.out.println("You've used all your attempts. The correct number was " + randomNumber);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");

            if (!playAgain) {
                System.out.println("Thank you for playing!");
                System.out.println("Total rounds won: " + roundsWon);
                System.out.println("Total score: " + totalScore);
            }
        }

        scanner.close();
    }
}
