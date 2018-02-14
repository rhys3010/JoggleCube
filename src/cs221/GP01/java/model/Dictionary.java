package cs221.GP01.java.model;

/**
 * The interface that handles the dictionary utilising a hashmap from the Java Standard library
 * @author Samuel Jones - srj12
 * @version 0.1
 */
public interface Dictionary {
    /**
     * Loads the dictionary into the local hash map handled by the object that is dictionary using the filename given
     * @param filename Finds the file named as the String parameter
     */
    void loadDictionary(String filename);

    /**
     * Searches the dictionary utilising the has map for the word and returns true if the word is present false if not
     * @param word the String that is being searched for in the dictionary
     * @return Returns true if the word is present, false if the word is not.
     */
    boolean searchDictionary(String word);

    /**
     * Returns the size of the local hash map
     * @return An int equal to the size of the dictionary
     */
    int getDictionarySize();
}
