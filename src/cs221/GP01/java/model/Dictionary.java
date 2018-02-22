package cs221.GP01.java.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This Interface handles the backend dictionary, as well as searching, loading and retrieving the size of the
 * dictionary
 * @author Samuel Jones - srj12
 * @version 0.1
 */

public class Dictionary implements IDictionary{
    private HashMap<String, String> dictionary = new HashMap();
    private String dictionaryFileName = "dictionary.txt";

    public Dictionary(){
        //Do nothing except make the hashmap instance variable
    }

    /**
     * Loads the dictionary into the local hash map handled by the object that is dictionary using the filename given
     * @param filename Finds the file named as the String parameter
     */
    public void loadDictionary(String filename){
        //Load
        File file = new File("src\\cs221\\GP01\\resource\\dictionary\\" + filename);
        //Finding the absolute path
        //System.out.println(file.getAbsolutePath());

        //Variable to take load time of the dictionary
        long currentTime = System.currentTimeMillis();

        String input;
        try{
            Scanner in = new Scanner(file);
            while(in.hasNext()){
                input = in.nextLine();
                dictionary.put(input, input);
            }
        } catch(FileNotFoundException e){
            //An error in file name
            System.out.println("Dictionary file not found");
        }
        System.out.print("Time taken to load dictionary: ");
        System.out.println(System.currentTimeMillis() - currentTime);
    }

    /**
     * Searches the dictionary utilising the has map for the word and returns true if the word is present false if not
     * @param word the String that is being searched for in the dictionary
     * @return Returns true if the word is present, false if the word is not.
     */
    public boolean searchDictionary(String word){
        //Uses toUpperCase as all words are stored in upper case
       // long currentTime = System.currentTimeMillis();

        boolean hasBeenFound = dictionary.containsKey(word.toUpperCase());

        //System.out.print("Time taken to search dictionary: ");
        //System.out.println(System.currentTimeMillis() - currentTime);
        return hasBeenFound;
    }

    /**
     * Returns the size of the local hash map
     * @return An int equal to the size of the dictionary
     */
    public int getDictionarySize(){
        return dictionary.size();
    }
}
