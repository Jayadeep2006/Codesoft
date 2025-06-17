import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    // Static objects and variables
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int maxAttempts = 9;
    static int roundCount = 0;
    static int totalScore = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Game!");
        boolean playAgain;

        do {
            roundCount++;
            int roundScore = playRound();
            totalScore += roundScore;

            System.out.println("Do you like to play another round? (YES/NO): ");
            playAgain = scanner.next().equalsIgnoreCase("YES");

        } while (playAgain);

        System.out.println("Game Over! You played " + roundCount + " round(s).");
        System.out.println("Your total score: " + totalScore);
    }

    // Method to play one round
    public static int playRound() {
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        int guess;

        System.out.println("Round " + roundCount + ": Guess a number between 1 and 100!");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess (" + (maxAttempts - attempts) + " attempts left): ");
            guess = scanner.nextInt();
            attempts++;

            if (guess == numberToGuess) {
                int score = (maxAttempts - attempts + 1) * 10;
                System.out.println("Correct! You guessed the number in " + attempts + " attempt(s).");
                System.out.println("Score for this round: " + score);
                return score;
            } else if (guess < numberToGuess) {
                System.out.println("Very low!");
            } else {
                System.out.println("Very high!");
            }
        }

        System.out.println("You've used all your attempts. The correct number was: " + numberToGuess);
        return 0;
    }
}
//J:\java project\NumberGame.java