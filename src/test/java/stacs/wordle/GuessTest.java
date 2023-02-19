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
    public void guessMatchesWordle() {
        String guess = "whine";
        app.setWordle("whine");
        assertTrue(app.isWordle(guess));
    }

    @Test
    public void guessMismatchesWordle() {
        String guess = "quick";
        app.setWordle("whine");
        assertTrue(!app.isWordle(guess));
    }

    @Test
    public void getResultforGuessBad()
    {
        IndexOutOfBoundsException oob = assertThrows(
            IndexOutOfBoundsException.class,
            () -> app.getResultForGuess(7));
    }

    @Test
    public void allGreys()
    {
        Result[] greys = {Result.RED, Result.RED, Result.RED, Result.RED , Result.RED}; 
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
        Result[] expected = {Result.GREEN, Result.RED, Result.RED, Result.RED, Result.GREEN}; 
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
        Result[] expected = {Result.YELLOW, Result.GREEN, Result.RED, Result.RED, Result.RED}; 
        app.setWordle( "range");
        String guess = "naval";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void mixedTest2()
    {
        Result[] expected = {Result.YELLOW, Result.GREEN, Result.RED, Result.YELLOW, Result.RED}; 
        app.setWordle( "range");
        String guess = "navel";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void mixedTest3()
    {
        Result[] expected = {Result.YELLOW, Result.GREEN, Result.RED, Result.RED, Result.RED}; 
        app.setWordle( "orbit");
        String guess = "track";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    // StudRes tests
    @Test
    public void studResTest1()
    {
        Result[] expected = {Result.RED, Result.RED, Result.YELLOW, Result.RED, Result.RED}; 
        app.setWordle( "ounce");
        String guess = "dream";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest2()
    {
        Result[] expected = {Result.RED, Result.RED, Result.RED, Result.RED, Result.YELLOW}; 
        app.setWordle( "ounce");
        String guess = "radio";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest3()
    {
        Result[] expected = {Result.RED, Result.RED, Result.RED, Result.RED, Result.GREEN}; 
        app.setWordle( "ounce");
        String guess = "table";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest4()
    {
        Result[] expected = {Result.RED, Result.YELLOW, Result.RED, Result.GREEN, Result.RED}; 
        app.setWordle( "ounce");
        String guess = "snack";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

    @Test
    public void studResTest5()
    {
        Result[] expected = {Result.YELLOW, Result.YELLOW, Result.RED, Result.RED, Result.GREEN}; 
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

    @Test
    public void kioskTest1()
    {
        Result[] expected = {Result.RED, Result.RED, Result.RED, Result.RED, Result.RED};
        app.setWordle( "kiosk");
        String guess = "where";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    @Test
    public void kioskTest2()
    {
        Result[] expected = {Result.RED, Result.YELLOW, Result.RED, Result.RED, Result.RED};
        app.setWordle( "kiosk");
        String guess = "polyp";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    @Test
    public void kioskTest3()
    {
        Result[] expected = {Result.YELLOW, Result.RED, Result.RED, Result.RED, Result.RED};
        app.setWordle( "kiosk");
        String guess = "stand";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    @Test
    public void kioskTest4()
    {
        Result[] expected = {Result.YELLOW, Result.YELLOW, Result.RED, Result.YELLOW, Result.RED};
        app.setWordle( "kiosk");
        String guess = "solid";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    @Test
    public void kioskTest5()
    {
        Result[] expected = {Result.RED, Result.GREEN, Result.RED, Result.RED, Result.RED};
        app.setWordle( "kiosk");
        String guess = "miner";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }


    @Test
    public void kioskTest6()
    {
        Result[] expected = {Result.RED, Result.GREEN, Result.RED, Result.YELLOW, Result.YELLOW};
        app.setWordle( "kiosk");
        String guess = "biros";
        app.processGuess(guess, 0);
        assertArrayEquals(app.getResultForGuess(0), expected);
    }

}
