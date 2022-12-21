package main.CapaDomini.Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.CapaDades.*;
import main.CapaDomini.Models.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * Implementation of Class CtrlDomini that communicates with CtrlPresentacio
 * @Autor Aashish Bhusal & Mario Wang
 */

/**
 * CtrlDomini is the main controller class in the domain layer of the application.
 * It communicates with the presentation Layer and handles the logic for managing
 * the library of documents and authors, as well as calculating document similarity
 * scores.
 */

public class CtrlDomini {

    /**
     * instance: a static instance of the CtrlDomini class
     */
    private static CtrlDomini instance;
    /**
     * dp: an instance of the CtrlPersistencia class
     */
    private CtrlPersistencia dp;
    /**
     * TXT: a string constant representing the ".txt" file extension
     */
    public static final String TXT = ".txt";
    /**
     * lb: an instance of the Llibreria class
     */
    private Llibreria lb;
    /**
     * c: an instance of the Calculator class
     */
    private Calculator c;
    /**
     * path: a string representing a file path
     */
    private String path;
    /**
     * booleanParser: an instance of the BooleanParser class
     */
    private BooleanParser booleanParser;

    /**
     * Constructor for the CtrlDomini class. Initializes lb, c, and booleanParser.
     */
    public CtrlDomini(){
        lb = new Llibreria();
        c = new Calculator();
        booleanParser = new BooleanParser();
    }

    /**
     * Constructor for the CtrlDomini class. Initializes lb and c.
     * @param documents a map of documents
     * @param authors a map of authors
     */
    public CtrlDomini(HashMap<String, Document> documents, HashMap<String, Autor> authors) {
        lb = new Llibreria();
        c = new Calculator();
    }

    /**
     * importDocumentPlainText is a method that allows importing a plain text document into the library.
     * It reads the document from the specified path, creates a new Document object with the first two
     * lines as the author and title,respectively, and adds all the following lines as the content of the document.
     * If the library already contains a document with the same author and title, an IOException is thrown.
     * Finally, the method updates the nverse document frequency (IDF) values of the library's documents.
     * @param path the path of the plain text document to import
     * @throws IOException if the library already contains a document with the same author and title as the one being
     * imported.
     */
    public void importDocumentPlainText(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        Frase autor = new Frase(br.readLine());
        Frase titol = new Frase(br.readLine());
        Document to_add = new Document(autor, titol);
        if (lb.hasDocument(to_add)) {throw new IOException("Duplicated document");}
        Contingut c = new Contingut();
        String line = br.readLine();
        while (line != null) {
            c.addFrase(new Frase(line));
            line = br.readLine();
        }
        to_add.setContingut(c);
        lb.addDocument(to_add);
        this.c.updateIDF(lb.getDocuments());
    }

    /**
     * This function imports a XML document from a given file path and adds it to the library
     * @param path the path of XML to import
     * @throws IOException if the library already contains a document with the same author and title as the one being
     * imported.
     */
    public void importDocumentXML(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        Frase autor = new Frase(br.readLine());
        Frase titol = new Frase(br.readLine());
        Document to_add = new Document(autor, titol);
        if (lb.hasDocument(to_add)) {throw new IOException("Duplicated document");}
        Contingut c = new Contingut();
        String line = br.readLine();
        while (line != null) {
            c.addFrase(new Frase(line));
            line = br.readLine();
        }
        to_add.setContingut(c);
        lb.addDocument(to_add);
        this.c.updateIDF(lb.getDocuments());
    }
    /**
     * This function imports a WORD document from a given file path and adds it to the library
     * @param path the path of the word to import
     * @throws IOException if the library already contains a document with the same author and title as the one being
     * imported.
     */
    public void importDocumentWord(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        Frase autor = new Frase(br.readLine());
        Frase titol = new Frase(br.readLine());
        Document to_add = new Document(autor, titol);
        if (lb.hasDocument(to_add)) {throw new IOException("Duplicated document");}
        Contingut c = new Contingut();
        String line = br.readLine();
        while (line != null) {
            c.addFrase(new Frase(line));
            line = br.readLine();
        }
        to_add.setContingut(c);
        lb.addDocument(to_add);
        this.c.updateIDF(lb.getDocuments());
    }
    /**
     * This function imports a PDF document from a given file path and adds it to the library
     * @param path the path of the PDF to import
     * @throws IOException if the library already contains a document with the same author and title as the one being
     * imported.
     */
    public void importDocumentPDF(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        Frase autor = new Frase(br.readLine());
        Frase titol = new Frase(br.readLine());
        Document to_add = new Document(autor, titol);
        if (lb.hasDocument(to_add)) {throw new IOException("Duplicated document");}
        Contingut c = new Contingut();
        String line = br.readLine();
        while (line != null) {
            c.addFrase(new Frase(line));
            line = br.readLine();
        }
        to_add.setContingut(c);
        lb.addDocument(to_add);
        this.c.updateIDF(lb.getDocuments());
    }
    /**
     * This function imports a LM document from a given file path and adds it to the library
     * @param path the path of the LM document to import
     * @throws IOException if the library already contains a document with the same author and title as the one being
     * imported.
     */
    public void importDocumentLM(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        Frase autor = new Frase(br.readLine());
        Frase titol = new Frase(br.readLine());
        Document to_add = new Document(autor, titol);
        if (lb.hasDocument(to_add)) {throw new IOException("Duplicated document");}
        Contingut c = new Contingut();
        String line = br.readLine();
        while (line != null) {
            c.addFrase(new Frase(line));
            line = br.readLine();
        }
        to_add.setContingut(c);
        lb.addDocument(to_add);
        this.c.updateIDF(lb.getDocuments());
    }

