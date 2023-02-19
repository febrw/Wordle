package stacs.wordle;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

import java.util.ArrayList;

/** WordleApp stores the state of the game, and the logic for processing guesses.
 *  @author 210025499
 */ 
public class WordleApp
{
    private static final String DEFAULT_WORDLIST_PATH = "src/main/resources/wordlist.txt";
    public static final int WORD_LENGTH = 5;
    public static final int MAX_GUESSES = 6;

    /** The collection of words from which the wordle could be sampled, and from which guesses are allowed. */
    private ArrayList<String> wordList;

    /** The word sampled from the wordList to be the wordle. */
    private String wordle;

    /** Stores each of the user's guesses. */
    private String[] guesses;

    /** Stores the results for each guess. */
    private Result[][] results;

    /** Entry point to the app.
     *  Exits unsuccessfully if scanner error occurs.
     *  @param args    path to wordle jar, path to class file containing main 
     */ 
    public static void main(String[] args) {
        System.out.println("Welcome to CS5031 - Wordle");
        WordleApp wordle;
        try {
            wordle = new WordleApp(DEFAULT_WORDLIST_PATH);
        } catch (IOException e) {
            System.out.println("Word List could not be loaded, exiting.");
            return;
        }

        try {
            wordle.runGame();
        } catch (IllegalStateException | NoSuchElementException e) {
            InputOutput.scannerErrorExit();
        }
    }

    /** Constructs a new WordleApp from a path to a file containing a wordlist.
     *  @param wordListPath             Path to a file containing word list
     *  @throws FileNotFoundException   Thrown if the file located at the file path cannot be found or read from
     *  @throws IOException             Thrown if closing the reader fails during loadWordlist
     */ 
    public WordleApp(String wordlistPath) throws FileNotFoundException, IOException {
        wordList = InputOutput.loadWordlist(wordlistPath);
        sampleNewWordle();
        guesses = new String[MAX_GUESSES];
        results = new Result[MAX_GUESSES][WORD_LENGTH];
    }

    /** runGame controls the main game loop, keeping track of the number of guesses, and updating the app.
     *  @throws IllegalStateException   Thrown if the scanner is closed
     *  @throws NoSuchElementException  Thrown if no line was found
     */ 
    public void runGame() throws IllegalStateException, NoSuchElementException {
        InputOutput.welcome(); // THROWS
        int guessCounter = 0;
        String guess;
        
        do {
            InputOutput.clearScreen();
            InputOutput.printBoard(this, guessCounter);
            InputOutput.promptGuess();
            
            for (;;) {
                guess = InputOutput.getUserInput().toLowerCase(); //THROWS
                // check quit
                if (InputOutput.checkQuit(guess)) {
                    return;
                } else {
                    try {
                        validateInput(guess);
                        break;
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                }
            }
            // only reached if guess is valid
            processGuess(guess, guessCounter);
            ++guessCounter;
                
            if (isWordle(guess)) {
                // win
                InputOutput.clearScreen();
                InputOutput.printBoard(this, guessCounter);
                System.out.println("Well Done!");
                // score?
                return;
            }
            
        }
        while (guessCounter < MAX_GUESSES);
        InputOutput.clearScreen();
        InputOutput.printBoard(this, guessCounter);
        System.out.println("Nice try, the wordle was: " + wordle);
    } 

    /** sampleNewWordle randomly samples a new wordle from this wordle's wordlist, modifying this app's wordle. */
    public void sampleNewWordle() {
        final int randomIndex = (int) (Math.random() * wordList.size());
        this.wordle = wordList.get(randomIndex);
    }

    /** processGuess takes a guess and a guessNumber (zero-indexed).
     *  processGuess updates the game state to include the new guess.
     *  @param guess        The user's guess
     *  @param guessNumber  Zero-indexed number corresponding to which guess is being entered. Zero is first guess, One is second.
     */ 
    public void processGuess(String guess, int guessNumber)
    {
        guesses[guessNumber] = guess;
        boolean[] skipChars = new boolean[WORD_LENGTH];
        boolean[] resultFilled = new boolean[WORD_LENGTH];

        // Match greens first
        for (int i = 0; i < WORD_LENGTH; ++i) {
            if (guess.charAt(i) == wordle.charAt(i)) {
                results[guessNumber][i] = Result.GREEN;
                skipChars[i] = true;
                resultFilled[i] = true;
            }
        }

        for (int i = 0; i < WORD_LENGTH; ++i) {
            if (resultFilled[i]) {
                continue;
            }
            for (int j = 0; j < WORD_LENGTH; ++j) {
                if (skipChars[j] || i == j) { // already checked i == j in match greens loop
                    continue;
                }
                // Yellow match found
                if (guess.charAt(i) == wordle.charAt(j)) {
                    results[guessNumber][i] = Result.YELLOW;
                    skipChars[j] = true;
                    resultFilled[i] = true;
                    break;
                }
            }
            // No match, set to red
            if (!resultFilled[i]) {
                results[guessNumber][i] = Result.GREY;
            }
        }  
    }

    /** validateInput takes a user's input and throws an exception if the input cannot be processed.
     *  @param  guess                           The user's input guess
     *  @throws IllegalArgumentException        Thrown if the guess is not a 5 letter word in the wordList
     */
    public void validateInput(String guess) throws IllegalArgumentException {
        if (!guess.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Letters only please!");
        }
        if (guess.length() != 5) {
            throw new IllegalArgumentException("Your guess must be 5 characters long!");
        }
        if (!wordList.contains(guess)) {
            throw new IllegalArgumentException("Not in dictionary: " + guess);
        }
    }

    /** getWordList gets this WordleApp's word list.
     *  @return The word list
     */
    public ArrayList<String> getWordList() {
        return wordList;
    }
    
    /** getWordle gets this WordleApp's wordle.
     *  @return The wordle
     */ 
    public String getWordle() {
        return wordle;
    }

    /** getGuesses gets this WordleApp's guesses.
     *  @return Array of guesses
     */ 
    public String[] getGuesses() {
        return guesses;
    }

    /** getResults gets this WordleApp's results.
     *  @return results
     */ 
    public Result[][] getResults()
    {
        return results;
    }

    /** setWordle sets this WordleApp's wordle to the input word.
     *  Used for testing only.
     *  @param wordle  The new wordle that will be set
     */ 
    protected void setWordle(String wordle)
    {
        this.wordle = wordle;
    }


    /** getResultsForGuess returns an array of results for the guess specified by a guessNumber.
     *  @param guessNumber                  The (zero-indexed) index of the guess we want to retrieve results for
     *  @return                             An array of results for the specified guess number
     *  @throws IndexOutOfBoundsException   Thrown if there is not a result for the specified guess number
     */
    protected Result[] getResultForGuess(int guessNumber) throws IndexOutOfBoundsException
    {
        return results[guessNumber];
    }

    /** isWordle determines if a guess matches the wordle.
     *  @param guess    The guess we want to check against the wordle
     *  @return         True if the guess is our wordle, False otherwise
     */ 
    public boolean isWordle(String guess) {
        return wordle.equals(guess);
    }
}
