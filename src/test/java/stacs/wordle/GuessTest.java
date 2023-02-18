package stacs.wordle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GuessTest
{
    public static final String TEST_WORDLIST_PATH = "src/test/resources/wordlist-test.txt";
    
    private WordleApp app;

    @BeforeEach
    public void setup() throws FileNotFoundException, IOException
    {
        app = new WordleApp(TEST_WORDLIST_PATH);
    }

    @Test
    public void getResultforGuessBadTest()
    {
        IndexOutOfBoundsException oob = assertThrows(
            IndexOutOfBoundsException.class,
            () -> app.getResultForGuess(7));
    }

    @Test
    public void allGreys()
    {
        Result[] greys = {Result.GREY, Result.GREY, Result.GREY, Result.GREY , Result.GREY}; 
        app.setWordle("scale");
        String guess = "grump";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), greys);
    }

    @Test
    public void allYellows()
    {
        Result[] yellows = {Result.YELLOW, Result.YELLOW, Result.YELLOW, Result.YELLOW, Result.YELLOW}; 
        app.setWordle( "space");
        String guess = "paces";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), yellows);
    }

    @Test
    public void allGreens()
    {
        Result[] greens = {Result.GREEN, Result.GREEN, Result.GREEN, Result.GREEN, Result.GREEN};
        app.setWordle( "lunch");
        String guess = "lunch";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), greens);
    }


    // we need to ensure that both the first and last 'e' in our guess attain result GREEN
    // and that the second 'e' in our guess does not rematch to either of the e's in 'eagle'
    @Test
    public void duplicateLetterNotRematched()
    {
        Result[] expected = {Result.GREEN, Result.GREY, Result.GREY, Result.GREY, Result.GREEN}; 
        app.setWordle( "eagle");
        String guess = "eerie";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    // mix of greens and yellows only
    @Test
    public void anagramTest1()
    {
        Result[] expected = {Result.GREEN, Result.GREEN, Result.GREEN, Result.YELLOW, Result.YELLOW}; 
        app.setWordle( "angle");
        String guess = "angel";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void anagramTest2()
    {
        Result[] expected = {Result.YELLOW, Result.YELLOW, Result.YELLOW, Result.GREEN, Result.GREEN}; 
        app.setWordle( "elbow");
        String guess = "below";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void anagramTest3()
    {
        Result[] expected = {Result.YELLOW, Result.YELLOW, Result.GREEN, Result.YELLOW, Result.YELLOW}; 
        app.setWordle( "scalp");
        String guess = "claps";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    // Mixes of greens, yellows, and greys
    @Test
    public void mixedTest1()
    {
        Result[] expected = {Result.YELLOW, Result.GREEN, Result.GREY, Result.GREY, Result.GREY}; 
        app.setWordle( "range");
        String guess = "naval";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void mixedTest2()
    {
        Result[] expected = {Result.YELLOW, Result.GREEN, Result.GREY, Result.YELLOW, Result.GREY}; 
        app.setWordle( "range");
        String guess = "navel";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void mixedTest3()
    {
        Result[] expected = {Result.YELLOW, Result.GREEN, Result.GREY, Result.GREY, Result.GREY}; 
        app.setWordle( "orbit");
        String guess = "track";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    // StudRes tests
    @Test
    public void studResTest1()
    {
        Result[] expected = {Result.GREY, Result.GREY, Result.YELLOW, Result.GREY, Result.GREY}; 
        app.setWordle( "ounce");
        String guess = "dream";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest2()
    {
        Result[] expected = {Result.GREY, Result.GREY, Result.GREY, Result.GREY, Result.YELLOW}; 
        app.setWordle( "ounce");
        String guess = "radio";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest3()
    {
        Result[] expected = {Result.GREY, Result.GREY, Result.GREY, Result.GREY, Result.GREEN}; 
        app.setWordle( "ounce");
        String guess = "table";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest4()
    {
        Result[] expected = {Result.GREY, Result.YELLOW, Result.GREY, Result.GREEN, Result.GREY}; 
        app.setWordle( "ounce");
        String guess = "snack";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest5()
    {
        Result[] expected = {Result.YELLOW, Result.YELLOW, Result.GREY, Result.GREY, Result.GREEN}; 
        app.setWordle( "ounce");
        String guess = "noise";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    @Test
    public void studResTest6()
    {
        Result[] expected = {Result.GREEN, Result.GREEN, Result.GREEN, Result.GREEN, Result.GREEN}; 
        app.setWordle( "ounce");
        String guess = "ounce";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    } 

}