    /**
     * This function parses a boolean expression and returns the result after
     * evaluating it for a given Document
     * @param expression expression The boolean expression to be parsed and evaluated.
     * @param d The Document object for which the expression is evaluated.
     * @return The result of parsing and evaluating the boolean expression for the given Document.
     */
    public Boolean parserExpression(String expression, Document d){
        // Use the booleanParser object to parse and evaluate the expression for the given Document
        return booleanParser.parserBooleanExpresion(expression, d);
    }

    /**
     * This function returns the Llibreria object
     * @return
     */
    public Llibreria getLlibreria(){
        return lb;
    }

    /**
     * This function adds a Document object to the Llibreria
     * and updates the IDF values for all documents
     * @param d document object to be added
     */
    public void addDocument(Document d){
        lb.addDocument(d);
        c.updateIDF(lb.getDocuments());
    }

    /**
     * This function checks if a given Document object is in the Llibreria
     * @param d document to be checked
     * @return true if the library contains the document passed as a parameter.
     */
    public boolean hasDocument(Document d) {
        return lb.hasDocument(d);
    }

    /**
     * This function removes a Document object from the Llibreria and updates
     * the IDF values for all documents
     * @param d document to be removed.
     */
    public void removeDocument(Document d){
        lb.removeDocument(d);
        c.updateIDF(lb.getDocuments());
    }

    /**
     * This function changes the author of a given Document object in the Llibreria
     * @param d document whose autor name is to be changed
     * @param newName New author of the document
     */
    public void changeAutorDocument(Document d, Frase newName){
        lb.changeAutorDocument(d, newName);
    }

    /**
     * This function changes the title of a given Document object in the Llibreria
     * @param d document whose Title name is to be changed
     * @param name New title of the document
     */
    public void changeTitolDocument(Document d, Frase name) {
        lb.changeTitolDocument(d, name);
    }

    /**
     * This function returns a list of Document objects from the Llibreria that have a
     * given author.
     * @param name The author name to search for.
     * @return A list of Document objects with the given author.
     */
    public ArrayList<Document> getDocumentFromAutor(Frase name) {
        return lb.getDocumentFromAutor(name);
    }

    /**
     * This function returns a list of author names from the Llibreria that have a given prefix.
     * @param prefix The prefix to search for in the author names.
     * @return A list of author names with the given prefix.
     */
    public ArrayList<String> getPrefixAutor(String prefix) {
        return lb.getPrefixAutor(prefix);
    }

