import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * This version can play the files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
    // A player for the music files.
    private MusicPlayer player;
    //
    private int position;
        
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<String>();
        player = new MusicPlayer();
    }
    
    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addFile(String filename)
    {
        files.add(filename);
    }
    
    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }
    
    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(index >= 0 && index < files.size()) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }
    
    /**
     * List all file from the collection.
     */
    public void listAllFile()
    {
        for (String tracks : files){
            position ++;
            System.out.println(position + ":" + tracks);
        }
        position= 0;
    }
    
    /**
     * Searching for files that contains the given string.
     */
    public void listMatching(String searchString)
    {
       boolean found = true;
        for(String filename : files) {
            if(filename.contains(searchString)) {
            // A match.
            System.out.println(filename);
            }else{
                found = false;
            }
        }
        if (found == false){
                System.out.println("Couldnt find anymore");
            }
    }
    
        /**
         * Play samples on an artis give by the String.
         */
        public void sampleArtist(String artist)
        {
            for(String filename : files) {
                System.out.println(filename);
                if(filename.contains(artist)) {
                // A match.
                    player.playSample(filename);
                }
                position++;
            }
            position=0;
        }
    
    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(index >= 0 && index < files.size()) {
            files.remove(index);
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        String filename = files.get(index);
        player.startPlaying(filename);
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }
}
