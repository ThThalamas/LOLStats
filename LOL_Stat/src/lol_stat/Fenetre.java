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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Fenetre extends JFrame {
    JTable jt;          // il faut en faite utiliser un jtable global à tout le projet, c'est plus facile,
    JScrollPane scroll; // mais c ce scrollPane qu'il faut afficher et non la jtable, puisqu'il la contient(la JTable), je l'ai déjà fait dans menuPrinc
    
    public Fenetre() {
        super();
        jt = new JTable();
        scroll = new JScrollPane(jt);
        build();
    }
    public void build()
    {
        setTitle("Scoring League Of Legend");
        setContentPane(content());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        setVisible(true);
    }
    public JTabbedPane content()
    {
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setPreferredSize(new Dimension(1000, 700));
        
        tabbedPane.addTab("Menu principal", MenuPrinc());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        
        tabbedPane.addTab("Ajouter joueur",ajouter());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        
        tabbedPane.addTab("Lancer partie",lancerPartie());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        
        tabbedPane.addTab("Statistiques", stat());
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        return tabbedPane;
    }
    
    public JPanel MenuPrinc(){ // ne plus toucher
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);    
        JPanel panelbouton = new JPanel();
        panelbouton.setBackground(Color.LIGHT_GRAY);
        scroll.setPreferredSize(new Dimension(450,600));
         

        
        JButton mmr = new JButton("Afficher joueurs triés par MMR"); 
        JButton role = new JButton("Afficher joueurs triés par rôle"); 
        JButton afficher = new JButton("Afficher champions");
        
        
        panelbouton.setLayout(new GridLayout(3,1,20,20));
        panel.setLayout(new FlowLayout(5,30,30)/*new BorderLayout(10,10)*/);
        
        
        panelbouton.add(mmr);
        panelbouton.add(role);
        panelbouton.add(afficher);
        
        

        //paneltable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(scroll);        
        panel.add(panelbouton);
        setVisible(true);
        return panel;
    }
    
    public JPanel ajouter()
    {
        JPanel label = new JPanel();
        JLabel Pseudo = new JLabel("Pseudo");
        JLabel Mmr = new JLabel("MMR");
        JLabel Main = new JLabel("Rank");
        label.add(Pseudo);
        label.add(Mmr);
        label.add(Main);
        //label.setLayout(new FlowLayout());
        
        JPanel tf = new JPanel();
        JTextField Pseudotf = new JTextField("          ");
        JTextField Mmrtf = new JTextField("          ");
        JTextField Maintf = new JTextField("          ");
        tf.add(Pseudotf);
        tf.add(Mmrtf);
        tf.add(Maintf);
        //tf.setLayout(new FlowLayout());
        
        JPanel bt = new JPanel();
        JButton ajouterjoueur = new JButton("Ajouter joueur");
        bt.add(ajouterjoueur);
        
        JPanel condense = new JPanel();
        condense.add(label);
        condense.add(tf);
        condense.setLayout(new GridLayout(3, 2));
        
        JPanel panel = new JPanel();
        panel.add(condense);
        panel.add(ajouterjoueur);
        
        
        return panel;
    }

    
    
    
    public JPanel lancerPartie(){
        JPanel panel = new JPanel();
        JLabel info = new JLabel("Label");
        panel.add(info);
        setVisible(true);
        return panel;
    }
    
    public JPanel stat()
    {
        JPanel label = new JPanel();
        JLabel Main = new JLabel("Main :                   ");
        JLabel Mainvalue = new JLabel("null");
        JLabel nombre = new JLabel("Nombre de partie(s) jouées :");
        JLabel nombrevalue = new JLabel("null");
        JLabel pourcent = new JLabel("Pourcentage de victoire :");
        JLabel pourcentvalue = new JLabel("null");
        label.add(Main);
        label.add(Mainvalue);        
        label.add(nombre);
        label.add(nombrevalue);
        label.add(pourcent); 
        label.add(pourcentvalue); 
        label.setLayout(new GridLayout(3, 2));   

        JPanel combo = new JPanel();
        Object[] liste = new Object[]{"Sélectionner joueur","Mettre array list nom joueur"};
        JComboBox selectjoueur = new JComboBox(liste);      
        combo.add(selectjoueur);
        //combo.setLayout(new GridLayout(1,1));  
        //Avec grid layout le bouton est énorme
        
        JPanel panel = new JPanel();
        setBackground(Color.orange);
        panel.add(combo);
        panel.add(label);
        panel.setLayout(new GridLayout(6,1)); 
        setVisible(true);
        return panel;
    }
    
    
    public static void main (String[]arg)
    {
        new Fenetre();
    }
}
    
    
    
    

