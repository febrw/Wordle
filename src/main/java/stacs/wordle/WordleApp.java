package stacs.wordle;

import java.util.ArrayList;

//import java.nio.file.Path;
import java.io.IOException;
import java.lang.IndexOutOfBoundsException;
import java.io.FileNotFoundException;


public class WordleApp
{
    private static final String DEFAULT_WORDLIST_PATH = "src/main/resources/wordlist.txt";

    private ArrayList<String> wordList;
    private String wordle;
    private String[] guesses;
    private Result[][] results;
    private InputOutput view;
    
   
    private static final int WORD_LENGTH = 5;
    private static final int MAX_GUESSES = 6;

    public static void main(String[] args) {
        System.out.println("Welcome to CS5031 - Wordle");
        WordleApp wordle;
        try {
            wordle = new WordleApp(DEFAULT_WORDLIST_PATH);
        } catch (IOException e) {
            System.out.println("Word List could not be loaded, exiting.");
            return;
        }

        // wordle.runGame();
        System.out.println("Thanks for playing!");

    }


    public WordleApp(String wordlistPath) throws FileNotFoundException, IOException {
        wordList = InputOutput.loadWordlist(wordlistPath);
        sampleNewWordle();
        guesses = new String[MAX_GUESSES];
        results = new Result[MAX_GUESSES][WORD_LENGTH];
    }     

    
    

    public ArrayList<String> getWordList() {
        return wordList;
    }

    // create random double in the range: [0, size of wordList)
    // cast to int returns an int in the range: [0, size of wordList) == [0, size of wordList -1]
    // use this random int to select a wordle
    public void sampleNewWordle() {
        final int randomIndex = (int) (Math.random() * wordList.size());
        this.wordle = wordList.get(randomIndex);
    }

    public String getWordle() {
        return wordle;
    }

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
            // No match, set to grey
            if (!resultFilled[i]) {
                results[guessNumber][i] = Result.GREY;
            }
        }  
    }

    // for testing only
    protected void setWordle(String wordle)
    {
        this.wordle = wordle;
    }

    public Result[] getResultForGuess(int guessNumber) throws IndexOutOfBoundsException
    {
        return results[guessNumber];
    }

    public boolean isValidInput(String input) {
        String guess = input.trim().toLowerCase();
        return (wordList.contains(guess));
    }

    public boolean isWordle(String guess) {
        return wordle.equals(guess);
    }
    
}
