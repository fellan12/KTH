import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommentTest
{
    /**
     * Default constructor for test class CommentTest
     */
    public CommentTest()
    {
    }

    /**
     * Exercise 7.18a Create a test class that has Comment as its reference class. Create a test
     * that checks whether the author and rating details are stored correctly after creation. 
     * 
     * Testar att author och rating hamnar på rätt ställe efter skapandet av en kommentar.
     * 
     * positiv test
     */
    @Test
    public void testAuthorRatingCreation()
    {
        Comment comment3 = new Comment("Felix", "tjena", 2);
        assertEquals("Felix", comment3.getAuthor());
        assertEquals(2, comment3.getRating());
    }

    /**
     * 7.18b 
     * Record separate tests that check whether the upvote method works as expected.
     * 
     * Testar att när man Upvotar att counter går upp med 1 per antal uppvote
     * 
     * positiv test
     */
    @Test
    public void testUpvote()
    {
        Comment comment1 = new Comment("Felix", "Tjena", 2);
        comment1.upvote();
        assertEquals(1, comment1.getVoteCount());
        comment1.upvote();
        assertEquals(2, comment1.getVoteCount());
        comment1.upvote();
        assertEquals(3, comment1.getVoteCount());
    }

    /**
     * 7.18c
     * Record separate tests that check whether the downvote method works as expected.
     * 
     * Testar att när man Downvotar att counter går ner med 1 per antal uppvote
     * 
     * positiv test
     */
    @Test
    public void testDownvote()
    {
        Comment comment1 = new Comment("Felix", "Tjena", 2);
        comment1.downvote();
        assertEquals(-1, comment1.getVoteCount());
        comment1.downvote();
        assertEquals(-2, comment1.getVoteCount());
        comment1.downvote();
        assertEquals(-3, comment1.getVoteCount());
    }
}