    /**
     * This function returns a map of Document objects and their
     * similarity scores to a given Document.
     * The map is sorted in descending order by the similarity scores.
     * If the number of documents in the Llibreria is less than the
     * given number k, all documents are returned.
     *
     * @param d The Document object to compare to.
     * @param k The maximum number of Document objects to return in the map.
     * @return A map of Document objects and their similarity scores to the
     * given Document, sorted in descending order by the similarity scores.
     */
    public HashMap<Document, Double> getMostSimilarDocuments(Document d, Integer k){
        // Create a map to store the similarity scores of each Document object
        HashMap<Document, Double> similarityScores = new HashMap<Document, Double>();
        // Compute the score vector for the given Document
        HashMap<String, Double> documentScoreVector = c.computeWordScore(d);
        // For each Document object in the Llibreria, compute its similarity
        // score to the given Document and add it to the map
        for (Document d2: lb.getDocuments()) {
            if (!d.equals(d2)) {
                similarityScores.put(d2, c.computeCosineSimilarity(documentScoreVector, d2));
            }
        }
        // If the number of documents is less than k, return all documents.
        // Otherwise, return the k documents with the highest similarity scores.
        if (k > similarityScores.size()) return similarityScores;
        else {
            return c.getKHigherScores(similarityScores, k);
        }
    }

    /**
     * This function returns a list of all Document objects in the Llibreria.
     * @return A list of all Document objects in the Llibreria.
     */
    public ArrayList<Document> getDocuments(){
        return lb.getDocuments();
    }

    /**
     * This function returns the number of Document objects in the Llibreria.
     * @return The number of Document objects in the Llibreria.
     */
    public Integer getNumDocuments(){ return lb.getDocuments().size(); }

    /**
     * This function returns a Document object from the Llibreria with a given title and author.
     * @param name The title of the Document object.
     * @param autor The author of the Document object.
     * @return The Document object with the given title and author, or null if it is not found.
     */
    public Document getDocumentFromNameAutor(String name, String autor) {
        return lb.getDocumentFromNameAutor(name, autor);
    }

    /**
     * Sets the content of a document in the library.
     * @param d The document to set the content for.
     * @param s The content to set for the document.
     */
    public void setContent(Document d, String s) { lb.setContent(d, s); }

    /**
     * Gets the list of authors in the library.
     * @return An ArrayList of Autor objects representing the authors in the library.
     */
    public ArrayList<Autor> getAutors(){
        return lb.getAutors();
    }

    /**
     * Saves the library and algorithm to a specified file path.
     * @param path The file path to save the library and algorithm to.
     * @throws IOException If an I/O error occurs while saving the file.
     * @throws Exception If an error occurs while serializing the library or algorithm.
     */
    public void save(String path) throws IOException, Exception {
        this.path = path;
        dp.save(path, serializeLibrary(), serializeAlgorithm());
        //dp.deleteAutosaved();
    }

    /*
        The serializeLibrary function converts the documents and authors in the library
        into a serialized format using the Gson library. The serialized data is returned
        as a String array containing two elements: the serialized documents and the serialized
        authors.
     */
    /**
     * Serializes the library into a String array.
     * @return A String array containing the serialized documents and authors in the library.
     */
    public String[] serializeLibrary() {
        Gson gson = new Gson();
        Document[] dc = lb.getDocuments().toArray(new Document[lb.getNumberDocuments()]);
        Autor[] a = lb.getAutors().toArray(new Autor[lb.getNumberAutors()]);
        return new String[]{gson.toJson(dc), gson.toJson(a)};
    }
    /*
    The serializeAlgorithm function converts the total number of words and the idf map of
    the algorithm into a serialized format using the Gson library. The serialized data is
    returned as a String array containing two elements: the serialized idf map and the
    serialized total number of words.
     */
    /**
     * Serializes the algorithm into a String array.
     * @return A String array containing the serialized
     * total number of words and the idf map of the algorithm.
     */
    public String[] serializeAlgorithm() {
        Gson gson = new Gson();
        Integer i = c.getTotalWords();
        HashMap<String, Double> idf = c.getIDF();
        return new String[]{gson.toJson(idf), gson.toJson(i)};
    }

    /**
     * Resets the CtrlDomini instance.
     * The reset function resets the CtrlDomini instance by creating
     * a new instance and returning it.
     * @return The reset CtrlDomini instance.
     */
    public CtrlDomini reset() {
        instance = new CtrlDomini();
        return instance;
    }

