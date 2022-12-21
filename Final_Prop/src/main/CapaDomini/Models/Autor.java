package main.CapaDomini.Models;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class Written by : Aashish Bhusal & Mario
 */

/**
 * This class represents the author of a Document. A author has a name and a list of documents
 * written by them.
 */

public class Autor {
    /**
     * Name of the Author
     */

    private Frase name;

    /**
     * List of documents written by the author.
     */

    private ArrayList<Document> documentList;

    /**
     * Basic Constructor.
     * @param name is the Name of Autor.
     */

    public Autor(Frase name) {
        if (name.isEmpty())
            throw new java.lang.IllegalArgumentException("Autor name cannot be empty.");
        this.name = name;
        documentList = new ArrayList<Document>();
    }

    /**
     * Returns the name of the Autor.
     */
    public Frase getName() {
        return name;
    }

    /**
     * Changes the name of author.
     * @param name New name.
     */
    public void setName(Frase name) {
        this.name = name;
    }

    /**
     *
     * @return documentList is the list of documents of the author
     */

    public ArrayList<Document> getDocumentList() {
        return documentList;
    }

    /**
     *
     * @param d is the new document added to the list of documents of the Autor.
     */

    public void addDocument(Document d) {
        documentList.add(d);
    }

    /**
     *
     * @param d is the document that is to be removed from the list of documents of the Autor.
     */

    public void removeDocument(Document d){
        documentList.remove(d);
    }

    /**
     *
     * @return returns the number of documents that a Autor has.
     */

    public int getNumberDocuments() {
        return documentList.size();
    }

    /**
     *
     * @param a is the name of the Autor
     * @return returns true if name a is similar to the name of the Autor.
     */

    public boolean hasName(Frase a){
        return a.equals(name);
    }

    /**
     *
     * @param prefix is the prefix of the name of the Autor.
     * @return true if the name of Autor starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return name.toString().startsWith(prefix);
    }

    /**
     *
     * @return returns the string representation of the object.
     * For eg: if we want to print or compare the name of the Autor
     * Java compiler internally invokes the toString() method on the object.
     * So overriding the toString() method, returns the desired output.
     * If we do not override it the hashcode values of the object will be returned.
     */
    @Override
    public String toString(){
        return name.toString();
    }
}