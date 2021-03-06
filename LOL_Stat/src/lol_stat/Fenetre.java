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
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import static jdk.internal.org.objectweb.asm.util.Printer.TYPES;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Fenetre extends JFrame {      
    protected JScrollPane scrollMenuPrinc; // mais c ce scrollPane qu'il faut afficher et non la jtable, puisqu'il la contient(la JTable), je l'ai déjà fait dans menuPrinc
    protected JScrollPane scrollLancerPartie;
    protected PreparedStatement pstmtMMR;
    protected PreparedStatement pstmtROLE;
    protected PreparedStatement pstmtCHAMP;
    protected JComboBox selectJoueurStat;
    protected JComboBox selectJoueurLancer;
    protected int nbJoueurAdd = 0;
    protected Connection con;
    
    public Fenetre() {
        super();
        
        
        scrollMenuPrinc = new JScrollPane();
        scrollLancerPartie = new JScrollPane();
        
        try
        {
            con = DriverManager.getConnection(
            "jdbc:oracle:thin:@134.214.112.67:1521:orcl","p1603697","267785");
            pstmtMMR = con.prepareStatement("Select Pseudo,rank,mmr from joueur order by mmr desc",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmtROLE = con.prepareStatement("select distinct pseudo,rank,nom,role from joueur,partie,champion where joueur.idjoueur = partie.idjoueur and partie.idchamp = champion.idchamp  order by role",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmtCHAMP = con.prepareStatement("select nom,role from champion order by role",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        
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
        scrollMenuPrinc.setPreferredSize(new Dimension(550,600));
         

        
        JButton mmr = new JButton("Afficher joueurs triés par MMR");
        JButton role = new JButton("Afficher joueurs triés par rôle"); 
        JButton afficherChamp = new JButton("Afficher champions");
        
        
        panelbouton.setLayout(new GridLayout(3,1,20,20));
        panel.setLayout(new FlowLayout(5,30,30)/*new BorderLayout(10,10)*/);
        
        
        panelbouton.add(mmr);
        panelbouton.add(role);
        panelbouton.add(afficherChamp);
        
        

        //paneltable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(scrollMenuPrinc);        
        panel.add(panelbouton);
        
        mmr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JTable table = new JTable(requete(con,pstmtMMR));
                    scrollMenuPrinc.setViewportView(table);
                    scrollMenuPrinc.revalidate();
                    scrollMenuPrinc.repaint();
                };
        });
        role.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JTable table = new JTable(requete(con,pstmtROLE));
                    scrollMenuPrinc.setViewportView(table);
                    scrollMenuPrinc.revalidate();
                    scrollMenuPrinc.repaint();
                };
        });
        afficherChamp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JTable table = new JTable(requete(con,pstmtCHAMP));
                    scrollMenuPrinc.setViewportView(table);
                    scrollMenuPrinc.revalidate();
                    scrollMenuPrinc.repaint();
                };
        });
        return panel;
    }
    
    public JPanel ajouter()
    {
        JPanel label = new JPanel();
        JLabel Pseudo = new JLabel("Pseudo");
        JLabel Mmr = new JLabel("MMR");
        JLabel Main = new JLabel("Rank");
        
        Dimension dimjt = new Dimension(200,40);
        
        Pseudo.setPreferredSize(dimjt);
        Mmr.setPreferredSize(dimjt);
        Main.setPreferredSize(dimjt);
        
        label.add(Pseudo);
        label.add(Mmr);
        label.add(Main);
        //label.setLayout(new FlowLayout());
        
        JPanel tf = new JPanel();
        JTextField Pseudotf = new JTextField();
        JTextField Mmrtf = new JTextField();
        JTextField rang = new JTextField();
        
        
        Pseudotf.setPreferredSize(dimjt);
        Mmrtf.setPreferredSize(dimjt);
        rang.setPreferredSize(dimjt);
        
        tf.add(Pseudotf);
        tf.add(Mmrtf);
        tf.add(rang);
        //tf.setLayout(new FlowLayout());
        
        JPanel bt = new JPanel();
        JButton ajouterJoueur = new JButton("Ajouter joueur");
        bt.add(ajouterJoueur);
        
        JPanel condense = new JPanel();
        condense.add(label);
        condense.add(tf);
        condense.setLayout(new GridLayout(3, 2));
        
        JPanel panel = new JPanel();
        panel.add(condense);
        panel.add(ajouterJoueur);
        ajouterJoueur.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try{
                        try(
                        CallableStatement cst = con.prepareCall("{call ajouterJoueur(?,?,?) }");
                        ){
                            String pseu = null,rangg = null;
                            int mmrr = 0;
                            boolean reussie = true;
                            if(Pseudotf != null)
                            {
                                pseu = new String(Pseudotf.getText().toUpperCase());
                               
                                //cst.setString("Pseudo",Pseudotf.getText());
                                if(Mmrtf != null)
                                {
                                    mmrr = Integer.parseInt(Mmrtf.getText());
                                    
                                    //cst.setInt("MMR",mmrtf.;)
                                    if(rang != null)
                                        rangg = new String(rang.getText().toUpperCase());
                                    else
                                        reussie = false;
                                }
                                else
                                    reussie = false;
                            }
                            else 
                                reussie = false;
                            boolean exec = false;
                            
                            if(reussie)
                            {
                                cst.setString(1,pseu);
                                cst.setString(2, rangg);
                                cst.setInt(3,mmrr);
                                
                                exec = cst.execute();
                                if(!exec)
                                {
                                    JOptionPane.showMessageDialog(null, "Insertion réussie !");
                                    rafraichirSelect(selectJoueurLancer,new ModeleDonnee(requete(con,"select pseudo from joueur")),"Joueur");
                                    rafraichirSelect(selectJoueurStat,new ModeleDonnee(requete(con,"select pseudo from joueur")),"Selectionner Joueur");


                                }
                                else
                                    JOptionPane.showMessageDialog(null, "L'Insertion n'as pas réussie ");
                                
                            }
                            /*JTable table = new JTable(requete(con,pstmtCHAMP));
                            scrollMenuPrinc.setViewportView(table);
                            scrollMenuPrinc.revalidate();
                            scrollMenuPrinc.repaint();*/
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "A problem has occured");
                    }
                };
        });
        return panel;
    }
    
    
    

    
    
    
   public JPanel lancerPartie(){
        JButton jButton1;
        JButton jButton2;
        JComboBox<String> jComboBox2;
        JComboBox<String> jComboBox3;
        
        Dimension dim = new Dimension(200,40);
        
        JTextArea JText;
        
        
        // End of variables declaration
       
        selectJoueurLancer = new JComboBox<>();
        rafraichirSelect(selectJoueurLancer,new ModeleDonnee(requete(con,"select pseudo from joueur")),"Joueur");
        
        
        
        
        jComboBox2 = new JComboBox<>();
        jComboBox2.addItem("Champion");
        
        jComboBox3 = new JComboBox<>();
        jComboBox3.addItem("Role");
        ModeleDonnee mod3 = new ModeleDonnee(requete(con,"select distinct role from champion"));
        
        
        
        for(int i = 0 ; i < mod3.getRowCount(); i++)
        {
            jComboBox3.addItem(mod3.getValueAt(i, 0).toString());
        }
        selectJoueurLancer.setPreferredSize(dim);
        jComboBox2.setPreferredSize(dim);
        jComboBox3.setPreferredSize(dim);
        jButton1 = new JButton();
        JText = new JTextArea("Il faut 10 joueurs pour une Partie");
        jButton2 = new JButton();
        
 
        setPreferredSize(new Dimension(1000, 700));
 
        
 
        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Ajouter Joueur");
 
        
        
 
        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jButton2.setForeground(new java.awt.Color(189, 179, 203));
        jButton2.setText("Lancer Partie !");
 
        
        
        
        
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(scrollLancerPartie, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(JText, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(selectJoueurLancer, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(340, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollLancerPartie, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(JText, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectJoueurLancer, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
    
        
        panel.add(selectJoueurLancer);
        panel.add(jComboBox2);
        panel.add(jComboBox3);
        panel.add(jButton1);
        panel.add(jButton1);
        panel.add(JText);
        panel.add(scrollLancerPartie);
        
        
        jComboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String selec = jComboBox3.getSelectedItem().toString();
                
                ModeleDonnee mod2 = new ModeleDonnee(requete(con,"select nom from champion where role ='"+selec+"'"));
                
        
                jComboBox2.removeAllItems();
                for(int i = 0 ; i < mod2.getRowCount(); i++)
                {
                    jComboBox2.addItem(mod2.getValueAt(i, 0).toString());
                }
            }
        });
        Object[][]data = new Object[0][0];
        String[]nom = {"Joueur","Role","Champion"};
        DefaultTableModel modJoueur = new DefaultTableModel(data,nom);
        
        
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object joueur,role,champion;
                int indJoueur = selectJoueurLancer.getSelectedIndex(),
                        indRole = jComboBox3.getSelectedIndex(),
                        indChamp = jComboBox2.getSelectedIndex();
                if(nbJoueurAdd < 10)
                {
                    if(indJoueur != 0 && indRole != 0  )
                    {
                        joueur = selectJoueurLancer.getSelectedItem().toString();
                        role = jComboBox3.getSelectedItem().toString();
                        champion = jComboBox2.getSelectedItem().toString();

                        Object[]joueurs = {joueur,role,champion};

                        modJoueur.addRow(joueurs);
                        JTable jt = new JTable(modJoueur);
                        scrollLancerPartie.setViewportView(jt);
                        nbJoueurAdd++;
                        selectJoueurLancer.remove(selectJoueurLancer.getSelectedIndex());
                        
                    }
                    else
                        JOptionPane.showMessageDialog(null, "                   ERREUR !!!\n"
                                + "Un des champs de la séléction est vide");
                }
                else
                    JOptionPane.showMessageDialog(null, "Il y à déjà 10 joueurs");
                       
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nbJoueurAdd<10)
                    JOptionPane.showMessageDialog(null, "Il n'y a pas assez de joueurs pour lancer une partie\nIl en faut 10");
                
            }
        });
        return panel;
    }
 
 

    
    public JPanel stat()
    {
        JPanel label = new JPanel();
        JLabel Main = new JLabel("Main :                   ");
        JLabel mainValue = new JLabel("null");
        JLabel nombre = new JLabel("Nombre de partie(s) jouée(s) :");
        JLabel nombrevalue = new JLabel("null");
        JLabel pourcent = new JLabel("Nombre de victoire :");
        JLabel pourcentvalue = new JLabel("null");
        selectJoueurStat = new JComboBox();
        rafraichirSelect(selectJoueurStat,new ModeleDonnee(requete(con,"select pseudo from joueur")),"Selectionner Joueur");

        selectJoueurStat.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String joueurselec;
                

                try{
                    joueurselec = selectJoueurStat.getSelectedItem().toString();
                    try(
                            
                            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select count(*) from joueur, partie where joueur.idjoueur = partie.idjoueur and pseudo ='"+joueurselec+"'");
                            Statement stmt2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs2 = stmt2.executeQuery("select role from \n" +
                            "(select pseudo, role from joueur join partie using(idjoueur) join champion using(idchamp)\n" +
                            "where pseudo='"+joueurselec+"') " +
                            "group by role having count(*)= " +
                            "(select max(count(*)) from (select pseudo,role from joueur join partie using(idjoueur) join champion using(idchamp)" +
                            "where pseudo='"+joueurselec+"') group by role)");
                            
                            Statement stmt3 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs3 = stmt3.executeQuery("select count(*) from joueur,partie where joueur.idjoueur=partie.idjoueur and gagnant=1 and pseudo='"+joueurselec+"'");
                            ){
                        
                            
                            
                            try{
                                rs.next();
                                rs2.next();
                                rs3.next();
                                nombrevalue.setText(rs.getBigDecimal(1).toString());

                                mainValue.setText(rs2.getString(1).toString());

                                pourcentvalue.setText(rs3.getBigDecimal(1).toString());
                                
                            }catch(Exception exc)
                            {
                                
                                JOptionPane.showMessageDialog(null, "Ce joueur n'a pas fait de partie");
                            }
                            
                            
                       
                    }
                }catch(Exception exe)
                {
                    exe.printStackTrace();
                }
                
                //pourcent.setText(new ModeleDonnee(requete(con,"select ")));
            }
            
        });
        
        label.add(Main);
        label.add(mainValue);        
        label.add(nombre);
        label.add(nombrevalue);
        label.add(pourcent); 
        label.add(pourcentvalue); 
        label.setLayout(new GridLayout(3, 2));   

        JPanel combo = new JPanel();
        
        
        
        combo.add(selectJoueurStat);
        
        
        
        
        
        JPanel panel = new JPanel();
        setBackground(Color.orange);
        panel.add(combo);
        panel.add(label);
        panel.setLayout(new GridLayout(6,1)); 
        setVisible(true);
        return panel;
    }
    
    public ModeleDonnee requete(Connection con,PreparedStatement requete) // retourne une instance de ModeleDonnee
    {
        
        String []nom = null ;
        Object [][]colonnes = new Object[0][0];
        ModeleDonnee mod;
        ResultSetMetaData rsMeta ;
        
        
        try
        {
            try(
                //Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = requete.executeQuery();
                
            ){
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
           
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        mod = new ModeleDonnee(nom, colonnes);
        if(mod == null)
            return null;
        else
             return mod;
    }
    
    public ModeleDonnee requete(Connection con,String requete) // retourne une instance de ModeleDonnee
    {
        
        String []nom = null ;
        Object [][]colonnes = new Object[0][0];
        ModeleDonnee mod;
        ResultSetMetaData rsMeta ;
        
        
        try
        {
            try(
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(requete);
                
            ){
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
           
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        mod = new ModeleDonnee(nom, colonnes);
        if(mod == null)
            return null;
        else
             return mod;
    }
    
    public void rafraichirSelect(JComboBox combo,ModeleDonnee mod,String premierElem)
    {
        combo.removeAllItems();
        combo.addItem(premierElem);
        for(int i = 0 ; i < mod.getRowCount(); i++)
        {
            combo.addItem(mod.getValueAt(i,0).toString());
        }
    }
    
    public static void main (String[]arg)
    {
        new Fenetre();
    }
}
    
    
    
    

