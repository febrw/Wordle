package stacs.wordle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.io.IOException;

public class WordleApp
{
    private ArrayList<String> wordList;

    public static void main( String[] args )
    {
        System.out.println("Welcome to CS5031 - Wordle");
        WordleApp wordle = new WordleApp();
        try {
            wordle.loadWordlist("src/test/resources/wordlist-test.txt");
        } catch (IOException e) {
            System.out.println("Word List could not be loaded, exiting.");
            return;
        }
        System.out.println("Thanks for playing!");
    }

    // Unimplemented skeleton
    // You may refactor this method
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
    
}
