package main.CapaPresentacio.Controllers;

import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Document;
import main.CapaDomini.Models.Frase;
import main.CapaDomini.Models.Llibreria;
import main.CapaPresentacio.Vista.VistaPrincipal;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CtrlPresentacio {
    private CtrlDomini cd;

    private VistaPrincipal vp;

    public CtrlPresentacio(){
        this.cd = new CtrlDomini();
        vp = new VistaPrincipal(this);
    }

    public Document getDocumentFromNameAutor(String name, String autor) {
        return cd.getDocumentFromNameAutor(name, autor);
    }

    public Document GetDocForK(String docName){
        return cd.GetDocForK(docName);
    }

    public void importDocumentPlainText(String path) throws IOException {
        cd.importDocumentPlainText(path);
    }

    public Llibreria getLlibreria(){
        return cd.getLlibreria();
    }


    public void addDocument(Document d) {
        cd.addDocument(d);
    }

    public Boolean parserExpression(String expression, Document d) {
        return cd.parserExpression(expression, d);
    }

    public boolean hasDocument(Document d) {
        return cd.hasDocument(d);
    }


    public void removeDocument(Document d) {
        cd.removeDocument(d);
    }

    public void changeAutorDocument(Document d, Frase name) {
        cd.changeAutorDocument(d, name);
    }

    public void changeTitolDocument(Document d, Frase titol) {
        cd.changeTitolDocument(d, titol);
    }

    public ArrayList<Document> getDocumentFromAutor(Frase name) {
        return cd.getDocumentFromAutor(name);
    }

    public ArrayList<String> getPrefixAutor(String prefix) {
        return cd.getPrefixAutor(prefix);
    }

    public HashMap<Document, Double> getMostSimilarDocuments(Document d, Integer k){
        return cd.getMostSimilarDocuments(d, k);
    }

    public Object[][] getDocumentosData(){
        return cd.getDocumentosData();
    }

    public Object[][] getAutorsData() {
        return cd.getAutorsData();
    }

    public void setContent(Document d, String s) { cd.setContent(d, s); }

    public void errorManagement(String s){
        JOptionPane.showMessageDialog(new JFrame("error"),
                s, "error", JOptionPane.ERROR_MESSAGE);

    }

    public void save(String path) {
        try{
            cd.save(path);
        } catch (IOException E){errorManagement("errorguardando");
        } catch (Exception E){
            errorManagement("errorguardando");
            E.printStackTrace();
        }
    }

    public void open(String path) {
        try{
            cd = cd.reset();
            System.gc();
            cd.open(path);
            System.out.println("disposing");
            vp.dispose();
            System.out.println("creating");
            vp = new VistaPrincipal(this);
            System.out.println("succesfully created");
        } catch (IOException E){errorManagement("Error occured at opening.");
        } catch (Exception E){
            errorManagement("Error occured at opening.");
            E.printStackTrace();
        }
    }

    public void createTextDoc(Frase titol,Frase autor, String filepath, ArrayList<String> sentence) throws FileNotFoundException {
        cd.createTextDoc(titol,autor,filepath,sentence);
    }


    public HashMap<Document, Double> getMostSimilarDocumentsWithWords(String words, Integer k){
        return cd.getMostSimilarDocumentsWithWords(words, k);
    }

    public ArrayList<Document> getDocuments() {
        return cd.getDocuments();
    }

    public void importDocumentXML(String path) throws IOException {
         cd.importDocumentXML(path);
    }

    public void importDocumentLM(String path) throws IOException{
        cd.importDocumentLM(path);
    }

    public void importDocumentPDF(String path) throws IOException{
         cd.importDocumentPDF(path);
    }

    public void importDocumentWord(String path) throws IOException{
        cd.importDocumentWord(path);
    }
}