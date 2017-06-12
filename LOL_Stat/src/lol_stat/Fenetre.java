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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Fenetre extends JFrame {      
    protected JScrollPane scroll; // mais c ce scrollPane qu'il faut afficher et non la jtable, puisqu'il la contient(la JTable), je l'ai déjà fait dans menuPrinc
    private Connection con;
    
    public Fenetre() {
        super();
        
        scroll = new JScrollPane();
        try
        {
            con = DriverManager.getConnection(
            "jdbc:oracle:thin:@134.214.112.67:1521:orcl","p1603697","267785");
            
        }catch(SQLException e){}
        build();
    }
    public void build()
    {
        setTitle("Scoring League Of Legend");
        setContentPane(content());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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
    
    public JPanel MenuPrinc(){ // ne plus toucher
        JPanel panel = new JPanel();
        JPanel panelbouton = new JPanel();
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
        
        mmr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String requete = "select * from Joueur order by mmr desc";
                    
                    scroll = new JScrollPane(new JTable(requete(con,requete)));
                    scroll.updateUI();
                }
        });
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
        JPanel panel = new JPanel();
        return panel;
    }
    
    public ModeleDonnee requete(Connection con,String requête) // retourne une instance de ModeleDonnee
    {
        Statement stmt;
        ResultSet rs;
        ResultSetMetaData rsMeta;
        String []nom = null ;
        Object [][]colonnes = new Object[0][0];
        ModeleDonnee mod;
        
        try
        {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(requête);
            rsMeta = rs.getMetaData();
            nom = new String[rsMeta.getColumnCount()];// On initialise notre tableau de nom de colonnes
            
            for(int i = 1; i <= rsMeta.getColumnCount(); i++) // On recupère les nom des colonnes
            {
                nom[i-1] = rsMeta.getColumnName(i).toUpperCase();
                //System.out.printf("%s ",nom[i-1]);
            }
           
            
            rs.last(); // on place le curseur à la fin 
            int nombreLignes = rs.getRow(); //on récupère le numéro de la ligne 
            rs.beforeFirst(); //on replace le curseur avant la première ligne 
            int nombreColonnes = rsMeta.getColumnCount();
            //System.out.println("Nombre de ligne(s) : "+nombreLignes+"\n"+"Nombre de colonne(s) : "+rsMeta.getColumnCount());
            
            colonnes = new Object[nombreLignes][nombreColonnes];
            //colonnes = new String[nombreLignes-1][nombreColonnes-1]; 
            
            
            while(rs.next())
            {   
                Object obj[] = new Object[nombreColonnes];
                for(int i = 0 ; i < nombreColonnes ; i++)
                {
                    obj[i] = rs.getObject(i+1);
                }
               
                for(int el = 0 ; el < nombreColonnes ; el++)
                {
                    colonnes [rs.getRow()-1][el] = obj[el];
                }
                
            }
           
            stmt.close();
            rs.close();
        }catch(Exception e){}
        mod = new ModeleDonnee(nom, colonnes);
        
        return mod;
    }
    
    public static void main (String[]arg)
    {
        new Fenetre();
    }
}
    
    
    
    

