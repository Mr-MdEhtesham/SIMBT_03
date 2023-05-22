import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {"hangman", "java", "programming", "computer", "openai"};
    private static final int MAX_TRIES = 6;

    public static void main(String[] args) {
        String wordToGuess = getRandomWord();
        char[] guessedLetters = new char[wordToGuess.length()];
        int numTries = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Guess a letter: ");
            char guess = scanner.next().charAt(0);

            if (alreadyGuessed(guess, guessedLetters)) {
                System.out.println("You've already guessed that letter!");
                continue;
            }

            if (!updateGuessedLetters(guess, wordToGuess, guessedLetters)) {
                numTries++;
                System.out.println("Wrong guess! You have " + (MAX_TRIES - numTries) + " tries left.");
            }

            if (hasWon(guessedLetters)) {
                System.out.println("Congratulations! You won! The word was: " + wordToGuess);
                break;
            }

            if (numTries == MAX_TRIES) {
                System.out.println("Game over! You lost. The word was: " + wordToGuess);
                break;
            }

            System.out.println("Current status: " + String.valueOf(guessedLetters));
        }

        scanner.close();
    }

    private static String getRandomWord() {
        int randomIndex = (int) (Math.random() * WORDS.length);
        return WORDS[randomIndex];
    }

    private static boolean alreadyGuessed(char guess, char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == guess) {
                return true;
            }
        }
        return false;
    }

    private static boolean updateGuessedLetters(char guess, String wordToGuess, char[] guessedLetters) {
        boolean found = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedLetters[i] = guess;
                found = true;
            }
        }
        return found;
    }

    private static boolean hasWon(char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == '\0') {
                return false;
            }
        }
        return true;
    }
}
