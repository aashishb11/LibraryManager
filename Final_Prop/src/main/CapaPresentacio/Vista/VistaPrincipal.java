package main.CapaPresentacio.Vista;

import main.CapaPresentacio.Controllers.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VistaPrincipal extends JFrame implements LangListener, ActionListener {

    private CtrlPresentacio ctrl;

    //private JFrame mainFrame = new JFrame("Main window");
    private JButton buttonImport = new JButton("Import");
    private JPanel panelContent = new JPanel();
    private JPanel panelButtons = new JPanel();
    private JTabbedPane tabbedPanel;
    private JMenuItem open, save;

    // Menus
    private JMenuBar menuBarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuItemQuit = new JMenuItem("Quit");
    private JMenu menuOptions = new JMenu("Options");
    private JMenuItem menuItemPreferences = new JMenuItem("Preferences");
    private VistaLlibreria tableLlibreria;
    private VistaAutors tableAutors;
    private VistaRelevance vistaRelevance;



    public VistaPrincipal (CtrlPresentacio ctrl) {
        super("Document manager");
        this.ctrl = ctrl;
        tableLlibreria = new VistaLlibreria(this.ctrl, this);
        tableAutors = new VistaAutors(this.ctrl);
        vistaRelevance = new VistaRelevance(this.ctrl);

        initializeComponents();
        assignListenersComponents();
        hacerVisible();
    }

    public void hacerVisible() {
        this.pack();
        this.setVisible(true);
        activar();
    }

    public void activar() {
        this.setEnabled(true);
    }

    public void updateAutors(Boolean required) {
        tableAutors.updateTable(false);
        vistaRelevance.updateAutorList();
    }

    public void updateLlibreria(Boolean required) {
        tableLlibreria.updateTable(false);
        vistaRelevance.updateAutorList();
    }

    public void desactivar() {
        this.setEnabled(false);
    }

    public void actionPerformed_buttonImport (ActionEvent event) {
        System.out.println("button has been clicked");
    }

    private void assignListenersComponents() {

        buttonImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_buttonImport(e);
            }
        });

        // Listeners para las opciones de menu
        menuItemQuit.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        System.exit(0);
                    }
                });

    }

    private void initializeComponents() {
        initMenuBarVista();
        initPanelContent();
        initPanelButtons();
        initTabbedPanel();
        assignListenersComponents();
        initMainFrame();
    }

    private void initMainFrame() {
        updateLlibreria(false);
        updateAutors(false);
        // SIze
        this.setMinimumSize(new Dimension(700,400));
        this.setPreferredSize(this.getMinimumSize());
        this.setResizable(true);
        // Position
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setJMenuBar(menuBarVista);
        this.add(tabbedPanel);
    }

    private void initMenuBarVista() {
        open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setActionCommand("open");
        save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setActionCommand("save");

        menuFile.add(menuItemQuit);
        menuFile.add(open);
        menuFile.add(save);
        menuOptions.add(menuItemPreferences);
        menuBarVista.add(menuFile);
        menuBarVista.add(menuOptions);
    }

    private void initTabbedPanel(){
        tabbedPanel = new JTabbedPane();
        tabbedPanel.add("Library", tableLlibreria);
        tabbedPanel.add("Authors", tableAutors);
        tabbedPanel.add("Similarity", vistaRelevance);
    }

    private void initPanelContent() {
        // Layout
        panelContent.setLayout(new BorderLayout());
        // Paneles
        panelContent.add(panelButtons,BorderLayout.NORTH);
        // panelContenidos.add(panelInformacion,BorderLayout.CENTER);
    }

    private void initPanelButtons() {
        // Layout
        panelButtons.setLayout(new FlowLayout());
        // Componentes
        panelButtons.add(buttonImport);
//        buttonAbrirDialog.setToolTipText("Abre un Dialogo modal simple");
    }

    @Override
    public void onLanguageChanged() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            JFrame parentFrame = new JFrame();
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Save as");
            fc.setCurrentDirectory(new java.io.File("."));
            FileNameExtensionFilter lmFilter = new FileNameExtensionFilter("lm", "lm");
            FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("PDF", "pdf");
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML", "xml");
            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text", "txt");
            FileNameExtensionFilter docxFilter = new FileNameExtensionFilter("Word", "docx");
            fc.addChoosableFileFilter(lmFilter);
            fc.addChoosableFileFilter(pdfFilter);
            fc.addChoosableFileFilter(xmlFilter);
            fc.addChoosableFileFilter(txtFilter);
            fc.addChoosableFileFilter(docxFilter);
            fc.setAcceptAllFileFilterUsed(false);
            int userSelection = fc.showSaveDialog(parentFrame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String fileToSave = fc.getSelectedFile().getPath();
                String extension = "";
                FileFilter selectedFilter = fc.getFileFilter();
                if (selectedFilter.equals(lmFilter)) {
                    extension = "lm";
                } else if (selectedFilter.equals(pdfFilter)) {
                    extension = "pdf";
                } else if (selectedFilter.equals(xmlFilter)) {
                    extension = "xml";
                } else if (selectedFilter.equals(txtFilter)) {
                    extension = "txt";
                } else if (selectedFilter.equals(docxFilter)) {
                    extension = "docx";
                }
                if (fileToSave.endsWith("." + extension)) {
                    ctrl.save(fileToSave);
                } else {
                    ctrl.save(fileToSave + "." + extension);
                }
            }
        }
        else if (e.getActionCommand().equals("open")) {
            int n = JOptionPane.showOptionDialog(null, "Do you want to save before opening new?", "Save",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, null,JOptionPane.YES_OPTION);
            if (n == JOptionPane.YES_OPTION){
                actionPerformed(new ActionEvent(e.getSource(), new Random().nextInt(), "save"));
            }
            if(n == -1) return;
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Open");
            fc.setCurrentDirectory(new java.io.File("."));
            FileNameExtensionFilter lmFilter = new FileNameExtensionFilter("lm", "lm");
            FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("PDF", "pdf");
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML", "xml");
            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text", "txt");
            FileNameExtensionFilter docxFilter = new FileNameExtensionFilter("Word", "docx");
            fc.addChoosableFileFilter(lmFilter);
            fc.addChoosableFileFilter(pdfFilter);
            fc.addChoosableFileFilter(xmlFilter);
            fc.addChoosableFileFilter(txtFilter);
            fc.addChoosableFileFilter(docxFilter);
            fc.setAcceptAllFileFilterUsed(false); // if true we can open any file no matter the extension
            int p = fc.showOpenDialog(open);
            try {
                if (p == JFileChooser.APPROVE_OPTION) {
                    String path = fc.getSelectedFile().getPath();
                    System.out.println(path);
                    ctrl.open(path);
                }
            } catch (Exception ex) {
                ctrl.errorManagement("Error opening");
                ex.printStackTrace();
            }
        }
    }
}
