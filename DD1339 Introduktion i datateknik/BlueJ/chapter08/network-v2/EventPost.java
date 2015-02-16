
/**
 * Write a description of class EventPost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventPost extends Post
{
    // instance variables - replace the example below with your own
    String eventType;  //What typ of event
    
    /**
     * Constructor for objects of class EventPost
     */
    public EventPost(String author, String eventType)
    {
        super(author);
        this.eventType = eventType;
    }
    
    /**
     * Return the type of this event.
     * 
     * @return The type of event
     */
    public String getEventType()
    {
        return eventType;
    }
    
    /**
     * Prints the autor of the message
     */
    public void printShortSummary(){
        System.out.println("Message Post from " + getAuthor());
    }
    
}
