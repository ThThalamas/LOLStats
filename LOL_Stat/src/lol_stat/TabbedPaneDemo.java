/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lol_stat;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TabbedPaneDemo extends JPanel {

    public TabbedPaneDemo() {
        super();
        
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");
        
        JComponent menuprincipal = new JPanel();
        tabbedPane.addTab("Menu principal", icon, menuprincipal,
                "Accueil");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent ajouter = new JPanel();
        tabbedPane.addTab("Ajouter joueur", icon, ajouter,
                "Insérer joueur dans BD");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent lancer = new JPanel();
        tabbedPane.addTab("Lancer partie", icon, lancer,
                "Émuler une partie");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        JComponent stat = new JPanel();
        stat.setPreferredSize(new Dimension(700,400));
        tabbedPane.addTab("Statistiques", icon, stat,
                "Quelques chiffres");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        //Ajout au menu des jpanel
        menuprincipal.add(MenuPrinc());
        ajouter.add(MenuPrinc());
        lancer.add(lancerPartie());
        stat.add(MenuPrinc());
    }
    
    public JPanel MenuPrinc(){
        JPanel panel = new JPanel();
        JPanel paneltable = new JPanel();
        JPanel panelbouton = new JPanel();
        paneltable.setSize(200,200);
        
        JTable table = new JTable();

                table.setSize(200,200);
        JButton mmr = new JButton("Afficher joueurs triés par MMR"); 
        JButton role = new JButton("Afficher joueurs triés par rôle"); 
        JButton afficher = new JButton("Afficher champions");
        
        panelbouton.setLayout(new BorderLayout(10,10));
        panel.setLayout(new BorderLayout(10,10));
        
        
        panelbouton.add(mmr,BorderLayout.NORTH);
        panelbouton.add(role,BorderLayout.CENTER);
        panelbouton.add(afficher,BorderLayout.SOUTH);
        
        
        paneltable.add(table); 
        paneltable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(paneltable,BorderLayout.WEST);        
        panel.add(panelbouton,BorderLayout.EAST);
        setVisible(true);
        return panel;
    }
    public JPanel lancerPartie(){
        JPanel panel = new JPanel();
        JLabel info = new JLabel("Label");
        panel.add(info);
        setVisible(true);
        return panel;
    }
    
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add content to the window.
        frame.add(new TabbedPaneDemo(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}
