package stacs.wordle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.lang.IllegalArgumentException;

import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

public class InputOutputTest {
        
    public static final String TEST_WORDLIST_PATH = "src/test/resources/wordlist-test.txt";
    
    @Test
    public void shouldLoadWordlist() throws FileNotFoundException, IOException
    { 
        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(3, InputOutput.loadWordlist(TEST_WORDLIST_PATH).size());
    }

    @Test
    public void incorrectFilePathThrows()
    {   
        FileNotFoundException fnfe = assertThrows(
            FileNotFoundException.class,
            () -> InputOutput.loadWordlist("src/test/resources/nonexistent.txt"));
    }

    @Test
    public void noUserInput()
    {
        String empty = "";
        InputStream inputStream = new ByteArrayInputStream(empty.getBytes());
        System.setIn(inputStream);
        IllegalStateException ise = assertThrows(
            IllegalStateException.class,
            () -> InputOutput.getUserInput());
    }

    @Test
    public void scannerClosed()
    {
        InputOutput.cleanUp();
        String testInput = "quick";
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);

        IllegalStateException ise = assertThrows(
            IllegalStateException.class,
            () -> InputOutput.getUserInput());
    }

}