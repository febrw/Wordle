package stacs.wordle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.io.IOException;
//import stacs.wordle.Result;

public class WordleApp
{
    private ArrayList<String> wordList;
    private String wordle;

    private static final String DEFAULT_WORDLIST_PATH = "src/main/resources/wordlist.txt";

    public WordleApp() throws FileNotFoundException, IOException {
        loadWordlist(DEFAULT_WORDLIST_PATH);
        randChooseNewWordle();
    }     

    public static void main( String[] args )
    {
        System.out.println("Welcome to CS5031 - Wordle");
        WordleApp wordle;
        try {
            wordle = new WordleApp();
        } catch (IOException e) {
            System.out.println("Word List could not be loaded, exiting.");
            return;
        }
        System.out.println("Thanks for playing!");
    }

    public void loadWordlist(String wordlistPath) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(wordlistPath));
        ArrayList<String> wordList = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            wordList.add(line);
        }
        reader.close();
        this.wordList = wordList;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    // create random double in the range: [0, size of wordList)
    // cast to int returns an int in the range: [0, size of wordList) == [0, size of wordList -1]
    // use this random int to select a wordle
    public void randChooseNewWordle() {
        final int randomIndex = (int) (Math.random() * wordList.size());
        this.wordle = wordList.get(randomIndex);
    }

    public String getWordle() {
        return wordle;
    }

    public void processGuess(String guess, int guessNumber)
    {

    }

    public void setWordle(String wordle)
    {

    }

    public Result[] getResultForGuess(int guessNumber)
    {
        return new Result[1];
    }
    
}
