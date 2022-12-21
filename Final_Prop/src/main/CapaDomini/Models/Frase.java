package main.CapaDomini.Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class Written by : Aashish Bhusal & Mario Wang
 */

/**
 * The Frase class represents a sentence made up of words. It contains
 * methods for storing the words in an ArrayList and for counting the
 * no. of times each word appears in the Frase.
 */
public class Frase {

    // ArrayList to store the words in the Frase
    private ArrayList<String> words;

    // HashMap to store the word count for each word in the Frase
    private HashMap<String, Integer> wordCount;

    /**
     * Default constructor for the class Frase.It Initializes the ArrayList and
     * HashMap objects.
     */
    public Frase()
    {
        words = new ArrayList<>();
        wordCount = new HashMap<String, Integer>();
    }
    /**
     * Constructor of the class Frase that takes an ArrayList of words as a parameter.
     * Initializes the ArrayList with the given words and the HashMap object.
     * @param words the ArrayList of words to be stored in the Frase object
     */
    public Frase(ArrayList<String> words) {
        this.words = words;
        wordCount = new HashMap<String, Integer>();
        updateCount();
    }
    /**
     * Constructor of the class Frase that takes a String as a parameter.
     * Initializes the ArrayList by splitting the String into individual words,
     * and the HashMap object.
     * @param frase the String to be stored as a Frase object.
     */
    public Frase(String frase) {
        if (frase != null) {
            words = new ArrayList<String>(Arrays.asList(frase.split(" ")));
            updateCount();
        }
    }

    /**
     * Setter for the words in the Frase object.
     * @param words the ArrayList of words to be stored in the Frase object
     */

    public void setFrases(ArrayList<String> words) {
        this.words = words;
        updateCount();
    }

    /**
     * Getter for the words in the Frase object.
     * @return the ArrayList of words stored in the Frase object
     */

    public ArrayList<String> getWords() {
        return words;
    }

    /**
     * Method to check if the Frase object has no words.
     * @return true if the Frase object is empty, false otherwise
     */
    public boolean isEmpty() {
        return words.isEmpty();
    }

    /**
     * Method to check if the Frase object is equal to another object.
     * @param o the object to compare with the Frase object
     * @return true if the Frase object and the other object have the same words,
     * false otherwise
     */
    @Override
    public boolean equals(Object o) {
        Frase aux = (Frase) o;
        return String.join(" ", aux.getWords()).equals(String.join(" ", words));
    }

    /**
     * Concatenates the elements of the words list and returns
     * the concatenated string.
     * @return returns the concateneted string.
     */
    @Override
    public String toString() {
        return String.join(" ", words);
    }

    /**
     *
     * @return Returns the wordCount hash map.
     */
    public HashMap<String, Integer> getWordCount() {
        return wordCount;
    }

    /**
     * Iterates over all words in the words list.If the current word equals
     * the given token, return true.
     * If no matching word was found, returns false.
     */
    public boolean hasToken(String token) {
        for (String s: words) {
            if(s.equals(token)) return true;
        }
        return false;
    }

    /**
     * Concatenates the elements of the words list and returns
     * the concatenated string.
     * @return Returns true if the concatenated string contains
     * the given @param sentence, false otherwise.
     */
    public boolean hasSentence(String sentence) {
        String joinFrase = String.join(" ", words);
        return joinFrase.contains(sentence);
    }

    /**
     * Updates the wordCount hash map by counting the number of occurrences
     * of each word in the words list.
     */
    private void updateCount() {
        wordCount = new HashMap<String, Integer>();
        countWords();
    }

    /**
     * Counts the number of occurrences of each word in the list.
     */
    private void countWords() {
        // Iterates over all words in the words list.
        for (String s: words) {
            // If the current word is already in the wordCount hash map, increments its count by 1.
            if(wordCount.containsKey(s)) wordCount.put(s, wordCount.get(s) + 1);
                // If the current word is not in the wordCount hash map, adds it with a count of 1.
            else wordCount.put(s, 1);
        }
    }
}
