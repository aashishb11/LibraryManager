package main.CapaDomini.Models;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class Written by : Aashish Bhusal & Mario
 */

/**
 * Llibreria is a class that contains all the Autor there exists and all the documents
 * there is.
 */

public class Llibreria {
    /**
     * An ArrayList of Autor objects representing the authors in the Llibreria.
     */
    private ArrayList<Autor> autors;

    /**
     * An ArrayList of Document objects representing the documents in the Llibreria.
     */
    private ArrayList<Document> documents;

    /**
     * Constructs a new Llibreria object with empty lists of authors and documents.
     */
    public Llibreria() {
        autors = new ArrayList<Autor>();
        documents = new ArrayList<Document>();
    }
    /**
     * Sets the documents in the Llibreria to the given ArrayList of documents.
     * @param documents the new ArrayList of documents
     */
    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    /**
     * Sets the authors in the Libreria to the given ArrayList of authors.
     * @param autors the new ArrayList of authors
     */

    public void setAutors(ArrayList<Autor> autors) {
        this.autors = autors;
    }

    /**
     * Adds a new document to the Llibreria and updates the list of documents for the corresponding author.
     * If the author does not already exist in the Llibreria, a new Autor object is created and added to the list of authors.
     * @param d the Document object to be added
     */
    public void addDocument(Document d) {
        documents.add(d);
        Frase name = d.getAutor();
        Autor autor = getAutor(name);
        if (autor != null) {
            autor.addDocument(d);
        } else {
            autor = new Autor(name);
            autor.addDocument(d);
            autors.add(autor);
        }
    }
    /**
     * Sets the content of the given document to the given string.
     * @param d the Document object to modify
     * @param s the new content for the document
     */
    public void setContent(Document d, String s) {
        for (Document d2 : documents) {
            if (d.equals(d2)) {
                d.setContingut(s);
            }
        }
    }
    /**
     * Returns true if the Llibreria contains the given document, and false otherwise.
     * @param d the Document object to search for
     * @return true if the Llibreria contains the given document, and false otherwise
     */
    public boolean hasDocument(Document d){
        return documents.contains(d);
    }
    /**
     * Returns the ArrayList of documents in the Llibreria.
     * @return the ArrayList of documents in Llibreria
     */
    public ArrayList<Document> getDocuments() {
        return documents;
    }

    /**
     * Returns the ArrayList of authors in the Llibreria.
     * @return the ArrayList of authors in the Llibreria
     */
    public ArrayList<Autor> getAutors(){
        return autors;
    }

    /**
     * Removes the given document from the Llibreria and updates the list of documents for the corresponding author.
     * If the document does not exist in the Llibreria, an IllegalArgumentException is thrown.
     * @param d the Document object to be removed
     * @throws IllegalArgumentException if the document does not exist in the Llibreria
     */
    public void removeDocument(Document d) {
        if (!hasDocument(d)) {
            throw new java.lang.IllegalArgumentException("El document no existeix a la llibreria");
        }
        documents.remove(d);
        Frase name = d.getAutor();
        Autor autor = getAutor(name);
        autor.removeDocument(d);
        updateAutors(autor);
    }

