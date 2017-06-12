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
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

    public Fenetre() {
        super();
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

    
    public JPanel MenuPrinc(){
        JPanel panel = new JPanel();
        JPanel paneltable = new JPanel();
        JPanel panelbouton = new JPanel();
        paneltable.setSize(200,200);
        
        JTable table = new JTable();

        table.setSize(500,500);
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
    
    public JPanel stat()
    {
        JPanel panel = new JPanel();
        return panel;
    }
    
    
    public static void main (String[]arg)
    {
        new Fenetre();
    }
}
    
    
    
    

