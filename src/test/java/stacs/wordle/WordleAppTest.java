package stacs.wordle;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;


import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class WordleAppTest
{
    private WordleApp app;

    @BeforeEach
    public void setup() throws FileNotFoundException, IOException
    {
        app = new WordleApp();
    }

    @Test
    public void shouldLoadWordlist() throws FileNotFoundException, IOException
    {
        app.loadWordlist("src/test/resources/wordlist-test.txt");
        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(3, app.getWordList().size());
    }

    @Test
    public void incorrectFilePathThrows()
    {   
        FileNotFoundException fnfe = assertThrows(
            FileNotFoundException.class,
            () -> app.loadWordlist("src/test/resources/nonexistent.txt"));
    }

    
    @Test
    public void wordleAppearsInWordList()
    {
        assertTrue(app.getWordList().contains(app.getWordle()));
    }

}
