/**
 * A Person has a name and an id.
 */
public class Person
{
    // the person's full name
    private String name;
    // the person ID
    private String id;
    public Person(String name, String id){
        this.name = name;
        this.id = id;
    }
    
    /**
    * Return the full name of this person.
    */
    public String getName()
    {
        return name;
    }
    
    /**
     * Set a new name for this person.
     */
    public void changeName(String replacementName)
    {
        name = replacementName;
    }

    /**
     * Return the person ID of this person.
     */
    public String getPersonID()
    {
        return id;
    }

    /**
     * Return the login name of this person. The login name is a combination
     * of the first four characters of the person's name and the first three
     * characters of the person's ID number.
     */
    public String getLoginName()
    {
        if(name.length() >= 4 && id.length() >=3){
            return name.substring(0,4) + id.substring(0,3);
        } else{
            if(name.length() < 4 && id.length() >=3){
                return name + id.substring(0,3);
            }
            if(name.length() >= 4 && id.length() < 3){
                return name.substring(0,4) + id;
            } else {
                return name + id;
            }
        }
    }
}
