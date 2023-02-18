package stacs.wordle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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
}
