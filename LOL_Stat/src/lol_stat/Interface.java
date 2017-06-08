/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lol_stat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author p1604707
 */
public class Interface  extends JFrame {


    public Interface() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setSize(700,400);
        JMenu principal = new JMenu("Menu principal");
        menuBar.add(principal);
        JMenu joueur = new JMenu("Ajouter joueur");
        menuBar.add(joueur);
        JMenu lancer = new JMenu("Lancer partie");
        menuBar.add(lancer); 
        JMenu statistiques = new JMenu("Statistiques");
        menuBar.add(statistiques);        
        JMenuItem item = new JMenuItem("Besoin d'aide ?");
        principal.add(item);
        setJMenuBar(menuBar);
        setVisible(true); 

    }
}

