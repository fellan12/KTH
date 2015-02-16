import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  mik
 * @version 0.1
 */
public class SalesItemTest
{
    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
    {
    }

    /**
     * Test that a comment can be added, and that the comment count is correct afterwards.
     * 
     * positivt test
     */
    @Test
    public void testAddCommentAndCount()
    {
        SalesItem salesIte1 = new SalesItem("Java for complete Idiots", 21998);
        assertEquals(true, salesIte1.addComment("James Duckling", "This book is great. I learned all my Java from it.", 4));
        assertEquals(1, salesIte1.getNumberOfComments());
    }

    /**
     * Test that a comment using an illegal rating value is rejected.
     * 
     * negativt test
     */
    @Test
    public void testIllegalRating()
    {
        SalesItem salesIte1 = new SalesItem("Java For Complete Idiots, Vol 2", 19900);
        assertEquals(false, salesIte1.addComment("Joshua Black", "Not worth the money. The font is too small.", -5));
    }

    /**
     * Test that a sales item is correctly initialised (name and price).
     * 
     * positivt test
     */
    @Test
    public void testInit()
    {
        SalesItem salesIte1 = new SalesItem("test name", 1000);
        assertEquals("test name", salesIte1.getName());
        assertEquals(1000, salesIte1.getPrice());
    }

    /**
     * Testar att om man lägger två kommentarer och sedan räknar den så räknar det rätt
     * 
     * positivt test
     */
    @Test
    public void testTwoComments()
    {
        SalesItem salesIte1 = new SalesItem("hej", 100);
        assertEquals(true, salesIte1.addComment("felix", "bra!", 5));
        assertEquals(true, salesIte1.addComment("adam", "skit nice", 3));
        assertEquals(2, salesIte1.getNumberOfComments());
    }

    /**
     * Exercise 7.15 Create a test to check that addComment returns false when a comment 
     * from the same author already exists.
     * 
     * Testar att om man lägger två kommentarer från samma skribent så ska det ge false
     * 
     * negativ test
     */
    @Test
    public void testAddComment()
    {
        SalesItem salesIte1 = new SalesItem("mjök", 100);
        assertEquals(true, salesIte1.addComment("felix", "fett gött", 5));
        assertEquals(false, salesIte1.addComment("felix", "den här var gammal...", 1));
    }

    /** 
     * Exercise 7.16 Create a test that performs negative testing on the boundaries of the rating
     * range. That is, test the values 0 and 6 as a rating (the values just outside the legal range). We
     * expect these to return false, so assert false in the result dialog. You will notice that one of
     * these actually (incorrectly) returns true. This is the bug we uncovered earlier in manual testing.
     * Make sure that you assert false anyway. The assertion states the expected result, not
     * the actual result.

     * Testar att om man lägger två kommentarer med rating utanför gränserna, så ska det ge false 
     * 
     * negativ test
     */
    @Test
    public void testRatingBoundry()
    {
        SalesItem salesIte1 = new SalesItem("mjölk", 100);
        assertEquals(false, salesIte1.addComment("felix", "det smakar röv", 0));
        assertEquals(false, salesIte1.addComment("adam", "guds gåva", 6));
    }

    /**
     *Exercise 7.19 Create tests for SalesItem that test whether the findMostHelpfulComment 
     *method works as expected. Note that this method returns a Comment object. During
     *your testing, you can use the Get button in the method result dialog to get the result object
     *onto the object bench, which then allows you to make further method calls and add assertions
     *for this object. This allows you to identify the comment object returned (e.g., by checking its
     *author). You can also assert that the result is null or not null, depending on what you expect. 
     *
     *Testar att om man lägger två kommentarer och votar upp dem och sedan callar på FindMostHelpfulComment
     *så ska det objektet som pekar på den med högst voting
     *
     *positiv test
     */
    @Test
    public void testFindMostHelpfulComment()
    {
        SalesItem salesIte1 = new SalesItem("Mjölk", 100);
        salesIte1.addComment("Felix", "fett bra", 5);
        salesIte1.addComment("Adam", "funkar", 3);
        salesIte1.upvoteComment(0);
        salesIte1.upvoteComment(0);
        salesIte1.upvoteComment(1);
        Comment comment1 = salesIte1.findMostHelpfulComment();
        assertEquals("Felix", comment1.getAuthor());
        assertEquals(2, comment1.getVoteCount());
    }
}


/**
 * Exercise 7.17 Run all tests again. Explore how the Test Result dialog displays the failed
 * test. Select the failed test in the list. What options do you have available to explore the details
 * of the failed test?
 * 
 * Den ger mig en log med info vad som hände och vart felet hände 
 * samt ger mig möjligheter att trycka på "Show Source" för att direkt tas till problemet.
 */


