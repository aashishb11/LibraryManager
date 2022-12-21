package main.CapaDomini.Models;
import java.util.Date;
import java.util.*;

/**
 * Class Written by : Aashish Bhusal & Mario
 */

public class Document {

    /**
     * Titol is the document name. (A Title is considered as a frase)
     */
    private Frase Titol;

    /**
     * Autor is the autor of the document (An author is consideres as a frase)
     */
    private Frase Autor;

    /**
     * EsPublic returns if the document is public or not, by default false.
     */
    private boolean EsPublic;

    /**
     * Editable returns true if the document is editable false otherwise, by default true.
     */
    private boolean Editable;

    /**
     * A document can have a code to manage who is editing.
     */
    private String Codi;

    /**
     * DateAlta returns the date when the document was first uploaded.
     */
    private Date DateAlta;

    /**
     * Object of class Contingut. A document has a contingut.
     */
    private Contingut contingut;

    /**
     * An empty Constructor of the class Document.
     */
    public Document() {

    }

    /**
     * Simple Constructor of the class Document
     * @param Titol identify a document.
     * @param Autor is the Author of the document.
     */
    public Document(Frase Titol, Frase Autor)
    {
        this.Titol = Titol;
        this.Autor = Autor;
        EsPublic = false;
        Editable = false;
        this.Codi = "";
        DateAlta = new Date();
        contingut = new Contingut();
    }

    public Document(Frase Titol)
    {
        this.Titol = Titol;
        this.Autor = Autor;
        EsPublic = false;
        Editable = false;
        this.Codi = "";
        DateAlta = new Date();
        contingut = new Contingut();
    }

    /**
     * Complex Constructor of the class Document.
     * @param titol identify a document.
     * @param autor is the Author of the document
     * @param c is the content of the document which is an array of Frases.
     */
    public Document(Frase titol, Frase autor, Contingut c) {
        this.Titol = titol;
        this.Autor = autor;
        EsPublic = false;
        Editable = false;
        this.Codi = "";
        DateAlta = new Date();
        contingut = c;
    }

    //Getters and Setters

    /**
     *
     * @return returns the Titol of the Document
     */
    public Frase getTitol() {
        return Titol;
    }

    /**
     *sets the value of the Titol field to the given @param titol object.
     */
    public void setTitol(Frase titol) {
        this.Titol = titol;
    }

    /**
     *
     * @return returns the Autor of the Document.
     */
    public Frase getAutor() {
        return Autor;
    }

    /**
     * sets the value of the Autor field to the given @param name object.
     */
    public void setAutor(Frase name) { Autor = name; }

    /**
     *
     * @return returns the value of the Codi
     */
    public String getCodi() {
        return Codi;
    }

    /**
     * Sets the value of the Codi field to the given
     * @param codi object
     */
    public void setCodi(String codi) {
        Codi = codi;
    }

    /**
     * Sets Document to Public.
     */
    public void setEsPublic() {
        EsPublic = true;
    }

    /**
     * Sets Document to Editable.
     */
    public void setEditable() {
        Editable = true;
    }

    /**
     *Sets the value of the contingut field to the given contingut object.
     * @param contingut is the new value for the contingut field
     */
    public void setContingut(Contingut contingut) {
        this.contingut = contingut;
    }

    /**
     *Sets the value of the contingut field to a new Contingut object created from the
     * given contingut string.
     * @param contingut is the string to be used to create the new Contingut object
     */
    public void setContingut(String contingut) {
        this.contingut.setContingut(contingut);
    }

    /**
     *
     * @return returns the Contingut object that represents the content of the Document object
     */
    public Contingut getContingut() {
        return contingut;
    }

    //PUBLIC FUNCTIONS

    public void removeCodi() {
        Codi = "";
    }

    public void DocumentPrivat() {
        if (Editable) Editable = false;
        EsPublic = false;
    }

    public void DocumentNoEditable() {
        Editable = false;
    }

    /**
     *
     * @return returns a string representation of the Document object by concatenating the string
     * representations of the Titol and Autor fields, separated by a colon.
     */
    @Override
    public String toString() {
        return Titol.toString() + ":" + Autor.toString();
    }

    /**
     *
     * @return returns a boolean value indicating whether an object of the Contingut class
     * has the given @param token string as a token in any of its frases.
     */
    public boolean hasToken(String token) {
        return contingut.hasToken(token);
    }

    /**
     *
     * @return returns a boolean value indicating whether an object of the Contingut class
     * has the given  @param sentence string as a sentence in any of its frases.
     */
    public boolean hasSentence(String sentence) {
        return contingut.hasSentence(sentence);
    }

    /**
     * @return returns a boolean value indicating whether the given @param o object is equal to
     * the Document object on which the method is called. The Document objects are
     * considered equal if they have the same Autor and Titol fields.
     */
    @Override
    public boolean equals(Object o){
        Document aux = (Document) o;
        return (aux.getAutor().equals(this.Autor) && aux.getTitol().equals(this.Titol));
    }

    /**
     * Returns true if any of the given words are present in the document, false otherwise.
     * @param wordList the list of words to check for
     * @return true if any of the given words are present in the document, false otherwise
     */
    public Boolean hasAnyWord(ArrayList<String> wordList) {
        for (String word: wordList) {
            if (contingut.hasWord(word)) return true;
        }
        return false;
    }

}
