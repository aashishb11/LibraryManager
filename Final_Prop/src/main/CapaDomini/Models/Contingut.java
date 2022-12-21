package main.CapaDomini.Models;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Written by : Aashish Bhusal & Mario
 */
public class Contingut {

    /**
     * The total number of words in all frases of the Contingut.
     */
    private Integer totalWords;

    /**
     * An ArrayList of Frase objects.It represents the frases in the Contingut object.
     */
    private ArrayList<Frase> frases;

    /**
     * wordMap maps each word in the Contingut object to the no. of time it appears.
     */
    private HashMap<String, Integer> wordMap;

    /**
     * TFMap maps each word in the Contingut object to its term frequency.
     * term frequency is the ratio of number of times the word appears in a Coningut
     * to the total number of words in the Contingut.
     */

    private HashMap<String, Double> TFMap;

    //Constructors for Class Contingut.

    /**
     * Creates a new empty Contingut object.
     */
    public Contingut(){
        frases = new ArrayList<>();
        initMaps(); //comment below in method.
    }

    /**
     * creates a new Contingut object from the given text.
     * @param text is a string containing one or more phrases separated by newlines.
     */

    public Contingut(String text){
        String[] items = text.split("\n");
        frases = new ArrayList<>();
        for (String frase: items) {
            frases.add(new Frase(frase));
        }
        initMaps();
        updateWordMap();
    }

    /**
     * creates a new Contingut object from the given frases.
     * @param frases is an ArrayList of Frase objects.
     */

    public Contingut(ArrayList<Frase> frases) {
        this.frases = frases;
        initMaps();
        updateWordMap();
    }

    //Getters and Setters

    /**
     * @return returns the ArrayList of Frase objects representing
     * the phrases in the Contingut object.
     */

    public ArrayList< Frase> getContinguts() {
        return frases;
    }

    /**
     * sets the Contingut object's frases to the given frases
     */

    public void setContinguts(ArrayList<Frase> frases) {
        this.frases = frases;
        initMaps();
        updateWordMap();
    }

    /**
     * sets the Contingut object's frases to the ones contained in the given s string
     * @param s is a string containing one or more phrases separated by newlines.
     */
    public void setContingut(String s) {
        String[] stringFrase = s.split("\n");
        this.frases = new ArrayList<Frase>(stringFrase.length);
        for (int i = 0; i < stringFrase.length; ++i){
            System.out.println(stringFrase[i]);
            this.frases.add(i, new Frase(stringFrase[i]));
        }
        System.out.println(this.frases);
        initMaps();
        updateWordMap();
    }

    /**
     *
     * @return returns the HashMap that maps each word in the Contingut
     * object to the number of times it appears.
     */
    public HashMap<String, Integer> getWordMap() {
        return wordMap;
    }

    /**
     *
     * @return returns the total number of words in all frases of the Contingut object.
     */
    public Integer getTotalWords() {
        return totalWords;
    }

    /**
     *
     * @return returns the HashMap that maps each word in the Contingut object to
     * its term frequency.
     */
    public HashMap<String, Double> getTFMap() {
        return TFMap;
    }

    //methods and implementations

    /**
     * @param fr is the Frase object to be added to the Contingut objects ArrayList of frases.
     */
    public void addFrase(Frase fr) {
        frases.add(fr);
        updateWordMapFrase(fr);
    }

    /**
     *
     * @param token is the string to be checked
     * @return returns true if the Contingut object has at least one frase that contains
     * the given token string, or false otherwise.
     */
    public boolean hasToken(String token) {
        for (Frase f: frases) {
            if (f.hasToken(token)) return true;
        }
        return false;
    }

    /**
     *
     * @param s is the string to be checked
     * @return returns true if the Contingut object's frases,
     * when concatenated with newlines, are equal to the given s string, or false otherwise.
     */
    public Boolean equalsString(String s) {
        String[] frases = new String[this.frases.size()];
        for (int i = 0; i < this.frases.size(); ++i) {
            frases[i] = this.frases.get(i).toString();
        }
        return s.equals(String.join("\n", frases));
    }

    /**
     *
     * @param sentence
     * @return returns true if the Contingut object has at least one frase that is equal
     * to the given sentence string, or false otherwise.
     */
    public boolean hasSentence(String sentence) {
        for (Frase f: frases) {
            if (f.hasSentence(sentence)) return true;
        }
        return false;
    }

    /**
     * initializes the totalWords, TFMap, and wordMap fields to their default values.
     */
    private void initMaps() {
        totalWords = 0;
        TFMap = new HashMap<String, Double>();
        wordMap = new HashMap<String, Integer>();
    }

    /**
     * updates the wordMap and totalWords fields by iterating through all phrases in the Contingut object
     * and adding the words and their counts to the wordMap
     */
    private void updateWordMap(){
        for (Frase f: frases) {
            updateWordMapFrase(f);
        }
        updateTFMap();
    }

    /**
     * updates the wordMap and totalWords fields by adding the words
     * and their counts from the given Frase object @param f to the wordMap
     */
    private void updateWordMapFrase(Frase f) {
        for (HashMap.Entry<String, Integer> entry: f.getWordCount().entrySet()){
            if (wordMap.containsKey(entry.getKey())) {
                wordMap.put(entry.getKey(), wordMap.get(entry.getKey()) + entry.getValue());
            }
            else {
                wordMap.put(entry.getKey(), entry.getValue());
            }
            totalWords += entry.getValue();
        }
        updateTFMap();
    }

    /**
     * updates the TFMap field by iterating through all entries in the
     * wordMap and calculating the term frequency for each word.
     */
    private void updateTFMap() {
        for(HashMap.Entry<String, Integer> entry: wordMap.entrySet()) {
            TFMap.put(entry.getKey(), (double) entry.getValue()/totalWords);
        }
    }

    /**
     *
     * @return returns a string representation of the Contingut object by
     * concatenating the string representations
     * of all frases in the frases field, separated by newlines.
     */
    @Override
    public String toString(){
        String[] frases = new String[this.frases.size()];
        for (int i = 0; i < this.frases.size(); ++i) {
            frases[i] = this.frases.get(i).toString();
        }
        return String.join("\n", frases);
    }

    /**
     * Returns true if the given word is present in the content, false otherwise.
     *
     * @param word the word to check for
     * @return true if the given word is present in the content, false otherwise
     */
    public Boolean hasWord(String word) {
        return wordMap.containsKey(word);
    }
}