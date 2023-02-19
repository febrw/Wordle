package stacs.wordle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;

/** InputOutput contains static methods for reading from stdin and writing to stdout.
 *  @author 210025499
 */
public class InputOutput {
    /** Scanner for reading user input from std in. */
    private static Scanner scanner = new Scanner(System.in);

    /** loadWordlist takes a file path, loads the contents of the file into a list, then returns this list.
     *  @param wordlistPath             The path to the file whose contents should be read from
     *  @return                         An arraylist containing the words in the file specified
     *  @throws FileNotFoundException   Thrown if the file can not be found or read from
     *  @throws IOException             Thrown if closing the reader fails
     */ 
    public static ArrayList<String> loadWordlist(String wordlistPath) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(wordlistPath));
        ArrayList<String> wordList = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            wordList.add(line);
        }
        reader.close();
        return wordList;
    }

    /** getUserInput scans stdin and returns the string entered.
     *  @return                         The user's input string
     *  @throws IllegalStateException   Thrown if this scanner is closed
     *  @throws NoSuchElementException  Thrown if no line was found
     */ 
    public static String getUserInput() throws IllegalStateException, NoSuchElementException
    {
        return scanner.nextLine();
    }

    /** cleanUp closes the scanner.*/
    protected static void cleanUp()
    {
        scanner.close();
    }

    /** promptGuess prints a message asking the user for their input. */
    protected static void promptGuess() {
        System.out.println("Enter your guess!");
    }

    /** scannerErrorExit() prints a message indicating the scanner is not avilable.
     *  The program then exits indicating unsuccessful termination.
     */
    protected static void scannerErrorExit() {
        System.out.println("Error reading from standard input, Scanner no longer available. Exiting...");
        System.exit(1);
    }

    /** checkQuit takes a (sanitised) input string from the user and returns true or false indicating if the user requested to quit. 
     *  @return True if the user entered "!quit", False otherwise
     */ 
    protected static boolean checkQuit(String userInput)
    {
        if (userInput.equals("!quit")) {
            InputOutput.clearScreen();
            System.out.println("Thanks for playing!");
            return true;
        } else {
            return false;
        }
    }
    
    /** welcome displays a welcome screen after the user starts the game.
     *  The stdard output is cleared, then the game rules and instructions are displayed.
     *  @throws IllegalStateException   Thrown if this scanner is closed
     *  @throws NoSuchElementException  Thrown if no line was found
     */
    public static void welcome() throws IllegalStateException, NoSuchElementException {
        clearScreen();
        System.out.println("Let's play some Wordle!\n");
        System.out.println("How to play:");
        System.out.println("\t- You have up to 6 guesses to guess the Wordle");
        System.out.println("\t- A correct letter in the correct position turns green");
        System.out.println("\t- A correct letter in the wrong position turns yellow");
        System.out.println("\t- An incorrect letter turns red");
        System.out.println("\t- Wordles are 5 letters long, so make sure your guesses are 5 letters long!");
        System.out.println("\t- If your guess is not in the dictionary, you can make another guess for free!\n");
        System.out.println("Type and Enter: !quit at any time to close the application.");
        System.out.println("Enter something to start.");

        getUserInput();
        System.out.println("Good Luck!");
    }

    /** clearScreen resets the current colour and flushes the standard output. */
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /** printBoard takes a WordleApp and a guessCount, and prints the board state.
     *  @param app          A WordleApp representing the state of the game
     *  @param guessCount   The number of guesses the user has used
     */ 
    public static void printBoard(WordleApp app, int guessCount) {
        
        for (int i = 0; i < WordleApp.MAX_GUESSES; ++i) {
            String guess = "";
            Result[] result = {Result.NONE, Result.NONE, Result.NONE, Result.NONE, Result.NONE};
            String line = "";
            if (i < guessCount) {
                guess = app.getGuesses()[i];
                result = app.getResults()[i];
            }
            
            for (int j = 0; j < WordleApp.WORD_LENGTH; ++j) {
                line += "[" + result[j]
                        + (guess.isEmpty() ? " " : guess.charAt(j))
                        + Result.NONE + "]";
            }
            System.out.println(line);
        }
    }
}
