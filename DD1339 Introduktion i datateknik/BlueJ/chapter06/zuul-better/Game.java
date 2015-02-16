import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes and Felix De Silva
 * @version 2011.08.08
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom; // the room you are currently in
    private int turnsLeft;  //number of turns you can have before losing
    private Room[] room; // The array that contains alla the rooms
    private Random random; //Generates the randomness for the teleporter
    private Room beamer; //the beamer tech that you are carrying
    private int keyChecker; //the checkar to see if you have the keys
    private ArrayList<Item> keys; //the Key to win
    private int keyRoomIndex; //the index for the room that the key is in

    /**
     * Create the game and initialise its internal map.
     * 
     * Set the ammount of turns you want to finish the game, the turns has to be bigger then 1
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        random = new Random();
    }

    /**
     * Exercise 6.42 Implement a trapdoor somewhere (or some other form of door that you can only cross one way).
     * 
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    { 
        Room outside, theater, pub, lab, office, teleporter, cellar, victoryRoom;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room(("in a dark cellar with two doors\nOne door to the east has a sign saying: Victory Room\n")+
            ("and one to the west has a sign saying: AT YOUR OWN RISK\nthe door behind locked by itself so you are stuck"));
        teleporter = new Room("in a room with random ammout of exits\nchoose one and see where u are");
        victoryRoom = new Room("in the victory room!!!");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);
        pub.setExit("down", cellar);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("down", cellar);

        office.setExit("west", lab);

        //Once you go are in the cellar you cant go back to the pub och the lab
        //because the door locked itself (trapdoor implementation).
        cellar.setExit("west", teleporter);
        cellar.setExit("east", victoryRoom);

        currentRoom = outside;  // start game outside

        //create the array for all the rooms
        room = new Room[]{outside, theater, pub, lab, office, teleporter, victoryRoom};

        //Create the key victory key
        Item key1 = new Item("Victory key 1", 1);
        Item key2 = new Item("Victory key 2", 1);
        Item key3 = new Item("Victory key 3", 1);

        //Creates an array for the key and add the key
        keys = new ArrayList<Item>();
        keys.add(key1);
        keys.add(key2);
        keys.add(key3);

        //Placing the key in a random room
        placeKey();
    }

    /**
     *  Main play routine.  Loops until end of play.
     *  
     *  Difficult Setting:
     *  Easy: 50 Turns
     *  Medium: 35 Turns
     *  Hard: 15 Turns  
     */
    public void play(int turns) 
    {            
        turnsLeft = turns;
        // checks that the value of turns is set to a positiv value.
        Scanner reader = new Scanner(System.in);
        int inputNewTurns;
        while(turnsLeft<0){
            System.out.print("The value of turns is negative, Please use a positiv value! : ");
            inputNewTurns = reader.nextInt();
            setTurns(inputNewTurns);
        }

        printWelcome();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        
        boolean finished = false;
        while (! finished) {
            System.out.println("Turns left: " + getTurns());
            System.out.println("Key : "+ getKeyStatus() + " of 3");
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(getTurns() == 0){
                finished = true;
                System.out.println("You didn't make it in time!!!");
            }
        }
        System.out.println("\nThank you for playing.  Good bye.");
    }

    /**
     * Exercise 6.41 Add some form of time limit to your game. If a certain task is not completed
     * in a specified time, the player loses. A time limit can easily be implemented by counting the
     * number of moves or the number of entered commands. You do not need to use real time.
     * 
     * Counts down the turns by one;
     */
    private void countDownTurns(){
        turnsLeft--;
    }

    /**
     * Returns the value of turns left
     * 
     * @return turns, the number of turns left
     */
    private int getTurns(){
        return turnsLeft;
    }

    /**
     * Set the value of turn
     */
    private void setTurns(int turns){
        turnsLeft = turns;
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("The goal of this game is easy and boring");
        System.out.println("All you have too do is to find the Victory Keys to access the Victory Room! Then you're done!");
        System.out.println("Type 'help' Too get the list of commands. \n"+ "Recomended: Do this if you're a first time player!");
        System.out.println("Type 'go' and a exit direction to move forward.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Exercise 6.45 Add a transporter room. Whenever the player enters this room, he/she is
     * randomly transported into one of the other rooms. Note: Coming up with a good design for
     * this task is not trivial. It might be interesting to discuss design alternatives for this with other
     * students. (We discuss design alternatives for this task at the end of Chapter 9. The adventurous
     * or advanced reader may want to skip ahead and have a look.)
     * 
     * Sets up the teleporter, så its exits becomes random
     */
    private void setTeleport(){
        Room teleporterroom = room[5];
        String[] directions = {"north", "west", "east", "south", "down"};
        for (int i = 0 ; i<directions.length;i++){
            int randRoom = random.nextInt(room.length);
            int randDirection = random.nextInt(directions.length);
            teleporterroom.setExit(directions[randDirection], room[randRoom]);
        }
    }

    /**
     * Places the keys at random rooms on start.
     */ 
    private void placeKey(){
        for (int i = 0; i<keys.size(); i++){
            Random randKey = new Random();
            keyRoomIndex = randKey.nextInt(room.length-1); //-1 for avoiding the key to end upp at the victory room
            Room keyRoom = room[keyRoomIndex];
            keyRoom.addItem(keys.get(i));
        }
    }

    /**
     * Returns the status for the key!
     * 
     * @return keychecker, the number of the key found.
     */
    private int getKeyStatus(){
        return keyChecker;
    }

    /**
     * Increases the keyChecker by one after a key has been found
     */
    private void setKeyStatus(){
        keyChecker++;
    }

    /**
     * Exercise 6.43 Add a beamer to the game. A beamer is a device that can be charged and fired.
     * When you charge the beamer, it memorizes the current room. When you fire the beamer, it transports
     * you immediately back to the room it was charged in. The beamer could either be standard equipment
     * or an item that the player can find. Of course, you need commands to charge and fire the beamer.
     * 
     * Locks the beamer to current position.
     */
    private void chargeBeamer(){
        beamer = currentRoom;
        System.out.println("\nBeamer has been locked!");
    }

    /**
     * Fires the beamer ans teleports you to the locked position
     */
    private void fireBeamer(){
        currentRoom = beamer;
        countDownTurns();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Exercise 6.44 Add locked doors to your game. The player needs to find (or otherwise obtain)
     * a key to open a door.
     * 
     * Inspect the room to see is there is a key.
     */
    private void inspect(){
        if (currentRoom.equals(room[keyRoomIndex])){
            System.out.println("\nYou found the Victory Key");
            System.out.println(currentRoom.getExitString());
            setKeyStatus();
        }else{
            System.out.println("\nThere is nothing useful here...");
            System.out.println(currentRoom.getExitString());
        }
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            System.out.println("");
            printHelp();
        }
        else if (commandWord.equals("go")) {
            System.out.println("");
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("chargeBeamer")){
            chargeBeamer();
        }
        else if (commandWord.equals("fireBeamer")){
            if (beamer!=null){
                fireBeamer();
                System.out.println("\nYou have been teleported by the Beamer");
            }else{
                System.out.println("\nThe beamer is not locked!");
            }
        }else if(commandWord.equals("inspect")){
            inspect();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
    }

    /** 
     * Exercise 6.45 Add a transporter room. Whenever the player enters this room, he/she is
     * randomly transported into one of the other rooms. Note: Coming up with a good design for
     * this task is not trivial. It might be interesting to discuss design alternatives for this with other
     * students. (We discuss design alternatives for this task at the end of Chapter 9. The adventurous
     * or advanced reader may want to skip ahead and have a look.)
     * 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(nextRoom.equals(room[5])){
            System.out.println("You got teleported and dont know where you are");
            setTeleport();
            countDownTurns();
            currentRoom = room[5];
            System.out.println(currentRoom.getLongDescription());
        }else if(nextRoom.equals(room[6])){
            if (getKeyStatus()== 3){
                System.out.println("You enterd the victory room");
                System.out.println("Here you are confronted by a friend of yours who tells you");
                System.out.println("that you are actually are dead...");
                turnsLeft = 1;
            }else{
                System.out.println("The door is locked! Try find the three keys");
            }
        }else{
            currentRoom = nextRoom;
            countDownTurns();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
