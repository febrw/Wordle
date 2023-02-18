package stacs.wordle;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


public class InputOutput {
    
    private static Scanner scanner = new Scanner(System.in);

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

    public static String getUserInput() throws IllegalStateException, NoSuchElementException
    {
        return scanner.next();
    }
 

    protected static void cleanUp()
    {
        scanner.close();
    }

}
