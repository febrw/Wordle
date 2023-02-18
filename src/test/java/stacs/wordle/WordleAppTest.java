package stacs.wordle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.lang.IllegalArgumentException;

public class WordleAppTest
{   
    public static final String TEST_WORDLIST_PATH = "src/test/resources/wordlist-test.txt";
    private WordleApp app;

    @BeforeEach
    public void setup() throws FileNotFoundException, IOException
    {
        app = new WordleApp(TEST_WORDLIST_PATH);
    }

    @Test
    public void wordleAppearsInWordList()
    {
        assertTrue(app.getWordList().contains(app.getWordle()));
    }

    @Test
    public void inputTooShort()
    {
        String guess = "gh";
        IllegalArgumentException iae = assertThrows(
            IllegalArgumentException.class,
            () -> app.validateInput(guess));
        assertEquals(iae.getMessage(), "Your guess must be 5 characters long!");
    }

    @Test
    public void inputTooLong()
    {
        String guess = "ghwsgsyudgqw";
        IllegalArgumentException iae = assertThrows(
            IllegalArgumentException.class,
            () -> app.validateInput(guess));
        assertEquals(iae.getMessage(), "Your guess must be 5 characters long!");
    }

    @Test
    public void inputNonAlpha()
    {
        String guess = "a2au9";
        IllegalArgumentException iae = assertThrows(
            IllegalArgumentException.class,
            () -> app.validateInput(guess));
        assertEquals(iae.getMessage(), "Letters only please!");
    }
    
    @Test
    public void guessNotInWordList()
    {
        String guess = "qwqwq";
        IllegalArgumentException iae = assertThrows(
            IllegalArgumentException.class,
            () -> app.validateInput(guess));
        assertEquals(iae.getMessage(), "Word not in dictionary");
    }
 
    /*
    @Test
    public void inputTooShort()
    {
        String guess = "gh";
        assertTrue(!app.isValidInput(guess));
    }

    @Test
    public void inputTooLong()
    {
        String guess = "xxfzwwdd";
        assertTrue(!app.isValidInput(guess));
    }

    @Test
    public void inputNonAlpha()
    {
        String guess = "a2au9";
        assertTrue(!app.isValidInput(guess));
    }

    
    @Test
    public void guessNotInWordList()
    {
        String guess = "ggppp";
        assertTrue(!app.isValidInput(guess));
    }
    */

    @Test // NYI
    public void noMoreThanSixGuesses() {
        String badGuess = "thumb";
        String wordle = "quite";

        app.setWordle(wordle);
        
    }

}