    /**
     * Changes the title of the given document to the given Frase object.
     * If the document does not exist in the Llibreria, an IllegalArgumentException is thrown.
     * @param d the Document object to modify
     * @param name the new Frase object for the document's title
     * @throws IllegalArgumentException if the document does not exist in the Llibreria
     */
    public void changeTitolDocument(Document d, Frase name) {
        if (!hasDocument(d)) {
            throw new java.lang.IllegalArgumentException("El document no existeix a la llibreria");
        }
        d.setTitol(name);
    }
    /**
     * Returns the document with the given title and author from the Llibreria, or a fake document
     * with the given title and author if it does not exist in the Llibreria.
     * @param name the title of the document to search for
     * @param autor the author of the document to search for
     * @return the document with the given title and author, or a fake document if it does not exist in the Llibreria
     */
    public Document getDocumentFromNameAutor(String name, String autor) {
        Document fake = new Document(
                new Frase(name),
                new Frase(autor)
        );
        for (Document d2: documents) {
            if (fake.equals(d2)) return d2;
        }
        return fake;
    }
    /**
     * Changes the author of the given document to the given Frase object.
     * If the document does not exist in the Llibreria, an IllegalArgumentException is thrown.
     * If the new author does not exist in the Llibreria, a new Autor object is created and added to the list of authors.
     * @param d the Document object to modify
     * @param name the new Frase object for the document's author
     * @throws IllegalArgumentException if the document does not exist in the Llibreria
     */
    public void changeAutorDocument(Document d, Frase name) {
        if (!hasDocument(d)) {
            throw new java.lang.IllegalArgumentException("El document no existeix a la llibreria");
        }
        Autor autor = getAutor(name);
        if (autor == null) {
            autor = new Autor(name);
            autors.add(autor);
        }
        autor.addDocument(d);
        Autor oldAutor = getAutor(d.getAutor());
        d.setAutor(autor.getName());
        oldAutor.removeDocument(d);
        updateAutors(oldAutor);
    }
    /**
     * Returns the ArrayList of documents written by the author with the given name.
     * If the author does not exist in the Llibreria, an empty ArrayList is returned.
     * @param name the name of the author to search for
     * @return the ArrayList of documents written by the given author, or an empty ArrayList if the author does not exist
     */
    public ArrayList<Document> getDocumentFromAutor(Frase name) {
        Autor a = getAutor(name);
        if (a == null) return new ArrayList<Document>();
        ArrayList<Document> documentListAutor = a.getDocumentList();
        return documentListAutor;
    }
    /**
     * Returns a list of all authors in the Llibreria whose names start with the given prefix.
     * @param prefix the prefix to search for
     * @return a list of all authors in the Llibreria whose names start with the given prefix
     */
    public ArrayList<String> getPrefixAutor(String prefix) {
        ArrayList<String> result = new ArrayList<String>();
        for (Autor a: autors) {
            if (a.startsWith(prefix)) result.add(a.getName().toString());
        }
        return result;
    }
    /**
     * Removes the given Autor object from the list of authors if it has no associated documents.
     * @param a the Autor object to update
     */
    private void updateAutors(Autor a){
        if(a.getNumberDocuments() == 0) {
            autors.remove(a);
        }
    }
    /**
     * Returns the Autor object with the given name, or null if it does not exist in the Llibreria.
     * @param f the name of the Autor object to search for
     * @return the Autor object with the given name, or null if it does not exist in the Llibreria
     */
    private Autor getAutor(Frase f){
        for (Autor a: autors){
            if (a.hasName(f)) return a;
        }
        return null;
    }

    /**
     * Returns a string representation of the Llibreria, which is a list of the string
     * representations of all the documents in the Llibreria.
     * @return a string representation of the Llibreria
     */
    @Override
    public String toString() {
        //the stream() method of the documents ArrayList creates a stream of Document objects,
        // then the map() method transforms each Document object into its string
        // representation using the toString() method of the Document class. Finally,
        // the collect() method is used to collect the stream of strings into a list,
        // and the toString() method of the list is called to get a string representation
        // of the list.
        return documents.stream().map(document->document.toString()).collect(Collectors.toList()).toString();
    }
    /**
     * Returns the number of documents in the Llibreria.
     * @return the number of documents in the Llibreria
     */
    public Integer getNumberDocuments() { return documents.size(); }

    /**
     * Returns the number of authors in the Llibreria.
     * @return the number of authors in the Llibreria
     */

    public Integer getNumberAutors() { return autors.size(); }

    public Document GetDocForK(String docName) {
        for(Document d: documents){
            if(d.equals(docName)) return d;
        }
        return null;
    }
}