package stacs.wordle;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;


import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class WordleAppTest
{
    @Test
    public void shouldLoadWordlist() throws FileNotFoundException, IOException
    {
        WordleApp wordle = new WordleApp();
        wordle.loadWordlist("src/test/resources/wordlist-test.txt");
        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(3, wordle.getWordList().size());
    }

    @Test
    public void incorrectFilePathThrows()
    {   
        WordleApp wordle = new WordleApp();
        FileNotFoundException fnfe = assertThrows(
            FileNotFoundException.class,
            () -> wordle.loadWordlist("src/test/resources/nonexistent.txt"));
    }

}
