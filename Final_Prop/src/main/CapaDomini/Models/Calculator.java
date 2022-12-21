package main.CapaDomini.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.lang.Math;

/**
 * Class Written by : Aashish Bhusal & Mario
 */

/**
 * This class represents a calculator that can compute the inverse document frequency (IDF) and
 * cosine similarity between documents.
 */
public class Calculator {
    // The total number of words in the documents
    private Integer totalWords;

    // A map that stores the IDF value for each word
    private HashMap<String, Double> IDF;

    /**
     * Constructor for the Calculator class.
     */
    public Calculator() {
        // Initialize totalWords and IDF
        totalWords = 0;
        IDF = new HashMap<String, Double>();
    }

    /**
     * Calculates the IDF value for each word in the given list of documents.
     * @param documentsList a list of documents
     */
    public void updateIDF(ArrayList<Document> documentsList) {
        // Create a temporary map to store the count of each word
        HashMap<String, Integer> temptCountMap = new HashMap<String, Integer>();
        // Iterate through each document
        for (Document d: documentsList) {
            // Get the map of words and their counts in the current document
            HashMap<String, Integer> temptMap = d.getContingut().getWordMap();
            // Get the set of all words in the current document
            Set<String> temptStringList = temptMap.keySet();
            // Iterate through each word in the current document
            for (String key: temptStringList) {
                // If the word has already been seen in another document, increment its count
                if (temptCountMap.containsKey(key)) temptCountMap.put(key, temptCountMap.get(key) + 1);
                    // If the word has not been seen before, add it to the map with a count of 1
                else temptCountMap.put(key, 1);
            }
        }
        // Calculate the total number of documents
        int totalDocuments = documentsList.size();
        // Iterate through the map of word counts and calculate the IDF value for each word
        for (HashMap.Entry<String, Integer> entry: temptCountMap.entrySet()) {
            IDF.put(entry.getKey(), Math.log(1 + (double) totalDocuments/entry.getValue()));
        }
    }
    /**
     * Calculates the TF-IDF score for each word in the given document.
     * @param d the document
     * @return a map of words and their TF-IDF scores
     */
    public HashMap<String, Double> computeWordScore(Document d){
        // Create a map to store the TF-IDF scores
        HashMap<String, Double> result = new HashMap<String, Double>();
        // Get the map of words and their term frequency (TF) scores for the given document
        HashMap<String, Double> TFMap = d.getContingut().getTFMap();
        // Iterate through the map of word TF scores and calculate the TF-IDF score for each word
        for (HashMap.Entry<String, Double> entry: TFMap.entrySet()) {
            result.put(entry.getKey(), entry.getValue()*IDF.get(entry.getKey()));
        }
        return result;
    }
    /**
     * Calculates the cosine similarity between the given document and another document.
     * @param TF_IDF a map of words and their TF-IDF scores for the first document
     * @param d2 the second document
     * @return the cosine similarity between the two documents
     */
    public Double computeCosineSimilarity(HashMap<String, Double> TF_IDF, Document d2) {
        // Calculate the TF-IDF scores for the second document
        HashMap<String, Double> TF_IDF_2 = computeWordScore(d2);
        System.out.println(TF_IDF);
        // Calculate the dot product of the two documents
        Double dotProductResult = dotProduct(TF_IDF, TF_IDF_2);
        // Calculate the absolute value of the first document's TF-IDF scores
        Double d1AbsoluteValue = absoluteValue(TF_IDF);
        // Calculate the absolute value of the second document's TF-IDF scores
        Double d2AbsoluteValue = absoluteValue(TF_IDF_2);
        // If either absolute value is 0, return 0
        if (d1AbsoluteValue * d2AbsoluteValue == 0) {
            return 0.0;
        }
        // Return the cosine similarity between the two documents
        return dotProductResult/(d1AbsoluteValue * d2AbsoluteValue);
    }
    /**
     * Calculates the dot product of the given maps of words and their scores.
     * @param x1 the first map
     * @param x2 the second map
     * @return the dot product of the two maps
     */
    public Double dotProduct(HashMap<String, Double> x1, HashMap<String, Double> x2) {
        Double result = 0.0;
        // Iterate through the first map
        for (HashMap.Entry<String, Double> entry: x1.entrySet()) {
            // If the second map contains the current word, add the product of the two scores to the result
            if (x2.containsKey(entry.getKey())) {
                result += entry.getValue() * x2.get(entry.getKey());
            }
        }
        return result;
    }
    /**
     * Calculates the absolute value of the given map of words and their scores.
     * @param x the map
     * @return the absolute value of the map
     */
    public Double absoluteValue(HashMap<String, Double> x) {
        // Get the values (scores) from the map
        Collection<Double> scores = x.values();
        Double result = 0.0;
        // Iterate through the values and add the square of each value to the result
        for (Double d: scores) {
            result += d*d;
        }
        // Return the square root of the result
        return Math.sqrt(result);
    }
    /**
     * Returns a map of the k highest-scoring documents and their similarity scores.
     * @param similarityScores a map of documents and their similarity scores
     * @param k the number of documents to return
     * @return a map of the k highest-scoring documents and their similarity scores
     */
    public HashMap<Document, Double> getKHigherScores(HashMap<Document, Double> similarityScores, Integer k) {
        // Create a list of the similarity scores
        List<Double> scores = new ArrayList<>(similarityScores.values());
        // Sort the list in descending order
        Collections.sort(scores, Collections.reverseOrder());
        System.out.println(scores);
        int count = 0;
        // Create a map to store the k highest-scoring documents
        HashMap<Document, Double> sortedMap = new HashMap<Document, Double>();
        // Iterate through the sorted list of scores
        for (double d: scores) { //FIX SAME SCORES
            // Iterate through the map of documents and similarity scores
            for(HashMap.Entry<Document, Double> entry: similarityScores.entrySet()){
                // If the current score in the map matches the current score in the list, add the document and score to the result map
                if (entry.getValue().equals(d)) {
                    sortedMap.put(entry.getKey(), entry.getValue());
                    // Increment the count of documents added to the result map
                    ++count;
                    break;
                }
                System.out.println(count);
            }
            if (k == count) break;
        }
        // If the count of documents added to the result map is equal to k, return the result map
        return sortedMap;
    }
    /**
     * Returns a map of the k documents with the highest scores for the given list of words.
     * @param wordList the list of words
     * @param documentsList the list of documents
     * @param k the number of documents to return
     * @return a map of the k documents with the highest scores for the given list of words
     */
    public HashMap<Document, Double> getPWordListScores(
            ArrayList<String> wordList,
            ArrayList<Document> documentsList,
            Integer k) {
        // Create a map to store the documents and their scores
        HashMap<Document, Double> result = new HashMap<Document, Double>();
        // Iterate through the list of documents
        for (Document d: documentsList){
            // Initialize the relevance score for the current document to 0
            double relevance = 0.0;
            // Get the map of words and their TF scores for the current document
            HashMap<String, Double> TFMap = d.getContingut().getTFMap();
            // Iterate through the list of words
            for (String word: wordList) {
                // If the word appears in both the IDF map and the TF map for the current document,
                // add the product of its IDF and TF scores to the relevance score
                if (IDF.containsKey(word) && TFMap.containsKey(word)) relevance += IDF.get(word) * TFMap.get(word);
            }
            // Add the current document and its relevance score to the result map
            result.put(d, relevance);
        }
        // Return the k highest-scoring documents from the result map
        return getKHigherScores(result, k);
    }

    /**
     * Returns the total number of words in the documents.
     * @return the total number of words in the documents
     */
    public Integer getTotalWords() {
        return totalWords;
    }

    /**
     * Returns the map of words and their IDF scores.
     * @return the map of words and their IDF scores
     */
    public HashMap<String, Double> getIDF() {
        return IDF;
    }

    /**
     * Sets the map of words and their IDF scores.
     * @param IDF the new map of words and their IDF scores
     */
    public void setIDF(HashMap<String, Double> IDF) {
        this.IDF = IDF;
    }

    /**
     * Sets the total number of words in the documents.
     * @param totalWords the new total number of words in the documents
     */
    public void setTotalWords(Integer totalWords) {
        this.totalWords = totalWords;
    }
}
