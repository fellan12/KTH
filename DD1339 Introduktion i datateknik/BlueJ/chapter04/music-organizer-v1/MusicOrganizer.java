import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;
        
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        files = new ArrayList<String>();
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
     * Checkins the index if the is there
     */
    public void checkIndex(int index){
        if (files.size() == 0){
            System.out.println("There is nothing stores in the collection");
        }else{
            if (index > files.size()-1){
                int lastIndex = files.size()-1;
                System.out.println("The index is to big, it should be between 0 and " + lastIndex);
            }
            if (index < files.size()){
                System.out.println("The index it valid");
            }
        }
    }
    
    /**
     * Checkins if the index is valid
     */
    public boolean validIndex(int index){
        boolean valid;
       if(files.size()==0) {
            System.out.println("Nothing it stored");
            valid = false;
        }else{
            if(index > files.size()-1) {
            System.out.println("Index is too large");
            valid = false;
           }else {
            System.out.println("The index it valid");
            valid = true;
            }
        }
        return valid;
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
        if(validIndex(index) == true) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }
    
    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index) == true) {
            System.out.println("The file is removed: " + files.get(index));
            files.remove(index);    
        }
    }
}