    /**
     * Opens a saved library and algorithm from a specified file path.
     * open > deserialize data > process the deserialize data using deserialize function
     * @param path The file path to open the library and algorithm from.
     * @throws Exception If an error occurs while opening the file or deserializing the data.
     */
    public void open(String path) throws Exception {
        String[] s = CtrlPersistencia.open(path);
        deserialize(s, path);
    }

    /**
     * Deserializes a library and algorithm from a String array.
     * @param s The String array containing the serialized data.
     * @param path The file path of the saved library and algorithm.
     * @throws Exception If an error occurs while deserializing the data.
     */
    public void deserialize(String[] s, String path) throws Exception {
        deserializeDocuments(s[0], s[1]);
        deserializeAlgorithm(s[2], s[3]);
        this.path = path;
    }

    /**
     * Deserializes the documents and authors in the library from a String array.
     * @param documents The serialized documents.
     * @param autors The serialized authors.
     * @throws Exception If an error occurs while deserializing the data.
     */
    public void deserializeDocuments(String documents, String autors) throws Exception {
        Gson gson = new Gson();
        Document[] dc = gson.fromJson(documents, Document[].class);
        lb.setDocuments(new ArrayList<Document>(Arrays.asList(dc)));
        Autor[] aut = gson.fromJson(autors, Autor[].class);
        lb.setAutors(new ArrayList<Autor>(Arrays.asList(aut)));
    }

    /**
     * Deserializes the algorithm from a String array.
     * @param idf The serialized idf map.
     * @param totalwords The serialized total number of words.
     */
    public void deserializeAlgorithm(String idf, String totalwords){
        Gson gson = new Gson();
        int i = Integer.parseInt(totalwords);
        Type type = new TypeToken<HashMap<String, Double>>(){}.getType();
        HashMap<String, Double> des_idf = gson.fromJson(idf, type);
        c.setIDF(des_idf);
        c.setTotalWords(i);
    }

    /**
     * Returns a 2D array of objects containing the title and author of each
     * document in the list of documents.
     *
     * @return 2D array of objects containing the title and author of each document
     */
    public Object[][] getDocumentosData(){
        ArrayList<Document> documents = getDocuments();
        Object[][] d = new Object[documents.size()][2];
        for (int i = 0; i < documents.size(); ++i){
            d[i][0] = documents.get(i).getTitol();
            d[i][1] = documents.get(i).getAutor();
        }
        return d;
    }

    /**
     * Returns a 2D array of objects containing the name, number of documents,
     * and list of documents of each author in the list of authors.
     *
     * @return 2D array of objects containing the name, number of documents,
     * and list of documents of each author
     */
    public Object[][] getAutorsData() {
        ArrayList<Autor> autors = getAutors();
        Object[][] d = new Object[autors.size()][3];
        for (int i = 0; i < autors.size(); ++i) {
            Autor a = autors.get(i);
            d[i][0] = a.getName().toString();
            d[i][1] = Integer.toString(a.getDocumentList().size());
            d[i][2] = a.getDocumentList().toString();
        }
        return d;
    }

    /**
     * Creates a text document with the given title, author, file path, and list of sentences.
     *
     * @param titol title of the document
     * @param autor author of the document
     * @param filepath file path where the document should be created
     * @param sentence list of sentences to include in the document
     * @throws FileNotFoundException if the file path is invalid or the file cannot be created
     */
    public void createTextDoc(Frase titol, Frase autor, String filepath, ArrayList<String> sentence) throws FileNotFoundException {
        File file = new File("/home/aashish/Desktop/PROP_THIRD/docs/" + titol + TXT);
        PrintWriter writer = new PrintWriter(file);
        //Also, maybe we need to add name of author in the document.
        for(String str: sentence) {
            writer.write(str + " ");
        }
        writer.close();
    }

    /**
     * Returns a map of the k most similar documents to the given set of words,
     * with their similarity scores as values.
     *
     * @param words set of words to compare documents to
     * @param k number of most similar documents to return
     * @return map of the k most similar documents to the given set of words, with their similarity scores as values
     */
    public HashMap<Document, Double> getMostSimilarDocumentsWithWords(String words, Integer k) {
        return null;
    }

    /**
     * Returns the document with the given name.
     *
     * @param docName name of the document to return
     * @return the document with the given name
     */
    public Document GetDocForK(String docName) {
        return lb.GetDocForK(docName);
    }


}