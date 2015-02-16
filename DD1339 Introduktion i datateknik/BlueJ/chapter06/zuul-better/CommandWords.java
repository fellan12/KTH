/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "chargeBeamer", "fireBeamer", "inspect"
    };
    private static final String[] commandDescription = {
            " - Too go to your next location using a exit direction.",
            " - Too quit the game.",
            " - Too get the list of commands",
            " - Lock the beamer at your current position.",
            " - Fire the beamer too send you to the locked position",
            " - Inspect the room too see if there is any useful stuff",
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public String getCommandList() {
        String commandList = "";
        for(int i = 0; i < validCommands.length; i++) {
            commandList = commandList + validCommands[i] + commandDescription[i] + " \n";
        }
        return commandList;
    }
}
